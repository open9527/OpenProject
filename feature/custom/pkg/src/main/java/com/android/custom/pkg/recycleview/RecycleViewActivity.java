package com.android.custom.pkg.recycleview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.custom.pkg.databinding.RecycleviewActivityBinding;
import com.android.custom.pkg.recycleview.cell.CollectCell;
import com.android.custom.pkg.recycleview.user.ContentVo;
import com.android.open9527.common.binding.refresh.IRefresh;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.okhttp.OkHttpUtils;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.adapter.BaseBindingCellListAdapter;
import com.android.open9527.recycleview.adapter.BaseCellViewHolder;
import com.blankj.utilcode.util.CollectionUtils;
import com.scwang.smart.refresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * @author open_9527
 * Create at 2021/4/25
 **/
public class RecycleViewActivity extends BaseCommonActivity {
    private int mPage = 0;

    private RecycleViewViewModel mViewViewModel;


    @Override
    protected void initViewModel() {
        mViewViewModel = getActivityScopeViewModel(RecycleViewViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.recycleview_activity, BR.vm, mViewViewModel)
                .addBindingParam(BR.adapter, new BaseBindingCellListAdapter<>())
                .addBindingParam(BR.click, new ClickProxy());
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        initConcatAdapter();
    }

    private void initConcatAdapter() {
        ConcatAdapter.Config config = new ConcatAdapter.Config.Builder()
                .setIsolateViewTypes(true)
                .setStableIdMode(ConcatAdapter.Config.StableIdMode.NO_STABLE_IDS)
                .build();
        ConcatAdapter adapter = new ConcatAdapter(config);
        adapter.addAdapter(new HeaderAdapter());
        adapter.addAdapter(new ContentAdapter());
        adapter.addAdapter(new FooterAdapter());
        RecyclerView recyclerView = ((RecycleviewActivityBinding) getBinding()).recyclerView;
        recyclerView.setAdapter(adapter);

    }

    private class HeaderAdapter extends RecyclerView.Adapter<BaseCellViewHolder> {

//        @Override
//        public void setHasStableIds(boolean hasStableIds) {
//            super.setHasStableIds(true);
//        }

        @NonNull
        @Override
        public BaseCellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BaseCellViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.common_title_bar, ((RecycleviewActivityBinding) getBinding()).recyclerView, false));
        }

        @Override
        public void onBindViewHolder(@NonNull BaseCellViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }

    private class ContentAdapter extends RecyclerView.Adapter<BaseCellViewHolder> {
//        @Override
//        public void setHasStableIds(boolean hasStableIds) {
//            super.setHasStableIds(true);
//        }

        @NonNull
        @Override
        public BaseCellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BaseCellViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.common_empty_cell, ((RecycleviewActivityBinding) getBinding()).recyclerView, false));
        }

        @Override
        public void onBindViewHolder(@NonNull BaseCellViewHolder holder, int position) {
            ImageView imageView = holder.findViewById(R.id.iv_empty);
            imageView.setImageResource(com.android.open9527.common.R.drawable.common_empty_icon);
        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }

    private class FooterAdapter extends RecyclerView.Adapter<BaseCellViewHolder> {
//        @Override
//        public void setHasStableIds(boolean hasStableIds) {
//            super.setHasStableIds(true);
//        }

        @NonNull
        @Override
        public BaseCellViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new BaseCellViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.common_no_more_data_cell, ((RecycleviewActivityBinding) getBinding()).recyclerView, false));
        }

        @Override
        public void onBindViewHolder(@NonNull BaseCellViewHolder holder, int position) {
            TextView textView = holder.findViewById(R.id.tv_no_more);
            textView.setText("无更多数据~");
        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }


    @Override
    public void initRequest() {
//        requestLogin();
        requestCollectList();
    }

    private void requestLogin() {
        mViewViewModel.requestLogin("open_9527", " ", OkHttpUtils.post(this));
    }

    private void requestCollectList() {
        mViewViewModel.requestCollectList(mPage, OkHttpUtils.get(this));
    }

    @Override
    public void initEvent() {
        mViewViewModel.getCollectListLiveData().observe(this, objectDataResult -> {
            List<ContentVo> contentVoList = new ArrayList<>();
            if (objectDataResult.getResponseStatus().isSuccess()) {
                contentVoList = objectDataResult.getResult();

            }
            setData(contentVoList);
        });
    }

    private void setData(List<ContentVo> contentVoList) {
        List<BaseBindingCell> cellList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(contentVoList)) {
            for (ContentVo contentVo : contentVoList) {
                cellList.add(new CollectCell(contentVo));
            }
            mViewViewModel.valueNoMoreData.set(contentVoList.size() < 20);
        }
        mViewViewModel.valueCellList.getValue().addAll(cellList);
        mViewViewModel.valueCellList.setValue(mViewViewModel.valueCellList.getValue());


        mViewViewModel.valueList.getValue().addAll(contentVoList);
        mViewViewModel.valueList.setValue(mViewViewModel.valueList.getValue());

        mViewViewModel.valueRefresh.setValue(mPage == 0);
    }


    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            finish();
        };

        public IRefresh<Boolean> onRefreshListeners = new IRefresh<Boolean>() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout, Boolean isRefresh) {
                if (isRefresh) {
                    mPage = 0;
                    mViewViewModel.valueCellList.getValue().clear();
                    mViewViewModel.valueList.getValue().clear();
                } else {
                    mPage++;
                }
                requestCollectList();
            }
        };
    }
}
