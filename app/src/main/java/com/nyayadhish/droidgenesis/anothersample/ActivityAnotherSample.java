package com.nyayadhish.droidgenesis.anothersample;

import com.nyayadhish.droidgenesis.lib.BaseActivityWithToolbar;
import com.nyayadhish.droidgenesis.lib.BasePresenter;

/**
 * Created by Nikhil Nyayadhish on 25 Mar 2019 at 17:18.
 */
public class ActivityAnotherSample extends BaseActivityWithToolbar {
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return 0;
    }

    @Override
    protected void setPresenter() {

    }

    @Override
    protected String getToolbarTitle() {
        return null;
    }

    @Override
    protected int getToolbarIcon() {
        return 0;
    }

    @Override
    public void onToolbarIconClicked() {

    }
}
