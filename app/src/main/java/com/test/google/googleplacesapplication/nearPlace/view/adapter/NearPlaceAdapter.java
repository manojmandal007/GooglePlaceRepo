package com.test.google.googleplacesapplication.nearPlace.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.test.google.googleplacesapplication.R;
import com.test.google.googleplacesapplication.nearPlace.view.ui.MapFragment;
import com.test.google.googleplacesapplication.nearPlace.view.ui.PlaceFragment;

import java.util.Arrays;

/**
 * Created by manoj on 12/6/17.
 */

public class NearPlaceAdapter extends FragmentStatePagerAdapter {

    private static final int TOTAL_TABS = 2;
    private static final int NEAR_PLACE_LIST_POS = 0;
    private static final int NEAR_PLACE_MAP_POS = 1;
    private Context mContext;

    public NearPlaceAdapter(FragmentManager fragmentManger, Context ctx) {
        super(fragmentManger);
        mContext = ctx;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case NEAR_PLACE_LIST_POS:
                return new PlaceFragment();
            case NEAR_PLACE_MAP_POS:
                return new MapFragment();
            default:
                return new PlaceFragment();
        }
    }

    @Override
    public int getCount() {
        return TOTAL_TABS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (Arrays.asList(mContext.getResources().getStringArray(R.array.tab_list))).get(position);
    }
}

