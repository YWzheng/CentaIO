package com.example.centaio;

import android.app.Activity;
import android.util.Log;

import com.example.centaio.db.model.Devices;
import com.example.centaio.db.model.Event;
import com.example.centaio.db.model.Page;
import com.example.centaio.util.DeviceUtils;

import static com.example.centaio.CentaIO.currentPageName;
import static com.example.centaio.CentaIO.customerKey;
import static com.example.centaio.CentaIO.database;
import static com.example.centaio.CentaIO.lastPageName;
import static com.example.centaio.CentaIO.path;
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

        database.getDevicesDao().updateDevices(new Devices());
        customerKey= DeviceUtils.getUUID();
    }

    /*
     *打开页面上传的数据
     * object  是activity或者Fragment
     */
    public static void onPageStart(Object object) {

        if (showLog)
            Log.d("CentaIO", "打开了新页面: " + object.getClass().getSimpleName() + "，上一页面=" + lastPageName);
        database.getPageDao().insertPage(new Page(currentPageName));

    }

    /*
     *点击按钮时上传的数据
     */
    public static void onClickButton(String viewID, String viewText, String viewType) {
        if (showLog)
            Log.d("CentaIO", viewText + " 按钮被点击 id = " + viewID + "，viewType = " + viewType);
        database.getEventDao().insertEvent(new Event(currentPageName, viewText, currentPageName, path));

    }

}
