package com.android.shuyong.activity;

import android.os.Bundle;
import android.util.Log;

import com.android.shuyong.R;
import com.android.shuyong.util.ActivityStackManager;

public class HomeActivity extends BaseActivity {
    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i(TAG, "HomeActivity onCreate: ");
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
