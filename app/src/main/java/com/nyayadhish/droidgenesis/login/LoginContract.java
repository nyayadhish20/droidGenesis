package com.nyayadhish.droidgenesis.login;

import com.nyayadhish.droidgenesis.lib.BasePresenter;
import com.nyayadhish.droidgenesis.lib.BaseView;

/**
 * Created by Nikhil Nyayadhish on 26 Mar 2019 at 11:51.
 */
public interface LoginContract {

    interface Presenter extends BasePresenter{
        void login(String username, String password);


    }

    interface View extends BaseView{

        void onLoginSuccess();

        void onLoginFailure();

    }

}
