package com.nyayadhish.droidgenesis.samplemvpactivity;

import android.util.Log;

import com.nyayadhish.droidgenesis.lib.retrofit.CustomRetroCallbacks;
import com.nyayadhish.droidgenesis.model.Resp;

/**
 * Created by Nikhil Nyayadhish on 25 Mar 2019 at 10:19.
 */
public class SamplePresenter implements SampleContract.Presenter {

    private SampleContract.View mView;

    public SamplePresenter(SampleContract.View mView) {
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
    public void callingPresenterFromView() {
        mView.callingViewFromPresenter();

        mView.getAPIComponent().getRetroService().getBaseUrl().getAllNews().enqueue(new CustomRetroCallbacks<Resp>() {
            @Override
            protected void onSuccess(Resp response) {
                mView.callingViewFromPresenter();
                Log.i("TAG", "onSuccess: response = " + response.getStatus());

            }

            @Override
            protected void onFailure(String generalErrorMsg, String systemErrorMsg) {
                mView.faliure();

            }
        });



    }
}
