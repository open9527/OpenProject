package com.android.open9527.image.pkg;

import android.view.View;

import androidx.core.app.SharedElementCallback;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.blankj.utilcode.util.ThreadUtils;

import java.util.List;
import java.util.Map;

/**
 * @author open_9527
 * Create at 2021/2/1
 **/
public final class ExitSharedElementCallback extends SharedElementCallback {

    private String mTransitionName = "transition_name";
    private RecyclerView mRecyclerView;
    private LocationChangeEvent mLocationChangeEvent;

    public ExitSharedElementCallback(RecyclerView mRecyclerView) {
        this.mRecyclerView = mRecyclerView;
    }

    /**
     * 装载共享元素
     *
     * @param names          :
     * @param sharedElements :
     */
    @Override
    public void onMapSharedElements(List<String> names, Map<String, View> sharedElements) {
        if (mLocationChangeEvent != null) {
            int startingPosition = mLocationChangeEvent.getStartPosition();
            int currentPosition = mLocationChangeEvent.getCurrentPosition();
            if (startingPosition != currentPosition) {
                //当退出预览时的索引和进入的索引不同时，我们需要更改共享元素
                //getChildAt() 只能获取当前屏幕显示得视图 容易获取到屏幕外的视图触发异常
                if (mRecyclerView.getLayoutManager() != null) {
                    //findViewByPosition() 可以获取未在屏幕中显示得视图,但是不在屏幕中得无法获得动画
                    //可以先触发smoothScrollToPosition() 滑动操作 使其滑动到 屏幕中然后再执行动画
                    View newSharedElement = mRecyclerView.getLayoutManager().findViewByPosition(currentPosition);
                    if (newSharedElement != null) {
                        names.clear();
                        names.add(mTransitionName);
                        sharedElements.clear();
                        sharedElements.put(mTransitionName, newSharedElement);
                    }
                }
            }
            mLocationChangeEvent = null;
        }
    }

    public void setRecyclerView(RecyclerView rvContent) {
        this.mRecyclerView = rvContent;
    }


    public void setLocationChangeEvent(LocationChangeEvent mLocationChangeEvent) {
        this.mLocationChangeEvent = mLocationChangeEvent;
    }

    public void setTransitionName(String mTransitionName) {
        this.mTransitionName = mTransitionName;
    }
}