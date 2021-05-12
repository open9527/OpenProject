package com.android.custom.pkg.video;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.custom.pkg.databinding.VideoListActivityBinding;
import com.android.open9527.common.binding.refresh.IRefresh;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellListAdapter;
import com.android.open9527.recycleview.adapter.BaseBindingCellViewHolder;
import com.android.custom.pkg.video.exo.PageListPlayDetector;
import com.android.custom.pkg.video.exo.PageListPlayManager;
import com.blankj.utilcode.util.LogUtils;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/5/11
 **/
public class VideoListActivity extends BaseCommonActivity {

    private VideoListViewModel mViewModel;
    private PageListPlayDetector playDetector;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(VideoListViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.video_list_activity, BR.vm, mViewModel)
                .addBindingParam(BR.adapter, new BaseBindingCellListAdapter<BaseBindingCell>() {
                    @Override
                    public void onViewAttachedToWindow(@NonNull BaseBindingCellViewHolder holder) {
                        LogUtils.i(TAG, "onViewAttachedToWindow");
//                        super.onViewAttachedToWindow(holder);
                        if (holder.getBindingAdapterPosition() >= 0) {
                            playDetector.addTarget(holder.itemView.findViewById(R.id.list_player));
                        }
//
                    }

                    @Override
                    public void onViewDetachedFromWindow(@NonNull BaseBindingCellViewHolder holder) {
                        LogUtils.i(TAG, "onViewDetachedFromWindow");
//                        super.onViewDetachedFromWindow(holder);
                        if (holder.getBindingAdapterPosition() >= 0) {
                            playDetector.removeTarget(holder.itemView.findViewById(R.id.list_player));
                        }
                    }

                    @Override
                    public void onCurrentListChanged(@NonNull List<BaseBindingCell> previousList, @NonNull List<BaseBindingCell> currentList) {
                        LogUtils.i(TAG, "onCurrentListChanged");
//                        super.onCurrentListChanged(previousList, currentList);
                        //这个方法是在我们每提交一次 pagelist对象到adapter 就会触发一次
                        //每调用一次 adpater.submitlist
//                        if (previousList != null && currentList != null) {
//                            if (!currentList.containsAll(previousList)) {
//                                mViewModel.scrollToPosition.set(0);
//                            }
//                        }
                    }
                })
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        playDetector = new PageListPlayDetector(this, ((VideoListActivityBinding) getBinding()).recyclerView);
    }

    @Override
    public void initRequest() {
        mViewModel.getCells();
    }

    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            finish();
        };

        public IRefresh<Boolean> onRefreshListeners = new IRefresh<Boolean>() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout, Boolean isRefresh) {
//                if (isRefresh) {
//                    mPage = 0;
//                    mViewViewModel.valueCellList.getValue().clear();
//                    mViewViewModel.valueList.getValue().clear();
//                } else {
//                    mPage++;
//                }
//                requestCollectList();
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        playDetector.onResume();
    }

    @Override
    protected void onPause() {
        playDetector.onPause();
        super.onPause();
    }


    @Override
    protected void onDestroy() {
        PageListPlayManager.release(mViewModel.valueVideoUrl.get());
        super.onDestroy();
    }
}
