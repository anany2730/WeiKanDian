package me.anany.weikandian.ui.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;

import me.anany.weikandian.App;
import me.anany.weikandian.R;
import me.anany.weikandian.base.BaseActivity;
import me.anany.weikandian.ui.fragment.BillboardFragment;
import me.anany.weikandian.ui.fragment.DiscoverFragment;
import me.anany.weikandian.ui.fragment.HomeFragment;
import me.anany.weikandian.ui.fragment.MineFragment;
import me.anany.weikandian.widget.CustomFragmentTabHost;

import static android.widget.TabHost.OnTabChangeListener;
import static me.anany.weikandian.R.id.iv_tab_icon;
import static me.anany.weikandian.R.id.tv_tab_text;

public class MainActivity extends BaseActivity implements OnTabChangeListener {

    private long exitTime = 0;
    private CustomFragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;

    private Class[] fragmentArray = {HomeFragment.class,
            BillboardFragment.class, DiscoverFragment.class, MineFragment.class,};

    private int[] mImageViewArray = {R.drawable.tab_home_btn,
            R.drawable.tab_billboard_btn,
            R.drawable.tab_discover_btn,
            R.drawable.tab_mine_btn};

    private int[] mTextColorArray = {R.drawable.tab_home_text_color,
            R.drawable.tab_billboard_text_color,
            R.drawable.tab_discover_text_color,
            R.drawable.tab_mine_text_color};

    private String[] mTextViewArray = {"首页", "排行榜", "发现", "我的"};

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

        layoutInflater = LayoutInflater.from(this);
        mTabHost = (CustomFragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.fl_content);

        if (android.os.Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }

        int count = fragmentArray.length;
        for (int i = 0; i < count; i++) {
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextViewArray[i]).setIndicator(getTabItemView(i));
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
        }
        mTabHost.setOnTabChangedListener(this);
    }

    private View getTabItemView(int index) {
        View tabView = layoutInflater.inflate(R.layout.tabhost_item_view, null);

        ImageView ivTabIcon = (ImageView) tabView.findViewById(iv_tab_icon);
        ivTabIcon.setImageResource(mImageViewArray[index]);
        TextView tvTabText = (TextView) tabView.findViewById(tv_tab_text);
        tvTabText.setText(mTextViewArray[index]);
        tvTabText.setTextColor(getResources().getColorStateList(mTextColorArray[index]));

        return tabView;
    }

    @Override
    public void onTabChanged(String tabId) {
        final int size = mTabHost.getTabWidget().getTabCount();
        for (int i = 0; i < size; i++) {
            View v = mTabHost.getTabWidget().getChildAt(i);
            if (i == mTabHost.getCurrentTab()) {
                v.setSelected(true);
            } else {
                v.setSelected(false);
            }
        }
        supportInvalidateOptionsMenu();
    }

    @Override
    public void onBackPressed() {
        if ((System.currentTimeMillis() - exitTime) > 2000) {
            App.toast("再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            System.exit(0);
        }
    }
}
