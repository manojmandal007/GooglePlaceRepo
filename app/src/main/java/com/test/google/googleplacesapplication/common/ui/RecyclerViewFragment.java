package com.test.google.googleplacesapplication.common.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.google.googleplacesapplication.R;


/**
 * This class is used as a base class for all listing page
 */
public abstract class RecyclerViewFragment extends BaseFragment implements View.OnClickListener {

    protected RecyclerView mRecyclerView;
    protected TextView mEmptyView;

    public abstract RecyclerView.LayoutManager getLayoutManager();

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setLayout(R.layout.fragment_recyclerview);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mEmptyView = (TextView) view.findViewById(R.id.empty_view);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(getLayoutManager());
    }

    protected void setEmptyView(String emptyText) {
        if (mRecyclerView.getAdapter() != null && mRecyclerView.getAdapter().getItemCount() == 0) {
            mEmptyView.setText(emptyText);
            mEmptyView.setVisibility(View.VISIBLE);
        }
    }

    protected void hideEmptyView() {
        mEmptyView.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        //Default implementation.
    }
}
