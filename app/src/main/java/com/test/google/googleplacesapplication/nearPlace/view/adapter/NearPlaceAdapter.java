package com.test.google.googleplacesapplication.nearPlace.view.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.test.google.googleplacesapplication.R;
import com.test.google.googleplacesapplication.nearPlace.view.ui.DummyFragment;

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
                return new DummyFragment();
            case NEAR_PLACE_MAP_POS:
                return new DummyFragment();
            default:
                return new DummyFragment();
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

