package com.test.google.googleplacesapplication.nearPlace.intractor;

/**
 * Created by manoj on 12/6/17.
 */

public interface NearPlaceIntractor {

    void getNearPlacesList(double lat, double lang, String key, NearPlaceIntractorImpl.OnNearPlaceDetailFetchListener listener);
}
