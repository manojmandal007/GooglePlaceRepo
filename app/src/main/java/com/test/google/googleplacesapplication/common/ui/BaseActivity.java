package com.test.google.googleplacesapplication.common.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;


public class BaseActivity extends AppCompatActivity implements BaseFragment.ActionBarIconListener {

    @Override
    public void setActionBarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(title);
    }

    @Override
    public void setbackButtonVisibility(boolean status) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(status);
    }

}
