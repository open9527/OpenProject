package com.android.custom.pkg.dialog;


import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.open9527.dialog.BaseDialogFragment;
import com.android.open9527.dialog.DialogDataBindingConfig;
import com.android.open9527.dialog.DialogGravity;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.blankj.utilcode.util.ViewUtils;

public class WithViewDialog extends BaseDialogFragment {

    public final ObservableField<String> valueTitle = new ObservableField<>("依附于View展示");

    private final ObservableInt valueGravity = new ObservableInt(Gravity.CENTER);

    private final ObservableInt valueWidth = new ObservableInt(0);
    private final ObservableInt valueHeight = new ObservableInt(0);
    private final ObservableInt valueOffsetX = new ObservableInt(0);
    private final ObservableInt valueOffsetY = new ObservableInt(0);
    private final ObservableInt valueDialogViewX = new ObservableInt(0);
    private final ObservableInt valueDialogViewY = new ObservableInt(0);

    private static WithViewDialog newInstance(@NonNull Context context) {
        return new WithViewDialog(context);
    }

    public WithViewDialog(@NonNull Context context) {
        super(context);
    }

    @Override
    public int bindTheme() {
        return R.style.AdaptiveHeightDialog;
    }

    @Override
    public int bindLayout() {
        return R.layout.with_view_dialog;
    }

    @Override
    public void setWindowStyle(@NonNull Window window) {
        BarUtils.transparentStatusBar(window);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.gravity = Gravity.START | Gravity.TOP;
        layoutParams.x = valueDialogViewX.get();
        layoutParams.y = valueDialogViewY.get();
//        layoutParams.dimAmount = 0;
        layoutParams.windowAnimations = R.style.RightTransAlphaAcceAnimation;
//        layoutParams.windowAnimations = R.style.RightTransAlphaDeceAnimation;
//        layoutParams.windowAnimations = R.style.RightTransAlphaADAnimation;
        window.setAttributes(layoutParams);
        window.setBackgroundDrawable(ContextCompat.getDrawable(mActivity, android.R.color.transparent));
    }

    @Override
    public DialogDataBindingConfig getDataBindingConfig() {
        return new DialogDataBindingConfig().addBindingParam(BR.dialog, this);
    }

    @Override
    public void initView(@NonNull BaseDialogFragment dialog, @NonNull View contentView) {
        super.initView(dialog, contentView);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }

    public View.OnClickListener cancelClick = v -> {
        dismiss();
    };

    public static WithViewDialog with(Context context) {
        return WithViewDialog.newInstance(context);
    }

    public WithViewDialog setWidth(int width) {
        valueWidth.set(width);
        return this;
    }

    public WithViewDialog setHeight(int height) {
        valueHeight.set(height);
        return this;
    }

    public WithViewDialog setOffsetX(int offsetX) {
        valueOffsetX.set(offsetX);
        return this;
    }

    public WithViewDialog setOffsetY(int offsetY) {
        valueOffsetY.set(offsetY);
        return this;
    }

    public WithViewDialog setDialogViewX(int dialogViewX) {
        valueDialogViewX.set(dialogViewX);
        return this;
    }

    public WithViewDialog setDialogViewY(int dialogViewY) {
        valueDialogViewY.set(dialogViewY);
        return this;
    }

    public WithViewDialog setDialogGravity(int gravity) {
        valueGravity.set(gravity);
        return this;
    }

    public WithViewDialog showCommentReportDialog() {
        show();
        return this;
    }

    public WithViewDialog showCommentReportDialog(View view) {
        showWithView(view);
        return this;
    }

