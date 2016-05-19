package com.woyou.loadingdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.woyou.loadingdemo.utils.SnackbarUtil;
import com.woyou.loadingdemo.widget.LoadingView;

public class DisplayActivity extends AppCompatActivity {

    private LoadingView mLoadingView;

    private static final String LOADING_STATE="state";
    private static final String LOADING="loading";
    private static final String LOADING_EMPTY="empty";
    private static final String LOADING_NONETWORK="nonet";
    private static final String LOADING_ERROR="error";
    private String mLoadState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        mLoadingView = (LoadingView) findViewById(R.id.lv_loading);
        mLoadingView.withLoadEmptyText("≥﹏≤ , 啥也木有 !").withEmptyIcon(R.drawable.disk_file_no_data).withBtnEmptyEnnable(false)
                    .withErrorIco(R.drawable.ic_chat_empty).withLoadErrorText("(῀( ˙᷄ỏ˙᷅ )῀)ᵒᵐᵍᵎᵎᵎ,我家程序猿跑路了 !").withBtnErrorText("臭狗屎!!!")
                    .withLoadNoNetworkText("你挡着信号啦o(￣ヘ￣o)☞ᗒᗒ 你走").withNoNetIcon(R.drawable.ic_chat_empty).withBtnNoNetText("网弄好了，重试")
                    .withLoadingIcon(R.drawable.loading_animation).withLoadingText("加载中...").withOnRetryListener(new LoadingView.OnRetryListener() {
                @Override
                public void onRetry() {
                    SnackbarUtil.show(mLoadingView,"已经在努力重试了",0);
                }
            }).build();

        Intent intent = this.getIntent();
        mLoadState = intent.getStringExtra(LOADING_STATE);

       if(mLoadState.contains(LOADING)){
            mLoadingView.setVisibility(View.VISIBLE);
            mLoadingView.setState(LoadingState.STATE_LOADING);

        }else if(mLoadState.contains(LOADING_EMPTY)){
            mLoadingView.setVisibility(View.VISIBLE);
            mLoadingView.setState(LoadingState.STATE_EMPTY);

        }else if(mLoadState.contains(LOADING_NONETWORK)){
            mLoadingView.setVisibility(View.VISIBLE);
            mLoadingView.setState(LoadingState.STATE_NO_NET);
        }else if(mLoadState.contains(LOADING_ERROR)){
           mLoadingView.setVisibility(View.VISIBLE);
           mLoadingView.setState(LoadingState.STATE_ERROR);
       }
    }
}
