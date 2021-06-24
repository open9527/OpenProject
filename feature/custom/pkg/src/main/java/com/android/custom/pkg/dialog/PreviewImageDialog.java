package com.android.custom.pkg.dialog;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.view.ViewCompat;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.custom.pkg.BR;
import com.android.custom.pkg.R;
import com.android.open9527.common.net.glide.ImageLoadConfig;
import com.android.open9527.common.widget.OnDragChangeListener;
import com.android.open9527.common.widget.PhotoViewContainer;
import com.android.open9527.common.widget.image.LoadPhotoView;
import com.android.open9527.common.widget.image.PhotoViewPager;
import com.android.open9527.dialog.BaseDialogFragment;
import com.android.open9527.dialog.DialogDataBindingConfig;
import com.android.open9527.recycleview.adapter.BaseBindingCellListAdapter;
import com.blankj.utilcode.util.LogUtils;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/6/22
 * <p>
 * 预览图片
 **/
public class PreviewImageDialog extends BaseDialogFragment implements OnDragChangeListener {

    public final ObservableField<PagerAdapter> valuePageAdapter = new ObservableField<>();
    public final ObservableField<ImageView> valueImageView = new ObservableField<>();
    public final ObservableInt valuePageIndex = new ObservableInt(0);
    private final ObservableField<IDialog> valueIDialog = new ObservableField<>();


    public final ObservableArrayList<String> valueUrls = new ObservableArrayList<>();
    private int mLeftDelta;
    private int mTopDelta;
    private float mWidthScale;
    private float mHeightScale;
    private LoadPhotoView loadPhotoView;
    private ColorDrawable colorDrawable;
    private PhotoViewPager photoViewPager;
    private PhotoViewContainer mContainer;


    private static PreviewImageDialog newInstance(@NonNull Context context) {
        return new PreviewImageDialog(context);
    }

    public PreviewImageDialog(@NonNull Context context) {
        super(context);
    }


    @Override
    public int bindTheme() {
        return R.style.CustomDialog;
    }

    @Override
    public int bindLayout() {
        return R.layout.preview_image_dialog;
    }


