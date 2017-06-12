package com.test.google.googleplacesapplication.nearPlace.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceResponse {

    @SerializedName("html_attributions")
    public List<Object> htmlAttributions;

    @SerializedName("results")
    public List<Result> results;

    @SerializedName("status")
    public String status;

}
