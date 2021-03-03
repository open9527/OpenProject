package com.android.feature.webview.pkg;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

import com.android.feature.webview.pkg.cell.BrowserContentTextCell;
import com.android.feature.webview.pkg.net.article.ArticleRequest;
import com.android.feature.webview.pkg.net.article.vo.ContentVo;
import com.android.open9527.common.cell.CommonEmptyCell;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.blankj.utilcode.util.CollectionUtils;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/6
 **/
public class BrowserViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("Browser");

    public final ObservableField<Drawable> valueTitleBarBg = new ObservableField<>(new ColorDrawable(Color.WHITE));
    public final ObservableField<String> valueLeft = new ObservableField<>("返回");
    public final ObservableField<String> valueRight = new ObservableField<>("菜单");

    public final ObservableInt valueWebPbVisibility = new ObservableInt(View.GONE);

    public final ObservableBoolean valueCloseHeaderOrFooter = new ObservableBoolean(false);
    public final ObservableBoolean valueNoMoreData = new ObservableBoolean(false);
    public final ObservableBoolean valueIsRefresh = new ObservableBoolean(true);
    public final ObservableArrayList<BaseBindingCell> valueCells = new ObservableArrayList<BaseBindingCell>();


    public final ObservableField<String> valueUrl = new ObservableField<>("http://jyh.beta.easttone.com:8010/api/app/doc/release/rm_js_sdk_testcase.html");

    public ArticleRequest articleRequest = new ArticleRequest();

    void onCreateCells(int page, List<ContentVo> contentVos) {
        valueIsRefresh.set(page == 0);
        if (CollectionUtils.isNotEmpty(contentVos)) {
            for (ContentVo contentVo : contentVos) {
                if (contentVo == null) continue;
                valueCells.add(new BrowserContentTextCell(contentVo));
            }
            valueNoMoreData.set(false);
        } else {
            if (page == 0) valueCells.add(new CommonEmptyCell());
            valueNoMoreData.set(true);
        }
        valueCloseHeaderOrFooter.set(true);
    }

}
