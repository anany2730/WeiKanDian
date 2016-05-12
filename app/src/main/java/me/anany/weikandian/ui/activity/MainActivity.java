package me.anany.weikandian.ui.activity;

import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import me.anany.weikandian.App;
import me.anany.weikandian.AppManager;
import me.anany.weikandian.R;
import me.anany.weikandian.base.BaseActivity;
import me.anany.weikandian.ui.fragment.BillboardFragment;
import me.anany.weikandian.ui.fragment.DiscoverFragment;
import me.anany.weikandian.ui.fragment.HomeFragment;
import me.anany.weikandian.ui.fragment.MineFragment;

import static android.widget.TabHost.OnTabChangeListener;

public class MainActivity extends BaseActivity implements OnTabChangeListener {

    private long exitTime = 0;

    //定义FragmentTabHost对象
    private FragmentTabHost mTabHost;

    //定义一个布局
    private LayoutInflater layoutInflater;

    //定义数组来存放Fragment界面
    private Class[] fragmentArray = {HomeFragment.class,
            BillboardFragment.class, DiscoverFragment.class, MineFragment.class,};

    //定义数组来存放按钮图片
    private int[] mImageViewArray = {R.drawable.tab_home_btn,
            R.drawable.tab_billboard_btn,
            R.drawable.tab_discover_btn,
            R.drawable.tab_mine_btn};

    //定义数组来存放文字颜色
    private int[] mTextColorArray = {R.drawable.tab_home_text_color,
            R.drawable.tab_billboard_text_color,
            R.drawable.tab_discover_text_color,
            R.drawable.tab_mine_text_color};

    //Tab选项卡的文字
    private String[] mTextViewArray = {"首页", "排行榜", "发现", "我的"};

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {

        layoutInflater = LayoutInflater.from(this);
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        if (android.os.Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }

        //得到fragment的个数
        int count = fragmentArray.length;

        for (int i = 0; i < count; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextViewArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮的背景
            // mTabHost.getTabWidget().getChildAt(i).setBackgroundResource(R.drawable.selector_tab_background);
        }
        mTabHost.setOnTabChangedListener(this);
    }

    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tabhost_item_view, null);

        ImageView iv_tab_icon = (ImageView) view.findViewById(R.id.iv_tab_icon);
        iv_tab_icon.setImageResource(mImageViewArray[index]);

        TextView tv_tab_text = (TextView) view.findViewById(R.id.tv_tab_text);
        tv_tab_text.setText(mTextViewArray[index]);
        tv_tab_text.setTextColor(getResources().getColorStateList(mTextColorArray[index]));

        return view;
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
            AppManager.getAppManager().AppExit(this);
        }
    }
}
