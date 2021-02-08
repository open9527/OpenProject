package com.android.open9527;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.android.annotation.pkg.AnnotationActivity;
import com.android.annotation.pkg.annotation.eg.BaseActivity;
import com.android.annotation.pkg.annotation.eg.ContentView;
import com.blankj.utilcode.util.ActivityUtils;

/**
 * @author open_9527
 * Create at 2021/2/4
 **/

@ContentView(R.layout.contentview_activity)
public class ContentViewActivity extends BaseActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
        super.initView();
        TextView textView = findViewById(R.id.tv_title);
        textView.setText("@ContentView: 配置成功");
        textView.setOnClickListener(v -> ActivityUtils.startActivity(AnnotationActivity.class));
    }
}
