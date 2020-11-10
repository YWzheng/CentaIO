package com.example.centaio.net;

import retrofit2.Call;
import retrofit2.http.POST;

public interface Api {


    @POST("/user/getToken.json")
    Call<ResponseData> send();

}
