package me.anany.weikandian.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import me.anany.weikandian.utils.LogUtil;

/**
 * Fragment基类
 * <p/>
 * Created by anany
 */
public abstract class BaseFragment extends Fragment {

    protected Context context;
    protected boolean isVisible;
    private View rootView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
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
        LogUtil.e("Fragment onCreateView");
        rootView = inflater.inflate(inflateLayoutId(), container, false);
        ButterKnife.bind(this, rootView);
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
    }

    @LayoutRes
    protected abstract int inflateLayoutId();

    protected void initViews() {
    }

    protected void prepare() {
    }

    public View getRootView() {
        return rootView;
    }
}
