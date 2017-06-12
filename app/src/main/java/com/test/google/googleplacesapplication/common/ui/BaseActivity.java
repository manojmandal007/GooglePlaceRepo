package com.test.google.googleplacesapplication.common.ui;

import android.support.v7.app.AppCompatActivity;

import com.test.google.googleplacesapplication.common.ui.BaseFragment;


public class BaseActivity extends AppCompatActivity implements BaseFragment.OnBackButtonClickListener {

    @Override
    public void onBackButtonClicked() {
        onBackPressed();
    }


}
