package com.android.open9527.image.pkg.gif;

import androidx.databinding.ObservableField;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.io.InputStream;


/**
 * @author open_9527
 * Create at 2021/5/26
 **/
public class CustomGifViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("CustomGif");

    public final MutableLiveData<InputStream> valueInputStream = new MutableLiveData<>();
}

