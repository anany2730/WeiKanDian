package me.anany.weikandian.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.components.support.RxFragment;

import butterknife.ButterKnife;
import me.anany.weikandian.App;
import me.anany.weikandian.utils.LogUtil;

/**
 * Fragment基类
 * <p/>
 * Created by __Berial___
 */
public abstract class BaseFragment extends RxFragment {

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
        prepare();
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
            LogUtil.v("BaseFragment InflateView ---");
            rootView = inflater.inflate(inflateLayoutId(), container, false);
            ButterKnife.bind(this, rootView);
        }

        /**
         *
         *   缓存的rootView需要判断是否已经被加过parent，
         *   如果有parent需要从parent删除，
         *   要不然会发生这个rootview已经有parent的错误。
         */
        ViewGroup parent = (ViewGroup) rootView.getParent();
        if (parent != null) {
            parent.removeView(rootView);
        }
        initViews();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        final App app = (App) getActivity().getApplication();
        app.getWatcher().watch(this);
    }

    @LayoutRes
    protected abstract int inflateLayoutId();

    protected void initViews() {}

    protected void prepare(){}

    public View getRootView(){
        return rootView;
    }
}
