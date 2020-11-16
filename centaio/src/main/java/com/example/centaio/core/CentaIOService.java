package com.example.centaio.core;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.centaio.net.NetWorkUtils;
import com.example.centaio.util.ReportUtil;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class CentaIOService extends Service {

    public CentaIOService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    @SuppressLint("CheckResult")
    @Override
    public void onCreate() {
        super.onCreate();
        Observable
                .interval(59, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .takeUntil(Observable.timer(2, TimeUnit.MINUTES))
                .subscribe(this::sendReport);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void sendReport(Long num) {
        Log.d("CentaIO", "服务执行次数: " + num);
        if (ReportUtil.canReport()) {
            new NetWorkUtils().send();
            Log.d("CentaIO", "网络执行次数: " + num);
        }
    }
}