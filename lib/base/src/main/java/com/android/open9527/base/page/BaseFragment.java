
package com.android.open9527.base.page;

import android.app.Activity;
import android.app.Application;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.android.open9527.base.application.BaseApplication;
import com.android.open9527.page.DataBindingFragment;


/**
 * @author open_9527
 * Create at 2021/1/4
 **/

public abstract class BaseFragment extends DataBindingFragment {

    protected final String TAG = getClass().getSimpleName();

    private ViewModelProvider mFragmentProvider;
    private ViewModelProvider mActivityProvider;
    private ViewModelProvider mApplicationProvider;

//    private static final Handler HANDLER = new Handler();
//    protected boolean mAnimationLoaded;


    protected <T extends ViewModel> T getFragmentScopeViewModel(@NonNull Class<T> modelClass) {
        if (mFragmentProvider == null) {
            mFragmentProvider = new ViewModelProvider(this);
        }
        return mFragmentProvider.get(modelClass);
    }

    protected <T extends ViewModel> T getActivityScopeViewModel(@NonNull Class<T> modelClass) {
        if (mActivityProvider == null) {
            mActivityProvider = new ViewModelProvider(mActivity);
        }
        return mActivityProvider.get(modelClass);
    }

    protected <T extends ViewModel> T getApplicationScopeViewModel(@NonNull Class<T> modelClass) {
        if (mApplicationProvider == null) {
            mApplicationProvider = new ViewModelProvider(
                    (BaseApplication) mActivity.getApplicationContext(), getApplicationFactory(mActivity));
        }
        return mApplicationProvider.get(modelClass);
    }

    private ViewModelProvider.Factory getApplicationFactory(Activity activity) {
        checkActivity(this);
        Application application = checkApplication(activity);
        return ViewModelProvider.AndroidViewModelFactory.getInstance(application);
    }

    private Application checkApplication(Activity activity) {
        Application application = activity.getApplication();
        if (application == null) {
            throw new IllegalStateException("Your activity/fragment is not yet attached to "
                    + "Application. You can't request ViewModel before onCreate call.");
        }
        return application;
    }

    private void checkActivity(Fragment fragment) {
        Activity activity = fragment.getActivity();
        if (activity == null) {
            throw new IllegalStateException("Can't create ViewModelProvider for detached fragment");
        }
    }

//    @Nullable
//    @Override
//    public Animation onCreateAnimation(int transit, boolean enter, int nextAnim) {
//        //TODO 错开动画转场与 UI 刷新的时机，避免掉帧卡顿的现象
//        HANDLER.postDelayed(() -> {
//            if (!mAnimationLoaded) {
//                mAnimationLoaded = true;
//                loadInitData();
//            }
//        }, 280);
//        return super.onCreateAnimation(transit, enter, nextAnim);
//    }
//
//    protected void loadInitData() {
//
//    }

    protected void toggleSoftInput() {
        InputMethodManager imm = (InputMethodManager) mActivity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
    }

}
