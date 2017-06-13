package com.test.google.googleplacesapplication.nearPlace.intractor;

import com.test.google.googleplacesapplication.common.io.APICallback;
import com.test.google.googleplacesapplication.common.util.APIServiceUtil;
import com.test.google.googleplacesapplication.nearPlace.manager.NearPlaceManager;
import com.test.google.googleplacesapplication.nearPlace.model.PlaceResponse;

import retrofit2.Call;

/**
 * Created by manoj on 12/6/17.
 */

public class NearPlaceIntractorImpl implements NearPlaceIntractor {

    public interface OnNearPlaceDetailFetchListener {

        void onNearPlaceDetailFetchSuccess(PlaceResponse response);

        void onNearPlaceDetailFetchFailed();

        void onError(String error);
    }

    @Override
    public void getNearPlacesList(double lat, double lang, String key, final OnNearPlaceDetailFetchListener listener) {
        String location = String.valueOf(lat) + "," + String.valueOf(lang);
        Call<PlaceResponse> callObj = APIServiceUtil.getInstance().getApiInterface().getNearbyPlaces("https://maps.googleapis.com/maps/api/place/nearbysearch/json?sensor=true", key, location, "1000");

        callObj.enqueue(new APICallback<PlaceResponse>() {
            @Override
            public void onResponseSuccess(PlaceResponse response) {
                if (listener == null)
                    return;
                if (response == null) {
                    listener.onNearPlaceDetailFetchFailed();
                    return;
                }
                NearPlaceManager.getInstance().setNearLocationData(response);
                listener.onNearPlaceDetailFetchSuccess(response);
            }

            @Override
            public void onResponseFailure(String errorMessage) {
                if (listener == null)
                    return;
                listener.onError(errorMessage);
            }
        });
    }
}
