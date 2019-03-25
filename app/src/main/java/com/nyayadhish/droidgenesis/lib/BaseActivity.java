package com.nyayadhish.droidgenesis.lib;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.nyayadhish.droidgenesis.BuildConfig;
import com.nyayadhish.droidgenesis.R;
import com.nyayadhish.droidgenesis.lib.dependancyinjections.APIComponent;

import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity implements BaseView {

    private final String TAG = BaseActivity.class.getSimpleName();
    private Unbinder unbinder;
    private APIComponent apiComponent;
    private View rootView;
    private View bottomSheetView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;
    private TextView messageTextView;
    private ImageView mMessageImage;
    private Toolbar toolbar;
    private MaterialDialog mFileDownloadProgressBar;
    private FloatingActionButton mFloatingActionButton;
    private BottomSheetBehavior mBottomSheetBehaviour;
    private Snackbar mSnackbar;
    private MaterialDialog materialProgressBar;


    @Override
    public void onUnauthoriseAccess() {

    }

    @Override
    public void onNoNetworkFoud() {
        showSnackBar("Network Unreachable");
    }

    @Nullable
    private String mSpinnerSetId;

    @Override
    public APIComponent getAPIComponent() {
        return apiComponent;
    }

    @Nullable
    private String mSpinnerSetName;
    private String[] helperItems =
            new String[]
                    {"share access token",
                            "share url",
                            "share json response",
                            "share url + jsonresponse + accesstoken",
                            "notification testing panel",
                            "clear appdata",
                            "clear doctor instance",
                            "Share GCM id",
                    };
    //private IdlingResource idlingResource;

    public int getColour(@ColorRes int colorid) {
        return getResources().getColor(colorid);
    }

    public void openFileInWebView(String url) {
        WebView mWebView = new WebView(this);
        mWebView.loadUrl("https://docs.google.com/gview?embedded=true&url=" + url);
        setContentView(mWebView);
    }

    protected void showIndefiniteSnackBar(String message) {
        if (rootView != null) {
            mSnackbar = Snackbar
                    .make(rootView, message, Snackbar.LENGTH_INDEFINITE);
            mSnackbar.show();
        } else {
            showDebugToast("Root View not Set " + getClass().getSimpleName());

        }

    }

    public void addFragment(BaseFragment fragment, @Nullable Bundle extras) throws IllegalStateException {
        if (extras != null)
            fragment.setArguments(extras);

        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(R.id.container, fragment)
                .commit();

        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.rootview);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }*/
    }

    public void addFragmentFromDrawer(BaseFragment fragment, @Nullable Bundle extras) throws IllegalStateException {
        if (extras != null)
            fragment.setArguments(extras);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack(null)
                .commit();

        /*DrawerLayout drawer = (DrawerLayout) findViewById(R.id.rootview);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }*/
    }


    public void onPermissionDenied() {
        //ActivityUtils.hideKeyboard(this);
        showNoPermissionsUi("");
    }

    public void showSoftKeyBoard(View view){
        if(view.requestFocus()){
            InputMethodManager imm =(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT);
        }
    }
    public void showDebugToast(String message) {
        if (BuildConfig.DEBUG) {
//            Toast.makeText(this, getClass().getSimpleName() + "  " + message, Toast.LENGTH_LONG).show();
       Toast.makeText(this,  message, Toast.LENGTH_LONG).show();

            Log.i("DEBUG", message);
        }
    }

    public void showErrorOnView(EditText editText, String message) {
        editText.requestFocus();
        editText.setError(message);
    }

    private Dialog mLoadingDialog;
    private View mLoadingView;

    @Override
    public void showMaterialProgress(@NonNull String message) {

        //todo without lottie

        if (mSwipeRefreshLayout != null) {
            showSwipeRefreshLoading();
            return;
        }

        if (message.trim().length() == 0)
            message = "Loading";

        if (materialProgressBar == null) {

            materialProgressBar = new MaterialDialog.
                    Builder(this).
                    cancelable(false).
                    progress(true, 101).
                    content(message).
                    build();


            materialProgressBar.setOnKeyListener((arg0, keyCode, event) -> {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    hideMaterialProgress();
                    finish();
                }
                return true;
            });


        }
        if (materialProgressBar.isShowing())
            materialProgressBar.dismiss();

        materialProgressBar.setContent(message);
        materialProgressBar.show();


        //todo With lottie

        /*if (mLoadingDialog == null || mLoadingView == null) {
            mLoadingDialog = new Dialog(this);
            mLoadingDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            mLoadingView = ((LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.loadingview, null, false);
            ((LottieAnimationView) mLoadingView).setScale(0.5f);
            mLoadingDialog.setContentView(mLoadingView);
            mLoadingDialog.setCancelable(false);


            mLoadingDialog.setOnKeyListener((arg0, keyCode, event) -> {
                // TODO Auto-generated method stub
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    mLoadingDialog.dismiss();
                    finish();
                }
                return true;
            });


        }
        mLoadingDialog.setCancelable(false);
        mLoadingDialog.show();
*/


    }

    public void hideMaterialProgress() {
        if (mSwipeRefreshLayout != null) {
            hideSwipeRefresh();
            return;
        }

        if (materialProgressBar != null)
            materialProgressBar.dismiss();

        if (mLoadingDialog != null)
            mLoadingDialog.dismiss();
    }


    @Override
    public void onUnHandledErrorCodeDebug(int errorCode) {
        if (BuildConfig.DEBUG)
            showDebugToast(errorCode + " Not Handled ");
    }

    @Override
    public void onUnhandledErrorCode(String message) {
        showSnackBar(message);
    }


    public void hideFloatingActionButton() {
        /*if (mFloatingActionButton != null && !mFloatingActionButton.isHidden())
            mFloatingActionButton.hide(true);*/
    }

    public void showFloatingActionButton() {
        /*if (mFloatingActionButton != null && mFloatingActionButton.isHidden())
            mFloatingActionButton.show(true);*/
    }

    protected void shareDebugText(String textToShare) {
        if (BuildConfig.DEBUG) {
            shareDebugSocial(textToShare);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate: BaseActivity");
        if (getLayout() != 0) {
            setContentView(getLayout());
            unbinder = ButterKnife.bind(this);
        } else
            showDebugToast("Get Layout Not Set");

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        rootView = findViewById(R.id.rootview);
        if (rootView == null)
            showDebugToast("Root View Not Set");

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        if (recyclerView != null)
            setupRecyclerView(recyclerView);

        //mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        if (mSwipeRefreshLayout != null)
            prepareSwipeRefreshLayout(mSwipeRefreshLayout);

        /*bottomSheetView = findViewById(R.id.bottom_sheet);
        if (bottomSheetView != null)
            prepareBottomSheet(bottomSheetView);
*/
        apiComponent = ((AppDroidGenesis) getApplication()).getAPIComponent();

        setPresenter();

        checkForIncomingSnacks();

        //messageTextView = (TextView) findViewById(R.id.message);

        //mFloatingActionButton = (FloatingActionButton) findViewById(R.id.floatingactionbutton);

        if (mFloatingActionButton != null) {
            mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onFloatingActionButtonClicked();
                }
            });
        }

        if (BuildConfig.DEBUG)
            setupDebugHelper();


        loadPage();
        //mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (getPresenter() != null)
            getPresenter().onStop();

    }


    @Override
    protected void onDestroy() {

        hideMaterialProgress();

        if (getPresenter() != null)
            getPresenter().onDestroy();


        if (unbinder != null)
            unbinder.unbind();

        super.onDestroy();
    }

    protected void onFailedToLoadPage() {
        hideMaterialProgress();
        showSnackBarWitAction("Check Network and try again", "Retry", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadPage();
            }
        });
    }

    public void onFloatingActionButtonClicked() {

    }

    public void setupDebugHelper() {
        if (rootView != null)
            rootView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    showDebugHelper();
                    return true;
                }
            });
    }

    public void setFloatingActionButtonIcon(@DrawableRes int floatingActionButtonIcon) {
        if (mFloatingActionButton != null && floatingActionButtonIcon != 0)
            mFloatingActionButton.setImageResource(floatingActionButtonIcon);
    }

    protected void showSnackBar(String message, String ok) {
        showSnackBarWitAction(message, ok);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        checkForIncomingSnacks();
    }

    @Override
    public void onBackPressed() {
        Log.i(TAG, "Fragment count = " + getSupportFragmentManager().getBackStackEntryCount());

        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            getSupportFragmentManager().popBackStack();
            showToast("Press Back again to Exit..");
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 0)
            finish();
        else
            getSupportFragmentManager().popBackStack();

        if (bottomSheetView != null && collapseBottomSheet()) {
            super.onBackPressed();
        } else {
            //super.onBackPressed();
        }


    }

    @Override
    protected void onPause() {
        //ActivityUtils.hideKeyboard(this);

        if (getPresenter() != null && apiComponent != null) {
            getPresenter().onPause();
            apiComponent.getServerData().cancelRequests(getPresenter().getClass().getSimpleName());
            apiComponent.getServerData().cancelRequests(getPresenter().getClass().hashCode() + "");

        }


        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    private void checkForIncomingSnacks() {
        if (getIntent() != null)
            if (getIntent().getStringExtra("SNACKS") != null)
                if (rootView != null) {
                    showSnackBar(getIntent().getStringExtra("SNACKS"));
                } else {
                    showDebugToast("rootview not set " + getClass().getSimpleName());
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

    public void prepareBottomSheet(@NonNull View bottomSheetView) {
        mBottomSheetBehaviour = BottomSheetBehavior.from(bottomSheetView);
        mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_COLLAPSED);
        mBottomSheetBehaviour.setPeekHeight(0);
    }

    public boolean collapseBottomSheet() {

        if (mBottomSheetBehaviour.getState() == BottomSheetBehavior.STATE_COLLAPSED)
            return false;//already collapsed
        else {
            mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_COLLAPSED);
            return true;//collapsed bottomsheet
        }
    }

    public boolean expandBottomSheet() {

        if (mBottomSheetBehaviour.getState() == BottomSheetBehavior.STATE_EXPANDED)
            return false;//already expanded
        else {
            mBottomSheetBehaviour.setState(BottomSheetBehavior.STATE_EXPANDED);
            return true;//expanded bottomsheet
        }
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void showSnackBar(@NonNull String message) {
        if (rootView != null) {
            Snackbar snackbar = Snackbar
                    .make(rootView, message, Snackbar.LENGTH_LONG);
            View sbView = snackbar.getView();
            TextView textView = (TextView) sbView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);
            snackbar.show();
        } else {
            showDebugToast("Root View not Set " + getClass().getSimpleName());
        }
    }

    public void showSnackBarWitAction(@NonNull String message, String button, @NonNull final View.OnClickListener callBack) {
        if (rootView != null) {
            mSnackbar = Snackbar
                    .make(rootView, message, Snackbar.LENGTH_INDEFINITE);
            View sbView = mSnackbar.getView();

            TextView textView = (TextView) sbView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);

            mSnackbar.setAction(button, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callBack.onClick(v);
                }
            });

            mSnackbar.show();
        } else {
            showToast(message);
            showDebugToast("Root View not Set " + getClass().getSimpleName());
        }
    }

    public void showSnackBarWitAction(@NonNull String message, String button) {
        if (rootView != null) {
            mSnackbar = Snackbar
                    .make(rootView, message, Snackbar.LENGTH_INDEFINITE);
            View sbView = mSnackbar.getView();

            TextView textView = (TextView) sbView.findViewById(R.id.snackbar_text);
            textView.setTextColor(Color.WHITE);

            mSnackbar.setAction(button, new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    hideSnackBar();
                }
            });

            mSnackbar.show();
        } else {
            showDebugToast("Root View not Set " + getClass().getSimpleName());
        }
    }

    public void hideSnackBar() {
        if (mSnackbar != null)
            mSnackbar.dismiss();
    }

    protected abstract BasePresenter getPresenter();
 /*   @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }*/

    public abstract @LayoutRes
    int getLayout();

    protected abstract void setPresenter();

    public void showMaterialProgress(@NonNull String message, String button, View.OnClickListener clicked) {
        if (materialProgressBar == null) {
            materialProgressBar = new MaterialDialog.Builder(this).progress(true, 101).content(message).build();
        }
        if (materialProgressBar.isShowing())
            materialProgressBar.dismiss();

        materialProgressBar.setContent(message);
        materialProgressBar.show();
    }

    protected void hideFileDownloadProgressBar() {
        if (mFileDownloadProgressBar != null)
            mFileDownloadProgressBar.dismiss();
    }

    protected void showDownloadProgressBar(String title, String message) {
        if (mFileDownloadProgressBar == null) {
            mFileDownloadProgressBar = new MaterialDialog.Builder(this)
                    .title(title)
                    .content(message)
                    .progress(false, 100, true)
                    .build();
        }

        if (mFileDownloadProgressBar != null
                && !mFileDownloadProgressBar.isShowing())
            mFileDownloadProgressBar.show();
        mFileDownloadProgressBar.setProgress(0);
    }


    public void setStatusBarColor(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(color);
        }
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

    public void showGoToAppSettingsPageDialog(@NonNull String message) {
        new MaterialDialog.Builder(this).
                title("Open Settings").content(message).
                positiveText("SETTINGS").
                onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        startInstalledAppDetailsActivity(getApplicationContext());
                    }
                }).negativeText("Cancel").
                onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        showSnackBar(";(");
                    }
                }).build().show();
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
    }

    public RecyclerView getRecyclerView() {
        return recyclerView;
    }

    public void makeVisible(@NonNull View hiddenView) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int cx = hiddenView.getWidth() / 2;
            int cy = hiddenView.getHeight() / 2;
            float finalRadius = (float) Math.hypot(cx, cy);
            Animator animator = null;

            animator = ViewAnimationUtils.createCircularReveal(hiddenView, cx, cy, 0, finalRadius);

            hiddenView.setVisibility(View.VISIBLE);
            animator.start();
        } else {
            hiddenView.setVisibility(View.VISIBLE);
        }
    }

    public void showMaterialDialog(String message) {
        new MaterialDialog.Builder(this).content(message).positiveText("OK").show();
    }

    public void setMessageDebug(String debugText) {
        if (messageTextView != null && BuildConfig.DEBUG)
            messageTextView.setText(debugText);
        else
            Log.i(TAG, debugText);
    }

    public void appendMessageDebug(String debugText) {
        if (messageTextView != null && BuildConfig.DEBUG)
            messageTextView.append("\n" + debugText);
        else
            Log.i(TAG, "\n" + debugText);
    }

    public void setMessageTextViewText(String textViewText) {
        if (messageTextView != null)
            messageTextView.setText(textViewText);
    }

    public void setMessage(String message, @DrawableRes int drawableId) {
        if (messageTextView != null)
            messageTextView.setText(message);

        if (mMessageImage != null && drawableId != 0) {
            mMessageImage.setImageDrawable(getResources().getDrawable(drawableId));
        }

    }

    @Nullable
    public String getmSpinnerSetId() {
        return mSpinnerSetId;
    }

    @Nullable
    public String getmSpinnerSetName() {
        return mSpinnerSetName;
    }

    /* public void loadAutocompletesInSpinner(@NonNull final Spinner spinner, @NonNull final List<? extends AutoCompleteSearchContract.Autocomplete> spinnerAutocompletes) {
        List<String> spinnerItem = new ArrayList<>();
        for (AutoCompleteSearchContract.Autocomplete pf :
                spinnerAutocompletes) {
            spinnerItem.add(pf.getName());
        }
        spinner.setAdapter(new ArrayAdapter<>(this, R.layout.prescription_spinner_dropdown_item, spinnerItem));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mSpinnerSetId = spinnerAutocompletes.get(position).getId();
                mSpinnerSetName = spinnerAutocompletes.get(position).getName();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mSpinnerSetId = null;
                mSpinnerSetName = null;
            }
        });
    } */



    /*void showBottomSheet(String[] items) {
        BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(this);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        for (String item :
                items) {
            TextView text = new TextView(this);
            text.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            text.setText(item);
            linearLayout.addView(text);
            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
        bottomSheetDialog.addContentView(linearLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        bottomSheetDialog.show();
    }*/

    public RecyclerView getProgramaticalRecyclerView() {
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        recyclerView.setNestedScrollingEnabled(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        return recyclerView;
    }

    public void setErrorOnEmptyViews(EditText editText, String error){
        editText.requestFocus();
        editText.setError(error);
    }


    public View getTextView(String subtitle) {
        TextView textView = new TextView(this);
        //textView.setTextAppearance(this, R.style.textAppearanceFeatureCarouselItemSubscription);
        textView.setText(subtitle);
        textView.setGravity(Gravity.CENTER);
        return textView;
    }

    public FloatingActionButton getFloatingActionButton() {
        return mFloatingActionButton;
    }

    public void showNoPermissionsUi(String permissionName) {
        /*showSnackBarWitAction("ACCESS DENIED", "OK", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideSnackBar();
            }
        });
        hideMaterialProgress();
        SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setRefreshing(false);
        }*/
    }

    /*public void loadListInShrecyclerView(List<? extends ShrecyclerViewModel> list, ShrecylerViewAdapter mAdapter, ShrecycleContract shrecycleContract) {
        if (mAdapter == null) {
            //mAdapter = new ShrecylerViewAdapter(BaseActivity.this, shrecycleContract, list, getRecyclerView());
        } else {
            mAdapter.updateList(list);
        }
    }*/


    /*public @Nullable ShrecylerViewAdapter getListAdapter(){
        return null;
    }*/

    /*protected boolean startHomeActivityIfCurrentActivityIsTheOnlyActivityInActivityStack(boolean finishHomeActivityAfterLaunch) {
        ActivityManager mngr = (ActivityManager) getSystemService(ACTIVITY_SERVICE);

        List<ActivityManager.RunningTaskInfo> taskList = mngr.getRunningTasks(10);

        if (taskList.get(0).numActivities == 1 &&
                taskList.get(0).topActivity.getClassName().equals(this.getClass().getName())) {

            Intent i = new Intent(this, FragmentHome.class);
            i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.putExtra(FragmentHome.KEY_FINISH_AFTER_LAUNCH, finishHomeActivityAfterLaunch);
            startActivity(i);

            return true;
        }

        return false;
    }*/

    public void showDebugHelper() {

        new MaterialDialog.Builder(this).items(helperItems).
                itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View itemView, int position, CharSequence text) {
                        onDebugItemSelected(position);
                    }
                }).title("Dev Helper").cancelable(false).negativeText("cancel").show();

    }

    public void shareDebugSocial(String text) {
        final Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        sharingIntent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(sharingIntent, "Share On"));
    }

    private void onDebugItemSelected(int position) {

    }

    protected void loadPage() {
        //showMaterialProgress("Loading");
    }

    public Toolbar getToolbar() {
        if (toolbar == null)
            return new Toolbar(this);
        else
            return toolbar;

    }

}
