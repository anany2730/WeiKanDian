package me.anany.weikandian.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import me.anany.weikandian.R;
import me.anany.weikandian.model.HomeNewsDataItem;

/**
 * Created by anany on 16/1/7.
 *
 *  首页RecycleView 的适配器
 *
 * Email:zhujun2730@gmail.com
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private ClickListener clickListener;

    public interface ClickListener {
        void onItemClick(int position, View v,List<HomeNewsDataItem> items);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
    }


    private List<HomeNewsDataItem> items;
    private Context context;

    public HomeRecyclerViewAdapter(Context context, List<HomeNewsDataItem> items) {
        this.items = items;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return Integer.parseInt(items.get(position).getImage_type());
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    /**
     * 创建VIewHolder
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v;
        ViewHolder holer = null;

        switch (viewType) {
            case 1:
                v = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_home_pager_1, null);
                holer = new ViewHolder1(v);
                break;
            case 2:
                v = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_home_pager_2, null);
                holer = new ViewHolder2(v);
                break;
            case 3:
                v = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_home_pager_3, null);
                holer = new ViewHolder3(v);
                break;
        }

        return holer;
    }

    /**
     *  将数据源绑定到ViewHolder的控件中
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        HomeNewsDataItem item = items.get(position);

        switch (getItemViewType(position)) {
            case 1:
                ViewHolder1 viewHolder1 = (ViewHolder1) holder;
                viewHolder1.tv_title.setText(item.getTitle());
                viewHolder1.tv_account_name.setText(item.getAccount_name());
                viewHolder1.tv_read_num.setText("阅读 "+item.getRead_num());

                Glide.with(context)
                        .load(item.getThumb())
                        .into(viewHolder1.iv_main_img);

                break;
            case 2:
                ViewHolder2 viewHolder2 = (ViewHolder2) holder;
                viewHolder2.tv_title.setText(item.getTitle());
                viewHolder2.tv_account_name.setText(item.getAccount_name());
                viewHolder2.tv_read_num.setText("阅读 "+item.getRead_num());

                Glide.with(context)
                    .load(item.getExtra().get(0))
                    .into(viewHolder2.iv_main_img1);

                Glide.with(context)
                        .load(item.getExtra().get(1))
                        .into(viewHolder2.iv_main_img2);

                Glide.with(context)
                        .load(item.getExtra().get(2))
                        .into(viewHolder2.iv_main_img3);
                break;
            case 3:
                ViewHolder3 viewHolder3 = (ViewHolder3) holder;
                viewHolder3.tv_title.setText(item.getTitle());
                viewHolder3.tv_account_name.setText(item.getAccount_name());
                viewHolder3.tv_read_num.setText(item.getRead_num());

                Glide.with(context)
                        .load(item.getThumb())
                        .into(viewHolder3.iv_main_img);
                break;
        }
    }

    /**
     *  右边一张小图片的布局
     *
     */
    public class ViewHolder1 extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tv_title,tv_account_name,tv_read_num,tv_time;
        public ImageView iv_main_img,iv_delete;

        public ViewHolder1(View view) {

            super(view);
            view.setOnClickListener(this);

            this.tv_title = (TextView) view.findViewById(R.id.tv_title);
            this.tv_account_name = (TextView) view.findViewById(R.id.tv_account_name);
            this.tv_read_num = (TextView) view.findViewById(R.id.tv_read_num);
            this.tv_time = (TextView) view.findViewById(R.id.tv_time);
            this.iv_main_img = (ImageView) view.findViewById(R.id.iv_main_img);
            this.iv_delete = (ImageView) view.findViewById(R.id.iv_delete);


        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v,items);
        }
    }

    /**
     *  多张小图片的布局
     *
     */
    public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView tv_title,tv_account_name,tv_read_num;
        public ImageView iv_main_img1,iv_delete,iv_main_img2,iv_main_img3;

        public ViewHolder2(View view) {

            super(view);
            view.setOnClickListener(this);

            this.tv_title = (TextView) view.findViewById(R.id.tv_title);
            this.tv_account_name = (TextView) view.findViewById(R.id.tv_account_name);
            this.tv_read_num = (TextView) view.findViewById(R.id.tv_read_num);
            this.iv_main_img1 = (ImageView) view.findViewById(R.id.iv_main_img1);
            this.iv_main_img2 = (ImageView) view.findViewById(R.id.iv_main_img2);
            this.iv_main_img3 = (ImageView) view.findViewById(R.id.iv_main_img3);
            this.iv_delete = (ImageView) view.findViewById(R.id.iv_delete);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v,items);
        }
    }

    /**
     *  首页 专题 大图的布局
     *
     */
    public class ViewHolder3 extends RecyclerView.ViewHolder  implements View.OnClickListener{

        public TextView tv_op_mark,tv_title,tv_account_name,tv_read_num;
        public ImageView iv_main_img,iv_delete,iv_op_mark;

        public ViewHolder3(View view) {

            super(view);
            view.setOnClickListener(this);

            this.tv_title = (TextView) view.findViewById(R.id.tv_title);
            this.tv_account_name = (TextView) view.findViewById(R.id.tv_account_name);
            this.tv_read_num = (TextView) view.findViewById(R.id.tv_read_num);
            this.tv_op_mark = (TextView) view.findViewById(R.id.tv_op_mark);
            this.iv_main_img = (ImageView) view.findViewById(R.id.iv_main_img);
            this.iv_delete = (ImageView) view.findViewById(R.id.iv_delete);
            this.iv_op_mark = (ImageView) view.findViewById(R.id.iv_op_mark);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v,items);
        }
    }
}
