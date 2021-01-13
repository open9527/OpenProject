package com.android.open9527.recycleview.pkg.main;

import android.graphics.Color;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import com.android.open9527.common.binding.drawables.GradientDrawableUtils;
import com.android.open9527.common.cell.CommonEmptyCell;
import com.android.open9527.common.cell.CommonLineCell;
import com.android.open9527.common.cell.CommonNoMoreDataCell;
import com.android.open9527.recycleview.adapter.BaseBindingCell;
import com.android.open9527.recycleview.export.bean.TabBean;
import com.android.open9527.recycleview.pkg.main.content.MainFragment;
import com.android.open9527.recycleview.pkg.R;
import com.blankj.utilcode.util.SizeUtils;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/11
 **/
public class RecycleViewViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("主页");

    public final ObservableArrayList<TabBean> valueTabList = new ObservableArrayList<>();
    public final ObservableArrayList<Fragment> valueFragments = new ObservableArrayList<>();
    public final ObservableField<FragmentManager> valueFragmentManager = new ObservableField<>();
    public final ObservableField<TabLayout.OnTabSelectedListener> valueTabSelectedListener = new ObservableField<>();
    public final ObservableInt valueDefaultIndex = new ObservableInt(2);
    public final ObservableInt valueItemLayout = new ObservableInt(R.layout.main_tab_item);



    public void initTab(@NonNull FragmentManager fragmentManager, @NonNull TabLayout.OnTabSelectedListener listener) {
        createTabList();
        createFragments();
        valueFragmentManager.set(fragmentManager);
        valueTabSelectedListener.set(listener);
    }


    private void createTabList() {
        valueTabList.clear();
        List<TabBean> tabList = new ArrayList<TabBean>() {{
            add(new TabBean("tab001", R.color.light_black, R.color.colorAccent, GradientDrawableUtils.createDrawableOval(Color.GREEN, SizeUtils.dp2px(35))));
            add(new TabBean("tab002", R.color.light_black, R.color.colorAccent, GradientDrawableUtils.createDrawableOval(Color.GREEN, SizeUtils.dp2px(35))));
            add(new TabBean("tab003", R.color.light_black, R.color.colorAccent, GradientDrawableUtils.createDrawableOval(Color.GREEN, SizeUtils.dp2px(35))));
            add(new TabBean("tab004", R.color.light_black, R.color.colorAccent, GradientDrawableUtils.createDrawableOval(Color.GREEN, SizeUtils.dp2px(35))));
            add(new TabBean("tab005", R.color.light_black, R.color.colorAccent, GradientDrawableUtils.createDrawableOval(Color.GREEN, SizeUtils.dp2px(35))));
        }};
        valueTabList.addAll(tabList);
    }

    private void createFragments() {
        valueFragments.clear();
        List<Fragment> tabList = new ArrayList<Fragment>() {{
            add(MainFragment.newInstance());
            add(MainFragment.newInstance());
            add(MainFragment.newInstance());
            add(MainFragment.newInstance());
            add(MainFragment.newInstance());
        }};
        valueFragments.addAll(tabList);

    }



}
