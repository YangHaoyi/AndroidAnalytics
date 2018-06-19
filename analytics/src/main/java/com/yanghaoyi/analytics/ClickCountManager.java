package com.yanghaoyi.analytics;

import android.content.Context;
import android.database.Cursor;

import com.yanghaoyi.db.SQLiteHelper;

/**
 * @author : YangHaoYi on 2018/6/15.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/6/15.
 *         Version : V 1.0
 */
public class ClickCountManager {

    private SQLiteHelper sqLiteHelper;
    private Cursor cursor;

    public ClickCountManager(Context context) {
        sqLiteHelper = new SQLiteHelper(context);
        cursor = sqLiteHelper.select("click_info");
    }

    public void setEvent(String event){
        String args[] = new String[]{"%"+event+"%"};
        cursor = sqLiteHelper.queryClick(args);
        //判断游标是否为空
        if(cursor.moveToFirst()){
            String eventName = null;
            int eventCount = 0;
            //遍历游标
            for(int i=0;i<cursor.getCount();i++){
                cursor.move(i);
                eventName = cursor.getString(cursor.getColumnIndex("ClickEvent"));
                eventCount = cursor.getInt(cursor.getColumnIndex("Count"));
            }
            if(null==eventName){
                sqLiteHelper.insertClick(event,1);
            }else {
                eventCount = eventCount + 1;
                sqLiteHelper.updateClick(event,eventCount);
            }
        }else {
            sqLiteHelper.insertClick(event,1);
        }
    }


}
