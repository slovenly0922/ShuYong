package com.android.shuyong.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Px;
import androidx.viewpager.widget.ViewPager;

import com.android.shuyong.R;
import com.android.shuyong.adapter.GuideViewPagerAdapter;
import com.android.shuyong.util.ActivityStackManager;
import com.android.shuyong.util.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

public class SplashActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "SplashActivity";
    boolean isFirstOpen = false;
    private List<View> views;
    private Button startBtn;
    private ViewPager viewPager;
    private GuideViewPagerAdapter adapter;
    //引导页图片资源
    private static final int[] pics = {R.layout.splash_view1, R.layout.splash_view2, R.layout.splash_view3};
    //底部小点图片
    private ImageView[] dots;
    //用于记录当前选中的位置
    private int currentIndex;
    private static Animation mAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "SplashActivity onCreate: ");
        super.onCreate(savedInstanceState);
        //判断该Activity是不是第一次启动
        isFirstOpen = SharedPreferencesUtil.getBoolean(this, SharedPreferencesUtil.FIRST_OPEN, true);
        if (!isFirstOpen) {
            skipAnotherActivity(this, HomeActivity.class);
            return;
        }
    }

    @Override
    protected void initView() {
        setContentView(R.layout.activity_splash);
        views = new ArrayList<View>();

        //初始化引导页试图列表
        for (int i = 0; i < pics.length; i++) {
            View view = LayoutInflater.from(this).inflate(pics[i], null);
            if (i == pics.length - 1) {
                startBtn = (Button) view.findViewById(R.id.start_btn);
                startBtn.setTag("enter");

                //先放大后缩小该按钮
                mAnimation=AnimationUtils.loadAnimation(SplashActivity.this,R.anim.from_big_to_small);
                startBtn.startAnimation(mAnimation);
            }
            views.add(view);
        }
        viewPager = (ViewPager) findViewById(R.id.vp_guide);
        initDots();

    }

    @Override
    public void onClick(View view) {
        if (view.getTag().equals("enter")) {
            SharedPreferencesUtil.putBoolean(SplashActivity.this, SharedPreferencesUtil.FIRST_OPEN, false);
            skipAnotherActivity(SplashActivity.this, HomeActivity.class);
            return;
        }
        int position = (Integer) view.getTag();
        setCurrentView(position);
        setCurDot(position);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void setEvent() {
        startBtn.setOnClickListener(this);

        adapter = new GuideViewPagerAdapter(views);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new PageChangeListener());
    }

    private void initDots() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll);
        dots = new ImageView[pics.length];

        //循环取得小点图片
        for (int i = 0; i < pics.length; i++) {
            //得到linearLayout下的每一个元素
            dots[i] = (ImageView) linearLayout.getChildAt(i);
            dots[i].setEnabled(false);  //设置为可用
            dots[i].setTag(i);
            dots[i].setOnClickListener(this);
        }
        currentIndex = 0;
        dots[currentIndex].setEnabled(true);
    }

    private void setCurrentView(int position) {
        if (position < 0 || position > pics.length)
            return;
        viewPager.setCurrentItem(position);
    }

    private void setCurDot(int position) {
        if (position < 0 || position > pics.length || currentIndex == position)
            return;
        dots[currentIndex].setEnabled(false);
        dots[position].setEnabled(true);
        currentIndex = position;
    }

    private class PageChangeListener implements ViewPager.OnPageChangeListener {
        //当页面滑动时调用
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //position:当前滑动到哪个页面
            //positionOffset:当前页面偏移的百分比
            //positionOffsetPixels:当前页面偏移的像素位置
        }

        //当页面被选中时调用
        @Override
        public void onPageSelected(int position) {
            setCurDot(position);
        }

        //当滑动状态改变时调用
        @Override
        public void onPageScrollStateChanged(int state) {
            //state有三种状态，0表示没有滑动，1表示正在滑动，2表示滑动完毕
        }
    }

}
