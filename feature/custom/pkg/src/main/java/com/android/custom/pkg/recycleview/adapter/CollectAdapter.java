package com.android.custom.pkg.recycleview.adapter;

import android.content.Context;

import androidx.recyclerview.widget.RecyclerView;

import com.android.custom.pkg.R;
import com.android.custom.pkg.adapter.DiffUtilCallbacks;
import com.android.custom.pkg.adapter.SimpleDataBindingAdapter;
import com.android.custom.pkg.databinding.CollectItemBinding;
import com.android.custom.pkg.recycleview.user.ContentVo;

/**
 * @author open_9527
 * Create at 2021/4/25
 **/
public class CollectAdapter extends SimpleDataBindingAdapter<ContentVo, CollectItemBinding> {

    public CollectAdapter(Context context) {
        super(context, R.layout.collect_item, new DiffUtilCallbacks().getCollectItemCallback());
    }


    @Override
    protected void onBindItem(CollectItemBinding binding, ContentVo item, RecyclerView.ViewHolder holder) {
//        binding.setItem(item);
    }

    @Override
    public void setOnItemClickListener(OnItemClickListener<ContentVo> onItemClickListener) {
        super.setOnItemClickListener(onItemClickListener);
        
    }
}
