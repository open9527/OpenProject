package com.open9527.wanandroid.pkg.main.article;


import com.android.open9527.common.binding.refresh.IRefresh;
import com.android.open9527.common.page.BaseCommonFragment;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.recycleview.adapter.BaseBindingCellAdapter;
import com.android.open9527.recycleview.decoration.SpacesItemDecoration;
import com.android.open9527.recycleview.layout_manager.WrapContentLinearLayoutManager;
import com.open9527.wanandroid.pkg.BR;
import com.open9527.wanandroid.pkg.R;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

/**
 * @author open_9527
 * Create at 2021/1/12
 **/
public class ArticleFragment extends BaseCommonFragment {

    private int mPage = 1;
    private ArticleViewModel mViewModel;

    public static ArticleFragment newInstance() {
        return new ArticleFragment();
    }

    @Override
    protected void initViewModel() {
        mViewModel = getFragmentScopeViewModel(ArticleViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.article_fragment , BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.layoutManager, new WrapContentLinearLayoutManager(mActivity))
                .addBindingParam(BR.itemDecoration, new SpacesItemDecoration(mActivity).setParam(R.color.color_line_main, 10))
                .addBindingParam(BR.adapter, new BaseBindingCellAdapter<>());
    }


    public class ClickProxy {
        public IRefresh<Boolean> onRefreshListeners = new IRefresh<Boolean>() {
            @Override
            public void loadComplete(RefreshLayout refreshLayout, Boolean isRefresh) {
                if (isRefresh) {
                    mPage = 1;
                } else {
                    mPage++;
                }
//                mViewModel.createCells(mPage);
                refreshLayout.closeHeaderOrFooter();
            }
        };
    }
}
