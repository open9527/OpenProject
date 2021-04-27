package com.android.custom.pkg;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.android.open9527.common.binding.drawables.DrawablesBindingAdapter;

/**
 * @author open_9527
 * Create at 2021/2/23
 **/
public class CustomViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("Custom");

    public final ObservableField<String> valueFilterTitle = new ObservableField<>("filter");
    public final ObservableField<String> valueShadowTitle = new ObservableField<>("Shadow");
    public final ObservableField<String> valueDialogTitle = new ObservableField<>("Dialog");
    public final ObservableField<String> valueRecycleViewTitle = new ObservableField<>("RecycleView");


}
