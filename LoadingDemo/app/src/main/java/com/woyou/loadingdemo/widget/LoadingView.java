package com.woyou.loadingdemo.widget;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.woyou.loadingdemo.LoadingState;
import com.woyou.loadingdemo.R;


/**
 * Created by Xiho on 11:21.
 */
public class LoadingView extends FrameLayout {

    private Context mContext;

    private LinearLayout mLinearLoad;

    private LinearLayout mLinearLoading;

    private TextView mTvLoading;

    private TextView mTvLoad;

    private ImageView mIvLoading;

    private ImageView mIvLoad;

    private Button mBtnLoad;

    private LoadingState mState;

    private AnimationDrawable animation;


    public LoadingView(Context context) {
        super(context);
        mContext = context;
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;

    }
    public void build(){
        LayoutInflater.from(mContext).inflate(R.layout.view_loading, this, true);

        mLinearLoading = (LinearLayout) findViewById(R.id.lin_loading);

        mLinearLoad = (LinearLayout) findViewById(R.id.lin_load);

        mIvLoading = (ImageView) findViewById(R.id.img_loading);

        mIvLoad = (ImageView) findViewById(R.id.iv_load);

        mTvLoading = (TextView) findViewById(R.id.tv_loading);

        mTvLoad = (TextView) findViewById(R.id.tv_load);

        mBtnLoad = (Button) findViewById(R.id.btn_load);

        mBtnLoad.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setState(LoadingState.STATE_LOADING);
                mOnRetryListener.onRetry();
            }
        });

    }


    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);
        if(View.GONE==visibility && mState==LoadingState.STATE_LOADING && animation!=null&&animation.isRunning()){
            animation.stop();
        }
    }

    /**
     * 加载中提示文字
     */
    private String mLoadingText;
    private int mLoadingIcon;
    public LoadingView withLoadingIcon(int resId){
        mLoadingIcon = resId;
        return this;
    }

    /**
     * 加载数据为空提示文字
     */
    private String mLoadEmptyText;
    private int mLoadEmptyIcon;
    public LoadingView withEmptyIcon(int resId){
        mLoadEmptyIcon = resId;
        return this;
    }

    /**
     * 无网络提示
     */
    private String mLoadNoNetworkText;
    private int mNoNetworkIcon;
    public LoadingView withNoNetIcon(int resId){
        mNoNetworkIcon = resId;
        return this;
    }

    private OnRetryListener mOnRetryListener;

    /**
     * 定义重试的的接口
     */
    public interface OnRetryListener {
        void onRetry();
    }

    public LoadingView withOnRetryListener(OnRetryListener mOnRetryListener){
        this.mOnRetryListener = mOnRetryListener;
        return this;
    }

    public void setState(LoadingState state){
        if(mState==state){
            return;
        }else if(state==LoadingState.STATE_LOADING){
            mLinearLoading.setVisibility(VISIBLE);
            mLinearLoad.setVisibility(GONE);
        }else if(state!=LoadingState.STATE_LOADING){
            mLinearLoading.setVisibility(GONE);
            mLinearLoad.setVisibility(VISIBLE);
            if(animation!=null && mState==LoadingState.STATE_LOADING)
                animation.stop();
        }
        changeState(state);
    }


    public boolean btnEmptyEnable = true;
    public boolean btnErrorEnable = true;
    public boolean btnNoNetworkEnable = true;

    public LoadingView withBtnNoNetEnnable(boolean ennable) {
        btnNoNetworkEnable = ennable;
        return this;
    }

    public LoadingView withBtnErrorEnnable(boolean ennable) {
        btnErrorEnable = ennable;
        return this;
    }


    public LoadingView withBtnEmptyEnnable(boolean ennable) {
        btnEmptyEnable = ennable;
        return this;
    }

    /**
     * 改变状态
     * @param state
     */
    private void changeState(LoadingState state) {
        switch (state) {
            case STATE_LOADING:
                mState = LoadingState.STATE_LOADING;
                mIvLoading.setImageResource(mLoadingIcon);
                mTvLoading.setText(mLoadingText);
                if (animation == null) {
                    animation = (AnimationDrawable) mIvLoading.getDrawable();
                }
                if (animation != null)
                    animation.start();
                break;
            case STATE_EMPTY:
                mState = LoadingState.STATE_EMPTY;
                mIvLoad.setImageResource(mLoadEmptyIcon);
                mTvLoad.setText(mLoadEmptyText);
                if (btnEmptyEnable) {
                    mBtnLoad.setVisibility(VISIBLE);
                    mBtnLoad.setText(btn_empty_text);
                } else {
                    mBtnLoad.setVisibility(GONE);
                }
                break;
            case STATE_ERROR:
                mState = LoadingState.STATE_ERROR;
                mIvLoad.setImageResource(mErrorIco);
                mTvLoad.setText(mLoadErrorText);
                if (btnErrorEnable) {
                    mBtnLoad.setVisibility(VISIBLE);
                    mBtnLoad.setText(btn_error_text);
                } else {
                    mBtnLoad.setVisibility(GONE);
                }
                break;
            case STATE_NO_NET:
                mState = LoadingState.STATE_NO_NET;
                mIvLoad.setImageResource(mNoNetworkIcon);
                mTvLoad.setText(mLoadNoNetworkText);
                if (btnNoNetworkEnable) {
                    mBtnLoad.setVisibility(VISIBLE);
                    mBtnLoad.setText(btn_nonet_text);
                } else {
                    mBtnLoad.setVisibility(GONE);
                }
                break;
        }

    }


    /**
     * 后台或者本地出现错误提示
     */
    private String mLoadErrorText;
    private int mErrorIco;

    public LoadingView withErrorIco(int resId) {
        mErrorIco = resId;
        return this;
    }

    public LoadingView withLoadEmptyText(int resId) {
        mLoadEmptyText = getResources().getString(resId);
        return this;
    }

    public LoadingView withLoadEmptyText(String mLoadEmptyText) {
        this.mLoadEmptyText = mLoadEmptyText;
        return this;
    }

    public LoadingView withLoadNoNetworkText(int resId) {
        mLoadNoNetworkText = getResources().getString(resId);
        return this;
    }

    public String btn_empty_text = "重试";
    public String btn_error_text = "重试";
    public String btn_nonet_text = "重试";

    public LoadingView withBtnEmptyText(String text) {
        this.btn_empty_text = text;
        return this;
    }

    public LoadingView withBtnErrorText(String text) {
        this.btn_error_text = text;
        return this;
    }

    public LoadingView withBtnNoNetText(String text) {
        this.btn_nonet_text = text;
        return this;
    }


    public LoadingView withLoadNoNetworkText(String mLoadedNoNetText) {
        this.mLoadNoNetworkText = mLoadedNoNetText;
        return this;
    }

    public LoadingView withLoadErrorText(int resId) {
        this.mLoadErrorText = getResources().getString(resId);
        return this;
    }

    public LoadingView withLoadErrorText(String mLoadedErrorText) {
        this.mLoadErrorText = mLoadedErrorText;
        return this;
    }

    public LoadingView withLoadingText(int resId) {
        this.mLoadingText = getResources().getString(resId);
        return this;
    }

    public LoadingView withLoadingText(String mLoadingText) {
        this.mLoadingText = mLoadingText;
        return this;
    }

}
