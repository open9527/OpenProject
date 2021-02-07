package com.android.annotation.pkg.annotation.eg;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @author open_9527
 * Create at 2021/2/4
 **/
public  class BaseActivity extends AppCompatActivity {
    protected final String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtils.injectLayout(this);
        initView();
    }

    protected void initView() {

    }
}
