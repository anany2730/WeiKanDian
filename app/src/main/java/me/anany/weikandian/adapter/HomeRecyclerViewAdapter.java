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

import butterknife.Bind;
import butterknife.ButterKnife;
import me.anany.weikandian.R;
import me.anany.weikandian.entry.HomeNewsData;

/**
 * Created by anany on 16/1/7.
 * <p>
 * 首页RecycleView 的适配器
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class HomeRecyclerViewAdapter extends RecyclerView.Adapter<ViewHolder> {

    private Context context;
    private List<HomeNewsData.ItemsBean> items;
    private ClickListener clickListener;

    public HomeRecyclerViewAdapter(Context context, List<HomeNewsData.ItemsBean> items) {
        this.items = items;
        this.context = context;
    }

    public interface ClickListener {
        void onItemClick(int position, View v, List<HomeNewsData.ItemsBean> items);
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        this.clickListener = clickListener;
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

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        ViewHolder viewHolder = null;

        switch (viewType) {
            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_home_pager_1, parent,false);
                viewHolder = new ViewHolderOne(view);
                break;
            case 2:
                view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_home_pager_2, parent,false);
                viewHolder = new ViewHolderTwo(view);
                break;
            case 3:
                view = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_home_pager_3, parent,false);
                viewHolder = new ViewHolderThird(view);
                break;
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        HomeNewsData.ItemsBean homeNewsDataItem = items.get(position);

        switch (getItemViewType(position)) {
            case 1:
                ViewHolderOne ViewHolderOne = (ViewHolderOne) holder;
                ViewHolderOne.tvTitle.setText(homeNewsDataItem.getTitle());
                ViewHolderOne.tvAccountName.setText(homeNewsDataItem.getAccount_name());
                ViewHolderOne.tvReadNum.setText("阅读 " + homeNewsDataItem.getRead_num());
                Glide.with(context)
                        .load(homeNewsDataItem.getThumb())
                        .into(ViewHolderOne.ivMainImg);
                break;

            case 2:
                ViewHolderTwo ViewHolderTwo = (ViewHolderTwo) holder;
                ViewHolderTwo.tvTitle.setText(homeNewsDataItem.getTitle());
                ViewHolderTwo.tvAccountName.setText(homeNewsDataItem.getAccount_name());
                ViewHolderTwo.tvReadNum.setText("阅读 " + homeNewsDataItem.getRead_num());
                Glide.with(context)
                        .load(homeNewsDataItem.getExtra().get(0))
                        .into(ViewHolderTwo.ivMainImg1);
                Glide.with(context)
                        .load(homeNewsDataItem.getExtra().get(1))
                        .into(ViewHolderTwo.ivMainImg2);
                Glide.with(context)
                        .load(homeNewsDataItem.getExtra().get(2))
                        .into(ViewHolderTwo.ivMainImg3);
                break;

            case 3:
                ViewHolderThird ViewHolderThird = (ViewHolderThird) holder;
                ViewHolderThird.tvTitle.setText(homeNewsDataItem.getTitle());
                ViewHolderThird.tvAccountName.setText(homeNewsDataItem.getAccount_name());
                ViewHolderThird.tvReadNum.setText(homeNewsDataItem.getRead_num());

                Glide.with(context)
                        .load(homeNewsDataItem.getThumb())
                        .into(ViewHolderThird.ivMainImg);
                break;
        }
    }

    /**
     * 右边一张小图片的布局
     */
    class ViewHolderOne extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.tv_title)
        TextView tvTitle;

        @Bind(R.id.tv_account_name)
        TextView tvAccountName;

        @Bind(R.id.tv_read_num)
        TextView tvReadNum;

        @Bind(R.id.iv_delete)
        ImageView ivDelete;

        @Bind(R.id.tv_time)
        TextView tvTime;

        @Bind(R.id.iv_main_img)
        ImageView ivMainImg;

        ViewHolderOne(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v, items);
        }
    }

    /**
     * 多张小图片的布局
     */
    class ViewHolderTwo extends RecyclerView.ViewHolder implements View.OnClickListener {

        @Bind(R.id.tv_title)
        TextView tvTitle;

        @Bind(R.id.iv_main_img1)
        ImageView ivMainImg1;

        @Bind(R.id.iv_main_img2)
        ImageView ivMainImg2;

        @Bind(R.id.iv_main_img3)
        ImageView ivMainImg3;

        @Bind(R.id.tv_account_name)
        TextView tvAccountName;

        @Bind(R.id.tv_read_num)
        TextView tvReadNum;

        @Bind(R.id.iv_delete)
        ImageView ivDelete;

        @Bind(R.id.tv_time)
        TextView tvTime;

        ViewHolderTwo(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v, items);
        }
    }

    /**
     * 首页 专题 大图的布局
     */
    class ViewHolderThird extends RecyclerView.ViewHolder implements View.OnClickListener {
        @Bind(R.id.tv_title)
        TextView tvTitle;

        @Bind(R.id.iv_main_img)
        ImageView ivMainImg;

        @Bind(R.id.iv_op_mark)
        ImageView ivOpMark;

        @Bind(R.id.tv_op_mark)
        TextView tvOpMark;

        @Bind(R.id.tv_account_name)
        TextView tvAccountName;

        @Bind(R.id.tv_read_num)
        TextView tvReadNum;

        @Bind(R.id.tv_time)
        TextView tvTime;

        @Bind(R.id.iv_delete)
        ImageView ivDelete;

        ViewHolderThird(View view) {
            super(view);
            ButterKnife.bind(this, view);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onItemClick(getAdapterPosition(), v, items);
        }
    }
}
