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

/**
 * Created by anany on 16/1/6.  首页新闻 Fragment
 *
 * Email:zhujun2730@gmail.com
 */
public class HomeFragment extends BaseFragment {

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

            App.getApi().getHomeNewsTitle("WIFI", "2.0.4", "c1005", "Nexus 4", "android", "6416405",
                    "7f08bcd287cc5096", "22", "5.1.1", "1", "1452050427", "9279697", "204",
                    "6b64883a89dbf5c36d669baa1bced5de")
                    .compose(RxApiThread.convert())
                    .compose(bindUntilEvent(FragmentEvent.PAUSE))
                    .subscribe(this::handleResponseData);

        }
    }

    /**
     * 处理从服务器获取的顶部Title数据
     *
     * @param homeTitleData 服务器返回的bean
     */
    private void handleResponseData(HomeTitleData homeTitleData) {

        hasInitData = true;

        // Title文字的List
        titleTextList = new ArrayList<>();
        titleTextList.add("推荐");// 推荐页要单独处理

        // 内容页Pager的List
        List<HomePager> pagerList = new ArrayList<>();
        pagerList.add(new HomePager(mActivity)); // 推荐页要单独处理

        List<HomeTitleData.HomeFragmentTitleItem> homeTitleDataItems =
                homeTitleData.getItems();

        for (int i = 0; i < homeTitleDataItems.size(); i++) {
            pagerList.add(new HomePager(mActivity));
            titleTextList.add(homeTitleDataItems.get(i).getName());
        }

        HomeTitlePagerAdapter titlePagerAdapter =
                new HomeTitlePagerAdapter(pagerList, homeTitleDataItems, titleTextList);

        //给ViewPager设置适配器
        vp_home.setAdapter(titlePagerAdapter);
        mTabLayout.setupWithViewPager(vp_home);

        //给Tabs设置适配器
        mTabLayout.setTabsFromPagerAdapter(titlePagerAdapter);

        saveHomeTitleDataToDB();
    }

    /**
     * 保存Title数据到数据库
     */
    private void saveHomeTitleDataToDB() {

        DaoSession daoSession = App.getDaoSession(mActivity);
        HomeTitleDBDao homeTitleDao = daoSession.getHomeTitleDBDao();

        for (String name : titleTextList) {

            HomeTitleDB homeTitle = new HomeTitleDB();
            homeTitle.setName(name);

            homeTitleDao.insert(homeTitle);
        }
    }
}
