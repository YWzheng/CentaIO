package com.example.centaio.net;

import android.annotation.SuppressLint;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class NetWorkUtils {

    private Retrofit retrofit;

    public NetWorkUtils() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("http://asian.dotplays.com")
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
        Call<ResponseData> send = retrofit.create(Api.class).send();
        send.enqueue(new Callback<ResponseData>() {
            @Override
            public void onResponse(Call<ResponseData> call, Response<ResponseData> response) {
                ResponseData body = response.body();
                Log.d("TAG", "onResponse: "+body);
            }

            @Override
            public void onFailure(Call<ResponseData> call, Throwable t) {
                Log.d("TAG", "onFailure: "+t.getMessage());
            }
        });


    }


}
