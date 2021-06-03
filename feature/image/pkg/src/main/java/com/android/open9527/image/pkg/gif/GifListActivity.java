package com.android.open9527.image.pkg.gif;

import android.graphics.Rect;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;
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
import com.android.open9527.recycleview.adapter.BaseBindingCellListAdapter;
import com.android.open9527.recycleview.decoration.GridSpaceItemDecoration;
import com.android.open9527.recycleview.layout_manager.WrapContentGridLayoutManager;
import com.android.open9527.recycleview.scroll.RecycleViewScrollListener;
import com.blankj.utilcode.util.LogUtils;
import com.bumptech.glide.load.resource.gif.GifDrawable;

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
                .addBindingParam(BR.layoutManager, new WrapContentGridLayoutManager(this, 1))
                .addBindingParam(BR.itemDecoration, new GridSpaceItemDecoration(10))
                .addBindingParam(BR.adapter, new BaseBindingCellListAdapter<>());
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
