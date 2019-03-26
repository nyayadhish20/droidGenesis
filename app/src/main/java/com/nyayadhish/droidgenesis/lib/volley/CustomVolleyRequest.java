package com.nyayadhish.droidgenesis.lib.volley;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.ExecutorDelivery;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ResponseDelivery;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.JsonArray;
import com.nyayadhish.droidgenesis.lib.Utilities.ErrorCodes;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;



/**
 * Created by root on 16/11/17.
 */

public class CustomVolleyRequest extends Request<JSONObject> implements Response.ErrorListener {
    private static final String TAG = CustomVolleyRequest.class.getSimpleName();
    private Response.Listener<JSONObject> mResponseListener;
    private Response.ErrorListener mErrorListener;
    private Map<String, String> mParams;
    private VolleyCallBack mVolleyCallBack;


    private static CustomVolleyRequest mRequest;

    public static class Builder{
        private int mMethod;
        private String mURL;
        private HashMap<String, String> mParams;
        private Response.Listener<JSONObject> mReponseListener;
        private Response.Listener mListener;
        private Response.ErrorListener mErrorListener;

        public CustomVolleyRequest setURL(String url){
            this.mURL = url;
            return mRequest;
        }

        public CustomVolleyRequest setMethod(int method){
            this.mMethod = method;
            return mRequest;
        }

        public CustomVolleyRequest setParams(HashMap<String, String> params){
            this.mParams = params;
            return mRequest;
        }

        public CustomVolleyRequest setResponseListener(Response.Listener listener){
            this.mListener = listener;
            return mRequest;
        }


        public CustomVolleyRequest setErrorListener(Response.ErrorListener listener){
            this.mErrorListener = listener;
            return mRequest;
        }

        public CustomVolleyRequest build(){
            return mRequest;
        }

    }

    public CustomVolleyRequest(int method, String url, Map<String, String> params,
                               Response.Listener<JSONObject> reponseListener,
                               Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        Log.i(TAG, url);
        this.mResponseListener = reponseListener;
        this.mParams = params;
        this.mErrorListener = errorListener;
    }

    public CustomVolleyRequest(int method, String url, Map<String, String> params,
                               final VolleyCallBack callBack) {

        super(method, url, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callBack.onFailed();
            }
        });
        Log.i(TAG, url);
        this.mParams = params;
        this.mVolleyCallBack = callBack;
        this.mErrorListener = this;
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        if (mVolleyCallBack != null) {
            mVolleyCallBack.onFailed();
        }

    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> map = new HashMap<>();
        return map;
    }

    public interface VolleyCallBack {
        void onSuccess(JSONObject jsonObject) throws JSONException;
        void onError(int errorCode, String message);


        void onFailed();

        /*void onPermissionDenied(@Nullable String message);

        void onNotSubscribed(int errorCode);*/

        void onUnauthorisedAccess();
    }
    public interface VolleyCallBackArray {
        void onSuccess(JsonArray jsonArray) throws JSONException;
        void onError(int errorCode, String message);


        void onFailed();

        /*void onPermissionDenied(@Nullable String message);

        void onNotSubscribed(int errorCode);*/

        void onUnauthorisedAccess();
    }

    protected Map<String, String> getParams()
            throws AuthFailureError {
        if (mParams != null) {
            /*mParams.put("type", HeallifyAppDoctors.TYPE);
            mParams.put("access_token",HeallifyAppDoctors.getToken());
            */for (String key :
                    mParams.keySet()) {
                String value = mParams.get(key);
                if (value == null || value == "") {
                    Log.w(TAG, value == null ? "NULL " + value + " for key " + key : "EMPTY value " + value + " for key " + key);
                } else {
                    Log.w(TAG, "KEY " + key + " VALUE " + value);
                }
            }
        }
        return mParams;
    }

    @Override
    protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
        try {
            Log.i(TAG, "parseNetworkResponse: Response code" + response.statusCode);
            if (response.statusCode == 401){
                if (mVolleyCallBack != null)
                    mVolleyCallBack.onUnauthorisedAccess();
            }
            String jsonString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            return Response.success(new JSONObject(jsonString),
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException je) {
            return Response.error(new ParseError(je));
        }
    }

    public static String mLastJSONResponse;

    @Override
    protected void deliverResponse(JSONObject response) {
        if (response != null)
            mLastJSONResponse = response.toString();
        else
            mLastJSONResponse = "[Last JSON Response Was Null]";

        if (response != null) {
            Log.i(TAG, "deliverResponse: response code" +response);
            try {
                if (mVolleyCallBack != null) {
                    /*mVolleyCallBack.onResponseReceived();*/
                    if (response.getString("status").equals("ok")) {
                        mVolleyCallBack.onSuccess(response);
                    } else if (response.getString("status").equals("failed")) {
//                        int errorCode = response.getInt("error");
                        int errorCode = ErrorCodes.getFirstErrorCode(response.getJSONArray("error"));
                        if (errorCode == 0 || errorCode == 4) {
                            mVolleyCallBack.onFailed();
                        } /*else if (errorCode == 192) {
                            mVolleyCallBack.onPermissionDenied("");
                        } else if (errorCode == 202 || errorCode == 204) {
                            mVolleyCallBack.onNotSubscribed(errorCode);
                           } else if (errorCode == 401) {
                            mVolleyCallBack.onUnauthorisedAccess();
                        }*/ else
                            mVolleyCallBack.onError(errorCode,response.getString("message"));

                    } else {
                        mVolleyCallBack.onFailed();
                    }
                }
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
                e.printStackTrace();
            }
            Log.i(TAG, response.toString());
        } else
            Log.i(TAG, "response IS NULL or app is in Release Mode, hence not logging");
        if (mResponseListener != null)
            mResponseListener.onResponse(response);
    }

    public static RequestQueue newRequestQueueForTest(Context context) {
        final File cacheDir = new File(context.getCacheDir(), "volley");

        final ResponseDelivery responseDelivery = new ExecutorDelivery(Executors.newSingleThreadExecutor());

        final RequestQueue queue =
                new CustomVolley.CustomRequestQueue(
                        new DiskBasedCache(cacheDir),
                        null,
                        4,
                        responseDelivery);

        queue.start();

        return queue;
    }
}
