package com.example.centaio.net;

import com.example.centaio.db.model.AppLog;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {


    @GET("/api/monitor/v1.0/system/functions/getBurPoints")
    Observable<ResponseData> get(@Query("projectKeyId") String pkeyId);

    @POST("/api/monitor/v1.1/upload-log/app")
    Observable<ResponseData> send(@Body AppLog log);


}
