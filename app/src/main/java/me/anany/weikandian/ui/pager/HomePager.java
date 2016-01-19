package me.anany.weikandian.ui.pager;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.anany.bean.HomeItemDB;
import me.anany.dao.DaoSession;
import me.anany.dao.HomeItemDBDao;
import me.anany.weikandian.App;
import me.anany.weikandian.R;
import me.anany.weikandian.adapter.HomeRecyclerViewAdapter;
import me.anany.weikandian.model.HomeNewsData;
import me.anany.weikandian.model.HomeNewsDataItem;
import me.anany.weikandian.retrofit.RxApiThread;
import me.anany.weikandian.ui.fragment.HomeFragment;
import me.anany.weikandian.utils.LogUtil;

/**
 * Created by anany on 16/1/7.
 * <p>
 * <p>
 * ViewPager 适配器 填充的 Pager【返回View给HomeTitlePagerAdapter适配器】
 * <p>
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class HomePager implements XRecyclerView.LoadingListener {

    private static final int LOAD_MORE = 1;
    private static final int PULL_TO_REFRESH = 0;

    public Context mContext;

    private XRecyclerView mRecyclerView;

    private boolean hasInitData = false;

    private String catId;// 传入顶部Title的ID，用来分别获取每页Pager的数据

    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    private TextView tv_error;
    private List<HomeNewsDataItem> items;

    private int step = 1;// 每次下拉刷新，递增传递此参数获取新的数据

    private String requestTime;
    private View mRootView;

    private HomeFragment homeFragment;
    private ProgressBar pb_pager_loading;

    public HomePager(HomeFragment homeFragment) {
        this.mContext = homeFragment.mActivity;
        this.homeFragment = homeFragment;
    }

    /**
     * @return 每一页的视图View【将View集中缓存到LinkedHashMap ，避免多次inflate】
     */
    public View inflateView() {

        LogUtil.e("HomePager  inflate  View ...");
        mRootView = View.inflate(mContext, R.layout.pager_home, null);
        mRecyclerView = (XRecyclerView) mRootView.findViewById(R.id.recycle_view);
        tv_error = (TextView) mRootView.findViewById(R.id.tv_error);
        pb_pager_loading = (ProgressBar) mRootView.findViewById(R.id.pb_pager_loading);

        initRefresh();

        return mRootView;
    }

    public void initRefresh() {

        items = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(mContext, items);

        mRecyclerView.setAdapter(homeRecyclerViewAdapter);

        mRecyclerView.setLoadingListener(this);
    }


    public void setPagerHasInitData(boolean hasInitData) {
        this.hasInitData = hasInitData;
    }

    /**
     * 加载每个Pager的内容数据
     *
     * @param catId 每页Pager的 catid
     */
    public void initData(String catId) {

        this.catId = catId;

        LogUtil.e("catId："+catId+"，getVisibility：");

        if (!hasInitData) {

            requestTime =
                    System.currentTimeMillis() + "";

            App.getApi().getHomeNewsData("WIFI", "2.0.4", catId, "c1005", "Nexus 4", "android", "6416405", "1452480703",
                    "7f08bcd287cc5096", "22", "5.1.1", "2", requestTime, step + "", "9279697", "355136051237892", "204",
                    "bec53ecbb89477589484bb05cbae74b0")
                    .compose(RxApiThread.convert())
                    .subscribe(this::handleResponseData);
        }
    }

    private void handleResponseData(HomeNewsData homeNewsData) {

        LogUtil.e(homeNewsData.toString());

        pb_pager_loading.setVisibility(View.GONE);

        if (homeNewsData != null && homeNewsData.getItems() != null) {

            tv_error.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);

            LogUtil.e(homeNewsData.toString());

            items.clear();
            items.addAll(homeNewsData.getItems());
            homeRecyclerViewAdapter.notifyDataSetChanged();

            saveDataToDB(PULL_TO_REFRESH, homeNewsData.getItems());

        } else {
            tv_error.setVisibility(View.VISIBLE);
        }

        mRecyclerView.refreshComplete();
    }

    @Override
    public void onRefresh() {
        LogUtil.e("下拉刷新");

        setPagerHasInitData(false); // 重置加载状态为可加载

        step ++;

        initData(catId);
    }

    @Override
    public void onLoadMore() {

        LogUtil.e("上拉刷新");

        DaoSession daoSession = App.getDaoSession(mContext);

        String sql = "select * from  HomeItemDB where position = " + catId;

        Cursor cursor = daoSession.getDatabase().rawQuery(sql, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToNext();

            String columnName = cursor.getString(4);

            LogUtil.e("数据库：" + columnName);

            getDataMore(catId, columnName);

            cursor.close();
        } else {
            mRecyclerView.loadMoreComplete();
        }

    }

    /**
     * 加载更多请求
     *
     * @param catid 每页Pager的 catid
     */
    public void getDataMore(String catid, String maxTime) {

        this.catId = catid;
        this.requestTime = System.currentTimeMillis() + "";


        App.getApi().getHomeNewsDataMore("WIFI", "2.0.4", catid, "c1005", "Nexus 4", "android", "6416405", maxTime, "7f08bcd287cc5096",
                "22", "5.1.1", "2", requestTime, "9279697", "355136051237892", "204",
                "3d3f7cf7d82228f8fd555c9c20961d99")
                .compose(RxApiThread.convert())
                .subscribe(this::handleResponseDataMore);

    }

    private void handleResponseDataMore(HomeNewsData homeNewsData) {

        if (homeNewsData != null && homeNewsData.getItems() != null) {

            tv_error.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            LogUtil.e(homeNewsData.toString());
            items.addAll(homeNewsData.getItems());
            homeRecyclerViewAdapter.notifyDataSetChanged();

            saveDataToDB(LOAD_MORE, homeNewsData.getItems());

        }

        mRecyclerView.loadMoreComplete();
    }

    private void saveDataToDB(int type, List<HomeNewsDataItem> homeNewsDataItems) {

        DaoSession daoSession = App.getDaoSession(mContext);
        HomeItemDBDao homeNewsDataItemDao = daoSession.getHomeItemDBDao();

        // 如果是下拉刷新的时候先把之前的数据删除了
        if (type == PULL_TO_REFRESH) {

            String sql = "delete from  HomeItemDB where position = " + catId;

            daoSession.getDatabase().execSQL(sql);

        }

        for (HomeNewsDataItem homeNewsDataItem : homeNewsDataItems) {

            HomeItemDB homeItemDB = new HomeItemDB();
            homeItemDB.setPosition(catId);
            homeItemDB.setCt(requestTime);
            homeItemDB.setCatid(homeNewsDataItem.getCatid());

            homeNewsDataItemDao.insert(homeItemDB);
        }
    }
}
