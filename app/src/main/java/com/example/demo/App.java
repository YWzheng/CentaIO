package com.example.demo;

import android.app.Application;
import android.os.Build;

import com.example.centaio.CentaIO;

public class App extends Application {
    private static App mInstance;//Application单例

    /**
     * 获得Application单例对象
     */
    public static App getInstance() {
        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        //埋点统计回调监听
        CentaIO.init(this,true);

        /*其他内容初始化*/
        mInstance = this;
    }
}
