package com.open9527.wanandroid.pkg.main;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import com.open9527.wanandroid.pkg.R;
import com.open9527.wanandroid.pkg.bean.TabBean;
import com.open9527.wanandroid.pkg.main.article.ArticleFragment;
import com.open9527.wanandroid.pkg.main.project.ProjectFragment;
import com.open9527.wanandroid.pkg.main.share.ShareFragment;
import com.open9527.wanandroid.pkg.net.user.UserRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/12
 **/
public class WanAndroidViewModel extends ViewModel {


    public final ObservableArrayList<TabBean> valueTabList = new ObservableArrayList<>();
    public final ObservableArrayList<Fragment> valueFragments = new ObservableArrayList<>();
    public final ObservableField<FragmentManager> valueFragmentManager = new ObservableField<>();
    public final ObservableInt valueDefaultIndex = new ObservableInt(1);
    public final ObservableInt valueItemLayout = new ObservableInt(R.layout.main_tab_item);

    public final UserRequest userRequest = new UserRequest();


    public void initTab(@NonNull FragmentManager fragmentManager) {
        createTabList();
        createFragments();
        valueFragmentManager.set(fragmentManager);
    }


    private void createTabList() {
        valueTabList.clear();
        List<TabBean> tabList = new ArrayList<TabBean>() {{
            add(new TabBean("project", R.color.color_text_sub, R.color.color_text_secondary, R.drawable.project_tab_icon));
            add(new TabBean("article", R.color.color_text_sub, R.color.color_text_secondary, R.drawable.article_tab_icon));
            add(new TabBean("share", R.color.color_text_sub, R.color.color_text_secondary, R.drawable.share_tab_icon));
        }};
        valueTabList.addAll(tabList);
    }

    private void createFragments() {
        valueFragments.clear();
        List<Fragment> tabList = new ArrayList<Fragment>() {{
            add(ProjectFragment.newInstance());
            add(ArticleFragment.newInstance());
            add(ShareFragment.newInstance());
        }};
        valueFragments.addAll(tabList);

    }
}
