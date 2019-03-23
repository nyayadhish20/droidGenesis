package com.nyayadhish.droidgenesis.lib;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.nyayadhish.droidgenesis.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

public abstract class BaseActivityWithToolbar extends BaseActivity {
    private Toolbar toolbar;
    private TextView messageTextView;
    private TextView customTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        if(toolbar!=null)
            setupToolbar(toolbar);
        //messageTextView = (TextView)findViewById(R.id.message);
        //setSupportActionBar(getToolbar());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (menuItem.getItemId() == android.R.id.home) {
            onToolbarIconClicked();
            return true;
        }
        return super.onOptionsItemSelected(menuItem);
    }


    protected abstract String getToolbarTitle();

/*
    private int mOptionsMenuId;
    public void setOptionsMenu(@MenuRes int menuId){
        this.mOptionsMenuId = menuId;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(mOptionsMenuId != 0){
            getMenuInflater().inflate(mOptionsMenuId, menu);
            return true;
        }else{
            return false;
        }
    }
*/

    protected abstract int getToolbarIcon();
    public void setupToolbar(androidx.appcompat.widget.Toolbar toolbar) {
        if(toolbar!=null){
            if(getToolbarTitle()!=null)
                setToolbarTitle(getToolbarTitle());


            if(getToolbarIcon()!=0)
                toolbar.setNavigationIcon(getToolbarIcon());
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onToolbarIconClicked();
            }
        });

        setSupportActionBar(toolbar);
    }


    public void setToolbarTitle(String title){
        if(toolbar!=null){
            toolbar.setTitle(title);
        }
        if (customTitle != null){
            customTitle.setText(title);
        }
    }

    public void setToolbarSubTitle(String subtitle){
        if(toolbar!=null){
            toolbar.setSubtitleTextColor(Color.WHITE);
            toolbar.setSubtitle(subtitle);
        }
    }

    public void setMessageTextViewText(String textViewText){
        if(messageTextView != null)
            messageTextView.setText(textViewText);
    }

    public abstract void onToolbarIconClicked();

    public Toolbar getToolbar() {
        return toolbar;
    }


}
