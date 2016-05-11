package me.anany.weikandian.base;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import me.anany.weikandian.App;
import me.anany.weikandian.AppManager;
import me.anany.weikandian.utils.LogUtil;

/**
 * 基类
 * Created by Berial on 16/1/6.
 */
public abstract class BaseActivity extends RxAppCompatActivity {


    public Context mContext;

    // 是否允许全屏
    private boolean mAllowFullScreen = true;

    /**
     * 设置是否允许屏幕全屏
     */
    public void setAllowFullScreen(boolean allowFullScreen) {
        this.mAllowFullScreen = allowFullScreen;
    }

    /**
     * 查看是否允许全屏
     */
    public boolean getAllowFullScreen() {
        return mAllowFullScreen;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtil.i("---------onCreate ");
        // 竖屏锁定
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        setContentView(inflateLayoutId());
        ButterKnife.bind(this);
        mContext = this;

        prepare();
        initViews();

        AppManager.getAppManager().addActivity(this);
    }

    protected abstract int inflateLayoutId();

    /**
     * 初始化布局前的准备工作
     */
    protected void prepare(){}

    /**
     * 初始化布局：首行需要setContentView()
     */
    protected void initViews(){}

    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.i("---------onStart ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.i("---------onResume ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.i("---------onPause ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.i("---------onStop ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.i("---------onDestroy ");

        ButterKnife.unbind(this);

        final App app = (App) getApplication();
        app.getWatcher().watch(this);
        AppManager.getAppManager().finishActivity(this);
    }

}
