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
    private static String APIVersion = BuildConfig.DEBUG ? "v2." : "v2.";
    public static String FCMToken = "";
    //private User user;
    public static String getAPIVersion() {
        return APIVersion;
    }


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);


    }

    @Override
    public void onCreate() {
        super.onCreate();

        initialize();
        //chech();


        Picasso.
                setSingletonInstance(
                        new Picasso.Builder(this).
                                indicatorsEnabled(true).
                                loggingEnabled(true).
                                build()
                );

        //redirectOnCondition();
        //initializeFonts();
        Log.i(TAG, "onCreate: FCM Token = " + FCMToken);

    }

    /*private void redirectOnCondition() {
        if(User.getInstance() != null){
            Intent i = new Intent(this, ActivityHomePage.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
           // i.setFlags(Intent.FLAG_FROM_BACKGROUND);
            startActivity(i);
        }else{
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            //intent.setFlags(Intent.FLAG_FROM_BACKGROUND);
            startActivity(intent);
        }
    }*/



    /*private void initializeFonts() {
        Log.i(TAG, "initializeFonts: Initializing Fonts");
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/DidactGothic.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }*/

    private void initialize(){

        apiComponent = DaggerAPIComponent.builder().aPIModule(new APIModule(this)).build();


        //NotificationGenerateHelper.init(this);
        //Realm.init(this);
        //Realm realm = apiComponent.getRealmHelper().getRealmInstance();
/*

        realm.beginTransaction();
        User user = realm.where(User.class).findFirst();
        realm.commitTransaction();
*/

 /*       User user;
        user = apiComponent.getGson().fromJson(apiComponent.getAppData().getUserData(),User.class);

        if (user != null) {
            User.setInstance(user);
        } else {
            // User.setInstance(null);
            Intent i = new Intent(this, ActivityLogin.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(i);
        }
*/
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

    /*@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    void chech() {
        String storedLang = apiComponent.getAppData().getSavedLanguage();
        if (storedLang == null && storedLang.equals("")) {
            return ;
        } else {
            Configuration configuration = new Configuration();
            Log.i("Checking", storedLang);
            Locale locale = new Locale(storedLang);
            Locale.setDefault(locale);
            configuration.locale = locale;
            getBaseContext().getResources().updateConfiguration(configuration,
                    getBaseContext().getResources().getDisplayMetrics());
        }
    }*/

    public APIComponent getAPIComponent(){
        return apiComponent;
    }



}
