package com.android.shuyong.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.android.shuyong.util.ActivityStackManager;
import com.android.shuyong.util.ScreenManager;

public abstract class BaseActivity extends AppCompatActivity {
    private static final String TAG = "BaseActivity";

    //是否设置为沉浸状态栏
    private boolean isStatusBarTranslucent = true;

    //是否允许全屏
    private boolean isFullScreen = false;

    //是否旋转屏幕
    private boolean isScreenRotate = false;

    protected Context mContext;

    //初始化界面
    protected abstract void initView();

    //初始化数据
    protected abstract void initData();

    //绑定事件
    protected abstract void setEvent();

    private ScreenManager screenManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, " BaseActivity onCreate: ");
        mContext = this;
        ActivityStackManager.getActivityStackManager().pushActivity(this);
        screenManager = ScreenManager.getInstance();
        screenManager.setScreenRotate(isScreenRotate, this);
        screenManager.setFullScreen(isFullScreen, this);
        screenManager.setStatusBar(isStatusBarTranslucent, this);
        initView();
        initData();
        setEvent();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
        ActivityStackManager.getActivityStackManager().popActivity(this);
    }

    //跳转activity
    public static void skipAnotherActivity(Activity activity, Class<? extends Activity> cls) {
        Intent intent = new Intent(activity, cls);
        activity.startActivity(intent);
        activity.finish();
    }

    //退出应用
    public void exitApplication() {
        ActivityStackManager.getActivityStackManager().popAllActivity();
    }

    //是否设置沉浸状态栏
    public void setStatusBarTranslucent(boolean isStatusBarTranslucent) {
        this.isStatusBarTranslucent = isStatusBarTranslucent;
    }

    //是否设置全屏
    public void setFullScreen(boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
    }

    //是否设置旋转屏幕
    public void setScreenRotate(boolean isScreenRotate) {
        this.isScreenRotate = isScreenRotate;
    }


}
