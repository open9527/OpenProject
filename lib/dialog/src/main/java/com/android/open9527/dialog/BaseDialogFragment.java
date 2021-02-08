package com.android.open9527.dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;


public abstract class BaseDialogFragment extends DialogFragment implements IDialogView {

    protected final String TAG = getClass().getSimpleName();

    protected FragmentActivity mActivity;

    public BaseDialogFragment(@NonNull Context context) {
        this.mActivity = getFragmentActivity(context);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog mDialog = getDialog();
        if (mDialog != null) {
            Window window = mDialog.getWindow();
            assert window != null;
            setWindowStyle(window);
        }
    }

    @Override
    public int getTheme() {
        if (bindTheme() > 0) {
            int theme = bindTheme();
            if (theme != View.NO_ID) {
                return theme;
            }
        }
        return super.getTheme();
    }

//    @NonNull
//    @Override
//    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
//        if (dialogConfig != null) {
//            dialog = bindDialog(mActivity);
//        } else {
//            dialog = super.onCreateDialog(savedInstanceState);
//        }
//
//        return dialog;
//    }

    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (bindLayout() > 0) {
            return inflater.inflate(bindLayout(), container, false);
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        initView(this, view);
//        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCancel(@NonNull DialogInterface dialog) {
        super.onCancel(dialog);
        onCancel(this);
    }

    @Override
    public void onDismiss(@NonNull DialogInterface dialog) {
        super.onDismiss(dialog);
        onDismiss(this);
    }

    @Override
    public void dismiss() {
        closeSoftInput(getDialog());
        Utils.runOnUiThread(() -> {
            if (Utils.isActivityAlive(mActivity)) {
                BaseDialogFragment.super.dismissAllowingStateLoss();
            }
        });
    }

    public void show() {
        show(getClass().getSimpleName());
    }

    public void show(final String tag) {
        Utils.runOnUiThread(new Runnable() {
            @SuppressLint("CommitTransaction")
            @Override
            public void run() {
                if (Utils.isActivityAlive(mActivity)) {
                    FragmentManager fm = mActivity.getSupportFragmentManager();

                    Fragment prev = fm.findFragmentByTag(tag);
                    if (prev != null) {
                        fm.beginTransaction().remove(prev);
                    }
                    BaseDialogFragment.super.show(fm, tag);
                }
            }
        });
    }

    private FragmentActivity getFragmentActivity(Context context) {
        Activity activity = Utils.getActivityByContext(context);
        if (activity != null) {
            if (activity instanceof FragmentActivity) {
                return (FragmentActivity) activity;
            }
        }
        throw new NullPointerException("context not instanceof FragmentActivity!");
    }

    private void closeSoftInput(Dialog dialog) {
        if (dialog != null) {
            View view = dialog.getCurrentFocus();
            if (view instanceof TextView) {
                Utils.hideSoftInput(mActivity, view);
            }
        }
    }
}