    private void showWithView(View view) {
        //1.设置dialog的位置在屏幕的左上角，因为这样才能更好的计算最终位置
        //WindowManager.LayoutParams layoutParams = window.getAttributes();
        //layoutParams.gravity = Gravity.START | Gravity.TOP;

        //2.获取dialog view得宽高(自适应宽高可能会计算不正确)
        int[] dialogViewSize = SizeUtils.measureView(ViewUtils.layoutId2View(R.layout.with_view_dialog));
        int dialogViewWidth = dialogViewSize[0];
        int dialogViewHeight = dialogViewSize[1];

        LogUtils.i(TAG, "dialogViewWidth: " + dialogViewWidth + " dialogViewHeight: " + dialogViewHeight);
        //3.设置view的数据
        int viewWidth = view.getWidth();
        int viewHeight = view.getHeight();
        LogUtils.i(TAG, "viewWidth: " + viewWidth + " viewHeight: " + viewHeight);

        //4.获取view 在整个屏幕内的绝对坐标
        int[] location = new int[2];
        view.getLocationOnScreen(location);
        int viewX = location[0];
        int viewY = location[1];
        LogUtils.i(TAG, "viewX: " + viewX + " viewY: " + viewY);
        //5.设置dialog 显示的位置
        int dialogGravity = valueGravity.get();


        if (DialogGravity.LEFT_TOP == dialogGravity) {
            showLeftTop(viewX, viewY, dialogViewWidth, dialogViewHeight);

        } else if (DialogGravity.CENTER_TOP == dialogGravity) {
            showCenterTop(viewX, viewY, dialogViewWidth, dialogViewHeight, viewWidth);

        } else if (DialogGravity.RIGHT_TOP == dialogGravity) {
            showRightTop(viewX, viewY, viewWidth, dialogViewHeight);

        } else if (DialogGravity.LEFT_CENTER == dialogGravity) {
            showLeftCenter(viewX, viewY, dialogViewWidth, dialogViewHeight, viewHeight);

        } else if (DialogGravity.CENTER_CENTER == dialogGravity) {
            showCenterCenter(viewX, viewY, dialogViewWidth, dialogViewHeight, viewWidth, viewHeight);

        } else if (DialogGravity.RIGHT_CENTER == dialogGravity) {
            showRightCenter(viewX, viewY, dialogViewHeight, viewWidth, viewHeight);

        } else if (DialogGravity.LEFT_BOTTOM == dialogGravity) {
            showLeftBottom(viewX, viewY, dialogViewWidth, viewHeight);

        } else if (DialogGravity.CENTER_BOTTOM == dialogGravity) {
            showCenterBottom(viewX, viewY, dialogViewWidth, viewWidth, viewHeight);

        } else if (DialogGravity.RIGHT_BOTTOM == dialogGravity) {
            showRightBottom(viewX, viewY, viewWidth, viewHeight);
        }

        //6.展示dialog
        LogUtils.i(TAG, "valueDialogViewX: " + valueDialogViewX.get() + " valueDialogViewY: " + valueDialogViewY.get());
        showCommentReportDialog();
    }


    //dialog显示在view的左上角(LEFT_TOP)
    private void showLeftTop(int viewX, int viewY, int dialogViewWidth, int dialogViewHeight) {

        if (valueWidth.get() != 0) {
            valueDialogViewX.set(viewX - valueWidth.get());
        } else {
            valueDialogViewX.set(viewX - (dialogViewWidth + valueOffsetX.get()));
        }
        if (valueHeight.get() != 0) {
            valueDialogViewY.set(viewY - valueHeight.get());
        } else {
            valueDialogViewY.set(viewY - (dialogViewHeight + valueOffsetY.get()));
        }
    }

    //dialog显示在view的上方(CENTER_TOP)
    private void showCenterTop(int viewX, int viewY, int dialogViewWidth, int dialogViewHeight, int viewWidth) {
        if (valueWidth.get() != 0) {
            valueDialogViewX.set(viewX - (valueWidth.get() - viewWidth) / 2 + valueOffsetX.get());
        } else {
            valueDialogViewX.set(viewX - (dialogViewWidth - viewWidth) / 2 + valueOffsetX.get());
        }
        if (valueHeight.get() != 0) {
            valueDialogViewY.set(viewY - valueHeight.get());
        } else {
            valueDialogViewY.set(viewY - (dialogViewHeight + valueOffsetY.get()));
        }
    }

