package com.test.google.googleplacesapplication.nearPlace.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("geometry")
    public Geometry geometry;

    @SerializedName("icon")
    public String icon;

    @SerializedName("id")
    public String id;

    @SerializedName("name")
    public String name;

    @SerializedName("opening_hours")
    public OpeningHours openingHours;

    @SerializedName("photos")
    public List<Photo> photos;

    @SerializedName("place_id")
    public String placeId;

    @SerializedName("scope")
    public String scope;

    @SerializedName("alt_ids")
    public List<AltId> altIds;

    @SerializedName("reference")
    public String reference;

    @SerializedName("types")
    public List<String> types;

    @SerializedName("vicinity")
    public String vicinity;

}
