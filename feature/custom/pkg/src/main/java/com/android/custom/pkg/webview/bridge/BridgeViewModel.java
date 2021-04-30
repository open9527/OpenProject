package com.android.custom.pkg.webview.bridge;

import android.text.TextUtils;
import android.view.View;

import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.open9527.common.event.LiveDataUtil;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.open9527.webview.bridge.impl.CallbackParam;
import com.open9527.webview.bridge.impl.WebAgreementImpl;
import com.open9527.webview.bridge.impl.vo.BridgeControlType;
import com.open9527.webview.bridge.impl.vo.WebConfigUIVo;
import com.open9527.webview.bridge.impl.vo.WebHeightVo;
import com.open9527.webview.bridge.impl.vo.WebTokenQueryParam;
import com.open9527.webview.bridge.impl.vo.WebUserInfoQueryParam;
import com.open9527.webview.bridge.impl.vo.WebVersionQueryParam;

/**
 * @author open_9527
 * Create at 2021/4/28
 **/
public class BridgeViewModel extends ViewModel {
    private static final String TAG = "BridgeViewModel";

    public final ObservableField<String> valueTitle = new ObservableField<>("Bridge");
    public final ObservableField<String> valueWebUrl = new ObservableField<>("https://mp.weixin.qq.com/s/hC2eN-kK3NVduw2Wy4KNHQ");

    public final ObservableField<BridgeBundleData> valueBridgeBundleData = new ObservableField<>();

    public final ObservableInt showTitleBar = new ObservableInt(View.VISIBLE);
    public final ObservableInt valueShowBack = new ObservableInt(View.VISIBLE);
    public final ObservableInt valueShowClose = new ObservableInt(View.GONE);
    public final ObservableInt valueShowMenu = new ObservableInt(View.GONE);

    public final ObservableInt valueShowBottom = new ObservableInt(View.GONE);
    public final ObservableInt valueShowInput = new ObservableInt(View.GONE);
    public final ObservableInt valueShowComment = new ObservableInt(View.GONE);
    public final ObservableInt valueShowCommentNum = new ObservableInt(View.GONE);
    public final ObservableField<String> valueCommentNum = new ObservableField<>();
    public final ObservableInt valueShowLikes = new ObservableInt(View.GONE);
    public final ObservableInt valueShowLikesNum = new ObservableInt(View.GONE);
    public final ObservableField<String> valueLikesNum = new ObservableField<>();
    public final ObservableBoolean valueLikesSelect = new ObservableBoolean(false);
    public final ObservableInt valueShowCollect = new ObservableInt(View.GONE);
    public final ObservableBoolean valueCollectSelect = new ObservableBoolean(false);
    public final ObservableInt valueShowShare = new ObservableInt(View.GONE);



    public final ObservableArrayList<BaseBindingCell> valueCellList = new ObservableArrayList<>();


    void registerJs(WebAgreementImpl webAgreementImpl, BridgeBundleData bundleData) {
        valueBridgeBundleData.set(bundleData);
        updateWebHeaderUI(bundleData);
        updateWebFooterUI(bundleData);
        if (webAgreementImpl == null) return;

        webAgreementImpl.getVersion(webBaseVo -> {
            LogUtils.i(TAG, "getVersion");
            webBaseVo.getCallback().onCallback(CallbackParam.success(WebVersionQueryParam.getWebVersion()).toJSON());
            webBaseVo.getCallback().onCallback(CallbackParam.complete().toJSON());
        });

        webAgreementImpl.getToken(webBaseVo -> {
            LogUtils.i(TAG, "getToken");
            String token = "token";
            if (!TextUtils.isEmpty(token)) {
                webBaseVo.getCallback().onCallback(CallbackParam.success(new WebTokenQueryParam(token)).toJSON());
            } else {
                webBaseVo.getCallback().onCallback(CallbackParam.fail().toJSON());
            }
            webBaseVo.getCallback().onCallback(CallbackParam.complete().toJSON());
        });

        webAgreementImpl.getAccount(webBaseVo -> {
            LogUtils.i(TAG, "getAccount");
            String account = "account";
            if (!TextUtils.isEmpty(account)) {
                webBaseVo.getCallback().onCallback(CallbackParam.success(WebUserInfoQueryParam.getWebUserInfo(account)).toJSON());
            } else {
                webBaseVo.getCallback().onCallback(CallbackParam.fail().toJSON());
            }
            webBaseVo.getCallback().onCallback(CallbackParam.complete().toJSON());
        });

        webAgreementImpl.configUI(webConfigUIVo -> {
            LogUtils.i(TAG, "configUI");

            if (webConfigUIVo == null) return;
            //标题条设置
            updateWebHeaderUI(webConfigUIVo.header);

            //底边栏设置
            updateWebFooterUI(webConfigUIVo.footer);

            //"更多"弹出框设置
            updateWebMoreBoxUI(webConfigUIVo.moreBox);

            //快捷分享区设置
            updateWebShortcutsUI(webConfigUIVo.shortcuts);

            //动态功能块设置
            updateWebPanelsUI(webConfigUIVo.panels);

            //评论区设置
            updateWebCommentsUI(webConfigUIVo.comments);

            //可选风格设置
            updateWebThemeUI(webConfigUIVo.theme);
        });

//        webAgreementImpl.updateWebViewHeight(webHeightVo -> {
//            LogUtils.i(TAG, "webHeightVo: " + webHeightVo.getHeight());
//            updateWebHeightUI(webHeightVo);
//        });
    }


