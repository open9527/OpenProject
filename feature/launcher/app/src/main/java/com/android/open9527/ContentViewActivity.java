package com.android.open9527;

import android.annotation.SuppressLint;
import android.widget.TextView;

import com.android.annotation.pkg.AnnotationActivity;
import com.android.annotation.pkg.annotation.eg.BaseActivity;
import com.android.open9527.router.Path;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.LogUtils;
import com.open9527.annotation.layout.ContentView;
import com.open9527.annotation.router.Router;
import com.open9527.router.callback.NavigationCallback;
import com.open9527.router.info.Postcard;

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
        textView.setOnClickListener(v -> com.open9527.router.Router.getsInstance()
                .build(Path.PATH_ANNOTATION_ANNOTATIONACTIVITY)
                .navigation(this, new NavigationCallback() {
                    @Override
                    public void onFound(Postcard postcard) {
                        LogUtils.i(TAG, "NavigationCallback" + "找到跳转页面 Path= " + postcard.getPath());
                    }

                    @Override
                    public void onLost(Postcard postcard) {
                        LogUtils.i(TAG, "NavigationCallback" + "未找到 Path= " + postcard.getPath());
                    }

                    @Override
                    public void onArrival(Postcard postcard) {
                        LogUtils.i(TAG, "NavigationCallback" + "成功跳转 Path= " + postcard.getPath());
                    }
                }));
    }
}
