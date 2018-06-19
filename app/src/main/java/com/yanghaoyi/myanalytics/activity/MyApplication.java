package com.yanghaoyi.myanalytics.activity;

import android.app.Application;

import com.yanghaoyi.analytics.MapBarClickAgent;

/**
 * @author : YangHaoYi on 2018/6/7.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/6/7.
 *         Version : V 1.0
 */
public class MyApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();
        MapBarClickAgent.INSTANCE.init(this);
    }
}