    private void updateWebHeaderUI(WebConfigUIVo.HeaderBean headerBean) {
        LogUtils.i(TAG, "updateWebHeaderUI: ");
        if (headerBean == null) return;
        BridgeBundleData bundleData = valueBridgeBundleData.get();
        if (bundleData != null) {
            BridgeBundleData.updateHead(bundleData, headerBean);
            updateWebHeaderUI(bundleData);
        }
    }

    private void updateWebHeaderUI(BridgeBundleData bundleData) {
        LogUtils.i(TAG, "updateWebHeaderUI: ");
        if (bundleData == null) return;
        showTitleBar.set(bundleData.isHeaderTitleBarShow() ? View.VISIBLE : View.GONE);
        if (bundleData.isHeaderTitleBarShow()) {
            valueTitle.set(bundleData.getHeaderTitle());
            valueShowBack.set(bundleData.isHeaderBackShow() ? View.VISIBLE : View.GONE);
            valueShowClose.set(bundleData.isHeaderCloseShow() ? View.VISIBLE : View.GONE);
            valueShowMenu.set(bundleData.isHeaderShareShow() ? View.VISIBLE : View.GONE);
        }
    }

    private void updateWebFooterUI(WebConfigUIVo.FooterBean footerBean) {
        LogUtils.i(TAG, "updateWebFooterUI: ");
        if (footerBean == null) return;
        BridgeBundleData bundleData = valueBridgeBundleData.get();
        if (bundleData != null) {
            BridgeBundleData.updateFooter(bundleData, footerBean);
            updateWebFooterUI(bundleData);
        }
    }

    private void updateWebFooterUI(BridgeBundleData bundleData) {
        LogUtils.i(TAG, "updateWebFooterUI: ");
        if (bundleData == null) return;
        valueShowBottom.set(bundleData.isEnableFooter() ? View.VISIBLE : View.GONE);
        valueShowInput.set(bundleData.isEnableFooter() ? View.VISIBLE : View.GONE);
        if (bundleData.isEnableFooter()) {
            updateCollect(bundleData);
            updateLike(bundleData);
            valueShowComment.set(BridgeControlType.isShow(bundleData.getFooterInput()) ? View.VISIBLE : View.GONE);
            valueShowCommentNum.set(bundleData.getFooterCommentCount() > 0 ? View.VISIBLE : View.GONE);
            valueCommentNum.set(String.valueOf(bundleData.getFooterCommentCount() > 99 ? "99+" : bundleData.getFooterCommentCount()));
            valueShowShare.set(BridgeControlType.isShow(bundleData.getFooterShare()) ? View.VISIBLE : View.GONE);
        }
    }


    private void updateCollect(BridgeBundleData bundleData) {
        valueShowCollect.set(BridgeControlType.isShow(bundleData.getFooterFavorite()) ? View.VISIBLE : View.GONE);
        valueCollectSelect.set(BridgeControlType.isCollect(bundleData.getFooterFavorite()));
    }

    private void updateLike(BridgeBundleData bundleData) {
        valueShowLikes.set(BridgeControlType.isShow(bundleData.getFooterLike()) ? View.VISIBLE : View.GONE);
        valueShowLikesNum.set(bundleData.getFooterLikeCount() > 0 ? View.VISIBLE : View.GONE);
        valueLikesSelect.set(BridgeControlType.isLike(bundleData.getFooterLike()));
        valueLikesNum.set(String.valueOf(bundleData.getFooterLikeCount() > 99 ? "99+" : bundleData.getFooterLikeCount()));
    }

//    public void updateLike(CountVo count) {
//        if (count != null) {
//            valueShowLikesNum.set(count.getLikeCount() > 0 ? View.VISIBLE : View.GONE);
//            valueLikesSelect.set(count.getLike());
//            valueLikesNum.set(String.valueOf(count.getLikeCount() > 99 ? "99+" : count.getLikeCount()));
//        }
//    }
//
//    public void updateFavor(CountVo count) {
//        if (count != null) {
//            valueCollectSelect.set(count.getFavorite());
//        }
//    }

