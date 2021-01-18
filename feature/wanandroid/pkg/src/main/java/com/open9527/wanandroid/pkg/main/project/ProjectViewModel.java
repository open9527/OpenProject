package com.open9527.wanandroid.pkg.main.project;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableField;
import androidx.databinding.ObservableInt;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModel;

import com.blankj.utilcode.util.CollectionUtils;
import com.open9527.wanandroid.pkg.bean.TabBean;
import com.open9527.wanandroid.pkg.main.project.content.ProjectContentFragment;
import com.open9527.wanandroid.pkg.net.project.ProjectRequest;
import com.open9527.wanandroid.pkg.net.project.ProjectTreeVo;

import java.util.List;

/**
 * @author open_9527
 * Create at 2021/1/12
 **/
public class ProjectViewModel extends ViewModel {

    public final ObservableField<String> valueTitle = new ObservableField<>("Project");

    public final ObservableArrayList<TabBean> valueTabList = new ObservableArrayList<>();
    public final ObservableArrayList<Fragment> valueFragments = new ObservableArrayList<>();
    public final ObservableField<FragmentManager> valueFragmentManager = new ObservableField<>();
    public final ObservableInt valueDefaultIndex = new ObservableInt(0);


    public final ProjectRequest projectRequest = new ProjectRequest();


    protected void initTab(@NonNull FragmentManager fragmentManager, List<ProjectTreeVo> projectTreeVos) {
        valueTabList.clear();
        valueFragments.clear();
        valueFragmentManager.set(fragmentManager);
        if (CollectionUtils.isNotEmpty(projectTreeVos)) {
            for (ProjectTreeVo projectTreeVo : projectTreeVos) {
                valueFragments.add(ProjectContentFragment.newInstance(projectTreeVo.getId(), projectTreeVo.getTitle()));
                valueTabList.add(new TabBean(projectTreeVo.getTitle()));
            }
        }
    }

}
