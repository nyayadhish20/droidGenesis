package com.nyayadhish.droidgenesis.lib.retrofit;


import android.util.Log;

import androidx.annotation.NonNull;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Custom API Callback class to handle response and failure from APIs.
 *
 * @param <T> Custom Model class which are created for the handle response of the API.
 */
public abstract class CustomRetroCallbacks<T> implements Callback<T> {

    private static final String TAG = CustomRetroCallbacks.class.getSimpleName();

    /**
     * @param call     Instance of Call interface.
     * @param response Response of the API to determine if it indicates success.
     */
    @Override
    public void onResponse(@NonNull Call<T> call, @NonNull Response<T> response) {


        Log.i(TAG, "URL : " + call.request().url());


        if (response.isSuccessful() && response.body() != null) {
            onSuccess(response.body());
        } else
            onFailure(call, new Exception());
    }


    /**
     * @param call      Instance of Call interface.
     * @param throwable Throw an error.
     */
    @Override
    public void onFailure(@NonNull Call<T> call, @NonNull Throwable throwable) {


    }

    /**
     * @param response If not null then sends response to the activity.
     */
    protected abstract void onSuccess(T response);

    /**
     * @param generalErrorMsg User defined error message.
     * @param systemErrorMsg  System defined error message.
     */
    protected abstract void onFailure(String generalErrorMsg, String systemErrorMsg);
}
