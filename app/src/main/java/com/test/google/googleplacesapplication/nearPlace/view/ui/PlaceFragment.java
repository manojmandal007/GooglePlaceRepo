package com.test.google.googleplacesapplication.nearPlace.view.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.test.google.googleplacesapplication.R;
import com.test.google.googleplacesapplication.common.ui.RecyclerViewFragment;
import com.test.google.googleplacesapplication.nearPlace.manager.NearPlaceManager;
import com.test.google.googleplacesapplication.nearPlace.model.Result;
import com.test.google.googleplacesapplication.nearPlace.view.adapter.PlaceAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manoj on 12/6/17.
 */

public class PlaceFragment extends RecyclerViewFragment implements PlaceAdapter.OnItemClickListener {
    private List<Result> mPlaceList = new ArrayList<>();
    private OnNearPlaceClickListener mListener;

    public interface OnNearPlaceClickListener {
        void onNearPlaceClicked(int pos);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mListener = (OnNearPlaceClickListener) context;
        } catch (ClassCastException e) {
            e.printStackTrace();
            throw new ClassCastException("Activity must implement OnNearPlaceClickListener");
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_list_grid, menu);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        return manager;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
        mPlaceList = NearPlaceManager.getInstance().getNearLocationData().results;
        setListAdapter();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.list_grid_icon:
                boolean isSwitched = ((PlaceAdapter) mRecyclerView.getAdapter()).toggleItemViewType();

                item.setTitle(isSwitched ? getString(R.string.grid) : getString(R.string.list));
                mRecyclerView.setLayoutManager(isSwitched ? new LinearLayoutManager(getActivity()) : new GridLayoutManager(getActivity(), 2));
                mRecyclerView.getAdapter().notifyDataSetChanged();
                return true;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setListAdapter() {
        PlaceAdapter adapter = new PlaceAdapter(mPlaceList, this);
        mRecyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClicked(int pos) {
        mListener.onNearPlaceClicked(pos);
    }
}
