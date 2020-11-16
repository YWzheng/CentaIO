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
        String webMonitorId = "0b0910cc-1f61-4ea8-8f21-ddc71829fe73";
        String userId = "e292b2c6-7331-cf31-3cff-08d6e88f83ab";
        String deptId = "0e70fb71-c4b4-41b3-8c6f-441d576d8a78";
        CentaIO.init(this, webMonitorId, userId, deptId, true);

        /*其他内容初始化*/
        mInstance = this;

        Stetho.initializeWithDefaults(this);



    }
}
