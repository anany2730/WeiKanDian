package me.anany.weikandian.base;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import me.anany.weikandian.App;

/**
 * 基类
 * Created by Berial on 16/1/6.
 */
public class BaseActivity extends RxAppCompatActivity {

    @Override
    protected void onDestroy() {
        super.onDestroy();
        final App app = (App) getApplication();
        app.getWatcher().watch(this);
    }
}
