package com.android.custom.pkg.umeng;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.open9527.dialog.BaseDialogFragment;
import com.android.open9527.dialog.DialogDataBindingConfig;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellAdapter;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.android.open9527.recycleview.layout_manager.WrapContentGridLayoutManager;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.StringUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.open9527.umeng.Platform;
import com.open9527.umeng.UmengClient;
import com.open9527.umeng.UmengShare;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * @author open_9527
 * Create at 2021/3/9
 **/

public class UmengShareDialog extends BaseDialogFragment {

    public final ObservableArrayList<BaseBindingCell> valueCells = new ObservableArrayList<>();

    private UmengShare.ShareData mShareData;
    private UmengShare.OnShareListener mListener;

    public static UmengShareDialog newInstance(@NonNull Context context) {
        return new UmengShareDialog(context);
    }

    public UmengShareDialog(@NonNull Context context) {
        super(context);
        initData();
    }

    @Override
    public int bindTheme() {
        return R.style.CustomBottomDialog;
    }

    @Override
    public int bindLayout() {
        return R.layout.umeng_share_dialog;
    }

    @Override
    public void setWindowStyle(@NonNull Window window) {
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.height = WRAP_CONTENT;
        layoutParams.width = MATCH_PARENT;
        layoutParams.gravity = Gravity.BOTTOM;
//        layoutParams.dimAmount = 0.5f;
//        layoutParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(layoutParams);
//        //设置背景为透明
        window.setBackgroundDrawable(ContextCompat.getDrawable(mActivity, android.R.color.transparent));
    }

    @Override
    public DialogDataBindingConfig getDataBindingConfig() {
        return new DialogDataBindingConfig().addBindingParam(BR.dialog, this)
                .addBindingParam(BR.layoutManager, new WrapContentGridLayoutManager(mActivity, valueCells.size()))
                .addBindingParam(BR.adapter, new BaseBindingCellAdapter<>());
    }

    @Override
    public void initView(@NonNull BaseDialogFragment dialog, @NonNull View contentView) {
        super.initView(dialog, contentView);
        setCancelable(true);
        setCanceledOnTouchOutside(true);
    }


    private void initData() {
        valueCells.add(new UmengShareCell(new UmengShareBean(ResourceUtils.getDrawable(R.drawable.share_wechat_ic), StringUtils.getString(R.string.share_platform_wechat), Platform.WECHAT)));
        valueCells.add(new UmengShareCell(new UmengShareBean(ResourceUtils.getDrawable(R.drawable.share_moment_ic), StringUtils.getString(R.string.share_platform_moment), Platform.CIRCLE)));
        valueCells.add(new UmengShareCell(new UmengShareBean(ResourceUtils.getDrawable(R.drawable.share_qq_ic), StringUtils.getString(R.string.share_platform_qq), Platform.QQ)));
        valueCells.add(new UmengShareCell(new UmengShareBean(ResourceUtils.getDrawable(R.drawable.share_qzone_ic), StringUtils.getString(R.string.share_platform_qzone), Platform.QZONE)));
        valueCells.add(new UmengShareCell(new UmengShareBean(ResourceUtils.getDrawable(R.drawable.share_link_ic), StringUtils.getString(R.string.share_platform_link), null)));
        mShareData = new UmengShare.ShareData(mActivity);
    }


    public class UmengShareCell extends BaseBindingCell<UmengShareCell> {

        public final ObservableField<Drawable> valueImageDrawable = new ObservableField<>();
        public final ObservableField<String> valueName = new ObservableField<>();
        private final ObservableField<Platform> valueSharePlatform = new ObservableField<>();


        public UmengShareCell(UmengShareBean umengShareBean) {
            super(R.layout.umeng_share_cell);
            valueImageDrawable.set(umengShareBean.getShareIcon());
            valueName.set(umengShareBean.getShareName());
            valueSharePlatform.set(umengShareBean.getSharePlatform());
        }

        @Override
        public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
            holder.addBindingParam(BR.cell, this);
        }

        @RequiresApi(api = Build.VERSION_CODES.M)
        @Override
        public void onCellClick(@NonNull View view, @NonNull UmengShareCell umengShareCell) {
            Platform platform = umengShareCell.valueSharePlatform.get();
            if (platform == null) {
                view.getContext().getSystemService(ClipboardManager.class).setPrimaryClip(ClipData.newPlainText("url", "https://www.wanandroid.com/index"));
                ToastUtils.showShort(R.string.share_platform_copy_hint);
                return;
            }
            UmengClient.share(mActivity, platform, mShareData, mListener);
        }

    }

    /*--------------------------------------------------------------------------------------------*/
    public static UmengShareDialog with(Context context) {
        return UmengShareDialog.newInstance(context);
    }

    public UmengShareDialog setShareTitle(@NonNull String shareTitle) {
        mShareData.setShareTitle(shareTitle);
        return this;
    }

    public UmengShareDialog setShareUrl(@NonNull String shareUrl) {
        mShareData.setShareUrl(shareUrl);
        return this;
    }

    public UmengShareDialog setShareDescription(@NonNull String shareDescription) {
        mShareData.setShareDescription(shareDescription);
        return this;
    }

    public UmengShareDialog setShareLogo(@DrawableRes int id) {
        mShareData.setShareLogo(id);
        return this;
    }

    public UmengShareDialog setShareLogo(@NonNull String logo) {
        mShareData.setShareLogo(logo);
        return this;
    }

    public UmengShareDialog setListener(@NonNull UmengShare.OnShareListener listener) {
        mListener = listener;
        return this;
    }
    /*--------------------------------------------------------------------------------------------*/
}