    @Override
    public void setWindowStyle(@NonNull Window window) {
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        layoutParams.gravity = Gravity.CENTER;
        layoutParams.dimAmount = 0.0f;
        layoutParams.flags |= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        window.setAttributes(layoutParams);
        //设置背景为透明
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public DialogDataBindingConfig getDataBindingConfig() {
        return new DialogDataBindingConfig().addBindingParam(BR.dialog, this)
                .addBindingParam(BR.adapter, new BaseBindingCellListAdapter<>())
                .addBindingParam(BR.vpChangeListener, onPageChangeListener);
    }

    @Override
    public void initView(@NonNull BaseDialogFragment dialog, @NonNull View contentView) {
        super.initView(dialog, contentView);
        setCancelable(true);
        setCanceledOnTouchOutside(false);
        mContainer = contentView.findViewById(R.id.fl_container);
        mContainer.setOnDragChangeListener(this);
        photoViewPager = contentView.findViewById(R.id.photo_view_pager);
        photoViewPager.setCurrentItem(valuePageIndex.get(), false);
        photoViewPager.setVisibility(View.INVISIBLE);

        colorDrawable = new ColorDrawable(ContextCompat.getColor(contentView.getContext(), R.color.black));
        loadPhotoView = new LoadPhotoView(contentView.getContext());

        loadPhotoView.setImageLoadConfig(ImageLoadConfig.with(loadPhotoView)
                .setUrl(valueUrls.get(valuePageIndex.get()).toString())
                .setSkipMemoryCache(false));
        loadPhotoView.init(backClick, null);
        mContainer.addView(loadPhotoView);

        if (0 == valuePageIndex.get()) {
            updateLocation();
        }


    }


    @Override
    public void dismiss() {
        photoViewPager.setVisibility(View.INVISIBLE);
        loadPhotoView.setVisibility(View.VISIBLE);
        exitAnimation(super::dismiss);

    }

    private void updateLocation() {
        ImageView imageView = valueImageView.get();
        ////将图片位置传到大图activity用于动画初始位置
        int[] screenLocationM = new int[2];
        imageView.getLocationOnScreen(screenLocationM);
        int left = screenLocationM[0];
        int top = screenLocationM[1];
        int width = imageView.getWidth();
        int height = imageView.getHeight();

        ViewTreeObserver observer = loadPhotoView.getViewTreeObserver();
        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                loadPhotoView.getViewTreeObserver().removeOnPreDrawListener(this);
                int[] screenLocation = new int[2];
                loadPhotoView.getLocationOnScreen(screenLocation);
                mLeftDelta = left - screenLocation[0];
                mTopDelta = top - screenLocation[1];

                mWidthScale = (float) width / loadPhotoView.getWidth();
                mHeightScale = (float) height / loadPhotoView.getHeight();

                enterAnimation(() -> {
                    loadPhotoView.setVisibility(View.INVISIBLE);
                    photoViewPager.setVisibility(View.VISIBLE);
                });
                return true;
            }
        });
    }


    private void enterAnimation(final Runnable enterAction) {
        loadPhotoView.setPivotX(0);
        loadPhotoView.setPivotY(0);
        loadPhotoView.setScaleX(mWidthScale);
        loadPhotoView.setScaleY(mHeightScale);
        loadPhotoView.setTranslationX(mLeftDelta);
        loadPhotoView.setTranslationY(mTopDelta);
        ViewCompat.animate(loadPhotoView)
                .setDuration(500)
                .scaleX(1)
                .scaleY(1)
                .translationX(0)
                .translationY(0)
                .setInterpolator(new DecelerateInterpolator())
                .withEndAction(enterAction);
        ObjectAnimator bgAnim = ObjectAnimator.ofInt(colorDrawable, "alpha", 0, 255);
        bgAnim.setDuration(500);
        bgAnim.start();
    }

    private void exitAnimation(final Runnable endAction) {
        ViewCompat.animate(loadPhotoView)
                .setDuration(500)
                .scaleX(mWidthScale)
                .scaleY(mHeightScale).
                translationX(mLeftDelta)
                .translationY(mTopDelta)
                .setInterpolator(new AccelerateInterpolator())
                .withEndAction(endAction);
        ObjectAnimator bgAnim = ObjectAnimator.ofInt(colorDrawable, "alpha", 0);
        bgAnim.setDuration(500);
        bgAnim.start();
    }


    private View.OnClickListener backClick = v -> dismiss();


    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            LogUtils.i(TAG, "onPageSelected:" + position);
            valuePageIndex.set(position);
            IDialog iDialog = valueIDialog.get();
            if (iDialog != null) {
                iDialog.onSelected(position, PreviewImageDialog.this);
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    @Override
    public void onRelease() {
        dismiss();
    }

    protected ArgbEvaluator argbEvaluator = new ArgbEvaluator();

    @Override
    public void onDragChange(int dy, float scale, float fraction) {
        //positionOffset:表示渐变度，取0.0F-1.0F之间某一值
        //PAGE_COLOR_ONE:表示起始颜色值
        //PAGE_COLOR_TWO:表示最终颜色值
        //currentLastColor:表示由以上三个参数计算得到的渐变颜色值
        mContainer.setBackgroundColor((Integer) argbEvaluator.evaluate(fraction * .5f, Color.BLACK, Color.TRANSPARENT));
    }


    private class PhotoViewPageAdapter extends PagerAdapter {
        private List mList;

        public PhotoViewPageAdapter(@NonNull List list) {
            mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LoadPhotoView loadPhotoView = new LoadPhotoView(container.getContext());
            loadPhotoView.setBackgroundColor(Color.BLACK);
            loadPhotoView.setImageLoadConfig(ImageLoadConfig.with(loadPhotoView)
                    .setUrl(mList.get(position).toString())
                    .setSkipMemoryCache(true));
            loadPhotoView.init(backClick, null);
            container.addView(loadPhotoView);
            return loadPhotoView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }
    }


    public static PreviewImageDialog with(Context context) {
        return PreviewImageDialog.newInstance(context);
    }

    public void updateImageView(ImageView imageView) {
        valueImageView.set(imageView);
        updateLocation();
    }

    public PreviewImageDialog setData(List<String> list, int index) {
        valueUrls.clear();
        valueUrls.addAll(list);
        valuePageAdapter.set(new PhotoViewPageAdapter(valueUrls));
        valuePageIndex.set(index);
        LogUtils.i(TAG, "setData :" + index);
        return this;
    }

    public PreviewImageDialog setImageView(ImageView imageView) {
        valueImageView.set(imageView);
        return this;
    }

    public PreviewImageDialog setListener(@NonNull IDialog listener) {
        valueIDialog.set(listener);
        return this;
    }


    public PreviewImageDialog showDialog() {
        show();
        return this;
    }

    public interface IDialog {
        void onSelected(int index, PreviewImageDialog dialog);
    }
}
