package com.test.google.googleplacesapplication.nearPlace.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.test.google.googleplacesapplication.R;
import com.test.google.googleplacesapplication.common.ui.BaseFragment;

/**
 * Created by manoj on 12/6/17.
 */

public class DummyFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setLayout(R.layout.fragment_dummy);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
