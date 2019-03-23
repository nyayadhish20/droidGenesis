package com.nyayadhish.droidgenesis;

import android.os.Bundle;

import com.nyayadhish.droidgenesis.lib.BaseActivity;
import com.nyayadhish.droidgenesis.lib.BasePresenter;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected BasePresenter getPresenter() {
        return null;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void setPresenter() {

    }
}
