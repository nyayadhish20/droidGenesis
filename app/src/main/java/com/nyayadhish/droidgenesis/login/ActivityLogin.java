package com.nyayadhish.droidgenesis.login;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import com.nyayadhish.droidgenesis.R;
import com.nyayadhish.droidgenesis.lib.BaseActivity;
import com.nyayadhish.droidgenesis.lib.BasePresenter;
import com.nyayadhish.droidgenesis.samplemvpactivity.ActivityNews;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Nikhil Nyayadhish on 25 Mar 2019 at 17:18.
 */
public class ActivityLogin extends BaseActivity implements LoginContract.View {

    @BindView(R.id.username)
    EditText username;

    @BindView(R.id.password)
    EditText password;

    @OnClick(R.id.login)
    void login(){
        mPresenter.login(username.getText().toString(),password.getText().toString());
    }

    private LoginPresenter mPresenter;



    @Override
    protected BasePresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void setPresenter() {
        mPresenter = new LoginPresenter(this);
    }


    @Override
    public void onLoginSuccess() {
        showSnackBar("Login success");
        startActivity(new Intent(this, ActivityNews.class));
    }

    @Override
    public void onLoginFailure() {
        showSnackBarWitAction("Failed to login", "OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSnackBar();
                showToast("OK");
            }
        });
    }
}
