package me.anany.weikandian.ui.fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.anany.weikandian.base.BaseFragment;

/**
 * Created by anany on 16/1/6.
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class MineFragment extends BaseFragment {
    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {

        TextView textView = new TextView(mActivity);
        textView.setText("MineFragment");
        return textView;
    }

    @Override
    public void initData() {

    }
}
