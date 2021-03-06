package com.android.shuyong.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import com.android.shuyong.R;
import com.android.shuyong.util.ActivityStackManager;
import com.android.shuyong.util.SharedPreferencesUtil;

public class SplashActivity extends BaseActivity {
    private static final String TAG = "SplashActivity";
    boolean isFirstOpen=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "SplashActivity onCreate: ");
        super.onCreate(savedInstanceState);

        //判断该Activity是不是第一次启动
        isFirstOpen= SharedPreferencesUtil.getBoolean(this,SharedPreferencesUtil.FIRST_OPEN,false);
        if (!isFirstOpen){
            skipAnotherActivity(this,HomeActivity.class);
            return;
        }
        setContentView(R.layout.activity_splash);

    }

    @Override
    protected void initView() {
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setEvent() {

    }

}
