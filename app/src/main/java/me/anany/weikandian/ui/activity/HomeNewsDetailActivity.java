package me.anany.weikandian.ui.activity;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.OnClick;
import me.anany.weikandian.BuildConfig;
import me.anany.weikandian.R;
import me.anany.weikandian.base.BaseActivity;
import me.anany.weikandian.model.HomeNewsDataItem;

import java.lang.reflect.Field;

import static me.anany.weikandian.R.id.pb_title;

/**
 * Created by anany on 16/1/19.
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class HomeNewsDetailActivity extends BaseActivity {

    @Bind(R.id.tv_title_text)
    TextView mTextViewTitle;

    @Bind(pb_title)
    ProgressBar mProgressBarTitle;

    @Bind(R.id.wv_content)
    WebView mWebView;


    @OnClick({R.id.btn_back, R.id.btn_more})
    void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_more:
                break;
        }
    }

    @Override
    protected int inflateLayoutId() {
        return R.layout.activity_home_news_detail;
    }

    @Override
    protected void initViews() {

        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        initData();
    }

    private void initData() {

        HomeNewsDataItem newsDataItem = (HomeNewsDataItem) getIntent().
                getSerializableExtra("news_data");

        if (newsDataItem != null) {
            mProgressBarTitle.setVisibility(View.VISIBLE);
            mTextViewTitle.setText(newsDataItem.getTitle());
            mWebView.loadUrl(newsDataItem.getUrl());
            mWebView.setWebChromeClient(new WebChromeClient() {

                @Override
                public void onProgressChanged(WebView view, int newProgress) {

                    if (newProgress == 100) {
                        // 网页加载完成
                        mProgressBarTitle.setVisibility(View.INVISIBLE);
                    } else {

                    }
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (android.os.Build.VERSION.SDK_INT < 16) {
            try {
                Field field = WebView.class.getDeclaredField("mWebViewCore");
                field = field.getType().getDeclaredField("mBrowserFrame");
                field = field.getType().getDeclaredField("sConfigCallback");
                field.setAccessible(true);
                field.set(null, null);
            } catch (NoSuchFieldException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            }
        } else {
            try {
                Field sConfigCallback = Class.forName("android.webkit.BrowserFrame").getDeclaredField("sConfigCallback");
                if (sConfigCallback != null) {
                    sConfigCallback.setAccessible(true);
                    sConfigCallback.set(null, null);
                }
            } catch (NoSuchFieldException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (ClassNotFoundException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            } catch (IllegalAccessException e) {
                if (BuildConfig.DEBUG) {
                    e.printStackTrace();
                }
            }
        }
    }
}
