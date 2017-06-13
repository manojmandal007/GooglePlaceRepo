package com.test.google.googleplacesapplication.placePicker.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.places.Places;
import com.test.google.googleplacesapplication.R;
import com.test.google.googleplacesapplication.common.ui.BaseActivity;
import com.test.google.googleplacesapplication.common.util.FragmentUtil;
import com.test.google.googleplacesapplication.nearPlace.view.ui.NearPlaceFragment;

import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.LATITUDE_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.LONGITUDE_KEY;

public class PlacePickerActivity extends BaseActivity implements PlacePickerFragment.OnLocationSelectedListener {

    private GoogleApiClient mClient;
    private Toolbar mToolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_picker);
        initView();
        mClient = new GoogleApiClient
                .Builder(this).addApi(Places.GEO_DATA_API).addApi(Places.PLACE_DETECTION_API).build();
        FragmentUtil.replaceFragment(this, new PlacePickerFragment(), R.id.main_container);
    }

    private void initView() {
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolBar);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mClient.connect();
    }

    @Override
    protected void onStop() {
        mClient.disconnect();
        super.onStop();
    }

    @Override
    public void onBackPressed() {
        Fragment parentFragment = FragmentUtil.getCurrentFragment(this, R.id.main_container);
        if (parentFragment != null && parentFragment.getChildFragmentManager().getBackStackEntryCount() > 0) {
            parentFragment.getChildFragmentManager().popBackStack();
        } else
            super.onBackPressed();
    }

    @Override
    public void onLocationSelected(double latitude, double longitude) {
        Fragment fragment = new NearPlaceFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(LATITUDE_KEY, latitude);
        bundle.putDouble(LONGITUDE_KEY, longitude);
        fragment.setArguments(bundle);
        FragmentUtil.replaceAndAddFragment(this, fragment, R.id.main_container);
    }
}
