package com.example.centaio.net;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.HeaderMap;
import retrofit2.http.POST;

public interface Api {


    @POST("/user/getToken.json")
    Call<ResponseData> send();
}
