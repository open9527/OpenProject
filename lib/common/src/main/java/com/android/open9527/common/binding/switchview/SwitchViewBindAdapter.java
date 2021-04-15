package com.android.open9527.common.binding.switchview;


import android.widget.CompoundButton;
import android.widget.Switch;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

public class SwitchViewBindAdapter {


    @BindingAdapter(value = {
            "bindSwitchViewCheckedChangeListener",
    },
            requireAll = false)
    public static void setBindingSwitchView(@NonNull Switch view, CompoundButton.OnCheckedChangeListener listener) {
        if (listener != null) {
            view.setOnCheckedChangeListener(listener);
        }
    }
}
