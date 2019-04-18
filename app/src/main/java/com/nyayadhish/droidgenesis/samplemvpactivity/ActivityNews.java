package com.nyayadhish.droidgenesis.samplemvpactivity;

import android.os.Bundle;
import android.widget.Button;

import com.nyayadhish.droidgenesis.R;
import com.nyayadhish.droidgenesis.lib.BaseActivityWithToolbar;
import com.nyayadhish.droidgenesis.lib.BasePresenter;
import com.nyayadhish.droidgenesis.model.Resp;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Created by Nikhil Nyayadhish on 25 Mar 2019 at 10:17.
 */
public class ActivityNews extends BaseActivityWithToolbar implements NewsContract.View {

    private NewsPresenter mPresenter;



    @BindView(R.id.presenter)
    Button button;

    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerview;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Calling news api written in presenter.
        mPresenter.callingPresenterFromView();
    }

    @Override
    protected String getToolbarTitle() {
        return "News";
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
        return mPresenter;
    }

    @Override
    public int getLayout() {
        return R.layout.activity_sample;
    }

    @Override
    protected void setPresenter() {
        mPresenter =  new NewsPresenter(this);
    }

    @Override
    public void onNewsFetched(Resp response) {
        if (response != null){
            NewsRecyclerView adapter = new NewsRecyclerView(this,response.getArticles());
            mRecyclerview.setLayoutManager(new LinearLayoutManager(this));
            mRecyclerview.setAdapter(adapter);
        }
    }

    @Override
    public void faliure() {
        showSnackBar("Failed to fetch News");
    }
}
