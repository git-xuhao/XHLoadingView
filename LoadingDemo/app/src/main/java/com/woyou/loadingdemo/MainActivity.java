package com.woyou.loadingdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnLoading;
    private Button mBtnLoadEmpty;
    private Button mBtnLoadNoNetwork;
    private Button mBtnLoadError;

    private static final String LOADING_STATE="state";
    private static final String LOADING="loading";
    private static final String LOADING_EMPTY="empty";
    private static final String LOADING_NONETWORK="nonet";
    private static final String LOADING_ERROR="error";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBtnLoading = (Button) findViewById(R.id.btn_loading);

        mBtnLoadEmpty = (Button) findViewById(R.id.btn_empty);

        mBtnLoadNoNetwork = (Button) findViewById(R.id.btn_no_network);

        mBtnLoadError = (Button) findViewById(R.id.btn_error);

        mBtnLoading.setOnClickListener(this);

        mBtnLoadEmpty.setOnClickListener(this);

        mBtnLoadNoNetwork.setOnClickListener(this);

        mBtnLoadError.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //加载中
            case R.id.btn_loading:
                Intent intent1 = new Intent(MainActivity.this,DisplayActivity.class);
                intent1.putExtra(LOADING_STATE,LOADING);
                startActivity(intent1);
                break;
            //空数据
            case R.id.btn_empty:
                Intent intent2 = new Intent(MainActivity.this,DisplayActivity.class);
                intent2.putExtra(LOADING_STATE,LOADING_EMPTY);
                startActivity(intent2);
                break;
            //无网络
            case R.id.btn_no_network:
                Intent intent3 = new Intent(MainActivity.this,DisplayActivity.class);
                intent3.putExtra(LOADING_STATE,LOADING_NONETWORK);
                startActivity(intent3);
                break;

            case R.id.btn_error:
                Intent intent4 = new Intent(MainActivity.this,DisplayActivity.class);
                intent4.putExtra(LOADING_STATE,LOADING_ERROR);
                startActivity(intent4);
                break;
        }

    }


}
