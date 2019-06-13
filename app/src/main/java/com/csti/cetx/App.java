package com.csti.cetx;

import android.app.Application;

import cn.bmob.v3.Bmob;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //第一：默认初始化
        Bmob.initialize(this, "2b1a69e22c5178432d789bc6b67a89c6");
    }
}
