package com.nyayadhish.droidgenesis.lib.shrecycle;

/**
 * Created by root on 26/4/17.
 */
public class ShrecyclerOptionModel {
    String title;
    int mOptionBackgroundColor;
    int mOptionDrawableId;

    public ShrecyclerOptionModel(String title, int mOptionBackgroundColor, int mOptionDrawableId) {
        this.title = title;
        this.mOptionBackgroundColor = mOptionBackgroundColor;
        this.mOptionDrawableId = mOptionDrawableId;
    }

    public String getTitle() {
        return title;
    }

    public int getmOptionBackgroundColor() {
        return mOptionBackgroundColor;
    }

    public int getmOptionDrawableId() {
        return mOptionDrawableId;
    }
}
