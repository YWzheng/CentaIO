package com.example.centaio.net;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface Api {


    @FormUrlEncoded
    @POST("/user/getToken.json")
    Observable<ResponseData> send();
}
