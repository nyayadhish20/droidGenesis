package com.nyayadhish.droidgenesis.samplemvpactivity;

import com.nyayadhish.droidgenesis.lib.BasePresenter;
import com.nyayadhish.droidgenesis.lib.BaseView;
import com.nyayadhish.droidgenesis.model.Resp;

/**
 * Created by Nikhil Nyayadhish on 25 Mar 2019 at 10:18.
 */
public interface NewsContract {

    interface Presenter extends BasePresenter{

        void callingPresenterFromView();
    }

    interface View extends BaseView{
        void onNewsFetched(Resp response);

        void faliure();
    }

}
