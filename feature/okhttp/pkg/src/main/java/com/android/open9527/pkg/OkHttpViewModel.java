package com.android.open9527.pkg;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

/**
 * @author open_9527
 * Create at 2021/1/7
 **/
public class OkHttpViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("OkHttp");
    public final ObservableField<String> valueContent = new ObservableField<>("请求结果:");


}
