package com.example.centaio;//

import android.app.Activity;
import android.app.Application;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.centaio.callback.LifecycleCallbacks;
import com.example.centaio.core.TouchHandle;
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

    private CentaIO() {
        mTouchHandle = new TouchHandle();
        mTouchHandle.registerViewClickListener(this::viewClickListener);
    }

    public static void init(@NonNull Application application, boolean collect) {
        application.registerActivityLifecycleCallbacks(new LifecycleCallbacks());
        collectMode = collect;
    }

    public static CentaIO getInstance() {
        return MonitorHolder.holder;
    }

    private void viewClickListener(MotionEvent event, View view) {
        try {
            if (event.getAction() == MotionEvent.ACTION_UP) {
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static class MonitorHolder {
        private static final CentaIO holder = new CentaIO();

        private MonitorHolder() {
        }
    }

    private void onActivityPageStart(Activity activity) {
        if (!collectMode) return;  //非收集模式  退出
        try {
            currentPageName = activity.getClass().getSimpleName();
            onAppStart(activity);  //应用启动时上传
            onPageStart(activity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void onActivityPageEnd() {
        lastPageName = currentPageName;
    }

    public void onFragmentPageStart(Fragment fragment) {
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


    public void onFragmentPageEnd(Fragment fragment) {
        ///如果当前fragment已经结束 则退出   避免重复结束
        if (!isFragmentStart) return;  //非收集模式  退出
        isFragmentStart = false;
        lastPageName = currentPageName;
    }


    public void onActivityCreated(Activity activity) {
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




    public static void event(String idName, String viewName, String viewType) {
        onClickButton(idName, viewName, viewType);
    }

}
