package com.android.feature.webview.pkg.cell;

import android.text.TextUtils;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableField;

import com.android.feature.webview.pkg.BR;
import com.android.feature.webview.pkg.R;
import com.android.feature.webview.pkg.net.article.vo.ContentVo;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.blankj.utilcode.util.StringUtils;

/**
 * @author open_9527
 * Create at 2021/3/2
 **/
public class BrowserContentTextCell extends BaseBindingCell<BrowserContentTextCell> {

    public final ObservableField<String> valueTitle = new ObservableField<>();
//    private final ObservableField<String> valueLink = new ObservableField<>();
    public final ObservableField<String> valueShareUser = new ObservableField<>();
    public final ObservableField<String> valueNiceShareDate = new ObservableField<>();

    public BrowserContentTextCell(ContentVo contentVo) {
        super(R.layout.browser_content_text_cell);
        valueTitle.set(contentVo.getTitle());
//        valueLink.set(contentVo.getLink());
        if (TextUtils.isEmpty(contentVo.getShareUser())) {
            valueShareUser.set(
                    String.format(
                            StringUtils.getString(R.string.browser_author),
                            contentVo.getAuthor())
            );
        } else {
            valueShareUser.set(
                    String.format(
                            StringUtils.getString(R.string.browser_sharer),
                            contentVo.getShareUser())
            );
        }
        valueNiceShareDate.set(contentVo.getNiceShareDate());
    }

    @Override
    public void bind(@NonNull BaseBindingCellViewHolder holder, int position) {
        holder.addBindingParam(BR.cell, this);
    }
}
