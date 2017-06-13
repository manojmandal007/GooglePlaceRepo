package com.test.google.googleplacesapplication.nearPlace.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test.google.googleplacesapplication.R;
import com.test.google.googleplacesapplication.nearPlace.model.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by manoj on 12/6/17.
 */

public class PlaceAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Result> mPlaceList = new ArrayList<>();
    private static final int LIST_ITEM = 0;
    private static final int GRID_ITEM = 1;
    private OnItemClickListener mOnItemClickListener;
    private boolean mIsSwitchView = true;

    public interface OnItemClickListener {
        void onItemClicked(int pos);
    }

    public PlaceAdapter(List<Result> menuResponseList, OnItemClickListener listener) {
        mPlaceList = menuResponseList;
        mOnItemClickListener = listener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        switch (viewType) {
            case LIST_ITEM:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_item_view, parent, false);
                break;
            case GRID_ITEM:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_item_grid_view, parent, false);
                break;
            default:
                itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.place_list_item_grid_view, parent, false);
        }
        return new ViewHolder(itemView);
    }

    @Override
    public int getItemViewType(int position) {
        if (mIsSwitchView) {
            return LIST_ITEM;
        } else {
            return GRID_ITEM;
        }
    }

    public boolean toggleItemViewType() {
        mIsSwitchView = !mIsSwitchView;
        return mIsSwitchView;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder placeViewHolder = (ViewHolder) holder;
        setPlaceData(placeViewHolder, position);
    }

    private void setPlaceData(ViewHolder holder, final int position) {
        if (mPlaceList.size() > position) {
            Context ctx = holder.placeImage.getContext();
            Result data = mPlaceList.get(position);
            holder.placeName.setText(data.name);
            holder.latitude.setText(String.format("%s%s", ctx.getString(R.string.lat), String.valueOf(data.geometry.location.lat)));
            holder.longtiude.setText(String.format("%s%s", ctx.getString(R.string.lat), String.valueOf(data.geometry.location.lng)));
            holder.highlight.setText(data.vicinity);
            String imageUrl = "";
            if (data.photos != null && data.photos.size() >= 1) {
                imageUrl = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=400&key=AIzaSyDbivDexfwFTRjfBSaiDSH02OTPeok6dLQ&photoreference=" + data.photos.get(0).photoReference;
            }
            Glide.with(ctx).load(imageUrl).placeholder(R.drawable.common_google_signin_btn_icon_dark).fitCenter().into(holder.placeImage);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickListener.onItemClicked(position);
                }
            });
        }
    }


    private static class ViewHolder extends RecyclerView.ViewHolder {
        TextView placeName, latitude, longtiude, highlight;
        ImageView placeImage;

        ViewHolder(View view) {
            super(view);
            placeName = (TextView) view.findViewById(R.id.place_tv);
            latitude = (TextView) view.findViewById(R.id.lat_tv);
            longtiude = (TextView) view.findViewById(R.id.lang_tv);
            highlight = (TextView) view.findViewById(R.id.highlight_tv);
            placeImage = (ImageView) view.findViewById(R.id.place_iv);
        }
    }

    @Override
    public int getItemCount() {
        return mPlaceList.size();
    }

}