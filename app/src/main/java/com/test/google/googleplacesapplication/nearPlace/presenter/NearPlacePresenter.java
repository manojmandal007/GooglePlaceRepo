package com.test.google.googleplacesapplication.nearPlace.presenter;

import android.content.Context;

import com.test.google.googleplacesapplication.common.presenter.BasePresenter;
import com.test.google.googleplacesapplication.nearPlace.view.ui.NearPlaceView;

/**
 * Created by manoj on 12/6/17.
 */

public interface NearPlacePresenter extends BasePresenter {

    void getNearLocationDetail(Context ctx, double lat, double lang);

    void setView(NearPlaceView view);
}
