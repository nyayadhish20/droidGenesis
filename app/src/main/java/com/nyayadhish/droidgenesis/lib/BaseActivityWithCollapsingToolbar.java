package com.nyayadhish.droidgenesis.lib;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.FrameLayout;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.nyayadhish.droidgenesis.R;

import androidx.annotation.Nullable;

/**
 * Created by root on 11/11/16.
 */

public abstract class BaseActivityWithCollapsingToolbar extends BaseActivityWithToolbar {

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private FrameLayout collapsingFrameLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        collapsingToolbarLayout = (CollapsingToolbarLayout)findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getCollapsingToolbarTitle());
        collapsingToolbarLayout.setContentScrimColor(getResources().getColor(R.color.primaryDarkColor));

       /* getSupportFragmentManager().
                beginTransaction().
                replace(R.id.collpasingFrameLayout,
                getCollapsingFragment()).commit();*/


    }

    protected void setCollapsingToolbarLayoutTitleExpanded(String expandedTitle){
        collapsingToolbarLayout.setTitle(expandedTitle);
    }

    protected void setCollapsingToolbarLayoutTitleCollapsed(String collapsedTitle){
        collapsingToolbarLayout.setTitle(collapsedTitle);
    }

    protected abstract String getCollapsingToolbarTitle();

    public CollapsingToolbarLayout gettCollapsintgToolar(){
        return this.collapsingToolbarLayout;
    }

    //protected abstract Fragment getCollapsingFragment();

    public void showErrorOnEmptyEditText(EditText editText, String message){
        editText.requestFocus();
        editText.setError(message);
    }

}
