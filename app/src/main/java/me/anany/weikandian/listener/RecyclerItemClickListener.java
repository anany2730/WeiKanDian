package me.anany.weikandian.listener;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import java.util.List;

import me.anany.weikandian.adapter.HomeRecyclerViewAdapter;
import me.anany.weikandian.model.HomeNewsDataItem;
import me.anany.weikandian.ui.activity.HomeNewsDetailActivity;

/**
 * Created by anany on 16/1/21.
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class RecyclerItemClickListener implements HomeRecyclerViewAdapter.ClickListener {

    private String mPosition;
    private Context mContext;

    public RecyclerItemClickListener(Context context, String position) {
        mContext = context;
        mPosition = position;
    }

    @Override
    public void onItemClick(int itemListPosition, View v, List<HomeNewsDataItem> items) {

        Intent intent = new Intent(mContext, HomeNewsDetailActivity.class);

        if ("0".equals(mPosition) && itemListPosition > 1) {
            // 推荐页的列表，因为有头部header，所以数据position会有变化
            intent.putExtra("news_data", items.get(itemListPosition - 2));

        } else {
            intent.putExtra("news_data", items.get(itemListPosition - 1));

        }
        mContext.startActivity(intent);
    }

    public void setPagerPosition(String position) {
        this.mPosition = position;
    }
}
