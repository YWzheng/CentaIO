package com.example.centaio;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.util.Log;

import com.example.centaio.db.Note;
import com.example.centaio.db.NoteDatabase;
import com.example.centaio.net.NetWorkUtils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

import static com.example.centaio.CentaIO.getInstance;
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
        NoteDatabase instance = NoteDatabase.getInstance(activity);
        instance.getNoteDao().insertNote(new Note("content", "Title"));


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


    }

    /*
     *点击按钮时上传的数据
     */
    public static void onClickButton(String idName, String viewName, String viewType) {
        if (showLog)
            Log.d("CentaIO", viewName + " 按钮被点击 id = " + idName + "，viewType = " + viewType);
        //TODO   添加你的代码


    }

}