    private void updateWebMoreBoxUI(WebConfigUIVo.MoreBoxBean moreBoxBean) {
        LogUtils.i(TAG, "updateWebMoreBoxUI: ");
        if (moreBoxBean == null) return;
        BridgeBundleData bundleData = valueBridgeBundleData.get();
        if (bundleData != null) {
            BridgeBundleData.updateMore(bundleData, moreBoxBean);
        }
    }

    private void updateWebShortcutsUI(WebConfigUIVo.ShortcutsBean shortcutsBean) {
        LogUtils.i(TAG, "updateWebShortcutsUI: ");
        if (shortcutsBean == null) return;
        BridgeBundleData bundleData = valueBridgeBundleData.get();
        if (bundleData != null) {
            BridgeBundleData.updateShortcuts(bundleData, shortcutsBean);
        }
    }


    private void updateWebPanelsUI(WebConfigUIVo.PanelsBean panelsBean) {
        LogUtils.i(TAG, "updateWebPanelsUI: ");
        if (panelsBean == null) return;
        BridgeBundleData bundleData = valueBridgeBundleData.get();
        if (bundleData != null) {
            bundleData.setEnablePanel(panelsBean.enable);
            //TODO:判断是否需要显示 Panel样式UI 以及接口请求
        }
    }

    private void updateWebCommentsUI(WebConfigUIVo.CommentsBean commentsBean) {
        LogUtils.i(TAG, "updateWebCommentsUI: ");
        if (commentsBean == null) return;
        BridgeBundleData bundleData = valueBridgeBundleData.get();
        if (bundleData != null) {
            bundleData.setEnableComment(commentsBean.enable);
            //TODO:判断是否需要显示 Comments样式UI 以及接口请求
        }
    }

    private void updateWebThemeUI(WebConfigUIVo.ThemeBean themeBean) {
        LogUtils.i(TAG, "updateWebCommentsUI: ");
        if (themeBean == null) return;
        BridgeBundleData bundleData = valueBridgeBundleData.get();
        if (bundleData != null) {
            bundleData.setTheme(themeBean);
            //TODO: 动态配置 Theme样式UI
        }
    }

    private void updateWebHeightUI(WebHeightVo webHeightVo) {
        LogUtils.i(TAG, "updateWebHeightUI: ");
        if (webHeightVo == null) return;
    }

    private void updateWebOthersUI() {
        BridgeBundleData bundleData = valueBridgeBundleData.get();
        if (bundleData == null) return;
        if (bundleData.isEnablePanel()) {
//            updatePanelStatus();
        } else if (bundleData.isEnableComment()) {
//            updateCommentStatus();
        } else {
//            updateShareModule();
        }
        boolean isEnableRefresh = bundleData.isShortcutShow() || bundleData.isEnablePanel() || bundleData.isEnableComment();
//        refreshLayout.setEnableLoadMore(bundleData.isEnableComment());
//        setScroll(isEnableRefresh);
    }


    /**
     * 区分适配，活动h5一般有弹框列表，列表可滑动，导致冲突，此处判断如果有配置快捷菜单或者评论的视为普通稿件，否则把滑动交给webview，这样活动类型h5才能滑动
     */
//    private void setScroll(boolean isEnableRefresh) {
//        ViewGroup.LayoutParams layoutParams = x5BridgeWebView.getLayoutParams();
//        if (layoutParams instanceof ConsecutiveScrollerLayout.LayoutParams) {
//            if (((ConsecutiveScrollerLayout.LayoutParams) layoutParams).isConsecutive != isEnableRefresh) {
//                ConsecutiveScrollerLayout.LayoutParams params = new ConsecutiveScrollerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, isEnableRefresh ? ViewGroup.LayoutParams.WRAP_CONTENT : ViewGroup.LayoutParams.MATCH_PARENT);
//                params.isConsecutive = isEnableRefresh;//false禁止，此处需要取非
//                x5BridgeWebView.setLayoutParams(params);
//            }
//        }
//    }
}
