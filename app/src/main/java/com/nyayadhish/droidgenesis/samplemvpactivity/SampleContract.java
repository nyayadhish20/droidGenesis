package com.nyayadhish.droidgenesis.samplemvpactivity;

import com.nyayadhish.droidgenesis.lib.BasePresenter;
import com.nyayadhish.droidgenesis.lib.BaseView;

/**
 * Created by Nikhil Nyayadhish on 25 Mar 2019 at 10:18.
 */
public interface SampleContract {

    interface Presenter extends BasePresenter{

        void callingPresenterFromView();
    }

    interface View extends BaseView{
        void callingViewFromPresenter();
    }

}
