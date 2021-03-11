package com.android.open9527.common.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.android.open9527.common.R;
import com.android.open9527.dialog.BaseDialogFragment;
import com.blankj.utilcode.util.SizeUtils;

/**
 * @author open_9527
 * Create at 2021/2/8
 **/
public class CommonLoadDialog extends BaseDialogFragment {


    public static CommonLoadDialog newInstance(@NonNull Context context) {
        return new CommonLoadDialog(context);
    }

    public CommonLoadDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int bindTheme() {
        return R.style.CustomDialog;
    }

    @Override
    public int bindLayout() {
        return R.layout.common_loading_dialog;
    }

    @Override
    public void initView(@NonNull BaseDialogFragment dialog, @NonNull View contentView) {
        setCancelable(true);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void setWindowStyle(@NonNull Window window) {
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.height = SizeUtils.dp2px(80);
        layoutParams.width = SizeUtils.dp2px(80);
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.dimAmount = 0.0f;
        layoutParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(layoutParams);
        //设置背景为透明
        window.setBackgroundDrawable(ContextCompat.getDrawable(mActivity, android.R.color.transparent));
    }

}
