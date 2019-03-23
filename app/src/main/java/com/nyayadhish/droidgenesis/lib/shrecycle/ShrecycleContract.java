package com.nyayadhish.droidgenesis.lib.shrecycle;

public interface ShrecycleContract {
    void onShrecycleOptionItemClicked(int optionPosition, int listPosition);

    void onShrecycleItemClicked(int adapterPosition, ShrecyclerViewModel item);

    void onShrecycleItemHighLightClicked(String highlightText, int adapterPosition);

    void onLoadMore(int current_page);



}
