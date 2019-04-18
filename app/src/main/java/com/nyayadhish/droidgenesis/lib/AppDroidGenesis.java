package com.nyayadhish.droidgenesis.lib;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.nyayadhish.droidgenesis.BuildConfig;
import com.nyayadhish.droidgenesis.lib.dependancyinjections.APIComponent;
import com.nyayadhish.droidgenesis.lib.dependancyinjections.APIModule;
import com.nyayadhish.droidgenesis.lib.dependancyinjections.DaggerAPIComponent;
import com.squareup.picasso.Picasso;


public class AppDroidGenesis extends Application {

    private static final String TAG = AppDroidGenesis.class.getSimpleName();
    public static String TYPE;
    private static APIComponent apiComponent;

    //private User user;



    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);


    }

    @Override
    public void onCreate() {
        super.onCreate();

        initialize();
        Picasso.
                setSingletonInstance(
                        new Picasso.Builder(this).
                                indicatorsEnabled(false).
                                loggingEnabled(true).
                                build()
                );


    }


    private void initialize(){

        apiComponent = DaggerAPIComponent.builder().aPIModule(new APIModule(this)).build();

    }




    public static String getToken() {
        String token;
        if (apiComponent != null && apiComponent.getAppData().getToken() != null)
            token = apiComponent.getAppData().getToken();
        else
            token = "";

        Log.i(TAG, token + " Returning Token");
        return token;
    }


    private BaseActivity mCurrentActivity;

    public void setCurrentActivity(BaseActivity currentActivity) {
        this.mCurrentActivity = currentActivity;
    }

    public BaseActivity getCurrentForegroundActivity() {
        return mCurrentActivity;
    }


    public APIComponent getAPIComponent(){
        return apiComponent;
    }



}
