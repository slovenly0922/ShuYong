package com.android.shuyong.util;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesUtil {
    private static final String FILE_NAME = "welcomePage";
    public static final String FIRST_OPEN = "first_open";
    private static SharedPreferences sharedPreferences;

    public static Boolean getBoolean(Context context, String strKey, Boolean booDefault) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(strKey, booDefault);
    }

    public static void putBoolean(Context context, String strKey, Boolean booValue) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(strKey, booValue);
        editor.commit();
    }

    public static String getString(Context context, String strKey, String strDefault) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(strKey, strDefault);
    }

    public static void putString(Context context, String strKey, Boolean strValue) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(strKey, strValue);
        editor.commit();
    }

    public static int getInt(Context context, String strKey, int intDefault) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getInt(strKey, intDefault);
    }

    public static void putInt(Context context, String strKey, int intDefault) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(strKey, intDefault);
        editor.commit();
    }

    public static long getLong(Context context, String strKey, long longDefault) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getLong(strKey, longDefault);
    }

    public static void putLong(Context context, String strKey, long longDefault) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong(strKey, longDefault);
        editor.commit();
    }

}
