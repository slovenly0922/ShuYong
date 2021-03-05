package com.android.shuyong.util;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.Stack;

public class ActivityStackManager {
    private static Stack<Activity> activityStack;
    private static ActivityStackManager instance;
    private static final String TAG = "ActivityStackManager";

    private ActivityStackManager() {

    }

    public synchronized static ActivityStackManager getActivityStackManager() {
        if (instance == null) {
            instance = new ActivityStackManager();
        }
        return instance;
    }

    /**
     * 关闭Activity
     * finish the activity and remove it from stack
     */

    public void popActivity(Activity activity) {
        if (activityStack == null) {
            Log.i(TAG, "popActivity: activityStack is null");
            return;
        }
        if (activity != null) {
            activity.finish();
            activityStack.remove(activity);
            activity = null;
        }
    }

    /**
     * 获取当前的Activity
     * get current Activity
     */
    public Activity getCurrentActivity() {
        if (activityStack == null || activityStack.isEmpty()) {
            Log.i(TAG, "getCurrentActivity: activityStack is null or empty");
            return null;
        }
        return activityStack.lastElement();
    }

    /**
     * 获取第一个Activity
     * get the first activity in the stack
     */
    public Activity getFirstActivity() {
        if (activityStack == null || activityStack.isEmpty()) {
            Log.i(TAG, "getFirstActivity: activityStack is null or empty");
            return null;
        }
        return activityStack.firstElement();
    }

    /**
     * 添加Activity到Stack
     * add the activity to the stack
     */
    public void pushActivity(Activity activity) {
        if (activityStack == null) {
            Log.i(TAG, "pushActivity: activityStack is null");
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 移除所有的Activity
     * remove all activity
     */
    public void popAllActivity() {
        if (activityStack == null) {
            Log.i(TAG, "popAllActivity: activityStack is null");
            return;
        }
        while (true) {
            if (activityStack.isEmpty())
                break;
            popActivity(getCurrentActivity());
        }
    }

    /**
     * 移除所有的Activity但保持当前的Activity
     * remove all activities but keep the current activity
     */
    public void popAllActivitiesButCurrentActivity() {
        while (true) {
            if (activityStack.size() == 1)
                break;
            if (getCurrentActivity() == getFirstActivity())
                break;
            popActivity(getFirstActivity());
        }
    }

    public void startNextActivity(Context context,)

}
