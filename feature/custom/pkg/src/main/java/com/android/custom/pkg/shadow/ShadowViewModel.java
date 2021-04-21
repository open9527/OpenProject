package com.android.custom.pkg.shadow;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.android.open9527.common.binding.drawables.DrawablesBindingAdapter;

/**
 * @author open_9527
 * Create at 2021/2/23
 **/
public class ShadowViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("Shadow");

    public final ObservableField<Integer> valueShapeMode = new ObservableField<>(DrawablesBindingAdapter.ShapeMode.RECTANGLE);


}
