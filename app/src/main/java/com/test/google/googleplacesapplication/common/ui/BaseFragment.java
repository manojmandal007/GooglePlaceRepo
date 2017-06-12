package com.test.google.googleplacesapplication.common.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.test.google.googleplacesapplication.R;


public class BaseFragment extends Fragment {
    private int mLayoutId;
    private ProgressBar mProgressBar;
    protected OnBackButtonClickListener mBackButtonClickListener;
    private FrameLayout mEmptyBaseContainer;

    public interface OnBackButtonClickListener {
        void onBackButtonClicked();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mBackButtonClickListener = (OnBackButtonClickListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new ClassCastException("Activity must implement OnBackButtonClickListener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_base, container, false);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        mEmptyBaseContainer = (FrameLayout) view.findViewById(R.id.empty_background__container);
        FrameLayout fragmentLayoutContainer = (FrameLayout) view.findViewById(R.id.layout_container);

        inflater.inflate(mLayoutId, fragmentLayoutContainer);
        return view;
    }

    protected void setLayout(int layoutId) {
        mLayoutId = layoutId;
    }


    protected void showProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.VISIBLE);
            mEmptyBaseContainer.setEnabled(true);
            mEmptyBaseContainer.setClickable(true);
        }
    }

    protected void hideProgressBar() {
        if (mProgressBar != null) {
            mProgressBar.setVisibility(View.GONE);
            mEmptyBaseContainer.setEnabled(false);
            mEmptyBaseContainer.setClickable(false);
        }
    }
}
