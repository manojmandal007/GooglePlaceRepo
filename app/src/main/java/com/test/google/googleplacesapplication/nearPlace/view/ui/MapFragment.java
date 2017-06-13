package com.test.google.googleplacesapplication.nearPlace.view.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.test.google.googleplacesapplication.nearPlace.manager.NearPlaceManager;
import com.test.google.googleplacesapplication.nearPlace.model.PlaceResponse;
import com.test.google.googleplacesapplication.nearPlace.model.Result;

import java.util.List;

/**
 * Created by manoj on 12/6/17.
 */

public class MapFragment extends BaseFragment implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setLayout(R.layout.fragment_map);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    private void initView(View view) {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    private void parseLocationResult() {
        double latitude, longitude;
        PlaceResponse response = NearPlaceManager.getInstance().getNearLocationData();
        if (response != null) {
            List<Result> placeList = response.results;

            for (Result place : placeList) {
                latitude = place.geometry.location.lat;
                longitude = place.geometry.location.lng;
                MarkerOptions markerOptions = new MarkerOptions();
                LatLng latLng = new LatLng(latitude, longitude);
                markerOptions.position(latLng);
                markerOptions.title(place.name + " : " + place.vicinity);

                mMap.addMarker(markerOptions);
                mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
            }
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        parseLocationResult();

    }
}
