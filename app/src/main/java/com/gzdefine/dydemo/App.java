package com.gzdefine.dydemo;

import android.app.Application;



/**
 * Created by K on 2016/3/21.
 */
public class App extends Application {
    static App me;

    public static App me(){ return me; }

    @Override
    public void onCreate() {
        super.onCreate();
    }
}