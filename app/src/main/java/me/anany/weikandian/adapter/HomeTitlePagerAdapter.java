package me.anany.weikandian.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import me.anany.weikandian.model.HomeTitleData;
import me.anany.weikandian.ui.pager.HomePager;

/**
 * Created by anany on 16/1/8.
 *
 *
 *  Tablayout 与 ViewPager 的 Pager数据源
 *
 *  Email:zhujun2730@gmail.com
 */
public class HomeTitlePagerAdapter extends PagerAdapter {

    private List<HomePager> pagerList;
    private List<String> titleTextList;
    private List<HomeTitleData.HomeFragmentTitleItem> homeTitleDataItems;

    /**
     *
     * @param pagerList  内容页Pager的集合
     * @param homeTitleDataItems Title的数据源
     * @param titleTextList Title 文字的集合
     */
    public HomeTitlePagerAdapter(List<HomePager> pagerList,
                                 List<HomeTitleData.HomeFragmentTitleItem> homeTitleDataItems,
                                 List<String> titleTextList) {
        this.pagerList = pagerList;
        this.titleTextList = titleTextList;
        this.homeTitleDataItems = homeTitleDataItems;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titleTextList.get(position);
    }

    @Override
    public int getCount() {
        return pagerList.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        HomePager homePager = pagerList.get(position);

        View view = null;

        if (position == 0) {
            view = homePager.getView("0");
        } else {
            view = homePager.getView(homeTitleDataItems.get(position).getId());
        }

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
