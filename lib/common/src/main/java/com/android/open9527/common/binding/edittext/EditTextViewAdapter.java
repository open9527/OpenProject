package com.android.open9527.common.binding.edittext;


import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;

import androidx.databinding.BindingAdapter;


public class EditTextViewAdapter {

    @BindingAdapter(value = {"bindEditTextChange", "bindEditText"}, requireAll = false)
    public static void setBindEditTextChanged(EditText editText, IEditTextChange<String> changed, String content) {
        if (!TextUtils.isEmpty(content)) {
            editText.setText(content);
            editText.setSelection(content.length());
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (changed != null) {
                    changed.onTextChanged(s.toString());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


    @BindingAdapter(value = {"bindingActionSearchListener"}, requireAll = false)
    public static void setBindEditTextActionSearch(EditText editText, IEditTextChange<EditText> iEditTextChange) {
        editText.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                iEditTextChange.onEditorAction(editText);
                return true;
            }
            return false;
        });

    }

}
