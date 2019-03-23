package com.nyayadhish.droidgenesis.lib.Utilities;

import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {
    public static String TAG = EndlessScrollListener.class.getSimpleName();

    private int previousTotal = 0; // The total number of items in the dataset after the last load
    private boolean loading = true; // True if we are still waiting for the last set of data to load.
    private int visibleThreshold = 3; // The minimum amount of items to have below your current scroll position before loading more.
    private int firstVisibleItem, visibleItemCount, totalItemCount;

    private int current_page = 1;

    private LinearLayoutManager mLinearLayoutManager;

    public EndlessScrollListener(LinearLayoutManager linearLayoutManager) {
        this.mLinearLayoutManager = linearLayoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        visibleItemCount = recyclerView.getChildCount();
        totalItemCount = mLinearLayoutManager.getItemCount();
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition();

      /*  Log.i(TAG, "Visible Item Count : " + visibleItemCount + " Total Item Count : " + totalItemCount + " First Visible Item Count : " + firstVisibleItem);
*/
        if (loading) {
            if (totalItemCount >= previousTotal) {
                loading = false;
                previousTotal = totalItemCount;
            }
        }


   /*     Log.i(TAG, "\n(totalItemCount - visibleItemCount): " + (totalItemCount - visibleItemCount)
                + "\n(firstVisibleItem + visibleThreshold) " + (firstVisibleItem + visibleThreshold));
*/

        if (!loading && (totalItemCount - visibleItemCount)
                <= (firstVisibleItem + visibleThreshold)) {

            Log.i(TAG, "Loading More");
            // End has been reached

            // Do something
            current_page++;

            onLoadMore(current_page);

            loading = true;
        }

    }

    public abstract void onLoadMore(int current_page);
}
