package com.android.open9527.image.pkg.preview;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.android.open9527.common.net.glide.ImageLoadConfig;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/25
 **/
public class PhotoViewPageAdapter extends PagerAdapter {
    private Activity mActivity;
    private List mList;
    private View.OnClickListener mOnClickListener;
    private PreviewViewModel mViewModel;

    public PhotoViewPageAdapter(@NonNull Activity activity, @NonNull List list, View.OnClickListener onClickListener) {
        mActivity = activity;
        mList = list;
        mOnClickListener = onClickListener;
        mViewModel = ((PreviewActivity) activity).getActivityScopeViewModel(PreviewViewModel.class);
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
        LoadPhotoView loadPhotoView = new LoadPhotoView(mActivity);
        loadPhotoView.setBackgroundColor(Color.BLACK);

        loadPhotoView.setImageLoadConfig(ImageLoadConfig.with(loadPhotoView)
                .setUrl(mList.get(position).toString())
                .setSkipMemoryCache(true));
        loadPhotoView.init(mOnClickListener, null);
        container.addView(loadPhotoView);
        container.setTransitionName(mViewModel.valueTransitionName.get());
        return loadPhotoView;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }


}
