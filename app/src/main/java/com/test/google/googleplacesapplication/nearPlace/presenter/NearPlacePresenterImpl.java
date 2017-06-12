package com.test.google.googleplacesapplication.nearPlace.presenter;

import android.content.Context;

import com.test.google.googleplacesapplication.R;
import com.test.google.googleplacesapplication.common.util.DialogUtil;
import com.test.google.googleplacesapplication.common.util.NetworkUtil;
import com.test.google.googleplacesapplication.nearPlace.intractor.NearPlaceIntractor;
import com.test.google.googleplacesapplication.nearPlace.intractor.NearPlaceIntractorImpl;
import com.test.google.googleplacesapplication.nearPlace.model.PlaceResponse;
import com.test.google.googleplacesapplication.nearPlace.view.ui.NearPlaceView;

/**
 * Created by manoj on 12/6/17.
 */

public class NearPlacePresenterImpl implements NearPlacePresenter, NearPlaceIntractorImpl.OnNearPlaceDetailFetchListener {

    private NearPlaceView mView;
    private NearPlaceIntractor mInteractor;

    @Override
    public void getNearLocationDetail(Context ctx, double lat, double lang) {
        if (!NetworkUtil.isAvailable(ctx)) {
            mView.onNetworkError();
            DialogUtil.showNoNetworkAlert(ctx);
            return;
        }
        mInteractor.getNearPlacesList(lat, lang, ctx.getString(R.string.api_key), this);
    }

    @Override
    public void setView(NearPlaceView view) {
        mView = view;
        mInteractor = new NearPlaceIntractorImpl();
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public void onNearPlaceDetailFetchSuccess(PlaceResponse response) {
        if (mView == null)
            return;
        mView.onNearPlaceFetchSuccess(response);
    }

    @Override
    public void onNearPlaceDetailFetchFailed() {
        if (mView == null)
            return;
        mView.onNearPlaceFetchFailed();
    }

    @Override
    public void onError(String error) {
        if (mView == null)
            return;
        mView.onError(error);
    }
}
