package com.test.google.googleplacesapplication.nearPlace.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photo {

    @SerializedName("height")
    public int height;

    @SerializedName("html_attributions")
    public List<Object> htmlAttributions;

    @SerializedName("photo_reference")
    public String photoReference;

    @SerializedName("width")
    public int width;

}
