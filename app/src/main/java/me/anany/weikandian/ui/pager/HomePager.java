package me.anany.weikandian.ui.pager;

import android.content.Context;
import android.os.SystemClock;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.anany.weikandian.App;
import me.anany.weikandian.R;
import me.anany.weikandian.adapter.HomeRecyclerViewAdapter;
import me.anany.weikandian.listener.RecyclerItemClickListener;
import me.anany.weikandian.entry.HomeNewsData;
import me.anany.weikandian.retrofit.RxApiThread;
import me.anany.weikandian.utils.LogUtil;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

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

    private final int PULL_TO_REFRESH = 0;
    private final int LOAD_MORE = 1;

    private Context mContext;
    private XRecyclerView mRecyclerView;
    private HomeRecyclerViewAdapter homeRecyclerViewAdapter;
    private TextView mTextViewError;
    private ProgressBar mProgressBar;

    private boolean hasInitData = false;
    private List<HomeNewsData.ItemsBean> homeNewsDataItems;
    private Subscription getMoreSubscribe;
    private CompositeSubscription mSubscriptions;

    private int pullStep = 1;
    private int moreStep = 1;
    private String catId;

    public HomePager(Context context) {
        this.mContext = context;
    }

    public View inflateView() {
        LogUtil.e("HomePager inflate View ...");

        View mRootView = View.inflate(mContext, R.layout.pager_home, null);
        mRecyclerView = (XRecyclerView) mRootView.findViewById(R.id.recycle_view);
        mRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.CubeTransition);
        mRecyclerView.setRefreshProgressStyle(ProgressStyle.CubeTransition);
        mTextViewError = (TextView) mRootView.findViewById(R.id.tv_error);
        mProgressBar = (ProgressBar) mRootView.findViewById(R.id.pb_pager_loading);

        initPagerView();
        return mRootView;
    }

    private void initPagerView() {
        homeNewsDataItems = new ArrayList<>();
        mSubscriptions = new CompositeSubscription();

        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        homeRecyclerViewAdapter = new HomeRecyclerViewAdapter(mContext, homeNewsDataItems);
        mRecyclerView.setAdapter(homeRecyclerViewAdapter);
        mRecyclerView.setLoadingListener(this);
        homeRecyclerViewAdapter.setOnItemClickListener(new RecyclerItemClickListener(mContext));
    }

    public void refreshData(String catId) {
        this.catId = catId;
        if (!hasInitData) {
            Subscription subscribe = App.getApi().getHomeNewsData(catId, pullStep, 0,
                    13946282, SystemClock.currentThreadTimeMillis())
                    .compose(RxApiThread.convert())
                    .subscribe(homeNewsData -> {
                        hasInitData = true;
                        handleResponseData(homeNewsData, PULL_TO_REFRESH);
                    }, e -> {
                        App.toast("网络君暂时出了一些问题");
                        mProgressBar.setVisibility(View.GONE);
                        mRecyclerView.refreshComplete();
                    });
            mSubscriptions.add(subscribe);
        }
    }

    private void getDataMore() {
        if (getMoreSubscribe != null && getMoreSubscribe.isUnsubscribed()) {
            getMoreSubscribe.unsubscribe();
        }
        getMoreSubscribe = App.getApi().getHomeNewsDataMore(catId, moreStep,
                1, 13946282, SystemClock.currentThreadTimeMillis())
                .delay(500, TimeUnit.MILLISECONDS)
                .compose(RxApiThread.convert())
                .subscribe(homeNewsData -> {
                    handleResponseData(homeNewsData, LOAD_MORE);
                }, e -> {
                    App.toast("网络君暂时出了一些问题");
                });
        mSubscriptions.add(getMoreSubscribe);
    }

    private void handleResponseData(HomeNewsData homeNewsData, int type) {
        if (type == PULL_TO_REFRESH) { // 下拉刷新
            mProgressBar.setVisibility(View.GONE);
            if (homeNewsData.getItems() != null) {
                pullStep = homeNewsData.getStep();
                mRecyclerView.setLoadingMoreEnabled(true);
                mTextViewError.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);

                homeNewsDataItems.clear();
                homeNewsDataItems.addAll(homeNewsData.getItems());
                homeRecyclerViewAdapter.notifyDataSetChanged();
            } else if (homeNewsDataItems.isEmpty()) {
                mTextViewError.setVisibility(View.VISIBLE);
            }
            mRecyclerView.refreshComplete();
        } else { // 加载更多
            if (homeNewsData.getItems() != null) {
                moreStep = homeNewsData.getStep();
                mTextViewError.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);

                homeNewsDataItems.addAll(homeNewsData.getItems());
                homeRecyclerViewAdapter.notifyDataSetChanged();
            }
            mRecyclerView.loadMoreComplete();
        }
    }

    @Override
    public void onRefresh() {
        hasInitData = false;
        refreshData(catId);
    }

    @Override
    public void onLoadMore() {
        getDataMore();
    }

    public void onDestroy() {
        mSubscriptions.clear();
    }
}
