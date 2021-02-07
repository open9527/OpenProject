package com.android.annotation.pkg;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

/**
 * @author open_9527
 * Create at 2021/2/4
 **/
public class AnnotationViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("Annotation");

}
