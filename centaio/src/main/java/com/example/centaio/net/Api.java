package com.example.centaio.net;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {


    @GET("/api/jokes/list")
    Call<ResponseData> send();

}
