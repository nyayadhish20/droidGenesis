package com.nyayadhish.droidgenesis.lib.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.nyayadhish.droidgenesis.lib.AppData;
import com.nyayadhish.droidgenesis.lib.BasePresenter;


/**
 * Created by root on 25/8/16.
 */
public class ServerData {
    public static final String TAG=ServerData.class.getName();
    //  private static ServerData serverData;
    private Context context;
    private CustomVolley.CustomRequestQueue requestQueue;
    private Request mLastRequest;
    private AppData mAppData;

    public ServerData(Context context, AppData appData) {
        this.mAppData = appData;
        this.context = context;
        this.requestQueue = CustomVolley.newCustomRequestQueue(this.context, null);
    }

    public <T> void addToRequestQueue(Request<T> req, BasePresenter basePresenter) {
        mAppData.addDebugPDFInfo(req.getUrl());
        mLastRequest = req;
        req.setTag(basePresenter.getClass().hashCode() + "");
        /*req.setRetryPolicy(new DefaultRetryPolicy(10000,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));*/
        Log.i(TAG,"Received TAG =  "+basePresenter.getClass().hashCode());
        requestQueue.add(req);
    }

    public <T> void addToRequestQueueNonCancellable(Request<T> req, BasePresenter basePresenter) {
        mAppData.addDebugPDFInfo(req.getUrl());
        mLastRequest = req;
        req.setTag(basePresenter.getClass().hashCode() + " non cancellable presenter call");
        requestQueue.add(req);
    }

    public RequestQueue getRequestQueue() {
        return requestQueue;
    }

    public void cancelRequests(String tag){
        Log.i(TAG,"cancelling API call for tag " + tag);
        requestQueue.cancelAll(tag);
    }


    public String getLastUrl() {
        return mLastRequest.getUrl();
    }

}