    //dialog显示在view的右上角(RIGHT_TOP)
    private void showRightTop(int viewX, int viewY, int viewWidth, int dialogViewHeight) {
        valueDialogViewX.set(viewX + viewWidth + valueOffsetX.get());
        if (valueHeight.get() != 0) {
            valueDialogViewY.set(viewY - valueHeight.get());
        } else {
            valueDialogViewY.set(viewY - (dialogViewHeight + valueOffsetY.get()));
        }
    }

    //dialog显示在view的左边
    private void showLeftCenter(int viewX, int viewY, int dialogViewWidth, int dialogViewHeight, int viewHeight) {

        if (valueWidth.get() != 0) {
            valueDialogViewX.set(viewX - valueWidth.get());
        } else {
            valueDialogViewX.set(viewX - (dialogViewWidth + valueOffsetX.get()));
        }

        if (valueHeight.get() != 0) {
            valueDialogViewY.set(viewY - (valueHeight.get() - viewHeight) / 2 + valueOffsetY.get());
        } else {
            valueDialogViewY.set(viewY - (dialogViewHeight - viewHeight) / 2 + valueOffsetY.get());
        }
    }

    //dialog显示在view的正中心
    private void showCenterCenter(int viewX, int viewY, int dialogViewWidth, int dialogViewHeight, int viewWidth, int viewHeight) {
        if (valueWidth.get() != 0) {
            valueDialogViewX.set(viewX - (valueWidth.get() - viewWidth) / 2 + valueOffsetX.get());
        } else {
            valueDialogViewX.set(viewX - (dialogViewWidth - viewWidth) / 2 + valueOffsetX.get());
        }

        if (valueHeight.get() != 0) {
            valueDialogViewY.set(viewY - (valueHeight.get() - viewHeight) / 2 + valueOffsetY.get());
        } else {
            valueDialogViewY.set(viewY - (dialogViewHeight - viewHeight) / 2 + valueOffsetY.get());
        }
    }

    //dialog显示在view的右边
    private void showRightCenter(int viewX, int viewY, int dialogViewHeight, int viewWidth, int viewHeight) {
        valueDialogViewX.set(viewX + viewWidth + valueOffsetX.get());
        if (valueHeight.get() != 0) {
            valueDialogViewY.set(viewY - (valueHeight.get() - viewHeight) / 2 + valueOffsetY.get());
        } else {
            valueDialogViewY.set(viewY - (dialogViewHeight - viewHeight) / 2 + valueOffsetY.get());
        }
    }

    //dialog显示在view的左下角
    private void showLeftBottom(int viewX, int viewY, int dialogViewWidth, int viewHeight) {
        if (valueWidth.get() != 0) {
            valueDialogViewX.set(viewX - valueWidth.get());
        } else {
            valueDialogViewX.set(viewX - (dialogViewWidth + valueOffsetX.get()));
        }
        valueDialogViewY.set(viewY + viewHeight + valueOffsetY.get());
    }

    //dialog显示在view的下方
    private void showCenterBottom(int viewX, int viewY, int dialogViewWidth, int viewWidth, int viewHeight) {
        if (valueWidth.get() != 0) {
            valueDialogViewX.set(viewX - (valueWidth.get() - viewWidth) / 2 + valueOffsetX.get());
        } else {
            valueDialogViewX.set(viewX - (dialogViewWidth - viewWidth) / 2 + valueOffsetX.get());
        }
        valueDialogViewY.set(viewY + viewHeight + valueOffsetY.get());
    }

    //dialog显示在view的右下角
    private void showRightBottom(int viewX, int viewY, int viewWidth, int viewHeight) {
        valueDialogViewX.set(viewX + viewWidth + valueOffsetX.get());
        valueDialogViewY.set(viewY + viewHeight + valueOffsetY.get());
    }


}
