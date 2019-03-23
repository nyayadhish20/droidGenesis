package com.nyayadhish.droidgenesis.lib;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.nyayadhish.droidgenesis.BuildConfig;
import com.nyayadhish.droidgenesis.R;
import com.nyayadhish.droidgenesis.lib.Utilities.ColorCodes;
import com.nyayadhish.droidgenesis.lib.dependancyinjections.APIComponent;

import androidx.annotation.DrawableRes;
import androidx.annotation.MenuRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment implements BaseView, Toolbar.OnMenuItemClickListener {
    private static final String TAG = BaseFragment.class.getClass().getSimpleName();
    private Unbinder unbinder;
    private Toolbar toolbar;
    private View rootView;
    private View bottomSheetView;
    private Toolbar mFragmentToolbar;
    private RecyclerView recyclerView;
    private TextView mMessage;
    private BaseActivity mBasaActivity;
    private static APIComponent mApiComponent;
    private boolean mWasViewDestroyed = true;
    private ImageView mMessageImage;
    private ProgressBar mProgress;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public void hideSnackBar() {
        getBaseActivity().hideSnackBar();
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //setFloatingActionButtonIcon(R.drawable.fab_add);
    }


    protected void showFloatingActionButton() {
        if (getBaseActivity() != null)
            getBaseActivity().showFloatingActionButton();
        /*if(mFloatingActionButton != null && mFloatingActionButton.isHidden())
            mFloatingActionButton.show(true);*/
    }

    @Override
    public void onUnhandledErrorCode(String message) {
        showSnackBar(message);
    }

    @Override
    public APIComponent getAPIComponent() {
        return mApiComponent;
    }

    protected void hideFloatingActionButton() {
        if (getBaseActivity() != null)
            getBaseActivity().hideFloatingActionButton();
        /*if(mFloatingActionButton!=null && !mFloatingActionButton.isHidden())
            mFloatingActionButton.hide(true);*/
    }

    protected void addFragment(BaseFragment fragment, Bundle bundle) {
        if (getBaseActivity() != null)
            getBaseActivity().addFragment(fragment, bundle);
    }

    protected void addFragmentFromDrawer(BaseFragment fragment, Bundle bundle) {
        if (getBaseActivity() != null)
            getBaseActivity().addFragmentFromDrawer(fragment, bundle);
    }

    public void onPermissionDenied() {
        //showNoPermissionsUi("");
    }

    public void showDebugToast(String message) {
        if (getBaseActivity() != null)
            getBaseActivity().showDebugToast(message);
    }

    public void showMaterialProgress(@NonNull String message) {
        if (mProgress != null) {
            mProgress.setVisibility(View.VISIBLE);
        } else {
            if (getBaseActivity() != null)
                getBaseActivity().showMaterialProgress(message);
        }
    }

    public void hideMaterialProgress() {
        if (mProgress != null) {
            mProgress.setVisibility(View.GONE);
        } else {
            if (getBaseActivity() != null)
                getBaseActivity().hideMaterialProgress();
        }
    }


    public void showErrorOnView(EditText editText, String message) {
        editText.requestFocus();
        editText.setError(message);
    }

    /*@Override
    public void onNotSubscribed() {
        hideMaterialProgress();
        if (mMessage != null)
            setMessage(getString(R.string.subscriptionerror));
        else
            showToast(getString(R.string.subscriptionerror));

    }
*/
    public void setErrorOnEmptyViews(EditText editText, String error) {
        if (mBasaActivity != null)
            mBasaActivity.setErrorOnEmptyViews(editText, error);
    }



    @Override
    public void onUnHandledErrorCodeDebug(int errorCode) {
        if (BuildConfig.DEBUG)
            showDebugToast("On Unhandled Error Code " + errorCode);
    }


    /*public boolean updateCard(String TAG, String updatedSubtitle) {
        LinearLayout mLinear = (LinearLayout) getView().findViewById(R.id.rootview);
        for (int i = 0; i < mLinear.getChildCount(); i++) {
            if (mLinear.getChildAt(i).getTag() != null && mLinear.getChildAt(i).getTag().equals(TAG)) {
                TextView subtitle = (TextView) mLinear.getChildAt(i).findViewById(R.id.subtitle);
                subtitle.setText(updatedSubtitle);
                return true;
            }
        }

        return false;
    }*/

    /*public void logEvent(String eventName){
        if (mBasaActivity!=null)
            mBasaActivity.logEvent(eventName);

    }
*/


    protected int getToolbarIcon() {
        return R.drawable.ic_arrow_back_black_24dp;

    }

    public void setHasActivityToolbar(boolean value) {


        /*if (getBaseActivity() != null) {
            getBaseActivity().getToolbar().setVisibility(value ? View.VISIBLE : View.GONE);
        }

        getBaseActivity().getToolbar().setNavigationIcon(null);

        getBaseActivity().getToolbar().setPadding(0, 0, 0, 0);
        if (mFragmentToolbar != null) {
            mFragmentToolbar.setNavigationIcon(null);
            mFragmentToolbar.setTitleTextColor(ColorCodes.COLOR_TOOLBAR_SUBTITLE);
            mFragmentToolbar.setSubtitleTextColor(ColorCodes.COLOR_TOOLBAR_SUBTITLE);

        }//mBaseActivity.setToolbarBackgroundColor(getResources().getColor(R.color.toolBarColorTwoPen));
        //mBaseActivity.setToolbarTitleTextColor(getResources().getColo);
        else {*/
            if (getBaseActivity() != null) {
                getBaseActivity().getToolbar().setVisibility(value ? View.VISIBLE : View.GONE);
            }
            if (mFragmentToolbar != null) {
                mFragmentToolbar.setNavigationIcon(getToolbarIcon());
                mFragmentToolbar.setTitleTextColor(ColorCodes.COLOR_TOOLBAR_SUBTITLE);
                mFragmentToolbar.setSubtitleTextColor(ColorCodes.COLOR_TOOLBAR_SUBTITLE);
                mFragmentToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onToolbarIconClicked();
                    }
                });
            }

        }


    /*protected View getCard(String title, String subtitle, final View.OnClickListener onClickListener, String TAG) {
        View cardView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).
                inflate(R.layout.simple_card,
                        (ViewGroup) getView().findViewById(R.id.cards),
                        false);
        ((TextView) cardView.findViewById(R.id.title)).setText(title);
        ((TextView) cardView.findViewById(R.id.subtitle)).setText(subtitle);
        ((TextView) cardView.findViewById(R.id.subtitle)).setCompoundDrawables(null, null,
                ContextCompat.getDrawable(getActivity(), R.drawable.ic_edit_white_18dp), null);
        if (subtitle != null && subtitle.trim().length() > 0)
            ((LinearLayout) getView().findViewById(R.id.cards)).addView(cardView);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v);
            }
        });
        cardView.setTag(TAG);
        return cardView;
    }*/

    /*public boolean isCardsListPopulated() {
        return ((LinearLayout) getView().findViewById(R.id.cards)).getChildCount() > 0;
    }

    protected View getCard(String title, String subtitle, final View.OnClickListener onClickListener) {
        View cardView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).
                inflate(R.layout.simple_card,
                        (ViewGroup) getView().findViewById(R.id.rootview),
                        false);
        ((TextView) cardView.findViewById(R.id.title)).setText(title);
        ((TextView) cardView.findViewById(R.id.subtitle)).setText(subtitle);
        ((TextView) cardView.findViewById(R.id.subtitle)).setCompoundDrawables(null, null,
                ContextCompat.getDrawable(getActivity(), R.drawable.ic_edit_white_18dp), null);
        if (subtitle != null && subtitle.trim().length() > 0)
            ((LinearLayout) getView().findViewById(R.id.cards)).addView(cardView);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.onClick(v);
            }
        });
        return cardView;
    }

    protected View getCard(String title, String subtitle) {
        View cardView;
        if (getView() != null) {
            cardView = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).
                    inflate(R.layout.simple_card,
                            (ViewGroup) getView().findViewById(R.id.rootview),
                            false);
            ((TextView) cardView.findViewById(R.id.title)).setText(title.toUpperCase());
            ((TextView) cardView.findViewById(R.id.subtitle)).setText(subtitle);
            if (subtitle != null && subtitle.trim().length() > 0)
                ((LinearLayout) getView().findViewById(R.id.cards)).addView(cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            return cardView;
        }
        return null;
    }
*/

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setRetainInstance(true);
        View returnView = inflater.inflate(getLayout(), container, false);
        mFragmentToolbar = (Toolbar) returnView.findViewById(R.id.toolbar);
        checkOptionsMenu();
        return returnView;
    }


    @Override
    public void onNoNetworkFoud() {
        mBasaActivity.onNoNetworkFoud();
    }

    @Override
    public void onStart() {
        super.onStart();
        unbinder = ButterKnife.bind(this, getView());
        //mProgress = (ProgressBar) getView().findViewById(R.id.progressview);
        if ((BuildConfig.DEBUG && isDetached()) || (getActivity() == null)) {
            Log.i(TAG, getClass().getSimpleName() + " is Detached from Activity");
        }

        hideActivityFloatingActionButton();
        mApiComponent = getBaseActivity().getAPIComponent();


        toolbar = (Toolbar) getView().findViewById(R.id.toolbar);
        rootView = getView().findViewById(R.id.rootview);
        //ToDO message vIew
        //mMessage = (TextView) getView().findViewById(R.id.message);

        //mMessageImage = (ImageView) getView().findViewById(R.id.messageimage);

        if (mMessageImage != null)
            mMessageImage.setImageDrawable(null);

        //todo empty List Message
        /*if (mMessage == null)
            mMessage = (TextView) getView().findViewById(R.id.emptylistmessage);
*/
        if (mMessage != null)
            mMessage.setText("");

        recyclerView = (RecyclerView) getView().findViewById(R.id.recyclerview);
        if (recyclerView != null)
            setupRecyclerView(recyclerView);

        //todo BottomSheet
        /*bottomSheetView = getView().findViewById(R.id.bottom_sheet);
        if (bottomSheetView != null)
            getBaseActivity().prepareBottomSheet(bottomSheetView);
*/
        /*if (getBaseActivity().getFloatingActionButton() != null)
            getBaseActivity().getFloatingActionButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onFloatingActionButtonClicked();
                }
            });*/

        /*mSwipeRefreshLayout = (SwipeRefreshLayout) getActivity().findViewById(R.id.swiperefresh);
        if (mSwipeRefreshLayout != null)
            prepareSwipeRefreshLayout(mSwipeRefreshLayout);
*/
        if (mWasViewDestroyed) {
            setPresenter();
            mWasViewDestroyed = false;
            onMakeServerCalls();
        }

    }

    private void prepareSwipeRefreshLayout(@NonNull SwipeRefreshLayout swipeRefreshLayout) {
        swipeRefreshLayout.setColorSchemeColors(Color.RED, Color.DKGRAY, Color.BLUE, Color.GREEN, Color.MAGENTA);
        swipeRefreshLayout.setSize(SwipeRefreshLayout.LARGE);
        if (rootView != null) {
            swipeRefreshLayout.setDistanceToTriggerSync(rootView.getHeight() / 2);
            swipeRefreshLayout.setProgressViewOffset(true, rootView.getHeight(), 0);
        }
        swipeRefreshLayout.setNestedScrollingEnabled(true);
        swipeRefreshLayout.setEnabled(false);
    }

    public void enableSwipeRefreshLayout() {
        mSwipeRefreshLayout.setEnabled(true);
    }

    public SwipeRefreshLayout getSwipeRefreshLayout() {
        return mSwipeRefreshLayout;
    }

    public void showSwipeRefreshLoading() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    public void hideSwipeRefresh() {
        mSwipeRefreshLayout.setRefreshing(false);
    }


    public abstract void onMakeServerCalls();


    @Override
    public void onPause() {
        if (getPresenter() != null)
            getPresenter().onPause();
        hideSnackBar();
        super.onPause();
    }


    @Override
    public void onDestroy() {
/*
        if(getListAdapter() != null)
            getListAdapter().onDestroy();
*/

        if (unbinder != null)
            unbinder.unbind();

        mWasViewDestroyed = true;
        super.onDestroy();
    }

    /*@Override
    public ShrecylerViewAdapter getListAdapter() {
        return null;
    }
*/

    private void checkOptionsMenu() {
        clearBaseOptionsMenu();
        if (getHasOptionsMenu()) {
            setHasOptionsMenu(getHasOptionsMenu());
            Toolbar toolBar = getBaseActivity().getToolbar();
            toolBar.inflateMenu(getMenuLayoutResource());
            toolBar.setOnMenuItemClickListener(this);

            if (mFragmentToolbar != null) {
                mFragmentToolbar.inflateMenu(getMenuLayoutResource());
                mFragmentToolbar.setOnMenuItemClickListener(this);
            }
        }
    }

    public
    @MenuRes
    int getMenuLayoutResource() {
        return 0;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return false;
    }

    public boolean getHasOptionsMenu() {
        return false;
    }

    public void clearBaseOptionsMenu() {
        if (getActivity() instanceof BaseActivityWithToolbar) {
            BaseActivityWithToolbar baseActivityWithToolbar = (BaseActivityWithToolbar) getActivity();
            if (baseActivityWithToolbar.getToolbar() != null
                    && baseActivityWithToolbar.getToolbar().getMenu() != null)
                baseActivityWithToolbar.getToolbar().getMenu().clear();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i(TAG, "Fragment is Detached From Activity");
    }

    public void onToolbarIconClicked() {
        getActivity().finish();
    }

    public void setMessage(String text) {
        if (mMessage != null)
            mMessage.setText(text);
        else
            showDebugToast("Message View Not Set " + getClass().getSimpleName());
    }

    public void setMessage(String text, @DrawableRes int drawableId) {
        if (mMessageImage != null)
            mMessageImage.setImageResource(drawableId);

        if (mMessage != null)
            mMessage.setText(text);
        else
            showDebugToast("Message View Not Set " + getClass().getSimpleName());
    }

   /* public void showNoPermissionsUi(String permissionName) {

        if (mMessage != null)
            setMessage("Permission Denied");
        else
            showToast("Permission Denied");
        hideMaterialProgress();
        hideSnackBar();
        if(getView() != null){
            SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) getView().findViewById(R.id.swiperefresh);
            if (swipeRefreshLayout != null) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }

    }*/

    @Override
    public void onUnauthoriseAccess() {
        mBasaActivity.onUnauthoriseAccess();
    }

    public void showToast(String message) {
        if (getActivity() != null) {
            Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
        }
    }

    public void setToolBarTitle(String title) {
        if (mFragmentToolbar != null)
            mFragmentToolbar.setTitle(title);
        else {
            ((BaseActivityWithToolbar) getActivity()).setToolbarTitle(title);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mBasaActivity = (BaseActivity) context;
    }


    public void setToolbarSubTitle(String title) {
        if (mFragmentToolbar != null)
            mFragmentToolbar.setSubtitle(title);
        else {
            ((BaseActivityWithToolbar) getActivity()).setToolbarSubTitle(title);
        }
    }

    protected void setOverflowIconOnFragmentToolbar(int ic_menu_more, int primary_dark) {
        Drawable drawable = getResources().getDrawable(ic_menu_more);
        Drawable overflowIconCompat = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(overflowIconCompat, primary_dark);
        if (mFragmentToolbar != null)
            mFragmentToolbar.setOverflowIcon(overflowIconCompat);
        else
            ((BaseActivityWithToolbar) getActivity()).getToolbar().setOverflowIcon(overflowIconCompat);
    }

    protected abstract BasePresenter getPresenter();

    protected abstract void setPresenter();

    protected abstract int getLayout();

    public void showGoToAppSettingsPageDialog(@NonNull String message) {
        new MaterialDialog.Builder(getActivity()).
                title("Open Settings").content(message).positiveText("SETTINGS").onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                startInstalledAppDetailsActivity(getActivity().getApplicationContext());
            }
        }).negativeText("Cancel").onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                showSnackBar(";(");
            }
        }).build().show();
    }

    public boolean expandBottomSheet() {
        return getBaseActivity() != null && getBaseActivity().expandBottomSheet();
    }

    public boolean collapseBottomSheet() {
        return getBaseActivity() != null && getBaseActivity().collapseBottomSheet();
    }

    private void startInstalledAppDetailsActivity(@NonNull Context c) {
        Intent i = new Intent();
        i.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        i.setData(Uri.parse("package:" + c.getPackageName()));
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        i.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        c.startActivity(i);
    }

    public void showSnackBar(String message) {
        getBaseActivity().showSnackBar(message);
    }

    public void showSnackBarWitAction(String message, String button, final View.OnClickListener callBack) {
        getBaseActivity().showSnackBarWitAction(message, button, callBack);
    }

    public void setupRecyclerView(RecyclerView recyclerView) {
        if (recyclerView.getLayoutManager() == null)
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }


    /*public void removeAllCards() {
        if ((getView() != null))
            ((LinearLayout) getView().findViewById(R.id.cards)).removeAllViews();
    }*/


    public void hideActivityFloatingActionButton() {
        if (getBaseActivity() != null)
            getBaseActivity().hideFloatingActionButton();

    }

    public void onFloatingActionButtonClicked() {
        if (getBaseActivity() != null)
            getBaseActivity().onFloatingActionButtonClicked();
        else
            showDebugToast("Base Activity is Null");
    }

    public
    @DrawableRes
    int getFloatingActionButtonIcon() {
        return 0;
    }

    protected void setFloatingActionButtonIcon(@DrawableRes int floatingActionButtonIcon) {
        if (getBaseActivity() != null)
            getBaseActivity().setFloatingActionButtonIcon(floatingActionButtonIcon);
        else
            showDebugToast("FAIL Setting Floating action Button while Base Activity is NULL");
        /*if(mFloatingActionButton != null && floatingActionButtonIcon != 0)
            mFloatingActionButton.setImageResource(floatingActionButtonIcon);*/
    }


    /*
     * Strictly call this method only when adding fragment to a viewpager
     * */
    public void registerFloatingActionButton() {
        if (getBaseActivity() == null)
            Log.i(TAG, "BaseActivity is null");

        Log.i(TAG, getHasFloatingActionButtonInViewPager() + " <= has fab in vp");

        if (getHasFloatingActionButtonInViewPager()) {
            showFloatingActionButton();
            if (getBaseActivity() != null && getBaseActivity().getFloatingActionButton() != null)
                getBaseActivity().getFloatingActionButton().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        onFloatingActionButtonClicked();
                    }
                });
        } else {
            hideActivityFloatingActionButton();
        }
    }

    public boolean getHasFloatingActionButtonInViewPager() {
        return false;
    }

    public BaseActivity getBaseActivity() {
        if (mBasaActivity == null) {
            if (getAPIComponent() != null)
                mBasaActivity = getAPIComponent().getApp().getCurrentForegroundActivity();
            else
                Log.i(TAG, "getBaseActivity: getApiComponent() is null");
        }
        return mBasaActivity;
    }

}


