package me.anany.weikandian.ui.pager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.anany.weikandian.App;
import me.anany.weikandian.R;
import me.anany.weikandian.adapter.HomeRecyclerViewAdapter;
import me.anany.weikandian.model.HomeNewsData;
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

    private String position;// 传入顶部Title的ID，用来分别获取每页Pager的数据

    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    private TextView tv_error;
    private List<HomeNewsData.HomeFragmentNewsDataItem> items;

    public HomePager(Context context) {
        mContext = context;
    }

    /**
     * @return
     */
    public View getView() {

        this.position = position;

        LogUtil.e("HomePager  inflate  View ...");
        View view = View.inflate(mContext, R.layout.pager_home, null);
        mRecyclerView = (XRecyclerView) view.findViewById(R.id.recycle_view);
        tv_error = (TextView) view.findViewById(R.id.tv_error);

        initRefresh();

        return view;
    }

    public void initRefresh() {

        items = new ArrayList<HomeNewsData.HomeFragmentNewsDataItem>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);

        homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(mContext, items);

        mRecyclerView.setAdapter(homeRecyclerViewAdapter);

        mRecyclerView.setLoadingListener(this);
    }

    /**
     * 加载每个Pager的内容数据
     *
     * @param position 每页Pager的 catid
     */
    public void initData(String position) {

        if (!hasInitData) {
            App.getApi().getHomeNewsData("WIFI", "2.0.4", position, "c1005", "Nexus 4", "android", "6416405", "1452161771",
                    "7f08bcd287cc5096", "22", "5.1.1", "1", "1452050427", "9", "9279697", "204",
                    "6b64883a89dbf5c36d669baa1bced5de")
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
        } else {
            mRecyclerView.setVisibility(View.GONE);
            tv_error.setVisibility(View.VISIBLE);
        }
    }

    public void setPagerHasInitData(boolean hasInitData) {
        this.hasInitData = hasInitData;
    }


    @Override
    public void onRefresh() {
        // TODO 下拉刷新
    }

    @Override
    public void onLoadMore() {
        // TODO 上拉刷新
    }
}
