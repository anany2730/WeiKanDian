package me.anany.weikandian.ui.pager;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
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

    public Context mContext;

    private XRecyclerView mRecyclerView;

    private boolean hasInitData = false;

    private String catid;// 传入顶部Title的ID，用来分别获取每页Pager的数据

    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    private TextView tv_error;
    private List<HomeNewsDataItem> items;

    private int step = 1;// 每次下拉刷新，递增传递此参数获取新的数据

    private String requestTime;

    public HomePager(Context context) {
        mContext = context;
    }

    /**
     * @return  每一页的视图View【将View集中缓存到LinkedHashMap ，避免多次inflate】
     */
    public View getView() {

        LogUtil.e("HomePager  inflate  View ...");
        View view = View.inflate(mContext, R.layout.pager_home, null);
        mRecyclerView = (XRecyclerView) view.findViewById(R.id.recycle_view);
        tv_error = (TextView) view.findViewById(R.id.tv_error);

        initRefresh();

        return view;
    }

    public void initRefresh() {

        items = new ArrayList<HomeNewsDataItem>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(mContext, items);

        mRecyclerView.setAdapter(homeRecyclerViewAdapter);

        mRecyclerView.setLoadingListener(this);
    }

    /**
     * 设置Pager 已经加载过了【避免重复加载】
     *
     * @param hasInitData
     */
    public void setPagerHasInitData(boolean hasInitData) {
        this.hasInitData = hasInitData;
    }

    /**
     * 加载每个Pager的内容数据
     *
     * @param catid 每页Pager的 catid
     */
    public void initData(String catid) {

        this.catid = catid;

        LogUtil.e(catid);

        if (!hasInitData) {

            requestTime =
                    System.currentTimeMillis() + "";

            App.getApi().getHomeNewsData("WIFI", "2.0.4", catid, "c1005", "Nexus 4", "android", "6416405", "1452480703",
                    "7f08bcd287cc5096", "22", "5.1.1", "2", requestTime, step + "", "9279697", "355136051237892", "204",
                    "bec53ecbb89477589484bb05cbae74b0")
                    .compose(RxApiThread.convert())
                    .subscribe(this::handleResponseData);
        } else {
            LogUtil.e("pager has initData");
        }
    }

    /**
     * 处理服务器返回的 首页每页Pager的列表数据源
     *
     * @param homeNewsData
     */
    private void handleResponseData(HomeNewsData homeNewsData) {

        if (homeNewsData != null && homeNewsData.getItems() != null) {
            tv_error.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            LogUtil.e(homeNewsData.toString());
            items.clear();
            items.addAll(homeNewsData.getItems());
            homeRecyclerViewAdapter.notifyDataSetChanged();

            // TODO 保存HomeNewsData数据到数据库
            saveDataToDB(0, homeNewsData.getItems());

        } else {
            mRecyclerView.setVisibility(View.GONE);
            tv_error.setVisibility(View.VISIBLE);
        }

        mRecyclerView.refreshComplete();
    }

    private void saveDataToDB(int type, List<HomeNewsDataItem> homeNewsDataItems) {

        // 先把之前的数据删除了


        DaoSession daoSession = App.getDaoSession(mContext);
        HomeItemDBDao homeNewsDataItemDao = daoSession.getHomeItemDBDao();

        if (type == 0) {

            String sql = "delete from  HomeItemDB where position = " + catid;

            daoSession.getDatabase().execSQL(sql);

        }

        for (HomeNewsDataItem bean : homeNewsDataItems) {

            HomeItemDB homeItemDB = new HomeItemDB();
            homeItemDB.setPosition(catid);
            homeItemDB.setCt(requestTime);
            long insert = homeNewsDataItemDao.insert(homeItemDB);
            //LogUtil.e("插入数据库：" + insert);
        }
    }




    @Override
    public void onRefresh() {
        LogUtil.e("下拉刷新");

        setPagerHasInitData(false); // 重置加载状态为可加载

        step++;

        initData(catid);
    }

    @Override
    public void onLoadMore() {
        // TODO 上拉刷新

        LogUtil.e("上拉刷新");

        DaoSession daoSession = App.getDaoSession(mContext);
       // HomeItemDBDao homeNewsDataItemDao = daoSession.getHomeItemDBDao();

        String sql = "select * from  HomeItemDB where position = " + catid;

        Cursor cursor = daoSession.getDatabase().rawQuery(sql, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToNext();
            String columnName = cursor.getString(4);

            LogUtil.e("数据库：" + columnName);

            getDataMore(catid, columnName);

        } else {
            mRecyclerView.loadMoreComplete();
        }


        // LogUtil.e("查询出来的数据："+dbBean.getCt());
    }

    /**
     * 加载更多请求
     *
     * @param catid 每页Pager的 catid
     */
    public void getDataMore(String catid, String maxTime) {

        this.catid = catid;
        this.requestTime = System.currentTimeMillis() + "";


        App.getApi().getHomeNewsDataMore("WIFI", "2.0.4", catid, "c1005", "Nexus 4", "android", "6416405", maxTime, "7f08bcd287cc5096",
                "22", "5.1.1", "2", requestTime, "9279697", "355136051237892", "204",
                "3d3f7cf7d82228f8fd555c9c20961d99")
                .compose(RxApiThread.convert())
                .subscribe(this::handleResponseDataMore);

    }

    /**
     * 处理服务器返回更多的 首页每页Pager的列表数据源
     *
     * @param homeNewsData
     */
    private void handleResponseDataMore(HomeNewsData homeNewsData) {

        if (homeNewsData != null && homeNewsData.getItems() != null) {

            tv_error.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
            LogUtil.e(homeNewsData.toString());
            items.addAll(homeNewsData.getItems());
            homeRecyclerViewAdapter.notifyDataSetChanged();

            // TODO 保存更多的数据到数据库
            saveDataToDB(1, homeNewsData.getItems());

        }

        mRecyclerView.loadMoreComplete();
    }
}
