package com.android.custom.pkg.webview.bridge.custom;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

/**
 * @author open_9527
 * Create at 2021/8/9
 **/
public class WebViewViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>(" WebView");
}
