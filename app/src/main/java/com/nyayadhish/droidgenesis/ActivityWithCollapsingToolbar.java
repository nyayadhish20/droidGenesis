package com.nyayadhish.droidgenesis;

import com.nyayadhish.droidgenesis.lib.BaseActivityWithCollapsingToolbar;
import com.nyayadhish.droidgenesis.lib.BasePresenter;

public class ActivityWithCollapsingToolbar extends BaseActivityWithCollapsingToolbar {
    @Override
    protected String getCollapsingToolbarTitle() {
        return null;
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

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.another_activity;
    }

    @Override
    protected void setPresenter() {

    }
}
