package com.yanghaoyi.analytics;

import android.content.Context;
import android.text.TextUtils;

import com.yanghaoyi.db.SQLiteHelper;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : YangHaoYi on 2018/6/11.
 *         Email  :  yang.haoyi@qq.com
 *         Description :页面统计工具
 *         Change : YangHaoYi on 2018/6/11.
 *         Version : V 1.0
 */
public class PageCountManager {

    private static JSONArray c = new JSONArray();
    private static Object pageInfo = new Object();
    private final Map<String, Long> pageMap = new HashMap<>();
    private SQLiteHelper sqLiteHelper;


    public PageCountManager(Context context) {
        sqLiteHelper = new SQLiteHelper(context);
    }

    public void insertStart(String pageName){
        if(!TextUtils.isEmpty(pageName)) {
            synchronized(this.pageMap) {
                this.pageMap.put(pageName, System.currentTimeMillis());
            }
        }
    }

    public void calculateDuration(String pageName){
        if(!TextUtils.isEmpty(pageName)) {
            if(this.pageMap.containsKey(pageName)) {
                Long pageEndTime;
                synchronized(this.pageMap) {
                    pageEndTime = this.pageMap.get(pageName);
                }
                if(pageEndTime == null) {
                    return;
                }
                long duration = System.currentTimeMillis() - pageEndTime.longValue();
                synchronized(pageInfo) {
                    try {
                        sqLiteHelper.insertPage(pageName, duration);
                    } catch (Throwable var9) {

                    }
                }
            }
        }
    }




}
