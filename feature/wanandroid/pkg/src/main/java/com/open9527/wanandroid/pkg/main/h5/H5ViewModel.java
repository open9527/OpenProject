package com.open9527.wanandroid.pkg.main.h5;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

/**
 * @author open_9527
 * Create at 2021/1/18
 **/
public class H5ViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("h5");
    public final ObservableField<String> valueH5Url = new ObservableField<>();
}
