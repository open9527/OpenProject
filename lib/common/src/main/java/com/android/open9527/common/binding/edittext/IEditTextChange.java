package com.android.open9527.common.binding.edittext;


public interface IEditTextChange<T> {

    default void onTextChanged(T changed) {
    }

    default void onEditorAction(T def) {
    }
}
