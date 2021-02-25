package com.open9527.wanandroid.pkg.binding.banner

import android.view.View
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import com.android.feature.webview.export.api.WebApi
import com.android.feature.webview.export.api.WebBundle
import com.blankj.utilcode.util.ApiUtils
import com.blankj.utilcode.util.ColorUtils
import com.blankj.utilcode.util.SizeUtils
import com.open9527.wanandroid.pkg.R
import com.open9527.wanandroid.pkg.databinding.BannerItemBinding
import com.open9527.wanandroid.pkg.main.h5.H5Activity.Companion.startH5
import com.open9527.wanandroid.pkg.net.BannerVo
import com.zhpan.bannerview.BannerViewPager
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder
import com.zhpan.bannerview.constants.IndicatorGravity
import com.zhpan.indicator.enums.IndicatorSlideMode
import com.zhpan.indicator.enums.IndicatorStyle
import java.util.*

object BannerBindingAdapter {
    @JvmStatic
    @BindingAdapter(value = ["bindBannerData"], requireAll = false)
    fun setCommonBanner(
        banner: BannerViewPager<BannerVo>?,
        contentVoList: List<BannerVo>
    ) {
        if (banner == null) return
        if (banner.adapter == null) {
            banner.setAdapter(CommonBannerAdapter())
                .setScrollDuration(1000)
                .setIndicatorGravity(IndicatorGravity.END)
                .setIndicatorSlideMode(IndicatorSlideMode.SCALE)
                .setIndicatorSliderRadius(SizeUtils.dp2px(4f))
                .setIndicatorSliderWidth(SizeUtils.dp2px(4f), SizeUtils.dp2px(8f))
                .setIndicatorHeight(SizeUtils.dp2px(4f))
                .setIndicatorSliderColor(
                    ColorUtils.getColor(R.color.white),
                    ColorUtils.getColor(R.color.white)
                )
                .setIndicatorStyle(IndicatorStyle.ROUND_RECT)
                .setOnPageClickListener { clickedView: View?, position: Int ->
                    contentVoList[position].link?.let {
//                        startH5(
//                            it, contentVoList[position].title
//                        )
                        if (ApiUtils.getApi(WebApi::class.java).toString().contains("WebApiMockImpl")) {
                            startH5(
                                it,
                                contentVoList[position].title
                            )
                        } else {
                            ApiUtils.getApi(WebApi::class.java)
                                .startWeb(WebBundle(it, contentVoList[position].title))
                        }
                    }
                }
                .create()
        }
        banner.create(contentVoList)
        //        banner.setLifecycleRegistry()
//        banner.refreshData(contentVoList);
    }

    class CommonBannerAdapter : BaseBannerAdapter<BannerVo?>() {
        override fun bindData(
            holder: BaseViewHolder<BannerVo?>,
            contentVo: BannerVo?,
            position: Int,
            pageSize: Int
        ) {
            val binding: BannerItemBinding? = DataBindingUtil.bind(holder.itemView)
            if (contentVo != null && binding != null) {
                binding.imageUrl = contentVo.imageUrl
                binding.title = contentVo.title
            }
        }

        override fun getLayoutId(viewType: Int): Int {
            return R.layout.banner_item
        }
    }
}