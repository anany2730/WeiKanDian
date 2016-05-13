package me.anany.weikandian.ui.fragment;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import butterknife.Bind;
import butterknife.OnClick;
import com.trello.rxlifecycle.FragmentEvent;
import me.anany.weikandian.App;
import me.anany.weikandian.R;
import me.anany.weikandian.adapter.HomeTabPagerAdapter;
import me.anany.weikandian.base.BaseFragment;
import me.anany.weikandian.db.dao.DaoSession;
import me.anany.weikandian.db.dao.DaoUtil;
import me.anany.weikandian.db.dao.HomeTitleDBDao;
import me.anany.weikandian.db.entity.HomeTitleDB;
import me.anany.weikandian.model.HomeTitleData;
import me.anany.weikandian.retrofit.RxApiThread;
import me.anany.weikandian.ui.activity.AddHomeChannelActivity;
import me.anany.weikandian.ui.pager.HomePager;
import me.anany.weikandian.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anany on 16/1/6.  首页新闻 Fragment
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class HomeFragment extends BaseFragment {

    private static final int FROM_NET_WORK = 0;
    private static final int FROM_DB = 1;
    private static final int ADD_CHANNEL = 1000;

    @Bind(R.id.vp_home) ViewPager mViewPager;

    @Bind(R.id.tab_layout) TabLayout mTabLayout;

    private boolean hasInitData = false;

    @Override
    protected int inflateLayoutId() {
        return R.layout.fragment_home;
    }

    @OnClick({R.id.ll_add_channel})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_add_channel:
                Intent intent = new Intent(mActivity, AddHomeChannelActivity.class);
                startActivityForResult(intent, ADD_CHANNEL);
                break;
        }
    }

    @Override
    protected void initViews() {

        if (!hasInitData) {

            // 先从数据库取出所有Title，没有的话重新从网络获取
            List<HomeTitleDB> homeTitleDataItems = DaoUtil.getHomeTitleList(mActivity);

            if (homeTitleDataItems != null && !homeTitleDataItems.isEmpty()) {

                List<HomeTitleData.HomeTitleItem> homeTitleItems = new ArrayList<>();

                for (HomeTitleDB homeTitleDB : homeTitleDataItems) {
                    HomeTitleData.HomeTitleItem homeTitleItem =
                            new HomeTitleData.HomeTitleItem();
                    homeTitleItem.setName(homeTitleDB.getName());
                    homeTitleItem.setId(homeTitleDB.getCat_id());
                    homeTitleItems.add(homeTitleItem);
                }

                handleResponseData(homeTitleItems, FROM_DB);

            } else {

                App.getApi().getHomeNewsTitle("WIFI", "2.0.4", "c1005", "Nexus 4",
                        "android", "6416405", "7f08bcd287cc5096", "22",
                        "5.1.1", "1", "1452050427", "9279697", "204",
                        "6b64883a89dbf5c36d669baa1bced5de")
                        .compose(RxApiThread.convert())
                        .compose(bindUntilEvent(FragmentEvent.PAUSE))
                        .map(HomeTitleData::getItems)
                        .subscribe(homeTitleItems ->
                                handleResponseData(homeTitleItems, FROM_NET_WORK), e -> {
                            LogUtil.e(e.getMessage());
                        });
            }

        }
    }


    /**
     * 处理从服务器获取的顶部Title数据
     */
    private void handleResponseData(List<HomeTitleData.HomeTitleItem> homeTitleDataItems, int type) {

        hasInitData = true;

        // Title文字的List
        List<String> titleTextList = new ArrayList<>();
        titleTextList.add("推荐");// 推荐页要单独处理

        // 内容页Pager的List
        List<HomePager> pagerList = new ArrayList<>();

        // 初始化推荐页
        pagerList.add(new HomePager(mActivity));

        for (HomeTitleData.HomeTitleItem homeTitleDataItem : homeTitleDataItems) {
            pagerList.add(new HomePager(mActivity));
            titleTextList.add(homeTitleDataItem.getName());
        }

        HomeTabPagerAdapter mAdapter = new HomeTabPagerAdapter(pagerList, titleTextList);
        mTabLayout.setTabsFromPagerAdapter(mAdapter);
        mViewPager.setAdapter(mAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mViewPager.setCurrentItem(0);

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            private int position;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                this.position = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                // 当滑动结束的时候，才去加载数据。实现【当快速切换ViewPager的时候不会加载数据】
                if (state == 0) {

                    String catid;// 每页请求的catid

                    // 当position == 0 时，catid == 0; 其他从集合中取catid
                    if (position == 0) {
                        catid = "0";
                    } else {
                        catid = homeTitleDataItems.get(position - 1).getId();
                    }

                    // 执行了initData方法，HomePager才会去联网获取数据
                    pagerList.get(position).initData(catid, position);
                    pagerList.get(position).setPagerHasInitData(true);
                    pagerList.get(position).setRecyclerItemClickPosition(Integer.toString(position));
                }
            }
        });

        pagerList.get(0).initData("0", 0);
        pagerList.get(0).setPagerHasInitData(true);

        if (type == FROM_NET_WORK) {

            new Thread(() -> {
                saveHomeTitleDataToDB(homeTitleDataItems);
            }).start();
        }

    }

    /**
     * 保存Title数据到数据库
     */
    private void saveHomeTitleDataToDB(List<HomeTitleData.HomeTitleItem> homeTitleDataItems) {

        DaoSession daoSession = App.getDaoSession(mActivity);
        HomeTitleDBDao homeTitleDao = daoSession.getHomeTitleDBDao();

        for (HomeTitleData.HomeTitleItem item : homeTitleDataItems) {
            HomeTitleDB homeTitle = new HomeTitleDB();
            homeTitle.setName(item.getName());
            homeTitle.setCat_id(item.getId());
            homeTitleDao.insert(homeTitle);
        }
    }
}
