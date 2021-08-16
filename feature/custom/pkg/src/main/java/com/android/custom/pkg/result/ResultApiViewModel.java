package com.android.custom.pkg.result;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

/**
 * @author open_9527
 * Create at 2021/7/22
 **/
public class ResultApiViewModel extends ViewModel {
    public final ObservableField<String> valueTitle = new ObservableField<>("ResultApi");
    public final ObservableField<String> valueRequestPermission = new ObservableField<>("requestPermission");
    public final ObservableField<String> valueRequestMultiplePermissions = new ObservableField<>("requestMultiplePermissions");

}
