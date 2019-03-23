package com.nyayadhish.droidgenesis.lib.Utilities;

import org.json.JSONArray;
import org.json.JSONException;

/**
 * Created by root on 16/11/17.
 */

public class ErrorCodes {
    static int errorCode;
    public static String JSONExceptionError="Server Error";
    public static String vollyError="Network Unreachable";
    public static String getError(JSONArray array) throws JSONException {

        for (int i=0;i<array.length();i++){
            errorCode = array.getInt(i);
            switch (errorCode) {
                case 100:
                    return "INVALID INPUT";
                // break;
                case 101:
                    return "EMAIL ALREADY IN USE";
                //break;
                case 102:
                    return "MOBILE NUMBER ALREADY IN USE";
                //   break;
                case 103:
                    return "INVALID CREDENTIAL";
                // break;
                case 104:
                    return "INVALID CODE/OTP";
                // break;
                case 105:
                    return "ONE OR MORE FIELD IS MISSING";
                // break;
                case 106:
                    return "MOBILE NOT VERIFIED";
                // break;
                case 107:
                    return "ALREADY VERIFIED";
                //break;
                case 108:
                    return "ACCOUNT NOT FOUND";
                // break;
                case 121:
                    return "TRY DIFFERENT INPUT";
                // break;
                case 135:
                    return "APPOINTMENT CANNOT BE CANCELLED NOW";
                // break;
                case 401:
                    return "UNAUTHORIZED ACCESS";
                //break;
                case 403:
                    return "FORBIDDEN ACCESS";
                // break;
                case 404:
                    return "NOT FOUND";
                //break;
                case 500:
                    return "INTERNAL SERVER ERROR";
                //break;
                case 138:
                    return "SCHEDULES SERVER ERROR";
                //break;
            }
        }
        return "";
    }

    public static int getFirstErrorCode(JSONArray array) throws JSONException {
        if(array.length() != 0)
            return array.getInt(0);
        else

            return -111;
    }
}
