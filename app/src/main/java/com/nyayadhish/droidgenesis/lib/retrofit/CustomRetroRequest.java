package com.nyayadhish.droidgenesis.lib.retrofit;


import com.nyayadhish.droidgenesis.lib.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * CustomRetroRequest class is to handel Retrofit instance and make call to server to retrieve data from server.
 */

public class CustomRetroRequest {

    //Local
//    public final String baseUrl = "http://27.109.19.234/ashrafs/ecommerceapi/";
    //Live
    public final String baseUrl = "https://ashrafs.magneto.co.in/ecommerceapi/";

    private static CustomRetroRequest customRetroRequest = null;

    /**
     * @return Instance of CustomRetroRequest class
     */
    public static CustomRetroRequest getInstance() {
        return (customRetroRequest == null) ? customRetroRequest = new CustomRetroRequest() : customRetroRequest;
    }

    public Retrofit retrofit = null;

    /**
     * @return Instance of RetrofitAPI class
     */
    public RetrofitAPI getBaseUrl() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(getClient())
                    .build();
        }
        return retrofit.create(RetrofitAPI.class);
    }

    /**
     * @return Instance of OkHttpClient class with modified timeout
     */
    private OkHttpClient getClient() {
        long HTTP_TIMEOUT = 20;
        final OkHttpClient.Builder okHttpClientBuilder = new
                OkHttpClient.Builder();
        okHttpClientBuilder.connectTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS);
        okHttpClientBuilder.readTimeout(HTTP_TIMEOUT, TimeUnit.SECONDS);
        return okHttpClientBuilder.build();
    }
}