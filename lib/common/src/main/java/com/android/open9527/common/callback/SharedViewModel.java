package com.android.open9527.common.callback;

import androidx.lifecycle.ViewModel;

import com.android.open9527.common.event.UnPeekLiveData;

/**
 * @author open_9527
 * Create at 2021/2/22
 * <p>
 * eg:
 * 所有跨页面的 "状态同步请求" 都交由该可信源在内部决策和处理，并统一分发给所有订阅者页面。
 **/
public class SharedViewModel extends ViewModel {

    private final UnPeekLiveData<String> startImageModel = new UnPeekLiveData<>();

    public UnPeekLiveData<String> getStartImageModel() {
        return startImageModel;
    }

    public void onStartImageModel(String string) {
        startImageModel.setValue(string);
    }


}
