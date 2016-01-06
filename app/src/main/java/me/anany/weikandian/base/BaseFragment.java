package me.anany.weikandian.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import me.anany.weikandian.utils.LogUtil;

/**
 * Fragment基类
 * <p/>
 * Created by __Berial___
 */
public abstract class BaseFragment extends Fragment {

    public Activity mActivity;
    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;

    private View rootView;//缓存Fragment view

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        isVisible = getUserVisibleHint();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = initView(inflater, container);
            ButterKnife.bind(rootView);
        }

        /*
        缓存的rootView需要判断是否已经被加过parent，
        如果有parent需要从parent删除，
        要不然会发生这个rootview已经有parent的错误。
         */
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtil.e("BaseFragment onResume ---");
        initData();
    }

    /**
     * 子类必须实现此方法, 返回一个View对象, 作为当前Fragment的布局来展示.
     */
    public abstract View initView(LayoutInflater inflater, ViewGroup container);

    /**
     * 子类需要初始化自己的数据
     */
    public abstract void initData();

}
