package me.anany.weikandian.ui.activity;

import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import me.anany.weikandian.R;
import me.anany.weikandian.base.BaseActivity;
import me.anany.weikandian.model.HomeNewsDataItem;

/**
 * Created by anany on 16/1/19.
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class HomeNewsDetailActivity extends BaseActivity {

    @Bind(R.id.tv_title_text)
    TextView tv_title_text;

    @Bind(R.id.btn_more)
    Button btn_more;

    @Bind(R.id.btn_back)
    Button btn_back;

    @Bind(R.id.pb_title)
    ProgressBar pb_title;

    @Bind(R.id.wv_content)
    WebView wv_content;


    @OnClick({R.id.btn_back,R.id.btn_more})
    void onClick(View view){
        switch (view.getId()){
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
        wv_content.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        WebSettings settings = wv_content.getSettings();
        settings.setJavaScriptEnabled(true);

        initData();
    }

    void initData() {

        HomeNewsDataItem newsDataItem = (HomeNewsDataItem) getIntent().
                getSerializableExtra("news_data");

        if (newsDataItem != null) {

            tv_title_text.setText(newsDataItem.getTitle());
            wv_content.loadUrl(newsDataItem.getUrl());
            wv_content.setWebChromeClient(new WebChromeClient() {

                @Override
                public void onProgressChanged(WebView view, int newProgress) {

                    if (newProgress == 100) {
                        // 网页加载完成
                        pb_title.setVisibility(View.INVISIBLE);
                    } else {
                        // 加载中

                    }
                }
            });
        }
    }
}
