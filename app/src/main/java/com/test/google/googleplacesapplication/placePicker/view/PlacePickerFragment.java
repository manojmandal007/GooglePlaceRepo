package com.test.google.googleplacesapplication.placePicker.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.test.google.googleplacesapplication.R;
import com.test.google.googleplacesapplication.common.ui.BaseFragment;

import static android.app.Activity.RESULT_OK;
import static com.test.google.googleplacesapplication.common.util.Constants.IntentKeys.PLACE_PICKER_REQUEST;

/**
 * Created by manoj on 11/6/17.
 */

public class PlacePickerFragment extends BaseFragment {
    private OnLocationSelectedListener mListener;

    public interface OnLocationSelectedListener {
        void onLocationSelected(double latitude, double longitude);
    }

    @Override
    public void onAttach(Context ctx) {
        Activity activity = (Activity) ctx;
        try {
            mListener = (OnLocationSelectedListener) activity;
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new ClassCastException(activity.getLocalClassName()
                    + " must implement OnLocationSelectedListener");
        }
        super.onAttach(ctx);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setLayout(R.layout.fragment_place_picker);
        return super.onCreateView(inflater, container, savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        launchPlacePickerWidget();
    }

    private void launchPlacePickerWidget() {

        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(getActivity()), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(getActivity(), data);
                mListener.onLocationSelected(place.getLatLng().latitude, place.getLatLng().longitude);
            }
        }
    }

}
