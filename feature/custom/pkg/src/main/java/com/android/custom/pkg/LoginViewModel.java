package com.android.custom.pkg;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.android.open9527.common.event.UnPeekLiveData;

/**
 * @author open_9527
 * Create at 2021/2/23
 **/
public class LoginViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("LoginViewModel");

    public final UnPeekLiveData<Boolean> valueLogin = new UnPeekLiveData<>();





}
