package me.anany.weikandian.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import me.anany.weikandian.App;
import me.anany.weikandian.R;
import me.anany.weikandian.db.dao.DaoSession;
import me.anany.weikandian.db.dao.DaoUtil;
import me.anany.weikandian.db.dao.HomeTitleDBDao;
import me.anany.weikandian.db.entity.HomeTitleDB;

import java.util.List;

public class DragGridViewAdapter extends BaseAdapter {
    /**
     * TAG
     */
    private final static String TAG = "DragAdapter";

    /**
     * 可以拖动的列表（即用户选择的频道列表）
     */
    public List<HomeTitleDB> channelList;

    /**
     * 是否可见
     */
    boolean isVisible = true;

    /**
     * 是否显示底部的ITEM
     */
    private boolean isItemShow = false;

    private Context context;
    /**
     * 控制的postion
     */
    private int holdPosition;
    /**
     * 是否改变
     */
    private boolean isChanged = false;
    /**
     * 列表数据是否改变
     */
    private boolean isListChanged = false;
    /**
     * TextView 频道内容
     */
    private TextView item_text;
    private LayoutInflater inflater;

    public DragGridViewAdapter(Context context, List<HomeTitleDB> homeTitleDataItems) {
        this.context = context;
        this.channelList = homeTitleDataItems;
        inflater = (LayoutInflater) LayoutInflater
                .from(context);
    }

    @Override
    public int getCount() {
        return channelList == null ? 0 : channelList.size();
    }

    @Override
    public HomeTitleDB getItem(int position) {
        if (channelList != null && !channelList.isEmpty()) {
            return channelList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(R.layout.grid_view_item_channel, parent, false);
        TextView tv = (TextView) view.findViewById(R.id.tv_name);
        tv.setText(channelList.get(position).getName());
        return view;
    }

    /**
     *
     * 保存title数据到数据库
     *
     */
    private void saveChannelToDB(List<HomeTitleDB> channelList) {

        notifyDataSetChanged();

        DaoSession daoSession = App.getDaoSession(context);
        HomeTitleDBDao homeTitleDBDao = daoSession.getHomeTitleDBDao();
        homeTitleDBDao.deleteAll();

        DaoUtil.saveHomeTitleList(channelList,context);
    }

    /**
     * 拖动变更频道排序
     */
    public void exchange(int dragPostion, int dropPostion) {
        holdPosition = dropPostion;
        HomeTitleDB dragItem = getItem(dragPostion);
        Log.d(TAG, "startPostion=" + dragPostion + ";endPosition=" + dropPostion);
        if (dragPostion < dropPostion) {
            channelList.add(dropPostion + 1, dragItem);
            channelList.remove(dragPostion);
        } else {
            channelList.add(dropPostion, dragItem);
            channelList.remove(dragPostion + 1);
        }
        isChanged = true;
        isListChanged = true;

        saveChannelToDB(channelList);
    }

    /**
     * 显示放下的ITEM
     */
    public void setShowDropItem(boolean show) {
        isItemShow = show;
    }
}