package com.open9527.wanandroid.pkg.binding.banner;

import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;

import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.SizeUtils;
import com.open9527.wanandroid.pkg.R;
import com.open9527.wanandroid.pkg.databinding.BannerItemBinding;
import com.open9527.wanandroid.pkg.main.h5.H5Activity;
import com.open9527.wanandroid.pkg.net.BannerVo;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.BaseBannerAdapter;
import com.zhpan.bannerview.BaseViewHolder;
import com.zhpan.bannerview.constants.IndicatorGravity;
import com.zhpan.indicator.enums.IndicatorSlideMode;
import com.zhpan.indicator.enums.IndicatorStyle;

import java.util.List;

public class BannerBindingAdapter {
    @BindingAdapter(value = {"bindBannerData"}, requireAll = false)
    public static void setCommonBanner(BannerViewPager<BannerVo> banner,
                                       List<BannerVo> contentVoList
    ) {
        if (banner == null) return;
        if (banner.getAdapter() == null) {
            banner.setAdapter(new CommonBannerAdapter())
                    .setScrollDuration(1000)
                    .setIndicatorGravity(IndicatorGravity.END)
                    .setIndicatorSlideMode(IndicatorSlideMode.SCALE)
                    .setIndicatorSliderRadius(SizeUtils.dp2px(4))
                    .setIndicatorSliderWidth(SizeUtils.dp2px(4), SizeUtils.dp2px(8))
                    .setIndicatorHeight(SizeUtils.dp2px(4))
                    .setIndicatorSliderColor(ColorUtils.getColor(R.color.white), ColorUtils.getColor(R.color.white))
                    .setIndicatorStyle(IndicatorStyle.ROUND_RECT)
                    .setOnPageClickListener((clickedView, position) -> {
                        H5Activity.startH5(contentVoList.get(position).getLink(), contentVoList.get(position).getTitle());
                    })
                    .create();
        }
        banner.create(contentVoList);
//        banner.setLifecycleRegistry()
//        banner.refreshData(contentVoList);
    }


    public static class CommonBannerAdapter extends BaseBannerAdapter<BannerVo> {

        @Override
        protected void bindData(BaseViewHolder<BannerVo> holder, BannerVo contentVo, int position, int pageSize) {
            BannerItemBinding binding = DataBindingUtil.bind(holder.itemView);

            if (contentVo != null && binding != null) {
                binding.setImageUrl(contentVo.getImageUrl());
                binding.setTitle(contentVo.getTitle());
            }
        }

        @Override
        public int getLayoutId(int viewType) {
            return R.layout.banner_item;
        }
    }

}