package com.test.google.googleplacesapplication.common.ui;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;

import com.test.google.googleplacesapplication.common.ui.BaseFragment;


public class BaseActivity extends AppCompatActivity implements BaseFragment.OnBackButtonClickListener,BaseFragment.ActionBarIconListener {

    @Override
    public void onBackButtonClicked() {
        onBackPressed();
    }

    @Override
    public void setActionBarTitle(String title) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(title);
    }
}
