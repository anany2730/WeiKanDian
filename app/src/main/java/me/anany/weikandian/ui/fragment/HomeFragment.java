package me.anany.weikandian.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.trello.rxlifecycle.FragmentEvent;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import me.anany.bean.HomeTitleDB;
import me.anany.dao.DaoSession;
import me.anany.dao.HomeTitleDBDao;
import me.anany.weikandian.App;
import me.anany.weikandian.R;
import me.anany.weikandian.adapter.HomeTitlePagerAdapter;
import me.anany.weikandian.base.BaseFragment;
import me.anany.weikandian.model.HomeTitleData;
import me.anany.weikandian.retrofit.RxApiThread;
import me.anany.weikandian.ui.pager.HomePager;
import me.anany.weikandian.utils.LogUtil;

/**
 * Created by anany on 16/1/6.  首页新闻 Fragment
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class HomeFragment extends BaseFragment {

    private static final int FROM_NET_WORK = 0;
    private static final int FROM_DB = 1;

    @Bind(R.id.tab_layout)
    TabLayout mTabLayout;

    @Bind(R.id.vp_home)
    ViewPager vp_home;

    private List<String> titleTextList;

    private boolean hasInitData = false;

    @Override
    protected int inflateLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initViews() {

        if (!hasInitData) {

            // 先从数据库取出所有Title，没有的话重新从网络获取
            DaoSession daoSession = App.getDaoSession(mActivity);
            HomeTitleDBDao titleDao = daoSession.getHomeTitleDBDao();
            List<HomeTitleDB> homeTitleDataItems = titleDao.loadAll();

            if (homeTitleDataItems != null && homeTitleDataItems.size() > 0) {

                LogUtil.e("从数据库取Title");
                List<HomeTitleData.HomeTitleItem> homeTitleItems = new ArrayList<>();

                for (HomeTitleDB homeTitleDB : homeTitleDataItems) {
                    HomeTitleData.HomeTitleItem homeTitleItem = new HomeTitleData.HomeTitleItem();
                    homeTitleItem.setName(homeTitleDB.getName());
                    homeTitleItem.setId(homeTitleDB.getCat_id());
                    homeTitleItems.add(homeTitleItem);
                }

                handleResponseData(homeTitleItems, FROM_DB);

            } else {

                App.getApi().getHomeNewsTitle("WIFI", "2.0.4", "c1005", "Nexus 4", "android", "6416405",
                        "7f08bcd287cc5096", "22", "5.1.1", "1", "1452050427", "9279697", "204",
                        "6b64883a89dbf5c36d669baa1bced5de")
                        .compose(RxApiThread.convert())
                        .compose(bindUntilEvent(FragmentEvent.PAUSE))
                        .map(HomeTitleData::getItems)
                        .subscribe(homeTitleItems -> handleResponseData(homeTitleItems, FROM_NET_WORK));
            }

        }
    }

    /**
     * 处理从服务器获取的顶部Title数据
     */
    private void handleResponseData(List<HomeTitleData.HomeTitleItem> homeTitleDataItems, int type) {

        hasInitData = true;

        // Title文字的List
        titleTextList = new ArrayList<>();
        titleTextList.add("推荐");// 推荐页要单独处理

        // 内容页Pager的List
        List<HomePager> pagerList = new ArrayList<>();
        pagerList.add(new HomePager(this)); // 推荐页要单独处理


        for (int i = 0; i < homeTitleDataItems.size(); i++) {
            pagerList.add(new HomePager(this));
            titleTextList.add(homeTitleDataItems.get(i).getName());
        }

        HomeTitlePagerAdapter titlePagerAdapter =
                new HomeTitlePagerAdapter(pagerList, homeTitleDataItems, titleTextList);

        //给ViewPager设置适配器
        vp_home.setAdapter(titlePagerAdapter);
        mTabLayout.setupWithViewPager(vp_home);

        vp_home.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            int position;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                this.position = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

                // 当滑动结束的时候，才去加载数据

                if (state == 0) {
                    String catid;

                    if (position == 0) {
                        catid = "0"; // 每页请求的catid
                    } else {
                        catid = homeTitleDataItems.get(position - 1).getId();
                    }

                    pagerList.get(position).initData(catid);
                    pagerList.get(position).setPagerHasInitData(true);
                }
            }
        });

        pagerList.get(0).initData("0");// 初始化加载第一页

        //给Tabs设置适配器
        mTabLayout.setTabsFromPagerAdapter(titlePagerAdapter);

        if (type == FROM_NET_WORK) {
            saveHomeTitleDataToDB(homeTitleDataItems);
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
