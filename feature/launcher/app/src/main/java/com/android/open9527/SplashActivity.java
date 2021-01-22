package com.android.open9527;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.util.BarUtils;

/**
 * @author open_9527
 * Create at 2021/1/19
 **/
public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        //处理状态栏和虚拟按键
        BarUtils.setNavBarVisibility(this, false);
        BarUtils.setStatusBarVisibility(this, false);
        //将window的背景图设置为空
        getWindow().setBackgroundDrawable(null);
        super.onCreate(savedInstanceState);
        startActivity(new Intent(this, LauncherActivity.class));
        finish();
//        ThreadUtils.runOnUiThreadDelayed(() -> {
//            startActivity(new Intent(SplashActivity.this, LauncherActivity.class));
//            finish();
//        }, 5000);


    }
}
