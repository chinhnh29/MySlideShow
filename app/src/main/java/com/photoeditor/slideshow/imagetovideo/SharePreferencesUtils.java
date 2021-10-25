package com.photoeditor.slideshow.imagetovideo;

import android.content.Context;
import android.content.SharedPreferences;

public class SharePreferencesUtils {
    private static SharePreferencesUtils mAppPreference;
    private SharedPreferences mSharedPreferences;

    public static SharePreferencesUtils getInstance(Context context) {
        if (mAppPreference == null) {
            SharePreferencesUtils sharePreferencesUtils = new SharePreferencesUtils();
            mAppPreference = sharePreferencesUtils;
            sharePreferencesUtils.mSharedPreferences = context.getSharedPreferences(GlobalDef.KEY_NAME_PREFERENCES, 0);
        }
        return mAppPreference;
    }

    public void putString(String str, String str2) {
        SharedPreferences.Editor edit = this.mSharedPreferences.edit();
        edit.putString(str, str2);
        edit.commit();
    }

    public void putInt(String str, int i) {
        SharedPreferences.Editor edit = this.mSharedPreferences.edit();
        edit.putInt(str, i);
        edit.commit();
    }

    public void putBoolean(String str, boolean z) {
        SharedPreferences.Editor edit = this.mSharedPreferences.edit();
        edit.putBoolean(str, z);
        edit.commit();
    }

    public String getString(String str, String str2) {
        return this.mSharedPreferences.getString(str, str2);
    }

    public int getInt(String str, int i) {
        return this.mSharedPreferences.getInt(str, i);
    }

    public boolean getBoolean(String str, boolean z) {
        return this.mSharedPreferences.getBoolean(str, z);
    }
}