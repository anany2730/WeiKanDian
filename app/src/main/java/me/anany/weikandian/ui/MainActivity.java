package me.anany.weikandian.ui;

import android.support.v4.app.FragmentTabHost;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.trello.rxlifecycle.ActivityEvent;

import me.anany.weikandian.App;
import me.anany.weikandian.AppManager;
import me.anany.weikandian.R;
import me.anany.weikandian.base.BaseActivity;
import me.anany.weikandian.model.HomeNewsData;
import me.anany.weikandian.retrofit.ApiService;
import me.anany.weikandian.retrofit.RxApiThread;
import me.anany.weikandian.ui.fragment.BillboardFragment;
import me.anany.weikandian.ui.fragment.DiscoverFragment;
import me.anany.weikandian.ui.fragment.HomeFragment;
import me.anany.weikandian.ui.fragment.MineFragment;
import me.anany.weikandian.ui.fragment.SubscribeFragment;
import me.anany.weikandian.utils.LogUtil;

import static android.widget.TabHost.OnTabChangeListener;

public class MainActivity extends BaseActivity implements OnTabChangeListener {

    private long exitTime = 0;

    //定义FragmentTabHost对象
    private FragmentTabHost mTabHost;

    //定义一个布局
    private LayoutInflater layoutInflater;

    //定义数组来存放Fragment界面
    private Class fragmentArray[] = {HomeFragment.class, MineFragment.class,
            SubscribeFragment.class, DiscoverFragment.class, BillboardFragment.class};

    //定义数组来存放按钮图片
    private int mImageViewArray[] = {R.drawable.tab_home_btn, R.drawable.tab_subscribe_btn,
            R.drawable.tab_billboard_btn,
            R.drawable.tab_discover_btn,
            R.drawable.tab_mine_btn};

    //定义数组来存放文字颜色
    private int mTextColorArray[] = {R.drawable.tab_home_text_color, R.drawable.tab_subscribe_text_color,
            R.drawable.tab_billboard_text_color,
            R.drawable.tab_discover_text_color,
            R.drawable.tab_mine_text_color};

    //Tab选项卡的文字
    private String mTextviewArray[] = {"首页", "订阅", "排行榜", "发现", "我的"};

    @Override
    protected void initViews() {

        setContentView(R.layout.activity_main);


        ApiService api = App.getApi();
        api.getHomeNewsData("WIFI", "2.0.4", "5", "c1005", "Nexus 4", "android", "6416405",
                "7f08bcd287cc5096", "22", "5.1.1", "1", "1452050427", "9279697", "204",
                "6b64883a89dbf5c36d669baa1bced5de")
                .compose(RxApiThread.convert())
                .compose(bindUntilEvent(ActivityEvent.PAUSE))
                .map(HomeNewsData::toString)
                .subscribe(LogUtil::e);

        //实例化布局对象
        layoutInflater = LayoutInflater.from(this);

        //实例化TabHost对象，得到TabHost
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);

        if (android.os.Build.VERSION.SDK_INT > 10) {
            mTabHost.getTabWidget().setShowDividers(0);
        }


        //得到fragment的个数
        int count = fragmentArray.length;

        for (int i = 0; i < count; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(mTextviewArray[i]).setIndicator(getTabItemView(i));
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
        tv_tab_text.setText(mTextviewArray[index]);
        tv_tab_text.setTextColor(getResources().getColorStateList(mTextColorArray[index]));

        return view;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                Toast.makeText(getApplicationContext(), "再按一次退出程序",
                        Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                AppManager.getAppManager().AppExit(this);
                finish();
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
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

}
