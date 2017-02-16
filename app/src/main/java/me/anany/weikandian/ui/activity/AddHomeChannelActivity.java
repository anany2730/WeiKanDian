package me.anany.weikandian.ui.activity;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import me.anany.weikandian.R;
import me.anany.weikandian.base.BaseActivity;

/**
 * Created by anany on 16/1/21.
 * <p>
 * Email:zhujun2730@gmail.com
 */
// TODO 频道管理
public class AddHomeChannelActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.tv_title_text)
    TextView mTextViewTitle;

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
        return R.layout.activity_home_add_channel;
    }

    @Override
    protected void initViews() {
        mTextViewTitle.setText("频道管理");

        initData();
    }

    void initData() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }

}
