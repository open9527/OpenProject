package com.android.open9527.image.pkg.gif;

import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.vectordrawable.graphics.drawable.Animatable2Compat;

import com.android.open9527.common.action.LifecycleHandler;
import com.android.open9527.common.page.BaseCommonActivity;
import com.android.open9527.common.widget.image.LoadImageView;
import com.android.open9527.glide.webp.WebpDrawable;
import com.android.open9527.image.pkg.BR;
import com.android.open9527.image.pkg.R;
import com.android.open9527.image.pkg.databinding.GifListActivityBinding;
import com.android.open9527.page.DataBindingConfig;
import com.android.open9527.recycleview.ItemTouchHelpCallback;
import com.android.open9527.recycleview.adapter.BaseBindingCellListAdapter;
import com.android.open9527.recycleview.decoration.GridSpaceItemDecoration;
import com.android.open9527.recycleview.layout_manager.WrapContentGridLayoutManager;
import com.android.open9527.recycleview.layout_manager.WrapContentLinearLayoutManager;
import com.android.open9527.recycleview.scroll.RecycleViewScrollListener;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.load.resource.gif.GifDrawable;

import java.util.Collections;

/**
 * @author open_9527
 * Create at 2021/5/26
 **/
public class GifListActivity extends BaseCommonActivity {

    private GifListViewModel mViewModel;

    private LifecycleHandler mLifecycleHandler;
    private int retryCount = 1;

    @Override
    protected void initViewModel() {
        mViewModel = getActivityScopeViewModel(GifListViewModel.class);
    }

    @Override
    protected DataBindingConfig getDataBindingConfig() {
        return new DataBindingConfig(R.layout.gif_list_activity, BR.vm, mViewModel)
                .addBindingParam(BR.click, new ClickProxy())
                .addBindingParam(BR.scrollListener, new RecycleViewScrollListener(iScrollListener))
                .addBindingParam(BR.layoutManager, new WrapContentGridLayoutManager(this,1))
//                .addBindingParam(BR.itemDecoration, new GridSpaceItemDecoration(10))
                .addBindingParam(BR.adapter, new BaseBindingCellListAdapter<>());
    }

