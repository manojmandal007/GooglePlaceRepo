package com.test.google.googleplacesapplication.nearPlace.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.test.google.googleplacesapplication.R;
import com.test.google.googleplacesapplication.common.ui.BaseFragment;

import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.LATITUDE_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.LONGITUDE_KEY;

/**
 * Created by manoj on 14/6/17.
 */

public class PlaceMapFragment extends BaseFragment implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double mLatitude, mLongitude;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setLayout(R.layout.fragment_map);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        extractArguments();
        mActionBarIconListener.setActionBarTitle(getString(R.string.place_map));
        mActionBarIconListener.setbackButtonVisibility(true);
        setHasOptionsMenu(false);
        getActivity().invalidateOptionsMenu();
        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map_container);
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_container, supportMapFragment).commit();
        }
        supportMapFragment.getMapAsync(this);
    }

    private void extractArguments() {
        mLatitude = getArguments().getDouble(LATITUDE_KEY);
        mLongitude = getArguments().getDouble(LONGITUDE_KEY);
    }

    private void parseLocationResult() {
        MarkerOptions markerOptions = new MarkerOptions();
        LatLng latLng = new LatLng(mLatitude, mLongitude);
        markerOptions.position(latLng);
        mMap.addMarker(markerOptions);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        parseLocationResult();

    }
}
