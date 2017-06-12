package com.test.google.googleplacesapplication.nearPlace.view.ui;

import com.test.google.googleplacesapplication.nearPlace.model.PlaceResponse;

/**
 * Created by manoj on 12/6/17.
 */

public interface NearPlaceView {

    void onError(String error);

    void onNetworkError();

    void onNearPlaceFetchSuccess(PlaceResponse response);

    void onNearPlaceFetchFailed();
}
