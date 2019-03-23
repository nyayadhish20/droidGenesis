package com.nyayadhish.droidgenesis.lib.Utilities;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;
import androidx.legacy.app.ActivityCompat;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class Permissions {

    public static final int PERMISSION_REQUEST_CODE = 100;

    public static boolean checkPermission(Context context) {
        int result = ContextCompat.checkSelfPermission(context, CAMERA);
        int result1 = ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE);

        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity, new String[]{CAMERA, WRITE_EXTERNAL_STORAGE}, PERMISSION_REQUEST_CODE);
    }
}
