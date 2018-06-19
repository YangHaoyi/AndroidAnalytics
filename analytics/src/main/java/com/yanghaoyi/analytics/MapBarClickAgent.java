package com.yanghaoyi.analytics;

import android.content.Context;
import android.text.TextUtils;

/**
 * @author : YangHaoYi on 2018/6/11.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/6/11.
 *         Version : V 1.0
 */
public enum  MapBarClickAgent {

    INSTANCE;

    private PageCountManager pageCountManager;
    private ClickCountManager clickCountManager;

    public void init(Context context){
        pageCountManager = new PageCountManager(context);
        clickCountManager = new ClickCountManager(context);
    }

    public void onPageStart(String pageName){
        if(!TextUtils.isEmpty(pageName)) {
            pageCountManager.insertStart(pageName);
        }
    }

    public void onPageEnd(String pageName){
        if(!TextUtils.isEmpty(pageName)) {
            pageCountManager.calculateDuration(pageName);
        }
    }

    public void onEvent(String eventName){
        clickCountManager.setEvent(eventName);
    }


}
