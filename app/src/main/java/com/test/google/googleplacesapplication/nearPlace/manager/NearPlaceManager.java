package com.test.google.googleplacesapplication.nearPlace.manager;

import com.test.google.googleplacesapplication.nearPlace.model.PlaceResponse;

/**
 * Created by manoj on 12/6/17.
 */

public class NearPlaceManager {

    private static NearPlaceManager sInstance;
    private PlaceResponse mResponse;

    private NearPlaceManager() {
    }

    public static synchronized NearPlaceManager getInstance() {
        if (sInstance == null) {
            sInstance = new NearPlaceManager();
        }
        return sInstance;
    }

    public void setNearLocationData(PlaceResponse response) {
        mResponse = response;
    }

    public PlaceResponse getNearLocationData() {
        return mResponse;
    }

}
