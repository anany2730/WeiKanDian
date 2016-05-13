package me.anany.weikandian.ui.pager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import me.anany.weikandian.App;
import me.anany.weikandian.R;
import me.anany.weikandian.adapter.HomeRecyclerViewAdapter;
import me.anany.weikandian.db.dao.DaoSession;
import me.anany.weikandian.db.dao.DaoUtil;
import me.anany.weikandian.db.dao.HomeItemDBDao;
import me.anany.weikandian.db.entity.HomeItemDB;
import me.anany.weikandian.listener.RecyclerItemClickListener;
import me.anany.weikandian.model.HomeNewsData;
import me.anany.weikandian.model.HomeNewsDataItem;
import me.anany.weikandian.retrofit.RxApiThread;
import me.anany.weikandian.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anany on 16/1/7.
 *
 *
 * ViewPager 适配器 填充的 Pager【返回View给HomeTitlePagerAdapter适配器】
 *
 *
 * Email:zhujun2730@gmail.com
 */
public class HomePager implements XRecyclerView.LoadingListener {

    private final int PULL_TO_REFRESH = 0;
    private final int LOAD_MORE = 1;

    private XRecyclerView mRecyclerView;
    private RecyclerItemClickListener recyclerItemClickListener;
    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    private TextView mTextViewError;
    private ProgressBar mProgressBar;

    private Context mContext;
    private List<HomeNewsDataItem> homeNewsDataItems;

    private int step = 1;// 每次下拉刷新，递增传递此参数获取新的数据
    private int position; // 每一页的位置
    private String requestTime;
    private String catId;// 传入顶部Title的ID，用来分别获取每页Pager的数据

    private boolean hasInitData = false;

    public HomePager(Context context) {
        this.mContext = context;
    }

    /**
     * 初始化View
     *
     * @param pagerPosition 每页的位置
     *
     * @return 每一页的视图View【将View集中缓存到LinkedHashMap ，避免多次inflate】
     */
    public View inflateView(String pagerPosition) {

        LogUtil.e("HomePager "+pagerPosition+" inflate  View ...");

        View mRootView = View.inflate(mContext, R.layout.pager_home, null);

        mRecyclerView = (XRecyclerView) mRootView.findViewById(R.id.recycle_view);
        mRecyclerView.setLaodingMoreProgressStyle(ProgressStyle.CubeTransition);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.CubeTransition);

        mTextViewError = (TextView) mRootView.findViewById(R.id.tv_error);
        mProgressBar = (ProgressBar) mRootView.findViewById(R.id.pb_pager_loading);

        initRefresh(pagerPosition);

