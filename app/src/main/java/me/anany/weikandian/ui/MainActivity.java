package me.anany.weikandian.ui;

import android.os.Bundle;

import com.trello.rxlifecycle.ActivityEvent;

import me.anany.weikandian.App;
import me.anany.weikandian.R;
import me.anany.weikandian.base.BaseActivity;
import me.anany.weikandian.retrofit.ApiService;
import me.anany.weikandian.retrofit.RxApiThread;
import me.anany.weikandian.utils.LogUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiService api = App.getApi();
        api.getHomeNewsData(5, 6416405, 9279697)
                .compose(RxApiThread.convert())
                .compose(bindUntilEvent(ActivityEvent.PAUSE))
                .subscribe(homeNewsData -> {
                    LogUtil.d(homeNewsData.toString());
                });
    }
}
