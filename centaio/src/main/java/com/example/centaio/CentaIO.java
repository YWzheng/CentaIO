package com.example.centaio;//

import android.app.Activity;
import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.centaio.callback.LifecycleCallbacks;
import com.example.centaio.core.CentaIOService;
import com.example.centaio.core.TouchHandle;
import com.example.centaio.db.AppDataBase;
import com.example.centaio.db.model.Devices;
import com.example.centaio.util.ViewUtils;

import java.util.concurrent.atomic.AtomicBoolean;

import static com.example.centaio.Result.onAppStart;
import static com.example.centaio.Result.onClickButton;
import static com.example.centaio.Result.onPageStart;


public class CentaIO {

    public static boolean collectMode = true;
    public static boolean showLog = true;
    static AtomicBoolean start = new AtomicBoolean(false);
    private boolean isFragmentStart = false; //当前fragment是否为显示界面
    private final TouchHandle mTouchHandle;
    static String lastPageName = "";//上一页面名字
    static String currentPageName = "";//当前页面名字
    static String path = "";//页面路径

    public static Application application;
    public static AppDataBase database;
    public static String webMonitorId;
    public static String customerKey;
    public static String userId;
    public static String deptId;

    private CentaIO() {
        mTouchHandle = new TouchHandle();
        mTouchHandle.registerViewClickListener(this::viewClickListener);
    }

    public static void init(@NonNull Application application, String webMonitorId, String userId, String depId, boolean collect) {
        application.registerActivityLifecycleCallbacks(new LifecycleCallbacks());
        collectMode = collect;
        CentaIO.application = application;
        CentaIO.webMonitorId = webMonitorId;
        CentaIO.userId = userId;
        CentaIO.deptId = depId;
        database = AppDataBase.getInstance(application);
        application.startService(new Intent(application, CentaIOService.class));
        database.getDevicesDao().insertDevices(new Devices());
    }

    public static CentaIO getInstance() {
        return MonitorHolder.holder;
    }


    private static class MonitorHolder {
        private static final CentaIO holder = new CentaIO();

        private MonitorHolder() {
        }
    }

    private void onActivityPageStart(Activity activity) {
        if (!collectMode) return;  //非收集模式  退出
        try {
            ActivityManager manager = (ActivityManager) CentaIO.application.getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);
            currentPageName = activity.getClass().getSimpleName();
            path = info.topActivity.getClassName();
            onAppStart(activity);  //应用启动时上传
            onPageStart(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onActivityPageEnd() {
        lastPageName = currentPageName;
    }

    private void onFragmentPageStart(Fragment fragment) {
        //如果当前fragment已经开始 则退出   避免重复开始
        if (!fragment.getUserVisibleHint() || fragment.isHidden() || !fragment.isResumed() || isFragmentStart)
            return;
        String pageName = fragment.getClass().getSimpleName();
        if (currentPageName.equals(pageName)) return;//重复进入  退出
        lastPageName = currentPageName;
        currentPageName = pageName;
        isFragmentStart = true;
        try {
            currentPageName = fragment.getClass().getSimpleName();
            onPageStart(fragment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private void onFragmentPageEnd(Fragment fragment) {
        ///如果当前fragment已经结束 则退出   避免重复结束
        if (!isFragmentStart) return;  //非收集模式  退出
        isFragmentStart = false;
        lastPageName = currentPageName;
    }


    private void onActivityCreated(Activity activity) {
        if (!collectMode) return;  //非收集模式  退出
    }

    public void onActivityResumed(Activity activity) {
        if (!collectMode) return;  //非收集模式  退出
        onActivityPageStart(activity);
    }

    public void onActivityPaused(Activity activity) {
        if (!collectMode) return;  //非收集模式  退出
        onActivityPageEnd();
    }

    public void dispatchTouchEvent(Activity activity, MotionEvent ev) {
        if (!collectMode) return;  //非收集模式  退出
        /*获取当前点击位置，遍历布局，获取当前点击位置对应的view，根据view映射路径，与json文件中的对比*/
        if (mTouchHandle != null) {
            mTouchHandle.eventViewHit(activity, ev);
        }
    }

    public void onFragmentResume(Fragment fragment) {
        if (!collectMode) return;  //非收集模式  退出
        onFragmentPageStart(fragment);
    }

    public void onFragmentPaused(Fragment fragment) {
        if (!collectMode) return;  //非收集模式  退出
        onFragmentPageEnd(fragment);
    }

    public void onFragmentHiddenChanged(Fragment fragment, boolean hidden) {
        if (!collectMode) return;  //非收集模式  退出
        if (!fragment.isResumed()) return;   //未初始化  退出
        if (hidden) {
            onFragmentPageEnd(fragment);
        } else {
            onFragmentPageStart(fragment);
        }
    }

    public void setUserVisibleHint(Fragment fragment, boolean isVisibleToUser) {
        if (!collectMode) return;  //非收集模式  退出
        if (!fragment.isResumed()) return;   //未初始化  退出
        if (isVisibleToUser) {
            onFragmentPageStart(fragment);
        } else {
            onFragmentPageEnd(fragment);
        }
    }

    private void viewClickListener(MotionEvent event, View view) {

        //事件拦截入口
        Log.d("MotionEvent", "CentaIO: " + event.getAction());

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_UP:
                try {
                    String viewName = "";
                    String viewType = "";
                    String idName = ViewUtils.getSimpleResourceName(view.getContext(), view.getId());
                    viewName = ViewUtils.getButtonName(view);//获取文本值
                    //动态生成view  比如listView
                    if (view.hasOnClickListeners() && TextUtils.isEmpty(idName)) {
                        idName = viewName;
                    }
                    viewType = (view instanceof EditText) ? "text" : "button";
                    onClickButton(idName, viewName, viewType);

                } catch (Exception e) {
                    e.printStackTrace();
                }

                break;
        }

    }

    public void event(String id, String text, String type) {
        onClickButton(id, text, type);
    }

}
