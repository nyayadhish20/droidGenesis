package com.nyayadhish.droidgenesis.lib.shrecycle;


/**
 * Created by root on 27/12/16.
 */

public interface ShrecyclerViewModel {
    String getShrecylerItemTitle();

    String[] getShrecyclerItemSubtitles();

    String getHighlightText();

    ShrecyclerOptionModel[] getDisplayOptions();

    int getHighLightColorBackground();

    void setTitleToUse(int position);

    int[] getDisplayDrawables();

    boolean wasDeleted();

    boolean wasEdited();

    String[] getDisplayImages();

    boolean hasCircleTextHighlight();

    char[] getCircleTextHighlight();

    int getCircleTextBackgroundColor();

}

