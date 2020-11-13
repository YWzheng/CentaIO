package com.example.demo;

import android.app.Application;

import com.example.centaio.CentaIO;
import com.facebook.stetho.Stetho;

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
        CentaIO.init(this,"0b0910cc-1f61-4ea8-8f21-ddc71829fe73",true);

        /*其他内容初始化*/
        mInstance = this;

        Stetho.initializeWithDefaults(this);


    }
}
