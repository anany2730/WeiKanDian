package me.anany.weikandian.ui.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import me.anany.weikandian.App;
import me.anany.weikandian.R;
import me.anany.weikandian.adapter.HomeTabPagerAdapter;
import me.anany.weikandian.base.BaseFragment;
import me.anany.weikandian.db.HomeTitleDao;
import me.anany.weikandian.entry.HomeTitle;
import me.anany.weikandian.entry.HomeTitleData;
import me.anany.weikandian.retrofit.RxApiThread;
import me.anany.weikandian.ui.activity.AddHomeChannelActivity;
import me.anany.weikandian.ui.pager.HomePager;
import me.anany.weikandian.utils.LogUtil;
import rx.Subscription;

/**
 * Created by anany on 16/1/6.  首页新闻 Fragment
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class HomeFragment extends BaseFragment {

    private static final int FROM_NET_WORK = 0;
    private static final int FROM_DB = 1;
    private static final int ADD_CHANNEL = 1000;

    @Bind(R.id.vp_home)
    ViewPager mViewPager;

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;

    private boolean hasInitData = false;
    private ArrayList<HomePager> pagerList;
    private Subscription getTitleSubscribe;

    @Override
    protected int inflateLayoutId() {
        return R.layout.fragment_home;
    }

    @OnClick({R.id.ll_add_channel})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_add_channel:
                Intent intent = new Intent(context, AddHomeChannelActivity.class);
                startActivityForResult(intent, ADD_CHANNEL);
                break;
        }
    }

    @Override
    protected void initViews() {
        if (!hasInitData) {
            HomeTitleDao homeTitleDao = App.getDaoSession().getHomeTitleDao();
            List<HomeTitle> homeTitleItemsDB = homeTitleDao.queryBuilder().build().list();

            if (homeTitleItemsDB.isEmpty()) {   // 先从数据库取出所有Title，没有的话重新从网络获取
                getTitleSubscribe = App.getApi().getHomeNewsTitle("WIFI", "c1004",
                        "3.4.1", "341", "android")
                        .compose(RxApiThread.convert())
                        .map(HomeTitleData::getItems)
                        .subscribe(homeTitleItems ->
                                handleResponseData(homeTitleItems, FROM_NET_WORK), e -> {
                            LogUtil.e(e.getMessage());
                        });
            } else {
                handleResponseData(homeTitleItemsDB, FROM_DB);
            }
        }
    }

    private void handleResponseData(List<HomeTitle> homeTitleItems, int type) {
        hasInitData = true;

        List<String> titleTextList = new ArrayList<>();
        titleTextList.add("推荐");

        pagerList = new ArrayList<>();
        pagerList.add(new HomePager(context));

        for (HomeTitle homeTitle : homeTitleItems) {
            pagerList.add(new HomePager(context));
            titleTextList.add(homeTitle.getName());
        }

        HomeTabPagerAdapter mAdapter = new HomeTabPagerAdapter(pagerList, titleTextList);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int position;

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                this.position = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                // 当滑动停止的时候，才去加载数据
                if (state == 0) {
                    if (position == 0) {
                        pagerList.get(position).refreshData("0");
                    } else {
                        String catId = homeTitleItems.get(position - 1).getId();
                        pagerList.get(position).refreshData(catId);
                    }
                }
            }
        });

        mViewPager.postDelayed(() -> pagerList.get(0).refreshData("0"), 200);

        if (type == FROM_NET_WORK) {
            new Thread(() -> {
                saveHomeTitleDataToDB(homeTitleItems);
            }).start();
        }
    }

    private void saveHomeTitleDataToDB(List<HomeTitle> homeTitleDataItems) {
        HomeTitleDao homeTitleDao = App.getDaoSession().getHomeTitleDao();
        homeTitleDao.deleteAll();
        homeTitleDao.insertInTx(homeTitleDataItems);
    }

    @Override
    public void onDestroy() {
        if (getTitleSubscribe != null) {
            getTitleSubscribe.unsubscribe();
        }
        for (HomePager homePager : pagerList) {
            homePager.onDestroy();
        }
        super.onDestroy();
    }
}
