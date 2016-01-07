package me.anany.weikandian.ui.pager;

import android.content.Context;
import android.view.View;
import android.widget.ArrayAdapter;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.LinkedList;

import me.anany.weikandian.App;
import me.anany.weikandian.R;
import me.anany.weikandian.model.HomeNewsData;
import me.anany.weikandian.retrofit.RxApiThread;
import me.anany.weikandian.utils.LogUtil;

/**
 * Created by anany on 16/1/7.
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class HomePager implements PullToRefreshBase.OnRefreshListener2 {

    public Context mContext;
    private boolean isRefreshing;

    private PullToRefreshListView mPullRefreshListView;

    private LinkedList<String> mListItems;    //显示的列表对应原字符串
    private ArrayAdapter<String> mAdapter;  //ListView的适配器
    private String position;

    private String[] mStrings = {"Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler", "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
            "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale", "Aisy Cendre",
            "Allgauer Emmentaler"};

    public HomePager(Context context) {
        mContext = context;
    }

    public View getView(String position) {

        this.position = position;

        LogUtil.e("HomePager  inflate  View ...");

        mPullRefreshListView = (PullToRefreshListView)
                View.inflate(mContext, R.layout.pager_home, null);
        initRefresh(position);
        return mPullRefreshListView;
    }

    public void initRefresh(String position) {

        initData(position);

        mPullRefreshListView.setOnRefreshListener(this);
        mPullRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);

        mAdapter = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, mStrings);
        mPullRefreshListView.setAdapter(mAdapter);
    }

    /**
     * 加载每个Pager的内容数据
     */
    private void initData(String position) {

        App.getApi().getHomeNewsData("WIFI", "2.0.4", position, "c1005", "Nexus 4", "android", "6416405", "1452161771",
                "7f08bcd287cc5096", "22", "5.1.1", "1", "1452050427", "9", "9279697", "204",
                "6b64883a89dbf5c36d669baa1bced5de")
                .compose(RxApiThread.convert())
                .subscribe(this::handleResponseData);
    }

    private void handleResponseData(HomeNewsData homeNewsData) {
        LogUtil.e(homeNewsData.toString());
    }


    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        if (!isRefreshing) {
            isRefreshing = true;
            LogUtil.e(position);
            initData(position);
            
        } else {

        }
    }

    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        if (!isRefreshing) {
            isRefreshing = true;

        } else {
        }
    }
}
