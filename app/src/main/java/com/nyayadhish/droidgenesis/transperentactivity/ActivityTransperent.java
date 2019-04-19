package com.nyayadhish.droidgenesis.transperentactivity;

import com.nyayadhish.droidgenesis.R;
import com.nyayadhish.droidgenesis.lib.BaseActivity;
import com.nyayadhish.droidgenesis.lib.BasePresenter;

public class ActivityTransperent extends BaseActivity {
    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_transperent;
    }

    @Override
    protected void setPresenter() {

    }
}
