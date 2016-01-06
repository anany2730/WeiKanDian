package me.anany.weikandian.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.trello.rxlifecycle.FragmentEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.anany.weikandian.App;
import me.anany.weikandian.R;
import me.anany.weikandian.base.BaseFragment;
import me.anany.weikandian.model.HomeTitleData;
import me.anany.weikandian.retrofit.ApiService;
import me.anany.weikandian.retrofit.RxApiThread;
import me.anany.weikandian.utils.LogUtil;
import rx.functions.Action1;

/**
 * Created by anany on 16/1/6.
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;

    @Bind(R.id.vp_home)
    ViewPager vp_home;

    private List<HomeTitleData.HomeFragmentTitleItem> titleItems;

    private List<String> titleTextList = new ArrayList<String>();


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {


        View view = View.inflate(mActivity, R.layout.fragment_home,null);

        ButterKnife.bind(this,view);


        return view;
    }

    @Override
    public void initData() {

        LogUtil.e("HomeFragment initData");

        ApiService api = App.getApi();

        api.getHomeNewsTitle("WIFI", "2.0.4", "c1005", "Nexus 4", "android", "6416405",
                "7f08bcd287cc5096", "22", "5.1.1", "1", "1452050427", "9279697", "204",
                "6b64883a89dbf5c36d669baa1bced5de")
                .compose(RxApiThread.convert())
                .compose(bindUntilEvent(FragmentEvent.PAUSE))
                .subscribe(

                        new Action1<HomeTitleData>() {
                            @Override
                            public void call(HomeTitleData homeTitleData) {
                                handleResponseData(homeTitleData);
                            }
                        }
                );
    }

    private void handleResponseData(HomeTitleData homeTitleData) {

        // 成功从服务拿到数据、保存到SharePreference
        titleTextList.add("推荐");

        titleItems = homeTitleData.getItems();
        for (HomeTitleData.HomeFragmentTitleItem item: titleItems){
            String name = item.getName();
            titleTextList.add(name);
        }

        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                vp_home.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        vp_home.setAdapter(mAdapter);//给ViewPager设置适配器
        mTabLayout.setupWithViewPager(vp_home);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);//给Tabs设置适配器
    }

    private PagerAdapter mAdapter = new PagerAdapter() {
        @Override
        public CharSequence getPageTitle(int position) {
            return titleTextList.get(position);
        }

        @Override
        public int getCount() {
            return titleTextList.size();
        }

        @Override
        public Object instantiateItem(View container, int position) {
            TextView tv = new TextView(mActivity);
            tv.setTextSize(30.f);
            tv.setText(titleTextList.get(position));
            ((ViewPager) container).addView(tv);
            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((ViewPager) container).removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    };
}
