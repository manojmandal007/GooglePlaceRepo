package com.test.google.googleplacesapplication.common.io;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class APICallback<T> implements Callback<T> {

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onResponseSuccess(response.body());
        } else {
            if (response.errorBody() != null) {
                try {
                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                    onResponseFailure(jObjError.getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                onResponseFailure(response.message());
            }
        }
    }


    @Override
    public void onFailure(Call<T> call, Throwable t) {
        onResponseFailure(t.getMessage());
    }

    public abstract void onResponseSuccess(T response);

    public abstract void onResponseFailure(String errorMessage);

    public void onResponseErrors(List<Error> data) {

    }
}