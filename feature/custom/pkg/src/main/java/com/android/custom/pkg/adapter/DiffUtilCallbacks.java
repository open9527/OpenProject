package com.android.custom.pkg.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.android.custom.pkg.recycleview.cell.CollectCell;
import com.android.custom.pkg.recycleview.user.ContentVo;

/**
 * @author open_9527
 * Create at 2021/4/25
 **/
public class DiffUtilCallbacks {

    public DiffUtil.ItemCallback<ContentVo> getCollectItemCallback() {
        return new DiffUtil.ItemCallback<ContentVo>() {
            @Override
            public boolean areItemsTheSame(@NonNull ContentVo oldItem, @NonNull ContentVo newItem) {
                return oldItem.getId().equals(newItem.getId());

            }
            @Override
            public boolean areContentsTheSame(@NonNull ContentVo oldItem, @NonNull ContentVo newItem) {
                return oldItem.equals(newItem);
            }
        };
    }


    public DiffUtil.ItemCallback<CollectCell> getCollectCellItemCallback() {

        return new DiffUtil.ItemCallback<CollectCell>() {
            @Override
            public boolean areItemsTheSame(@NonNull CollectCell oldItem, @NonNull CollectCell newItem) {
                return oldItem.getUUID().equals(newItem.getUUID());

            }

            @Override
            public boolean areContentsTheSame(@NonNull CollectCell oldItem, @NonNull CollectCell newItem) {
                return oldItem.equals(newItem);
            }
        };
    }

}
