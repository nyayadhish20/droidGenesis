package com.nyayadhish.droidgenesis.lib.custombottomsheet;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.nyayadhish.droidgenesis.R;

import java.util.ArrayList;

import androidx.annotation.DrawableRes;

public class CustomBottomSheet {
    private static final String TAG = CustomBottomSheet.class.getSimpleName();
    private Context mContext;
    private ArrayList<Item> mList = new ArrayList<>();
    private CustomBottomSheetListener mListener;
    private String mViewType = VIEWTYPE.LIST;
    private String bottomSheetTitle = "";

    public CustomBottomSheet(Context mContext) {
        this.mContext = mContext;
    }

    public CustomBottomSheet(Context context, String bottomSheetTitle) {
        this.mContext = context;
        this.bottomSheetTitle = bottomSheetTitle;
    }

    public void hide() {
        bottomSheetDialog.dismiss();
    }

    public interface CustomBottomSheetListener {
        void onItemClicked(int position);
    }

    public interface VIEWTYPE {
        String LIST = "00";
        String GRID = "11";
    }

    public void setViewType(String viewType) {
        this.mViewType = viewType;
    }

    public void show() {

        if (mViewType.equals(VIEWTYPE.LIST)) {
            View bottomSheetView = View.inflate(mContext, R.layout.textview_bottomsheet_title, null);
            LinearLayout bottomSheetParent = (LinearLayout) bottomSheetView.findViewById(R.id.bottomsheet_parent);
            TextView textView = (TextView) bottomSheetParent.findViewById(R.id.bottomsheet_title);
            textView.setText(bottomSheetTitle);

            LinearLayout bottomSheetItems = (LinearLayout) bottomSheetParent.findViewById(R.id.bottomsheet_items);
            bottomSheetItems.removeAllViews();

            if (mList != null && mList.size() > 0)
                for (int i =0; i<mList.size(); i++) {
                    View itemView = View.inflate(mContext, R.layout.bottomsheet_item_layout, null);
                    LinearLayout mLinear = (LinearLayout) itemView.findViewById(R.id.rootview);
                    TextView itemTitle = (TextView) mLinear.findViewById(R.id.title);
                    ImageView itemImage = (ImageView) mLinear.findViewById(R.id.item_image);
                    itemTitle.setText(mList.get(i).getLabel());
                    itemImage.setImageResource(mList.get(i).getImageUrl());
                    bottomSheetItems.addView(itemView);
                    final int finalI = i;
                    itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mListener.onItemClicked(finalI);
                        }
                    });
                }

            bottomSheetDialog = new BottomSheetDialog(mContext);
            bottomSheetDialog.setContentView(bottomSheetParent);
            bottomSheetDialog.setTitle("Bottom Sheet Title");
            bottomSheetDialog.show();
        } else {
            View bottomSheetView = View.inflate(mContext, R.layout.textview_bottomsheet_title, null);
            LinearLayout bottomSheetParent = (LinearLayout) bottomSheetView.findViewById(R.id.bottomsheet_parent);
            TextView textView = (TextView) bottomSheetParent.findViewById(R.id.bottomsheet_title);
            LinearLayout bottomSheetItems = (LinearLayout) bottomSheetParent.findViewById(R.id.bottomsheet_items);
            textView.setText(bottomSheetTitle);


            bottomSheetDialog = new BottomSheetDialog(mContext);
            bottomSheetDialog.setContentView(bottomSheetParent);
            bottomSheetDialog.setTitle("Select Category");
            bottomSheetDialog.show();
        }
    }

    BottomSheetDialog bottomSheetDialog;


//    public void setList(String[] list) {
//        this.mList = list;
//    }

    class Item {
        int imageUrl;
        String label;

        public Item(@DrawableRes int imageUrl, String label) {
            this.imageUrl = imageUrl;
            this.label = label;
        }

        public Item(String label) {
            this.label = label;
        }

        public int getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(int imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public void addGridItem(String label, int imageUrl) {
        this.mList.add(new Item(imageUrl, label));
    }


    public void addGridItem(String label) {
        mList.add(new Item(label));
    }

    public void setListener(CustomBottomSheetListener listener) {
        this.mListener = listener;
    }

    class BottomSheetItem {

        String title;
        String subtitle;
        int imageUrl;

        public BottomSheetItem(Item title) {
            this.title = title.label;
            this.imageUrl = title.imageUrl;
            Log.i(TAG, "BottomSheetItem: Image Url = " + imageUrl);
        }


    }
}