package me.anany.weikandian.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import me.anany.weikandian.R;
import me.anany.weikandian.db.entity.HomeTitleDB;

import java.util.List;

/**
 * Created by anany on 16/1/21.
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class ChannelGridViewAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private List<HomeTitleDB> homeTitleDataItems;

    public ChannelGridViewAdapter(Context context, List<HomeTitleDB> homeTitleDataItems) {
        this.homeTitleDataItems = homeTitleDataItems;
        inflater = (LayoutInflater) LayoutInflater
                .from(context);
    }

    @Override
    public int getCount() {
        return homeTitleDataItems != null ? homeTitleDataItems.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return homeTitleDataItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(R.layout.grid_view_item_channel, parent, false);
        TextView tv = (TextView) view.findViewById(R.id.tv_name);
        tv.setText(homeTitleDataItems.get(position).getName());
        return view;
    }
}
