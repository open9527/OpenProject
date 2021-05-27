package com.android.custom.pkg;

import androidx.lifecycle.ViewModel;

import com.android.open9527.common.event.UnPeekLiveData;

/**
 * @author open_9527
 * Create at 2021/2/22
 * <p>
 * eg:
 * 所有跨页面的 "状态同步请求" 都交由该可信源在内部决策和处理，并统一分发给所有订阅者页面。
 **/
public class CustomAppViewModel extends ViewModel {

    private final UnPeekLiveData<Boolean> valueLogin = new UnPeekLiveData<>();

    public UnPeekLiveData<Boolean> getValueLogin() {
        return valueLogin;
    }

    public void setValueLogin(boolean login) {
        valueLogin.setValue(login);
    }


}
