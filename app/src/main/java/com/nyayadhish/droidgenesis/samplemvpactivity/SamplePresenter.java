package com.nyayadhish.droidgenesis.samplemvpactivity;

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
    }
}
