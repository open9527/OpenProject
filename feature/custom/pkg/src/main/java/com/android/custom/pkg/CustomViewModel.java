package com.android.custom.pkg;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

/**
 * @author open_9527
 * Create at 2021/2/23
 **/
public class CustomViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("Copy");

}
