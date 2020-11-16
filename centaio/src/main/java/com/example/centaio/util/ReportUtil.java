package com.example.centaio.util;

import android.util.Log;

import com.example.centaio.CentaIO;
import com.example.centaio.db.model.AppLog;
import com.example.centaio.db.model.Event;
import com.example.centaio.db.model.Page;
import com.google.gson.Gson;

import java.util.List;

public class ReportUtil {

    private static final String TAG = "Report";

    public static AppLog getReport() {

        List<Page> pages = CentaIO.database.getPageDao().getPages();
        List<Event> events = CentaIO.database.getEventDao().getEvent();
        StringBuilder sb = new StringBuilder();
        for (Page page : pages) {
            sb.append(new Gson().toJson(page));
            sb.append("$$$");
        }
        for (Event event : events) {
            sb.append(new Gson().toJson(event));
            sb.append("$$$");
        }
        Log.d(TAG, new Gson().toJson(new AppLog(sb.toString())));
        return new AppLog(sb.toString());
    }

    public static boolean canReport() {

        List<Page> pages = CentaIO.database.getPageDao().getPages();
        List<Event> events = CentaIO.database.getEventDao().getEvent();

        return pages.size() > 0 || events.size() > 0;
    }
}
