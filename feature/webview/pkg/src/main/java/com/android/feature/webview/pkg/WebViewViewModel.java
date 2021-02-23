package com.android.feature.webview.pkg;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;

import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.ViewModel;

/**
 * @author open_9527
 * Create at 2021/1/6
 **/
public class WebViewViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("WebView");

    public final ObservableField<Drawable> valueTitleBarBg = new ObservableField<>(new ColorDrawable(Color.WHITE));
    public final ObservableField<String> valueLeft = new ObservableField<>("返回");
    public final ObservableField<String> valueRight = new ObservableField<>("菜单");

    public final ObservableInt valueWebPbVisibility = new ObservableInt(View.GONE);

    public final ObservableBoolean valueCloseHeaderOrFooter = new ObservableBoolean(false);
    public final ObservableBoolean valueNoMoreData = new ObservableBoolean(false);

    public final ObservableField<String> valueUrl = new ObservableField<>("https://www.wanandroid.com");

}
