package com.open9527.wanandroid.pkg.main;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableBoolean;
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

/**
 * @author open_9527
 * Create at 2021/1/12
 **/
public class WanAndroidViewModel extends ViewModel {



    public final ObservableArrayList<TabBean> valueTabList = new ObservableArrayList<>();
    public final ObservableArrayList<Fragment> valueFragments = new ObservableArrayList<>();
    public final ObservableField<FragmentManager> valueFragmentManager = new ObservableField<>();
    public final ObservableInt valueDefaultIndex = new ObservableInt(1);

    public final UserRequest userRequest = new UserRequest();


    public void initTab(@NonNull FragmentManager fragmentManager) {

        valueTabList.clear();
        valueTabList.add(new TabBean("project", R.drawable.project_tab_icon));
        valueTabList.add(new TabBean("article", R.drawable.article_tab_icon));
        valueTabList.add(new TabBean("share", R.drawable.share_tab_icon));

        valueFragments.clear();
        valueFragments.add(ProjectFragment.newInstance());
        valueFragments.add(ArticleFragment.newInstance());
        valueFragments.add(ShareFragment.newInstance());

        valueFragmentManager.set(fragmentManager);
    }
}
