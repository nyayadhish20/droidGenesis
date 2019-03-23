package com.nyayadhish.droidgenesis.lib.Utilities;

import android.content.Context;
import android.content.res.Configuration;

public class DisplayUtils {
    public static boolean isScreenLarge(Context context){
        int screenSize = context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;

        switch(screenSize) {
            case Configuration.SCREENLAYOUT_SIZE_LARGE:
                return true;
            case Configuration.SCREENLAYOUT_SIZE_NORMAL:
                return false;
            case Configuration.SCREENLAYOUT_SIZE_SMALL:
                return false;
            case Configuration.SCREENLAYOUT_SIZE_XLARGE:
                return true;
            default:
                return true;
        }
    }
}
