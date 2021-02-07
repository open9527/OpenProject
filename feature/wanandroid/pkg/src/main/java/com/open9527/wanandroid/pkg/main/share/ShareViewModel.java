package com.open9527.wanandroid.pkg.main.share;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.android.open9527.common.cell.CommonEmptyCell;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.blankj.utilcode.util.CollectionUtils;
import com.open9527.wanandroid.pkg.cell.ShareContentTextCell;
import com.open9527.wanandroid.pkg.net.ContentVo;
import com.open9527.wanandroid.pkg.net.share.ShareRequest;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/12
 **/
public class ShareViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("share");
    public final ObservableField<Drawable> valueTitleBarBg = new ObservableField<>(new ColorDrawable(Color.TRANSPARENT));


    public final ObservableBoolean valueNoMoreData = new ObservableBoolean(false);
    public final ObservableBoolean valueIsRefresh = new ObservableBoolean(true);
    public final ObservableBoolean valueCloseHeaderOrFooter = new ObservableBoolean(false);
    public ObservableArrayList<BaseBindingCell> valueCells = new ObservableArrayList<>();

    public final ShareRequest shareRequest = new ShareRequest();


    protected void onCreateCells(int page, List<ContentVo> contentVos) {
        if (page == 0 && valueCells.size() > 0) valueCells.clear();
        valueIsRefresh.set(page == 0);

        if (CollectionUtils.isNotEmpty(contentVos)) {
            for (ContentVo contentVo : contentVos) {
                if (contentVo == null) continue;
                valueCells.add(new ShareContentTextCell(contentVo));
            }
            valueNoMoreData.set(false);
        } else {
            if (page == 0) valueCells.add(new CommonEmptyCell());
            valueNoMoreData.set(true);
        }
        valueCloseHeaderOrFooter.set(true);
    }

}
