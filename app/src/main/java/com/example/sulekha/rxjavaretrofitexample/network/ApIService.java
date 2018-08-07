package com.example.sulekha.rxjavaretrofitexample.network;

import com.example.sulekha.rxjavaretrofitexample.network.model.StarwarsCharacter;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApIService {
    @GET("people/{id}")
    Single<StarwarsCharacter> getCharacter(@Path("id") int id);
}
