package com.example.centaio.net;

import android.annotation.SuppressLint;
import android.util.Log;

import com.example.centaio.CentaIO;
import com.example.centaio.util.ReportUtil;
import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetWorkUtils {

    private Retrofit retrofit;

    private final String BASE_URL = "http://10.7.11.22:9090";

    public NetWorkUtils() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(
                            new OkHttpClient
                                    .Builder()
                                    .addInterceptor(new HttpLoggingInterceptor(message -> Log.i("okHttp", message)).setLevel(HttpLoggingInterceptor.Level.BODY))
                                    .connectTimeout(30, TimeUnit.SECONDS)
                                    .readTimeout(30, TimeUnit.SECONDS)
                                    .build())
                    .build();
        }
    }

    @SuppressLint("CheckResult")
    public void send() {
        retrofit.create(Api.class)
                .send(ReportUtil.getReport())
                .subscribeOn(Schedulers.io()) // 在子线程中进行Http访问
                .observeOn(AndroidSchedulers.mainThread()) // UI线程处理返回接口
                .subscribe(Data ->
                        {

                            Log.d("CentaIO", "send: " + new Gson().toJson(Data));
                            if (Data.getCode() == 200) {
                                CentaIO.database.getEventDao().deleteAll();
                                CentaIO.database.getPageDao().deleteAll();
                            }
                        }

                );

    }


}
