package com.android.annotation.pkg;

import android.os.Bundle;

import androidx.annotation.Nullable;

import com.android.annotation.pkg.annotation.eg.AnnotationTest;
import com.android.annotation.pkg.annotation.eg.Test;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.blankj.utilcode.util.LogUtils;
import com.open9527.annotation.router.Router;

/**
 * @author open_9527
 * Create at 2021/2/4
 **/

@Router(path = "/annotation/AnnotationActivity")
public class AnnotationActivity extends BaseCommonActivity {

    private AnnotationViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(AnnotationViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.annotation_activity, BR.vm, mViewModel);
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        createTest();
    }

    private void createTest() {
        Test test = new Test();
        //获取Test的Class对象
        Class<? extends Test> clazz = test.getClass();
        //使用Class对象获取对应的注解
        AnnotationTest annotation = clazz.getAnnotation(AnnotationTest.class);
        if (annotation != null) {
            //获取注解传递的值
            int value = annotation.value();
            mViewModel.valueTitle.set(String.valueOf("@AnnotationTest:配置成功 value=" + value));
            LogUtils.d(TAG, "createTest: " + value);
        }
    }
}
