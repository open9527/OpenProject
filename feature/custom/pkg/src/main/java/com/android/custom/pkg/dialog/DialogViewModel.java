package com.android.custom.pkg.dialog;

import androidx.databinding.ObservableField;
import androidx.lifecycle.ViewModel;

import com.android.open9527.common.binding.drawables.DrawablesBindingAdapter;

/**
 * @author open_9527
 * Create at 2021/2/23
 **/
public class DialogViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("dialog");
    public final ObservableField<String> valueMenuTitle = new ObservableField<>("ShareDialog");
    public final ObservableField<String> valueDateTitle = new ObservableField<>("DateDialog");
    public final ObservableField<String> valueLoginTitle = new ObservableField<>("Login");

}
