package com.nyayadhish.droidgenesis.lib;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class AppData {
    private static final String KEY_FCM_TOKEN = "KEY_FCM_TOKEN";
    private static final String KEYLANGUAGE = "en";
    private static final String KEY_TOKEN = "token";
    private static final String KEYUSER = "user";
    private final String KEYEMAIL = "email";
    private final String KEYPASS = "password";
    private final String KEYREMEMBER = "remember";
    private final String KEYSPECIALIZATION="specialization";
    private static final String DEFAULTPREFERENCE = "default";
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPreferenceEditor;
    private static final String TAG = AppData.class.getSimpleName();

    /*   private static AppData appData;
     */
    public AppData(Context context) {
        loginPreferences = context.getSharedPreferences(DEFAULTPREFERENCE, Context.MODE_PRIVATE);
        loginPreferenceEditor = loginPreferences.edit();
        loginPreferenceEditor.commit();
    }

/*

    public static void init(Context context) {
        if (appData == null) {
            appData = new AppData(context);
        }

    }
    public static AppData getAppData(){
        return appData;
    }
*/

    public boolean getRememberMe(){
        return loginPreferences.getBoolean(KEYREMEMBER,false);
    }

    public void setRememberMe(){
        loginPreferenceEditor.putBoolean(KEYREMEMBER,true);
        loginPreferenceEditor.commit();
    }

    public void unsetRememberMe(){
        loginPreferenceEditor.clear();
        loginPreferenceEditor.commit();
    }

    public void savePassword(String password){
        loginPreferenceEditor.putString(KEYPASS,password);
        loginPreferenceEditor.commit();
    }

    public void saveEmail(String email){
        loginPreferenceEditor.putString(KEYEMAIL,email);
        loginPreferenceEditor.commit();
    }

    public void saveUserData(String user){
        Log.i(TAG, "saveUserData: Saving User Data" + user);
        loginPreferenceEditor.putString(KEYUSER,user);
        loginPreferenceEditor.commit();
    }

    public String getSavedEmail(){
        return loginPreferences.getString(KEYEMAIL,"");
    }

    public String getSavedPassword(){
        return loginPreferences.getString(KEYPASS,"");
    }


    public void setSpecialization(String name) {
        loginPreferenceEditor.putString(KEYSPECIALIZATION, name);
        loginPreferenceEditor.commit();
    }

    public String getSpecialization(){
        return loginPreferences.getString(KEYSPECIALIZATION,"");
    }

    public void storeFCMToken(String token) {
        loginPreferenceEditor.putString(KEY_FCM_TOKEN,token).commit();
    }

    public String getFCMToken() {
        return loginPreferences.getString(KEY_FCM_TOKEN, null);
    }


    public void saveLangInfo(String lang){
        loginPreferenceEditor.putString(KEYLANGUAGE,lang);
        loginPreferenceEditor.commit();
    }

    public String getSavedLanguage() {
        return loginPreferences.getString(KEYLANGUAGE,"");
    }



    public void saveToken(String token) {
        Log.i(TAG, "saveToken: Token = " + token);
        loginPreferenceEditor.putString(KEY_TOKEN,token);
        loginPreferenceEditor.commit();
    }

    public String getToken(){
        return loginPreferences.getString(KEY_TOKEN,null);
    }

    public String getUserData() {
        return loginPreferences.getString(KEYUSER,null);
    }

    public void removeToken() {
        loginPreferenceEditor.remove(KEY_TOKEN);
        loginPreferenceEditor.commit();
    }

    public void clearEverything() {
        Log.i(TAG, "clearEverything: Clearing Data");
        loginPreferenceEditor.clear();
        loginPreferenceEditor.commit();
    }
}

