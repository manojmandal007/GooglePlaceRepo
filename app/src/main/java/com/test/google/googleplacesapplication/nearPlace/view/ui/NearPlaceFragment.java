package com.test.google.googleplacesapplication.nearPlace.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.test.google.googleplacesapplication.R;
import com.test.google.googleplacesapplication.common.ui.BaseFragment;
import com.test.google.googleplacesapplication.nearPlace.model.PlaceResponse;
import com.test.google.googleplacesapplication.nearPlace.presenter.NearPlacePresenter;
import com.test.google.googleplacesapplication.nearPlace.presenter.NearPlacePresenterImpl;
import com.test.google.googleplacesapplication.nearPlace.view.adapter.NearPlaceAdapter;

import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.LATITUDE_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.LONGITUDE_KEY;

/**
 * Created by manoj on 12/6/17.
 */

public class NearPlaceFragment extends BaseFragment implements NearPlaceView {
    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private double mLatitude, mLongitude;
    private NearPlacePresenter mPresenter = new NearPlacePresenterImpl();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setLayout(R.layout.fragment_near_place);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        mViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) view.findViewById(R.id.sliding_tabs);
    }

    private void extractArguments() {
        mLatitude = getArguments().getDouble(LATITUDE_KEY);
        mLongitude = getArguments().getDouble(LONGITUDE_KEY);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        extractArguments();
        mActionBarIconListener.setActionBarTitle(getString(R.string.near_places));
        mActionBarIconListener.setbackButtonVisibility(true);
        showProgressBar();
        mPresenter.getNearLocationDetail(getActivity(), mLatitude, mLongitude);

    }

    private void setViewPager() {
        mViewPager.setAdapter(new NearPlaceAdapter(getChildFragmentManager(), getActivity()));
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setSmoothScrollingEnabled(true);
        mViewPager.setCurrentItem(0);
    }

    @Override
    public void onError(String error) {
        hideProgressBar();
        Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNetworkError() {
        hideProgressBar();
    }

    @Override
    public void onNearPlaceFetchSuccess(PlaceResponse response) {
        hideProgressBar();
        setViewPager();
    }

    @Override
    public void onNearPlaceFetchFailed() {
        hideProgressBar();
    }
}
