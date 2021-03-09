package com.open9527.wanandroid.pkg.dialog;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.android.open9527.dialog.BaseDialogFragment;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellAdapter;
import com.android.open9527.recycleview.layout_manager.WrapContentGridLayoutManager;
import com.blankj.utilcode.util.ResourceUtils;
import com.blankj.utilcode.util.StringUtils;
import com.open9527.umeng.Platform;
import com.open9527.umeng.UmengShare;
import com.open9527.wanandroid.pkg.R;

import java.util.Objects;

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
        layoutParams.dimAmount = 0.5f;
        layoutParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(layoutParams);
//        //设置背景为透明
        window.setBackgroundDrawable(ContextCompat.getDrawable(mActivity, android.R.color.transparent));
    }

    @Override
    public void initView(@NonNull BaseDialogFragment dialog, @NonNull View contentView) {
        setCancelable(true);
        Objects.requireNonNull(getDialog()).setCanceledOnTouchOutside(true);
//        UmengShareDialogBinding mBinding = DataBindingUtil.getBinding(contentView);
////        assert mBinding != null;
//        if (mBinding != null) {
//            mBinding.setDialog(this);
//            mBinding.setLayoutManager(new WrapContentGridLayoutManager(mActivity, valueCells.size()));
//            mBinding.setAdapter(new BaseBindingCellAdapter<>());
//        } else {
//            LogUtils.i(TAG, "mBinding is  null !");
//        }
        RecyclerView recyclerView = contentView.findViewById(R.id.rv_share_list);
        BaseBindingCellAdapter<BaseBindingCell> mAdapter = new BaseBindingCellAdapter<>();
        recyclerView.setLayoutManager(new WrapContentGridLayoutManager(mActivity, valueCells.size()));
        recyclerView.setAdapter(mAdapter);
        mAdapter.submitItems(valueCells, false);

    }

    private void initData() {
        valueCells.add(new UmengShareCell(new UmengShareBean(ResourceUtils.getDrawable(R.drawable.share_wechat_ic), StringUtils.getString(R.string.share_platform_wechat), Platform.WECHAT)));
        valueCells.add(new UmengShareCell(new UmengShareBean(ResourceUtils.getDrawable(R.drawable.share_moment_ic), StringUtils.getString(R.string.share_platform_moment), Platform.CIRCLE)));
        valueCells.add(new UmengShareCell(new UmengShareBean(ResourceUtils.getDrawable(R.drawable.share_qq_ic), StringUtils.getString(R.string.share_platform_qq), Platform.QQ)));
        valueCells.add(new UmengShareCell(new UmengShareBean(ResourceUtils.getDrawable(R.drawable.share_qzone_ic), StringUtils.getString(R.string.share_platform_qzone), Platform.QZONE)));
        valueCells.add(new UmengShareCell(new UmengShareBean(ResourceUtils.getDrawable(R.drawable.share_link_ic), StringUtils.getString(R.string.share_platform_link), null)));
        mShareData = new UmengShare.ShareData(mActivity);
    }

    public void setShareTitle(@NonNull String shareTitle) {
        mShareData.setShareTitle(shareTitle);
    }

    public void setShareUrl(@NonNull String shareUrl) {
        mShareData.setShareUrl(shareUrl);
    }

    public void setShareDescription(@NonNull String shareDescription) {
        mShareData.setShareDescription(shareDescription);
    }

    public void setShareLogo(@DrawableRes int id) {
        mShareData.setShareLogo(id);
    }

    public void setShareLogo(@NonNull String logo) {
        mShareData.setShareLogo(logo);
    }

    public void setListener(@NonNull UmengShare.OnShareListener listener) {
        mListener = listener;
    }
}
