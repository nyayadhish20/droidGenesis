package com.nyayadhish.droidgenesis.lib.shrecycle;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nyayadhish.droidgenesis.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import androidx.annotation.LayoutRes;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

/**
 * Created by root on 27/12/16.
 */

public class ShrecylerViewAdapter extends RecyclerView.Adapter<ShrecylerViewAdapter.ViewHolder> {

    private static final String TAG = ShrecylerViewAdapter.class.getSimpleName();
    private
    @LayoutRes
    int mCustomLayoutResource;
    private Context mContext;
    private ShrecycleContract mContract;
    private List<ShrecyclerViewModel> mList = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private int highLightTextSize = -1;
    private boolean mShowDisplayOptions = true;
    private boolean mHighlightBlockOverride = false;

    @Deprecated/*Use Custom getters for initialization instead*/
    public ShrecylerViewAdapter(Context mContext, final ShrecycleContract mContract, List<? extends ShrecyclerViewModel> mList, RecyclerView mRecyclerView) {
        this.mContext = mContext;
        this.mContract = mContract;
        this.mList.addAll(mList);
        this.mRecyclerView = mRecyclerView;
        this.mRecyclerView.setAdapter(this);

        for (int i = 0; i < this.mList.size(); i++)
            notifyItemInserted(i);

    }

    public ShrecylerViewAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public ShrecylerViewAdapter(Context mContext, final ShrecycleContract mContract, List<? extends ShrecyclerViewModel> mList) {
        this.mContext = mContext;
        this.mContract = mContract;
        this.mList.addAll(mList);
    }


    public void enablePagination() {
        /*EndlessScrollListener mEndlessScrollListener = new EndlessScrollListener((LinearLayoutManager) mRecyclerView.getLayoutManager()) {
            @Override
            public void onLoadMore(int current_page) {
                mContract.onLoadMore(current_page);
            }
        };
        mRecyclerView.addOnScrollListener(mEndlessScrollListener);*/
    }

    public ShrecylerViewAdapter(Context mContext, ShrecycleContract mContract, List<? extends ShrecyclerViewModel> mList, RecyclerView mRecyclerView,
                                LAYOUTTYPE layoutType,
                                int numberOfColumns) {
        this.mContext = mContext;
        this.mContract = mContract;
        this.mList.addAll(mList);
        this.mRecyclerView = mRecyclerView;
        this.mRecyclerView.setAdapter(this);
        this.mLayoutManagerType = layoutType;
        this.setAsGridView(calculateNoOfColumns(numberOfColumns));
    }

    public ShrecylerViewAdapter(List<? extends ShrecyclerViewModel> mList, Context context, ShrecycleContract shrecycleContract, RecyclerView rec, LAYOUTTYPE dialog) {
        this.mContext = context;
        this.mContract = shrecycleContract;
        this.mList.addAll(mList);
        this.mRecyclerView = rec;
        this.mRecyclerView.setAdapter(this);
        this.mLayoutManagerType = dialog;
    }

    public ShrecylerViewAdapter(List<? extends ShrecyclerViewModel> mList, Context context, ShrecycleContract shrecycleContract, RecyclerView rec, int custom_layout_resource) {
        this.mContext = context;
        this.mContract = shrecycleContract;
        this.mList.addAll(mList);
        this.mRecyclerView = rec;
        this.mRecyclerView.setAdapter(this);
        this.mCustomLayoutResource = custom_layout_resource;
    }

    private void setAsGridView(int numberOfColumns) {
        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(mRecyclerView.getContext(), numberOfColumns);
        mRecyclerView.setLayoutManager(gridLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManagerType = LAYOUTTYPE.GRID;
    }

    private LAYOUTTYPE mLayoutManagerType = LAYOUTTYPE.LINEAR;


    public void onLoadingMore() {

    }

    public void onLoadedMore() {

    }

    public void onResume() {

    }

    public void onPause() {

    }

    public void onStop() {

    }

    public void setHasDisplayOptions(boolean displayNoOptions) {
        this.mShowDisplayOptions = displayNoOptions;
    }

    public void onDestroy() {
        if (mList != null) {
            mList.clear();
            notifyDataSetChanged();
            mList = null;
        }
    }

    public void setRecyclerView(RecyclerView recyclerView) {
        this.mRecyclerView = recyclerView;
        this.mRecyclerView.setAdapter(this);
    }

    public void setListener(ShrecycleContract listener) {
        this.mContract = listener;
    }

    public void setCustomListItem(int customListItem) {
        this.mCustomLayoutResource = customListItem;
    }

    public void disableHighlightOverride(boolean b) {
        this.mHighlightBlockOverride = true;
    }


    public enum LAYOUTTYPE {
        GRID, LINEAR, DIALOG
    }

    public void updateList(List<? extends ShrecyclerViewModel> newList) {
        if (mList != null) {
            this.mList.clear();
            this.mList.addAll(newList);

        }
        this.notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mCustomLayoutResource != 0)
            return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(mCustomLayoutResource, parent, false));

