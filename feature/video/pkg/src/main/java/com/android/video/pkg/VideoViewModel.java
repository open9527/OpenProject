package com.android.video.pkg;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

/**
 * @author open_9527
 * Create at 2021/2/23
 **/
public class VideoViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("Video");

}
