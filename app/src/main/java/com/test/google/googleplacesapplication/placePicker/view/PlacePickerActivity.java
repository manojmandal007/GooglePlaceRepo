package com.test.google.googleplacesapplication.placePicker.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.test.google.googleplacesapplication.R;
import com.test.google.googleplacesapplication.common.ui.BaseActivity;
import com.test.google.googleplacesapplication.common.util.FragmentUtil;
import com.test.google.googleplacesapplication.nearPlace.view.ui.NearPlaceDetailFragment;
import com.test.google.googleplacesapplication.nearPlace.view.ui.NearPlaceFragment;
import com.test.google.googleplacesapplication.nearPlace.view.ui.PlaceFragment;
import com.test.google.googleplacesapplication.nearPlace.view.ui.PlaceMapFragment;
import com.test.google.googleplacesapplication.nearPlace.view.ui.WebsiteFragment;

import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.LATITUDE_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.LONGITUDE_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_ADDRESS_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_NAME_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_PHONE_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_POS_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_RATING_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_WEBSITE_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_WEBSITE_URL_KEY;

public class PlacePickerActivity extends BaseActivity implements PlacePickerFragment.OnLocationSelectedListener, PlaceFragment.OnNearPlaceClickListener, NearPlaceDetailFragment.OnWebsiteButtonClickListener,
        NearPlaceDetailFragment.OnMenuButtonClickListener {

    private GoogleApiClient mClient;

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
        Toolbar toolBar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolBar);
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
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
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

    @Override
    public void onNearPlaceClicked(final int pos, String placeId) {
        Places.GeoDataApi.getPlaceById(mClient, placeId)
                .setResultCallback(new ResultCallback<PlaceBuffer>() {
                    @Override
                    public void onResult(@NonNull PlaceBuffer places) {
                        if (places.getStatus().isSuccess() && places.getCount() > 0) {
                            launchPlaceDetailScreen(pos, places.get(0));
                        }
                        places.release();
                    }
                });
    }

    private void launchPlaceDetailScreen(int pos, Place place) {
        Fragment fragment = new NearPlaceDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(PLACE_POS_KEY, pos);
        bundle.putString(PLACE_NAME_KEY, place.getName().toString());
        bundle.putFloat(PLACE_RATING_KEY, place.getRating());
        bundle.putString(PLACE_ADDRESS_KEY, place.getAddress().toString());
        bundle.putString(PLACE_PHONE_KEY, place.getPhoneNumber().toString());
        if (place.getWebsiteUri() != null)
            bundle.putString(PLACE_WEBSITE_KEY, place.getWebsiteUri().toString());
        fragment.setArguments(bundle);
        FragmentUtil.replaceAndAddFragment(this, fragment, R.id.main_container);
    }

    @Override
    public void onWebsiteButtonClicked(String url) {
        Fragment fragment = new WebsiteFragment();
        Bundle bundle = new Bundle();
        bundle.putString(PLACE_WEBSITE_URL_KEY, url);
        fragment.setArguments(bundle);
        FragmentUtil.replaceAndAddFragment(this, fragment, R.id.main_container);
    }

    @Override
    public void onMenuButtonClicked(double lat, double lang) {
        Fragment fragment = new PlaceMapFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(LATITUDE_KEY, lat);
        bundle.putDouble(LONGITUDE_KEY, lang);
        fragment.setArguments(bundle);
        FragmentUtil.replaceAndAddFragment(this, fragment, R.id.main_container);
    }
}