        return mRootView;
    }

    /**
     * 初始化RecyclerView ，但并不立即加载数据
     *
     * @param pagerPosition 当前页的position
     */
    private void initRefresh(String pagerPosition) {

        homeNewsDataItems = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        if ("0".equals(pagerPosition)) {

            // 添加推荐页的header Item
            View v = View.inflate(mContext, R.layout.header_home_pager, null);
            v.findViewById(R.id.tv_invited_friends).
                    setOnClickListener(new HeaderItemClickListener());
            v.findViewById(R.id.tv_fav).
                    setOnClickListener(new HeaderItemClickListener());
            v.findViewById(R.id.tv_today).
                    setOnClickListener(new HeaderItemClickListener());
            v.findViewById(R.id.tv_sign).
                    setOnClickListener(new HeaderItemClickListener());
            mRecyclerView.addHeaderView(v);
        }

        homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(mContext, homeNewsDataItems);

        mRecyclerView.setAdapter(homeRecyclerViewAdapter);

        mRecyclerView.setLoadingListener(this);

        // 由于该死的ViewPager缓存机制，第一次初始化的时候会加载两页pager
        recyclerItemClickListener = new RecyclerItemClickListener(mContext, Integer.toString(position));

        homeRecyclerViewAdapter.setOnItemClickListener(recyclerItemClickListener);

    }

    /**
     * 设置是否已经初始化数据
     *
     * @param hasInitData true 为已经初始化数据了
     *                    false 重置初始状态为还未获取
     */
    public void setPagerHasInitData(boolean hasInitData) {
        this.hasInitData = hasInitData;
    }

    public void setRecyclerItemClickPosition(String position) {
        recyclerItemClickListener.setPagerPosition(position);
    }

    /**
     * 加载每个Pager的内容数据
     *
     * @param catId    每页Pager的请求 catid
     * @param position 每页Pager在viewpager中的position
     */
    public void initData(String catId, int position) {

        this.position = position;
        this.catId = catId;

        if (!hasInitData) {

            requestTime = Long.toString(System.currentTimeMillis());
            requestTime = requestTime.substring(0, 10);

            App.getApi().getHomeNewsData("WIFI", "2.0.4",
                    catId, "c1005", "Nexus 4", "android", "6416405", "1453274918",
                    "7f08bcd287cc5096", "22", "5.1.1", "2", requestTime, Integer.toString(step),
                    "9279697", "355136051237892", "204",
                    "fcd163d6ed68ef79784848eb1b1fc842")
                    .compose(RxApiThread.convert())
                    .subscribe(homeNewsData -> {
                        handleResponseData(homeNewsData, PULL_TO_REFRESH);
                    }, e -> {

                        App.toast("网络君暂时出了一些问题");
                        mProgressBar.setVisibility(View.GONE);
                        mRecyclerView.refreshComplete();

                    });
        }
    }

    private void handleResponseData(HomeNewsData homeNewsData, int type) {

        if (type == PULL_TO_REFRESH) { // 下拉刷新

            mProgressBar.setVisibility(View.GONE);
            setPagerHasInitData(true);

            if (homeNewsData.getItems() != null) {

                mTextViewError.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);

                LogUtil.e(homeNewsData.toString());

                this.homeNewsDataItems.clear();
                this.homeNewsDataItems.addAll(homeNewsData.getItems());
                homeRecyclerViewAdapter.notifyDataSetChanged();


                new Thread(() -> {
                    saveDataToDB(PULL_TO_REFRESH, homeNewsData.getItems());
                }).start();

            } else if (homeNewsDataItems.isEmpty()) {
                mTextViewError.setVisibility(View.VISIBLE);
            }

            mRecyclerView.refreshComplete();

            step ++;

        } else { // 加载更多

            if (homeNewsData.getItems() != null) {

                mTextViewError.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
                LogUtil.e(homeNewsData.toString());
                homeNewsDataItems.addAll(homeNewsData.getItems());
                homeRecyclerViewAdapter.notifyDataSetChanged();


                new Thread(() -> {
                    saveDataToDB(LOAD_MORE, homeNewsData.getItems());
                }).start();
            }

            mRecyclerView.loadMoreComplete();
        }
    }

    @Override
    public void onRefresh() {
        setPagerHasInitData(false); // 重置加载状态为可加载

        initData(catId, position);
    }

    @Override
    public void onLoadMore() {

        // 先从数据库获取当前pager最后一条数据的时间
        String inputTime = DaoUtil.getHomeItemInput_time(mContext, position);

        if (!TextUtils.isEmpty(inputTime)) {
            getDataMore(catId, inputTime);
        } else {
            mRecyclerView.loadMoreComplete();
        }
    }

    /**
     * 加载更多请求
     *
     * @param catId 每页Pager的 catId
     * @param maxTime 每页Pager最后一条数据的时间
     */
    private void getDataMore(String catId, String maxTime) {

        this.catId = catId;
        this.requestTime = Long.toString(System.currentTimeMillis());
        this.requestTime = requestTime.substring(0, 10);

        App.getApi().getHomeNewsDataMore("WIFI", "2.0.4", catId, "c1005",
                "Nexus 4", "android", "6416405", maxTime, "7f08bcd287cc5096",
                "22", "5.1.1", "2", requestTime, "9279697", "355136051237892", "204",
                "3d3f7cf7d82228f8fd555c9c20961d99")
                .compose(RxApiThread.convert())
                .subscribe(homeNewsData -> {
                    handleResponseData(homeNewsData, LOAD_MORE);
                }, e -> {

                    App.toast("网络君暂时出了一些问题");
                    mProgressBar.setVisibility(View.GONE);
                    mRecyclerView.refreshComplete();
                });
    }

    private void saveDataToDB(int type, List<HomeNewsDataItem> homeNewsDataItems) {

        DaoSession daoSession = App.getDaoSession(mContext);
        HomeItemDBDao homeNewsDataItemDao = daoSession.getHomeItemDBDao();

        // 如果是下拉刷新的时候先把之前的数据删除了
        if (type == PULL_TO_REFRESH) {
            String sql = "delete from  HomeItemDB where position = " + position;
            daoSession.getDatabase().execSQL(sql);
        }

        for (HomeNewsDataItem homeNewsDataItem : homeNewsDataItems) {
            HomeItemDB homeItemDB = new HomeItemDB();
            homeItemDB.setPosition(Integer.toString(position));
            homeItemDB.setCt(requestTime);
            // input_time是作为加载更多的max_time参数请求服务器
            homeItemDB.setInput_time(homeNewsDataItem.getInput_time());
            homeItemDB.setCatid(homeNewsDataItem.getCatid());
            homeNewsDataItemDao.insert(homeItemDB);
        }
    }

    private class HeaderItemClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tv_fav:   //兴趣选择
                    break;
                case R.id.tv_today:
                    break;
                case R.id.tv_sign:
                    break;
                case R.id.tv_invited_friends:
                    break;
            }
        }
    }

}
