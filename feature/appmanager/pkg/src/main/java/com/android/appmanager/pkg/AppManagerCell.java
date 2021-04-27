package com.android.appmanager.pkg;

import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ClipboardUtils;
import com.blankj.utilcode.util.ToastUtils;

/**
 * @author open_9527
 * Create at 2021/2/5
 **/
public class AppManagerCell extends BaseBindingCell<AppManagerCell> {

    public final ObservableField<Drawable> valueImageDrawable = new ObservableField<>();
    public final ObservableField<String> valueTitle = new ObservableField<>();
    public final ObservableField<String> valuePkgName = new ObservableField<>();
    public final ObservableField<String> valueVersionName = new ObservableField<>();
    public final ObservableField<String> valueVersionCode = new ObservableField<>();

    public AppManagerCell(AppUtils.AppInfo appInfo) {
        super(R.layout.app_manager_cell);
        valueImageDrawable.set(appInfo.getIcon());
        valueTitle.set(appInfo.getName());
        valuePkgName.set(appInfo.getPackageName());
        valueVersionName.set(appInfo.getVersionName());
        valueVersionCode.set(String.valueOf(appInfo.getVersionCode()));
    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        holder.addBindingParam(BR.cell, this);
    }

    @Override
    public void onCellClick(View view, AppManagerCell appManagerCell) {
        AppUtils.launchAppDetailsSettings(appManagerCell.valuePkgName.get());
    }

    @Override
    public boolean onCellLongClick(View view, AppManagerCell appManagerCell) {
        //getAppSignatures                  : 获取 App 签名
        //getAppSignaturesSHA1              : 获取应用签名的的 SHA1 值
        //getAppSignaturesSHA256            : 获取应用签名的的 SHA256 值
        //getAppSignaturesMD5               : 获取应用签名的的 MD5 值
//        LogUtils.i(TAG, "获取应用签名的的 SHA1 值" + AppUtils.getAppSignaturesSHA1(valuePkgName.get()));
//        LogUtils.i(TAG, "获取应用签名的的 SHA256 值" + AppUtils.getAppSignaturesSHA256(valuePkgName.get()));
//        LogUtils.i(TAG, "获取应用签名的的 MD5 值" + AppUtils.getAppSignaturesMD5(valuePkgName.get()));
        String stringBuilder =
                "app名称: " + valueTitle.get() + "\r\n"
                        + "app包名: " + valuePkgName.get() + "\r\n"
                        + "app签名的SHA1 值: " + AppUtils.getAppSignaturesSHA1(valuePkgName.get()) + "\r\n"
                        + "app签名的SHA256 值: " + AppUtils.getAppSignaturesSHA256(valuePkgName.get()) + "\r\n"
                        + "app签名的MD5 值: " + AppUtils.getAppSignaturesMD5(valuePkgName.get()) + "\r\n";
        ClipboardUtils.copyText(stringBuilder);
        ToastUtils.showShort("复制成功!");
        return true;
    }

    @Override
    public String getUUID() {
        return valuePkgName.get();
    }
}
