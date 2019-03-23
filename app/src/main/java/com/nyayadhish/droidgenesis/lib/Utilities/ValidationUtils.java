package com.nyayadhish.droidgenesis.lib.Utilities;

import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by root on 22/11/17.
 */

public class ValidationUtils {
    public static final boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    public static boolean validateEditTexts(EditText editable[]) {
        for (EditText ed :
                editable) {
            if (validateEditText(ed) == false) {
                return false;
            }
        }
        return true;
    }
    public static String makeSpaceLessUri(String s){
        URI uri = null;
        try {
            uri = new URI(s.replaceAll(" ","%20"));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

        if (uri != null) {
            Log.i("TAG", "makeSpaceLessUri: " + uri.toString());
            return uri.toString();
        }else
            return null;
    }

    public static boolean validateEditText(EditText editable) {
        if (editable == null) {
            editable.setError("Edit Text Not Initialized");
            return false;
        } else if (editable.getText().toString().trim().length() > 0) {
            return true;
        } else {
            editable.setError(editable.getHint());
            return false;
        }
    }

    public static boolean validateNoSpaces(EditText editText) {
        return editText.length() == editText.getText().toString().trim().length();
    }


    public static boolean isValidMobile(String editText) {
        return editText.trim().length() ==10;
    }


    public static boolean isValidMobile(EditText editText) {
        return editText.getText().toString().trim().length() == 10;
    }
    public static boolean validEmail(String editText) {
        return editText.trim().length() == 20;
    }


    public static boolean validEmail(EditText editText) {
        return editText.getText().toString().trim().length() == 20;
    }


    private static final String LOCATION_PATTERN = "^\\\\([+-]?((90(\\\\.0+)?)|([1-8][0-9](\\\\.[0-9]+)?)|([0-9](\\\\.[0-9]+)?)),\\\\s*[+-]?(((([1-9][0-9])|([0-9]))(\\\\.[0-9]+)?)|(1((80(\\\\.0+)?)|([0-7][0-9](\\\\.[0-9]+)?))))\\\\)$";

    public static boolean isValidLocationPair(String input) {
        return input.matches(LOCATION_PATTERN);
    }

    public static void main(String[] args) {
        String latitude = "18.5204";
        String longitude = "73.8567";

        System.out.println(isValidLocationPair(latitude));

        System.out.println("(" + latitude + ", " + longitude + ")");
        System.out.println(isValidLocationPair("(" + latitude + "," + longitude + ")"));


        System.out.println(latitude + "\n" + longitude);
        System.out.println(isValidLocationPair(latitude + "\n" + longitude));

        System.out.println("(77.11112223331, 149.99999999)");
        System.out.println(isValidLocationPair("(77.11112223331, 149.99999999)"));
    }


    public static boolean checkValid(String inputString) {
        return inputString != null && inputString.trim().length() > 0;
    }

    public static boolean isInvalidCountryCode(String countryCode){
        if (countryCode.equals("0"))
            return true;
        else
            return false;
    }

    public static boolean validateURL(String imageUrl) {
        return (imageUrl != null && imageUrl.trim().length() > 0);
    }
}
