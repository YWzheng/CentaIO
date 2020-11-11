package com.example.centaio.net;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface Api {


    @GET("/api/jokes/list")
    Observable<ResponseData> send();

}
