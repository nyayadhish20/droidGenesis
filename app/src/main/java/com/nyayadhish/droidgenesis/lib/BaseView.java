package com.nyayadhish.droidgenesis.lib;


import com.nyayadhish.droidgenesis.lib.dependancyinjections.APIComponent;

public interface BaseView<T> {
    void showDebugToast(String mesage);
    void showMaterialProgress(String message);
    void hideMaterialProgress();
    void onUnauthoriseAccess();


    void onUnHandledErrorCodeDebug(int errorCode);

    void onUnhandledErrorCode(String message);

    APIComponent getAPIComponent();

    void showToast(String s);

    void onNoNetworkFoud();

}
