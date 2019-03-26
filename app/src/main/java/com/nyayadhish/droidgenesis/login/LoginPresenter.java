package com.nyayadhish.droidgenesis.login;

import com.nyayadhish.droidgenesis.lib.retrofit.CustomRetroCallbacks;
import com.nyayadhish.droidgenesis.model.Success;

/**
 * Created by Nikhil Nyayadhish on 26 Mar 2019 at 11:52.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View mView;

    public LoginPresenter(LoginContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void login(String username, String password) {
        mView.showMaterialProgress("");
        mView.getAPIComponent().getRetroService().getBaseUrl().login("http://sale24by7.com/playballnet_staging/index.php/api/userLogin?email=shrinivas.magneto%40gmail.com&password=1234567&deviceId=e6vmm9DzlnM%3AAPA91bE0FdoEaoICasPDdJedBK8gZT_hVPdYC6Z3QjqLxs5JNpFUiGyafSo-xFPaQ6kS-Zn16ZuMaSwAm2H8b22xZWEpPkcl4heOTMYhxoZBds5YJs5IX1XJDvd5B7qVGgB61xw9sT-N&deviceType=2&userType=1&loginType=1&socialId=&username=")
                .enqueue(new CustomRetroCallbacks<Success>() {
                    @Override
                    protected void onSuccess(Success response) {
                        mView.hideMaterialProgress();
                        if (response.getStatus().equals("1")){
                            mView.onLoginSuccess();
                        }else{
                            mView.onLoginFailure();
                        }
                    }

                    @Override
                    protected void onFailure(String generalErrorMsg, String systemErrorMsg) {
                        mView.hideMaterialProgress();
                        mView.onLoginFailure();
                    }
                });
    }
}
