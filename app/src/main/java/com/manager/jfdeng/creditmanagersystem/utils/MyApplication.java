package com.manager.jfdeng.creditmanagersystem.utils;

import android.app.Application;
import android.content.Context;

/**
 * Created by yf on 18-7-4.
 */

public class MyApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

}
