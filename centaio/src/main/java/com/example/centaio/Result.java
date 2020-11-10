package com.example.centaio;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.example.centaio.db.Devices;
import com.example.centaio.db.Event;
import com.example.centaio.db.Page;

import static com.example.centaio.CentaIO.currentPageName;
import static com.example.centaio.CentaIO.database;
import static com.example.centaio.CentaIO.lastPageName;
import static com.example.centaio.CentaIO.showLog;
import static com.example.centaio.CentaIO.start;

public class Result {



    /*
     *打开app时上传的数据
     */
    public static void onAppStart(Activity activity) {
        if (!start.compareAndSet(false, true)) {
            return;
        }
        if (showLog) Log.d("CentaIO", "app被打开了 ");
        //TODO   添加你的代码
        database.getDevicesDao().insertDevices(new Devices("OPPO R9m"));
    }

    /*
     *打开页面上传的数据
     * object  是activity或者Fragment
     */
    public static void onPageStart(Object object) {
        ActivityManager manager = (ActivityManager) CentaIO.application.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.RunningTaskInfo info = manager.getRunningTasks(1).get(0);
        String shortClassName = info.topActivity.getShortClassName();    //类名
        String className = info.topActivity.getClassName();              //完整类名
        String packageName = info.topActivity.getPackageName();
        if (showLog)
            Log.d("CentaIO", "打开了新页面: " + object.getClass().getSimpleName() + "，上一页面=" + lastPageName);
        //TODO   添加你的代码

        database.getPageDao().insertPage(new Page(currentPageName,lastPageName));

    }

    /*
     *点击按钮时上传的数据
     */
    public static void onClickButton(String idName, String viewName, String viewType) {
        if (showLog)
            Log.d("CentaIO", viewName + " 按钮被点击 id = " + idName + "，viewType = " + viewType);
        //TODO   添加你的代码

        database.getEventDao().insertEvent(new Event(currentPageName, idName, viewType));

    }

}
