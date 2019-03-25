package com.nyayadhish.droidgenesis.lib.dependancyinjections;

import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.nyayadhish.droidgenesis.lib.AppData;
import com.nyayadhish.droidgenesis.lib.AppDroidGenesis;
import com.nyayadhish.droidgenesis.lib.retrofit.CustomRetroRequest;
import com.nyayadhish.droidgenesis.lib.volley.ServerData;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {APIModule.class})
public interface APIComponent {
    ServerData getServerData();
    SharedPreferences getPreferences();
    Gson getGson();
    AppData getAppData();
    //RealmHelper getRealmHelper();
    CustomRetroRequest getRetroService();

    AppDroidGenesis getApp();
/*
    HeallifyService getRetrofitService();
    NotificationPresenter getNotificationManager();
*/

}
