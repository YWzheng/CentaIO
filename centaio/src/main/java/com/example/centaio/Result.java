package com.example.centaio;

import android.app.Activity;
import android.util.Log;

import static com.example.centaio.CentaIO.sLastPageName;
import static com.example.centaio.CentaIO.sShowLog;
import static com.example.centaio.CentaIO.sStart;

public class Result {

    /*
     *打开app时上传的数据
     */
    public static void onAppStart(Activity activity) {
        if (!sStart.compareAndSet(false, true)) {
            return;
        }
        if (sShowLog) Log.d("CentaIO", "app被打开了 ");
        //TODO   添加你的代码

    }

    /*
     *打开页面上传的数据
     * object  是activity或者Fragment
     */
    public static void onPageStart(Object object) {
        if (sShowLog)
            Log.d("CentaIO", "打开了新页面: " + object.getClass().getSimpleName() + "，上一页面=" + sLastPageName);
        //TODO   添加你的代码

    }

    /*
     *点击按钮时上传的数据
     */
    public static void onClickButton(String idName, String viewName, String viewType) {
        if (sShowLog)
            Log.d("CentaIO", viewName + " 按钮被点击 id = " + idName + "，viewType = " + viewType);
        //TODO   添加你的代码


    }

}
