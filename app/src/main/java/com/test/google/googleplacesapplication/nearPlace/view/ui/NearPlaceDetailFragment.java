package com.test.google.googleplacesapplication.nearPlace.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_ADDRESS_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_NAME_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_PHONE_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_POS_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_RATING_KEY;
import static com.test.google.googleplacesapplication.common.util.Constants.BundleKeys.PLACE_WEBSITE_KEY;

/**
 * Created by manoj on 13/6/17.
 */

public class NearPlaceDetailFragment extends BaseFragment implements OnMapReadyCallback, View.OnClickListener {
    private int mPlacePosition;
    private String mPlaceName, mPlaceAddress, mPlaceWebsite, mPlacePhoneNumber;
    private Float mRating;
    private TextView mPlaceNameText, mPlaceAddressText, mPlacePhoneNumberText, mRatingText;
    private ImageView mPlaceImage;
    private GoogleMap mMap;
    private RatingBar mRatingBar;
    private OnWebsiteButtonClickListener mWebsiteButtonClickListener;
    private OnMenuButtonClickListener mOnMenuButtonClickListener;
    private double mLatitude, mLongitude;

    public interface OnWebsiteButtonClickListener {
        void onWebsiteButtonClicked(String url);
    }

    public interface OnMenuButtonClickListener {
        void onMenuButtonClicked(double lat, double lang);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mWebsiteButtonClickListener = (OnWebsiteButtonClickListener) context;
            mOnMenuButtonClickListener = (OnMenuButtonClickListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new ClassCastException("Activity must implement OnWebsiteButtonClickListener,OnMenuButtonClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setLayout(R.layout.fragment_near_place_detail);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.map_tab, menu);
    }

    private void initView(View view) {
        mPlaceNameText = (TextView) view.findViewById(R.id.place_tv);
        mPlaceAddressText = (TextView) view.findViewById(R.id.place_address_tv);
        mPlacePhoneNumberText = (TextView) view.findViewById(R.id.place_phone_tv);
        mRatingText = (TextView) view.findViewById(R.id.place_rating_tv);
        mPlaceImage = (ImageView) view.findViewById(R.id.place_iv);
        mRatingBar = (RatingBar) view.findViewById(R.id.rating_bar);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().invalidateOptionsMenu();
        mActionBarIconListener.setActionBarTitle(getString(R.string.place_detail));
        extractArguments();
        initListener();
        setPlaceView();
        FragmentManager fm = getChildFragmentManager();
        SupportMapFragment supportMapFragment = (SupportMapFragment) fm.findFragmentById(R.id.map_container);
        if (supportMapFragment == null) {
            supportMapFragment = SupportMapFragment.newInstance();
            fm.beginTransaction().replace(R.id.map_container, supportMapFragment).commit();
        }
        supportMapFragment.getMapAsync(this);
    }

    private void extractArguments() {
        mPlacePosition = getArguments().getInt(PLACE_POS_KEY);
        mPlaceName = getArguments().getString(PLACE_NAME_KEY);
        mRating = getArguments().getFloat(PLACE_RATING_KEY);
        mPlaceAddress = getArguments().getString(PLACE_ADDRESS_KEY);
        mPlacePhoneNumber = getArguments().getString(PLACE_PHONE_KEY);
        mPlaceWebsite = getArguments().getString(PLACE_WEBSITE_KEY);
    }

    private void initListener() {
        getView().findViewById(R.id.place_website_btn).setOnClickListener(this);
    }

    private void setPlaceView() {
        mPlaceNameText.setText(mPlaceName);
        mRatingBar.setRating(mRating);
        setAddress();
        setPhoneNumber();
        mRatingText.setText(String.valueOf(mRating));

        Result data = NearPlaceManager.getInstance().getNearLocationData().results.get(mPlacePosition);
        String imageUrl = "";
        if (data.photos != null && data.photos.size() >= 1) {
            imageUrl = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&key=AIzaSyDbivDexfwFTRjfBSaiDSH02OTPeok6dLQ&photoreference=" + data.photos.get(0).photoReference;
        }
        Glide.with(getActivity()).load(imageUrl).placeholder(R.drawable.common_google_signin_btn_icon_dark).fitCenter().into(mPlaceImage);
    }

    private void setAddress() {
        String addressText = String.format(getString(R.string.address), mPlaceAddress);
        SpannableStringBuilder spanAddressTxt = new SpannableStringBuilder(addressText);
        spanAddressTxt.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mPlaceAddressText.setText(spanAddressTxt);
    }

    private void setPhoneNumber() {
        if (TextUtils.isEmpty(mPlacePhoneNumber)) {
            mPlacePhoneNumberText.setVisibility(View.GONE);
        } else {
            String phoneText = String.format(getString(R.string.phone), mPlacePhoneNumber);
            SpannableStringBuilder spanPhoneTxt = new SpannableStringBuilder(phoneText);
            spanPhoneTxt.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            mPlacePhoneNumberText.setText(spanPhoneTxt);
        }
    }

    private void parseLocationResult() {

        PlaceResponse response = NearPlaceManager.getInstance().getNearLocationData();
        if (response != null) {
            Result place = NearPlaceManager.getInstance().getNearLocationData().results.get(mPlacePosition);
            mLatitude = place.geometry.location.lat;
            mLongitude = place.geometry.location.lng;
            MarkerOptions markerOptions = new MarkerOptions();
            LatLng latLng = new LatLng(mLatitude, mLongitude);
            markerOptions.position(latLng);
            markerOptions.title(place.name + " : " + place.vicinity);

            mMap.addMarker(markerOptions);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(14));
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        parseLocationResult();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.place_website_btn:
                if (!TextUtils.isEmpty(mPlaceWebsite))
                    mWebsiteButtonClickListener.onWebsiteButtonClicked(mPlaceWebsite);
                else
                    Toast.makeText(getActivity(), getString(R.string.no_website), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.map_icon:
                mOnMenuButtonClickListener.onMenuButtonClicked(mLatitude, mLongitude);
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
