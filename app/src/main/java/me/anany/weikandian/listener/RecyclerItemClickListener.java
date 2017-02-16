package me.anany.weikandian.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;

import me.anany.weikandian.adapter.HomeRecyclerViewAdapter;
import me.anany.weikandian.entry.HomeNewsData;
import me.anany.weikandian.ui.activity.HomeNewsDetailActivity;

/**
 * Created by anany on 16/1/21.
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class RecyclerItemClickListener implements HomeRecyclerViewAdapter.ClickListener {

    private Context mContext;

    public RecyclerItemClickListener(Context context) {
        mContext = context;
    }

    @Override
    public void onItemClick(int itemListPosition, View v, List<HomeNewsData.ItemsBean> items) {

        Intent intent = new Intent(mContext, HomeNewsDetailActivity.class);
        intent.putExtra("news_data", items.get(itemListPosition - 1));
        mContext.startActivity(intent);
    }
}
