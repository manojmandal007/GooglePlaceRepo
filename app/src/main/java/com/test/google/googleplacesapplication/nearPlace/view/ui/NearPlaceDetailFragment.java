package com.test.google.googleplacesapplication.nearPlace.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.google.googleplacesapplication.R;
import com.test.google.googleplacesapplication.common.ui.BaseFragment;

import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_POS_KEY;

/**
 * Created by manoj on 13/6/17.
 */

public class NearPlaceDetailFragment extends BaseFragment {
    private int mPlacePosition;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setLayout(R.layout.fragment_near_place_detail);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        extractArguments();
    }

    private void extractArguments() {
        mPlacePosition = getArguments().getInt(PLACE_POS_KEY);
    }
}
