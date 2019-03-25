package com.nyayadhish.droidgenesis.samplemvpactivity;

import android.widget.Button;

import com.nyayadhish.droidgenesis.R;
import com.nyayadhish.droidgenesis.lib.BaseActivityWithToolbar;
import com.nyayadhish.droidgenesis.lib.BasePresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Nikhil Nyayadhish on 25 Mar 2019 at 10:17.
 */
public class ActivitySample extends BaseActivityWithToolbar implements SampleContract.View {

    private SamplePresenter mPresenter;



    @BindView(R.id.presenter)
    Button button;


    @OnClick(R.id.presenter)
    void callToPresenter(){
        mPresenter.callingPresenterFromView();


    }



    @Override
    protected String getToolbarTitle() {
        return "Sample";
    }

    @Override
    protected int getToolbarIcon() {
        return R.drawable.ic_arrow_back_black_24dp;
    }

    @Override
    public void onToolbarIconClicked() {
        finish();
    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_sample;
    }

    @Override
    protected void setPresenter() {
        mPresenter =  new SamplePresenter(this);
    }

    @Override
    public void callingViewFromPresenter() {
        showToast("This is calling view from Presenter");
    }

    @Override
    public void faliure() {
        showToast("Failed");
    }
}
