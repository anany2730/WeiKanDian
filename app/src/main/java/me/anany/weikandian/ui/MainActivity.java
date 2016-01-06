package me.anany.weikandian.ui;

import android.os.Bundle;

import com.trello.rxlifecycle.ActivityEvent;

import me.anany.weikandian.App;
import me.anany.weikandian.R;
import me.anany.weikandian.base.BaseActivity;
import me.anany.weikandian.model.HomeNewsData;
import me.anany.weikandian.retrofit.ApiService;
import me.anany.weikandian.retrofit.RxApiThread;
import me.anany.weikandian.utils.LogUtil;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiService api = App.getApi();
        api.getHomeNewsData("WIFI", "2.0.4", "5", "c1005", "Nexus 4", "android", "6416405",
                "7f08bcd287cc5096", "22", "5.1.1", "1", "1452050427", "9279697", "204",
                "6b64883a89dbf5c36d669baa1bced5de")
                .compose(RxApiThread.convert())
                .compose(bindUntilEvent(ActivityEvent.PAUSE))
                .map(HomeNewsData::toString)
                .subscribe(LogUtil::d);
    }
}