    @Override
    public void initView(@Nullable Bundle bundle) {
        super.initView(bundle);
        RecyclerView recyclerView = ((GifListActivityBinding) getBinding()).recyclerView;
        ItemTouchHelpCallback callback = new ItemTouchHelpCallback(new ItemTouchHelpCallback.OnItemTouchCallbackListener() {
            @Override
            public void onSwiped(int adapterPosition) {
                LogUtils.i(TAG, "onSwiped: adapterPosition=" + adapterPosition);

                mViewModel.valueCells.remove(adapterPosition);
                recyclerView.getAdapter().notifyItemRemoved(adapterPosition);

            }

            @Override
            public boolean onMove(int srcPosition, int targetPosition) {
                LogUtils.i(TAG, "onMove: srcPosition=" + srcPosition + "  targetPosition=" + targetPosition);

                // 更换数据源中的数据Item的位置。更改list中开始和结尾position的位置
                Collections.swap(mViewModel.valueCells, srcPosition, targetPosition);
                // 更新UI中的Item的位置，主要是给用户看到交互效果
                recyclerView.getAdapter().notifyItemMoved(srcPosition, targetPosition);

                return true;
            }
        });

        callback.setDragEnable(true);
        callback.setSwipeEnable(true);
//        callback.setColor(this.getResources().getColor(R.color.base_background_block));
        //创建helper对象，callback监听recyclerView item 的各种状态
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        //关联recyclerView，一个helper对象只能对应一个recyclerView
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    @Override
    public void initRequest() {
        mViewModel.getImageUrl();
    }

    private void initGif() {
        mLifecycleHandler = new LifecycleHandler(Looper.getMainLooper(), this) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                if (msg.obj != null && msg.obj.equals("start")) {
                    startGifDrawable(0);
                } else {
                    startGifDrawable(msg.what);
                }
            }
        };
        Message message = new Message();
        message.obj = "start";
        mLifecycleHandler.sendMessageDelayed(message, 3000);

    }

    private void startGifDrawable(int index) {
        Drawable drawable = getGifDrawableByIndex(index);
        if (drawable instanceof Animatable) {
            if (drawable instanceof GifDrawable) {
                GifDrawable gifDrawable = (GifDrawable) drawable;
                gifDrawable.setLoopCount(1);
                gifDrawable.start();
                gifDrawable.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
                    @Override
                    public void onAnimationEnd(Drawable drawable) {
                        super.onAnimationEnd(drawable);
                        gifDrawable.stop();
                        gifDrawable.clearAnimationCallbacks();
                        if (index == mViewModel.valueCells.size() - 1) {

                            mLifecycleHandler.sendEmptyMessageDelayed(0, 1000);
                        } else {
                            mLifecycleHandler.sendEmptyMessageDelayed(index + 1, 1000);
                        }
                    }
                });
            }

            if (drawable instanceof WebpDrawable) {
                WebpDrawable gifDrawable = (WebpDrawable) drawable;
                gifDrawable.setLoopCount(1);
                gifDrawable.start();
                gifDrawable.registerAnimationCallback(new Animatable2Compat.AnimationCallback() {
                    @Override
                    public void onAnimationEnd(Drawable drawable) {
                        super.onAnimationEnd(drawable);
                        gifDrawable.stop();
                        gifDrawable.clearAnimationCallbacks();
                        if (index == mViewModel.valueCells.size() - 1) {

                            mLifecycleHandler.sendEmptyMessageDelayed(0, 1000);
                        } else {
                            mLifecycleHandler.sendEmptyMessageDelayed(index + 1, 1000);
                        }
                    }
                });
            }

            retryCount = 1;
        } else {
            mLifecycleHandler.sendEmptyMessageDelayed(0, 1000 * retryCount);
            retryCount++;
            LogUtils.i(TAG, "retryCount=" + retryCount + "  delayMillis=" + (1000 * retryCount));
        }
    }


    private Drawable getGifDrawableByIndex(int index) {
        GifListActivityBinding mBinding = (GifListActivityBinding) getBinding();
        View view = mBinding.recyclerView.getLayoutManager().findViewByPosition(index);
        if (view == null) return null;
        LoadImageView loadImageView = view.findViewById(R.id.iv_pic);
        return loadImageView.getDrawable();
    }

    private void autoGifDrawable(RecyclerView view, boolean loadImage) {
        if (view == null) return;
        //遍历RecyclerView子控件,如果LoadImageView完全可见就播放gif
        int count = view.getChildCount();//8
        LogUtils.i(TAG, "autoGifDrawable: count=" + count);
        for (int i = 0; i < count; i++) {
            View itemView = view.getChildAt(i);
            if (itemView == null) continue;
            LoadImageView loadImageView = itemView.findViewById(R.id.iv_pic);
            Rect rect = new Rect();
            loadImageView.getLocalVisibleRect(rect);
            int height = loadImageView.getHeight();

            onAnimDrawable(getGifDrawableByIndex(i), loadImage && rect.top == 0 && rect.bottom == height);
        }


    }


    public class ClickProxy {
        public View.OnClickListener backClick = v -> {
            finish();
        };

    }

    public RecycleViewScrollListener.IScrollListener iScrollListener = new RecycleViewScrollListener.IScrollListener() {
        @Override
        public void onImageLoadChange(@NonNull RecyclerView recyclerView, boolean loadImage) {
            if (mActivity == null || mActivity.isFinishing() || mActivity.isDestroyed()) return;
//            autoGifDrawable(recyclerView, loadImage);

        }
    };


    private void onAnimDrawable(Drawable drawable, boolean startAnim) {
        if (drawable instanceof GifDrawable) {
            GifDrawable gifDrawable = (GifDrawable) drawable;
            gifDrawable.setLoopCount(1);
            if (startAnim) {
                gifDrawable.start();
            } else {
                gifDrawable.stop();
            }

        } else if (drawable instanceof WebpDrawable) {
            WebpDrawable webpDrawable = (WebpDrawable) drawable;
            webpDrawable.setLoopCount(1);
            if (startAnim) {
                webpDrawable.start();
            } else {
                webpDrawable.stop();
            }
        }
    }
}
