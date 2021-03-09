package com.open9527.wanandroid.pkg.dialog;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.databinding.ObservableField;
import androidx.databinding.library.baseAdapters.BR;

import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.open9527.umeng.Platform;
import com.open9527.umeng.UmengClient;
import com.open9527.umeng.UmengLogin;
import com.open9527.umeng.UmengShare;
import com.open9527.wanandroid.pkg.R;

/**
 * @author open_9527
 * Create at 2021/3/9
 **/
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
        //登陆
//        UmengClient.login(ActivityUtils.getTopActivity(), platform, new UmengLogin.OnLoginListener() {
//            @Override
//            public void onSucceed(Platform platform, UmengLogin.LoginData data) {
//                LogUtils.i(TAG, "onSucceed: " + GsonUtils.toJson(data));
//            }
//
//            @Override
//            public void onError(Platform platform, Throwable t) {
//                LogUtils.i(TAG, "onSucceed: " + GsonUtils.toJson(platform));
//            }
//
//            @Override
//            public void onCancel(Platform platform) {
//                LogUtils.i(TAG, "onSucceed: " + GsonUtils.toJson(platform));
//            }
//        });

        //分享
        UmengShare.ShareData shareData = new UmengShare.ShareData(view.getContext());
        shareData.setShareDescription("分享描述");
//        shareData.setShareLogo(R.mipmap.ic_launcher);
        shareData.setShareLogo("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.soutu123.cn%2Felement_origin_min_pic%2F16%2F11%2F23%2F213e7941e6e2e53b5bc90527759e3997.jpg%21%2Ffw%2F700%2Fquality%2F90%2Funsharp%2Ftrue%2Fcompress%2Ftrue&refer=http%3A%2F%2Fpic.soutu123.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1617869971&t=c725a19f3adc5f14ba82a72c8159f382");
        shareData.setShareTitle("分享标题");
        shareData.setShareUrl("https://www.wanandroid.com/index");

        UmengClient.share(ActivityUtils.getTopActivity(), platform, shareData, new UmengShare.OnShareListener() {
            @Override
            public void onSucceed(Platform platform) {
                LogUtils.i(TAG, "onSucceed: " + GsonUtils.toJson(platform));
            }

            @Override
            public void onError(Platform platform, Throwable t) {
                LogUtils.i(TAG, "onError: " + GsonUtils.toJson(platform));
            }

            @Override
            public void onCancel(Platform platform) {
                LogUtils.i(TAG, "onCancel: " + GsonUtils.toJson(platform));
            }
        });
    }

}