        switch (mLayoutManagerType) {
            case GRID:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.shrecyclerview_list_item_grid, parent, false));
            case LINEAR:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.shrecyclerview_list_item, parent, false));
            case DIALOG:
                return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.shrecyclerview_list_item_dialog, parent, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        ShrecyclerViewModel shrecycleItem = mList.get(position);

        if (holder.title != null)
            holder.title.setText(shrecycleItem.getShrecylerItemTitle());

        //Only For BottomSheet
        if (holder.cardView != null) {
            if (mList.get(position).getDisplayImages().length > 0 || mList.get(position).getDisplayDrawables().length > 0) {
                holder.bindImageViewForBottomSheet(position);
                holder.setIsRecyclable(false);
            }
        }

        if (holder.circleHighlightText != null && shrecycleItem.hasCircleTextHighlight()) {

            if (shrecycleItem.getShrecylerItemTitle() != null
                    && shrecycleItem.getShrecylerItemTitle().length() > 0) {

                String highLightText = "";
                Scanner s = new Scanner(shrecycleItem.getShrecylerItemTitle());
                while (s.hasNext()) {
                    if (highLightText.length() == 2)
                        break;
                    highLightText += s.next().charAt(0);
                }

                holder.circleHighlightText.setText(
                        highLightText.toUpperCase());

            } else
                holder.circleHighlightText.setVisibility(View.INVISIBLE);

        } else if (holder.circleHighlightText != null) {
            holder.circleHighlightText.setVisibility(View.GONE);
        }

        if (holder.highlight != null && !mHighlightBlockOverride) {

            if (shrecycleItem.getHighlightText() != null)
                holder.highlight.setText(" " + shrecycleItem.getHighlightText().toUpperCase() + " ");
            else
                holder.highlight.setVisibility(View.GONE);

            holder.highlight.setBackgroundColor(shrecycleItem.getHighLightColorBackground());

        }



  /*      if(this.highLightTextSize != -1)
            holder.highlight.setTextSize(highLightTextSize);*/
        if (holder.subtitles != null) {
            holder.subtitles.setText("");

            for (int i = 0; i < shrecycleItem.getShrecyclerItemSubtitles().length; i++) {
                holder.subtitles.setVisibility(View.VISIBLE);
                if (shrecycleItem.getShrecyclerItemSubtitles()[i] != null && shrecycleItem.getShrecyclerItemSubtitles()[i].trim().length() > 0)
                    holder.subtitles.append((i == 0 ? "" : "\n") + (shrecycleItem.getShrecyclerItemSubtitles()[i]));
            }

        }


        if (holder.options != null) {

            if (shrecycleItem.getDisplayOptions().length == 0) {
                holder.options.setVisibility(View.GONE);
            }

            holder.options.removeAllViews();

            for (int i = 0; i < shrecycleItem.getDisplayOptions().length; i++) {

                getShreCycleOptionsView(holder.options);

                final int finalI = i;
                ShrecyclerOptionModel shrecyclerOptionModel = shrecycleItem.getDisplayOptions()[i];
                LinearLayout linearLayout = holder.options.getChildAt(i).findViewById(R.id.optionitem);
                TextView textView = linearLayout.findViewById(R.id.textview);
                textView.setTextColor(Color.WHITE);
                textView.setBackgroundColor(mContext.getResources().getColor(R.color.primaryDarkColor));


                ImageView imageView = linearLayout.findViewById(R.id.imageview);
                imageView.setColorFilter(ContextCompat.getColor(mContext,
                        android.R.color.white));

                textView.setText(shrecyclerOptionModel.getTitle());
                imageView.setImageResource(shrecyclerOptionModel.getmOptionDrawableId());
                linearLayout.setBackgroundColor(mContext.getResources().getColor(R.color.primaryDarkColor));

                // linearLayout.setBackgroundColor(Color.WHITE);


                (holder.options.getChildAt(i)).setTag(i);

                (holder.options.getChildAt(i)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mContract.onShrecycleOptionItemClicked(finalI, position);
                    }
                });


            }
        }

        if (holder.cardView != null){
            if (mList.get(position).getDisplayImages().length > 0 || mList.get(position).getDisplayDrawables().length > 0) {
                holder.bindImageView(position);
                holder.setIsRecyclable(false);
            }
        }


        if (mLayoutManagerType == LAYOUTTYPE.GRID) {
            Log.i(TAG, "onBindViewHolder: Grid Layout Detected");
            if (mList.get(position).getDisplayImages().length > 0 || mList.get(position).getDisplayDrawables().length > 0) {
                holder.bindImageView(position);
                holder.setIsRecyclable(false);
            } else if (mLayoutManagerType == LAYOUTTYPE.DIALOG) {
                holder.bindDialogImageView();
                holder.setIsRecyclable(false);
            }
        }
    }

    private View getShreCycleOptionsView(ViewGroup parent) {
        return View.inflate(mContext, R.layout.shrecycle_option_item, parent);
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public ShrecyclerViewModel getItem(int adapterPosition) {
        return mList.get(adapterPosition);
    }


    public void addItem(ShrecyclerViewModel newItem) {
        mList.add(newItem);
        notifyDataSetChanged();
    }


    public List<ShrecyclerViewModel> getList() {
        return mList;
    }

    public void removeItem(int listPostion) {
        mList.remove(listPostion);
        this.notifyItemRemoved(listPostion);
    }

    public void setHighLightTextSize(int highLightTextSize) {
        this.highLightTextSize = highLightTextSize;
    }

    private int mLastClickedItemPosition = -121;

    public void removeLastClickedItem() {
        if (mLastClickedItemPosition != -121) {
            mList.remove(mLastClickedItemPosition);
            notifyItemRemoved(mLastClickedItemPosition);
        }
    }

    public void updateListClickedItem(ShrecyclerViewModel model) {
        Log.i(TAG, "Last Clicked Item Position " + mLastClickedItemPosition);
        if (mLastClickedItemPosition != -121) {
            if (model.wasDeleted()) {
                Log.i(TAG, "REMOVING ITEM FROM SHRECYCLER LIST posistion " + mLayoutManagerType);
                mList.remove(mLastClickedItemPosition);
                notifyItemRemoved(mLastClickedItemPosition);
                if (mLayoutManagerType == LAYOUTTYPE.GRID) {
                    notifyDataSetChanged();
                }
            } else if (model.wasEdited()) {
                Log.i(TAG, "UPDATING ITEM FROM SHRECYCLER LIST posistion " + mLayoutManagerType);
                mList.set(mLastClickedItemPosition, model);
                notifyItemChanged(mLastClickedItemPosition);
            } else {
                Log.i(TAG, "lol wut ?");
            }
        }
    }

    public void clear() {
        if (mList != null) {
            mList.clear();
        }
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView cardView;
        private View rootView;
        private TextView title;
        private TextView highlight;
        private TextView subtitles;
        private LinearLayout options;
        private ImageView dialogImageView;
        private TextView circleHighlightText;

        public ViewHolder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            highlight = itemView.findViewById(R.id.highlight);
            subtitles = itemView.findViewById(R.id.linearlayoutsubtitles);
            options = itemView.findViewById(R.id.optionsview);
            rootView = itemView.findViewById(R.id.rootview);

            if (highlight != null)
                highlight.setOnClickListener(this);

            if (rootView != null)
                rootView.setOnClickListener(this);

            cardView = itemView.findViewById(R.id.card_view);
            dialogImageView = itemView.findViewById(R.id.imageview);
            circleHighlightText = itemView.findViewById(R.id.circlehighlighttext);
        }

        private void bindDialogImageView() {
            Log.i(TAG, "bindDialogImageView: In Bind Dialog");
            if (mLayoutManagerType == LAYOUTTYPE.DIALOG && getAdapterPosition() != -1) {
                if (mList.get(getAdapterPosition()).getDisplayImages().length > 0) {

                    if (mList.get(getAdapterPosition()).
                            getDisplayImages().length > 0) {

                        if (dialogImageView.getDrawable() == null)
                            Picasso.get().
                                    load(mList.get(getAdapterPosition()).
                                            getDisplayImages()[0]).
                                    into(dialogImageView);

                    } else if(mList.get(getAdapterPosition()).getDisplayDrawables().length > 0 ) {
                        Log.i(TAG, "bindDialogImageView: image count" + mList.get(getAdapterPosition()).getDisplayDrawables().length );
                        if (dialogImageView.getDrawable() == null)
                            dialogImageView.setImageResource(mList.get(getAdapterPosition()).getDisplayDrawables()[0]);
                    }
                    else
                        dialogImageView.setVisibility(View.GONE);

                } else {
                    cardView.setVisibility(View.GONE);
                }
            }

        }

        public void bindImageViewForBottomSheet(int position) {

            if (mLayoutManagerType == LAYOUTTYPE.LINEAR){
                if (mList.get(getAdapterPosition()).getDisplayDrawables().length > 0){
                    if (cardView.getDrawable() == null) {
                        cardView.setImageDrawable(mContext.getResources().getDrawable(mList.get(getAdapterPosition()).getDisplayDrawables()[0]));

                    }
                }
            }
        }
        private void bindImageView(int i) {
            Log.i(TAG, "bindImageView: inBind ImageView");
            if (mLayoutManagerType == LAYOUTTYPE.GRID && getAdapterPosition() != -1) {
                if (mList.get(getAdapterPosition()).getDisplayImages().length > 0) {

                    if (cardView.getDrawable() == null)
                        Picasso.get().
                                load(mList.get(getAdapterPosition()).
                                        getDisplayImages()[0]).
                                into(cardView, new Callback() {
                                    @Override
                                    public void onSuccess() {
                                        highlight.setAlpha(0.5f);
                                        title.setTextColor(Color.WHITE);
                                    }

                                    @Override
                                    public void onError(Exception e) {

                                    }

                                });

                } else if (mList.get(getAdapterPosition()).getDisplayDrawables().length > 0) {
                    Log.i(TAG, "bindImageView: image count" + mList.get(getAdapterPosition()).getDisplayDrawables()[0]);
                    if (cardView.getDrawable() == null) {
                        cardView.setImageDrawable(mContext.getResources().getDrawable(mList.get(getAdapterPosition()).getDisplayDrawables()[0]));
                    } else {
                        cardView.setVisibility(View.GONE);
                    }
                }

            }else{
                if (cardView.getDrawable() == null)
                    Picasso.get().
                            load(mList.get(getAdapterPosition()).
                                    getDisplayImages()[0]).
                            error(mContext.getResources().getDrawable(R.drawable.ic_person_black_24dp))
                            .into(cardView);


            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rootview:
                    if (options != null && options.getChildCount() > 0 && mShowDisplayOptions) {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                            TransitionManager.beginDelayedTransition(mRecyclerView);
                        }

                        if (options.getVisibility() == View.VISIBLE) {

                            if (subtitles != null)
                                subtitles.setMaxLines(subtitles.getLineCount());

                            options.setVisibility(View.GONE);
                            if (highlight != null)
                                highlight.setVisibility(View.VISIBLE);


                        } else {

                            if (subtitles != null)
                                subtitles.setMaxLines(subtitles.getLineCount());

                            options.setVisibility(View.VISIBLE);
                            if (highlight != null)
                                highlight.setVisibility(View.GONE);


                        }
                    } else {
                        if (mList != null && !mList.isEmpty()) {
                            mLastClickedItemPosition = getAdapterPosition();
                            mContract.onShrecycleItemClicked(getAdapterPosition(), mList.get(getAdapterPosition()));
                        }
                    }
                    break;
                case R.id.highlight:
                    mContract.onShrecycleItemHighLightClicked(mList.get(getAdapterPosition()).getHighlightText(), getAdapterPosition());
                    break;
            }
        }


    }

    public int calculateNoOfColumns(int numberOfColumns) {
        if (mContext!=null) {
            DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
            /*if (DisplayUtils.isScreenLarge(mContext)) {
                float dpWidth = 0f;
                if (DisplayUtils.isScreenLarge(mContext)) {
                    dpWidth = (displayMetrics.widthPixels - mContext.getResources().getDimension(R.dimen.drawerwidthforlargelayout)) / displayMetrics.density;
                } else
                    dpWidth = displayMetrics.widthPixels / displayMetrics.density;

                int noOfColumns = Math.round(dpWidth / mContext.getResources().getDimension(R.dimen.shrecycler_grid_item_wh));
                Log.i(TAG, dpWidth + "<= DP WIDTH\n"
                        + mContext.getResources().getDimension(R.dimen.shrecycler_grid_item_wh) + " < = "
                        + " Shrecycle Grid Item Width Height\n" + dpWidth / mContext.getResources().getDimension(R.dimen.shrecycler_grid_item_wh) + " < = db/shrewh ratio");

                return noOfColumns;
            } else {*/
                return numberOfColumns;
        }

        return numberOfColumns;

    }
}


