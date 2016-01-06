package me.anany.weikandian.ui.fragment;

import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle.FragmentEvent;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.anany.weikandian.App;
import me.anany.weikandian.R;
import me.anany.weikandian.base.BaseFragment;
import me.anany.weikandian.model.HomeTitleData;
import me.anany.weikandian.retrofit.ApiService;
import me.anany.weikandian.retrofit.RxApiThread;
import me.anany.weikandian.utils.LogUtil;
import me.anany.weikandian.view.CustomViewPager;

/**
 * Created by anany on 16/1/6.
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class HomeFragment extends BaseFragment {

    @Bind(R.id.tab_layout)
    TabLayout tab_layout;

    @Bind(R.id.vp_home)
    CustomViewPager vp_home;


    @Override
    public View initView(LayoutInflater inflater, ViewGroup container) {



        View view = View.inflate(mActivity, R.layout.fragment_home,null);

        ButterKnife.bind(this,view);


        return view;
    }

    @Override
    public void initData() {

        LogUtil.e("HomeFragment initData");

        ApiService api = App.getApi();
        api.getHomeNewsTitle("WIFI", "2.0.4", "5", "c1005", "Nexus 4", "android", "6416405",
                "7f08bcd287cc5096", "22", "5.1.1", "1", "1452050427", "9279697", "204",
                "6b64883a89dbf5c36d669baa1bced5de")
                .compose(RxApiThread.convert())
                .compose(bindUntilEvent(FragmentEvent.PAUSE))
                .map(HomeTitleData::toString)
                .subscribe(LogUtil::e);
    }
}
