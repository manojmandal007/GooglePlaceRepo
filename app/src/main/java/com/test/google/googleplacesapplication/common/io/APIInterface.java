package com.test.google.googleplacesapplication.common.io;

import com.test.google.googleplacesapplication.nearPlace.model.PlaceResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIInterface {

    @GET
    Call<PlaceResponse> getNearbyPlaces(@Url String url, @Query("key") String key, @Query("location") String location, @Query("radius") String radius);

}
