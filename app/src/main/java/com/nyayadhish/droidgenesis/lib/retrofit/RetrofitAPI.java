package com.nyayadhish.droidgenesis.lib.retrofit;

import com.nyayadhish.droidgenesis.model.Resp;
import com.nyayadhish.droidgenesis.model.Success;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Interface to declare API references with their input parameters
 */
public interface RetrofitAPI {
    @GET()
    Call<Resp> getAllNews( @Url String url );

    @GET()
    Call<Success> login(@Url String url);

}