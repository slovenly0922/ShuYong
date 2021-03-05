package com.android.shuyong.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.android.shuyong.activity.BaseActivity;

public class ScreenManager {
    private static ScreenManager instance;
    private static final String TAG = "ScreenManager";

    private ScreenManager() {
    }

    public synchronized static ScreenManager getInstance() {
        if (instance == null) {
            instance = new ScreenManager();
        }
        return instance;
    }

    /**
     * 窗口全屏
     */
    public void setFullScreen(boolean isFullScreen, BaseActivity mActivity) {
        if (!isFullScreen) {
            Log.i(TAG, "setFullScreen: false");
            return;
        }
        mActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mActivity.supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
    }

    /**
     * 沉浸状态栏
     */
    @TargetApi(19)
    public void setStatusBar(boolean isStatusBarTranslucent, BaseActivity mActivity) {
        if (!isStatusBarTranslucent) {
            Log.i(TAG, "setStatusBar: status bar is not translucent");
            return;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            mActivity.getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            mActivity.getWindow().addFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            mActivity.getWindow().getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN|View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    /**
     * 旋转屏幕
     */
    public void setScreenRotate(boolean isScreenRotate, BaseActivity mActivity) {
        if (!isScreenRotate)
            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        else
            mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

}
