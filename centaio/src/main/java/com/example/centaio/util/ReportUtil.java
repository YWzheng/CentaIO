package com.example.centaio.util;

import android.util.Log;

import com.example.centaio.CentaIO;
import com.example.centaio.db.Devices;
import com.example.centaio.db.Event;
import com.example.centaio.db.Page;
import com.example.centaio.db.Report;
import com.google.gson.Gson;

import java.util.List;

public class ReportUtil {

    private static final String TAG = "Report";

    public static void getReport() {

        List<Devices> devices = CentaIO.database.getDevicesDao().getDevices();
        List<Page> pages = CentaIO.database.getPageDao().getPages();
        List<Event> event = CentaIO.database.getEventDao().getEvent();

        Report report = new Report();
        report.setDevices(devices.get(0));
        report.setPage(pages);
        report.setEvent(event);
        String s = new Gson().toJson(report);

        Log.d(TAG, "getReport: " + s);
    }
}
