package com.android.appmanager.pkg;

import android.view.View;

import com.android.open9527.common.binding.refresh.IRefresh;
import com.android.open9527.common.dialog.CommonLoadDialog;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.recycleview.adapter.BaseBindingCellListAdapter;
import com.android.open9527.recycleview.decoration.SpacesItemDecoration;
import com.android.open9527.recycleview.layout_manager.WrapContentLinearLayoutManager;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.ThreadUtils;
import com.open9527.annotation.router.Router;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/2/5
 **/
@Router(path = "/appmanager/AppManagerActivity")
public class AppManagerActivity extends BaseCommonActivity {

    private AppManagerViewModel mViewModel;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(AppManagerViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.app_manager_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.layoutManager, new WrapContentLinearLayoutManager(mActivity))
                .addBindingParam(BR.itemDecoration, new SpacesItemDecoration(mActivity).setParam(R.color.common_line_color, 10))
                .addBindingParam(BR.adapter, new BaseBindingCellListAdapter<>());
    }


    @Override
    public void initRequest() {
        createLoadDialog().show();
        requestAppsInfo(true);
    }

    private void requestAppsInfo(boolean isSystem) {
        ThreadUtils.executeByIo(new ThreadUtils.SimpleTask<List<AppUtils.AppInfo>>() {
            @Override
            public List<AppUtils.AppInfo> doInBackground() throws Throwable {
                return AppUtils.getAppsInfo();
            }

            @Override
            public void onSuccess(List<AppUtils.AppInfo> result) {
                mViewModel.initData(result, isSystem);
                mCommonLoadDialog.dismiss();
            }

            @Override
            public void onFail(Throwable t) {
                super.onFail(t);
                mCommonLoadDialog.dismiss();
            }
        });
    }


    public class ClickProxy {
        public View.OnClickListener menuClick = v -> {
            requestAppsInfo(false);
        };

        public IRefresh<Boolean> onRefreshListeners = new IRefresh<Boolean>() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout, Boolean isRefresh) {
                if (isRefresh) {
                    requestAppsInfo(true);
                }

            }
        };
    }


    private CommonLoadDialog mCommonLoadDialog;

    private CommonLoadDialog createLoadDialog() {
        if (mCommonLoadDialog == null) {
            mCommonLoadDialog = CommonLoadDialog.newInstance(this);
        }
        return mCommonLoadDialog;
    }
}
