package com.nyayadhish.droidgenesis.lib.dependancyinjections;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.nyayadhish.droidgenesis.lib.AppData;
import com.nyayadhish.droidgenesis.lib.AppDroidGenesis;
import com.nyayadhish.droidgenesis.lib.volley.ServerData;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
@Module
public class APIModule {

    private AppDroidGenesis context;

    public APIModule(AppDroidGenesis context) {
        this.context = context;
    }

    @Provides
    @Singleton
    ServerData providesServerData(){
        return new ServerData(context, getAppData());
    }

    @Provides
    @Singleton
    SharedPreferences providePreferences(){
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Singleton
    Gson getMeGson(){
        return new Gson();
    }

    @Provides
    @Singleton
    AppData getAppData(){
        return new AppData(context);
    }

    /*@Provides
    @Singleton
    RealmHelper getRealmHelper(){
        return new RealmHelper(context);
    }*/

    @Provides
    @Singleton
    AppDroidGenesis getApp() {
        return context;
    }

    /*


    @Provides
    @Singleton
    HeallifyService getRetrofitService(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HeallifyService.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(HeallifyService.class);
    }
*/
/*
    @Provides
    @Singleton
    NotificationPresenter provideNotificationManager(){
        return new NotificationPresenter(null, context);
    }*/
}
