package com.example.centaio.util;

import android.util.Log;

import com.example.centaio.CentaIO;
import com.example.centaio.db.Devices;

import java.util.List;

public class ReportUtil {

    private static final String TAG = "Report";

    public static void getReport() {

        List<Devices> devices = CentaIO.database.getDevicesDao().getDevices();
        Log.d(TAG, "getReport: " + devices.get(0).toString());
    }
}
