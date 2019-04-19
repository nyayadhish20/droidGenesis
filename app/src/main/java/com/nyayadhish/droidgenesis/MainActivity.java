package com.nyayadhish.droidgenesis;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.nyayadhish.droidgenesis.lib.BaseActivity;
import com.nyayadhish.droidgenesis.lib.BasePresenter;
import com.nyayadhish.droidgenesis.transperentactivity.ActivityTransperent;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.button)
    Button button;

    @OnClick(R.id.button)
    void showNextActivity(){
        Intent i = new Intent(this, ActivityTransperent.class);
        startActivity(i);
    }

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
