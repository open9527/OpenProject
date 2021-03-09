package com.android.open9527;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.android.annotation.pkg.AnnotationActivity;
import com.android.annotation.pkg.annotation.eg.BaseActivity;
import com.android.open9527.router.Path;
import com.blankj.utilcode.util.ActivityUtils;
import com.open9527.annotation.layout.ContentView;
import com.open9527.annotation.router.Router;

/**
 * @author open_9527
 * Create at 2021/2/4
 **/

@Router(path = Path.PATH_LAUNCHER_CONTENTVIEWACTIVITY)
@ContentView(R.layout.contentview_activity)
public class ContentViewActivity extends BaseActivity {
    @SuppressLint("SetTextI18n")
    @Override
    protected void initView() {
//        setContentView(R.layout.contentview_activity);
        super.initView();
        TextView textView = findViewById(R.id.tv_title);
        textView.setText("@ContentView: 配置成功");
        textView.setOnClickListener(v -> ActivityUtils.startActivity(AnnotationActivity.class));
    }
}
