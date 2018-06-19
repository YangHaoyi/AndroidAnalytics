package com.yanghaoyi.myanalytics.activity.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;


import com.yanghaoyi.analytics.MapBarClickAgent;
import com.yanghaoyi.db.SQLiteHelper;
import com.yanghaoyi.myanalytics.R;
import com.yanghaoyi.myanalytics.activity.model.AnalyticsModel;
import com.yanghaoyi.myanalytics.activity.model.ClickEventModel;
import com.yanghaoyi.myanalytics.activity.model.bean.ClickEvent;
import com.yanghaoyi.myanalytics.activity.model.bean.PageInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : YangHaoYi on 2018/6/7.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/6/7.
 *         Version : V 1.0
 */
public class MapActivity extends FragmentActivity implements View.OnClickListener {

    private TextView tvToMapPage;
    private TextView tvToSettingPage;
    private TextView tvToCollectionPage;
    private ListView userList;

    private SQLiteHelper sqLiteHelper;
    private Cursor cursor;
    private Cursor pageCursor;
    private ClickEventModel clickEventModel;
    private AnalyticsModel analyticsModel;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        init();
    }


    private void init(){
        sqLiteHelper = new SQLiteHelper(this);
        cursor = sqLiteHelper.select("click_info");
        pageCursor = sqLiteHelper.select("page_info");
        initView();
        initEvent();
        sendAnalytics();
    }

    private void initView(){
        tvToMapPage = findViewById(R.id.tvToMapPage);
        tvToSettingPage = findViewById(R.id.tvToSettingPage);
        tvToCollectionPage = findViewById(R.id.tvToCollectionPage);
        userList = findViewById(R.id.userList);
    }

    private void initEvent(){
        tvToMapPage.setOnClickListener(this);
        tvToSettingPage.setOnClickListener(this);
        tvToCollectionPage.setOnClickListener(this);
        clickEventModel = new ClickEventModel();
        analyticsModel = new AnalyticsModel();
        SimpleCursorAdapter simpleCursorAdapter = new SimpleCursorAdapter(this,
                R.layout.item_user,
                cursor,
                new String[]{"ClickEvent","Count"},
                new int[]{R.id.userName,R.id.userToken});
        userList.setAdapter(simpleCursorAdapter);
    }


    private void sendAnalytics(){
        List<ClickEvent> list = new ArrayList<>();
        cursor = sqLiteHelper.queryClickTable();
        if(cursor.moveToFirst()){
            for(int i=0;i<cursor.getCount();i++){
                cursor.moveToPosition(i);
                String eventName = cursor.getString(cursor.getColumnIndex("ClickEvent"));
                int count = cursor.getInt(cursor.getColumnIndex("Count"));
                ClickEvent clickEvent = new ClickEvent();
                clickEvent.setEventName(eventName);
                clickEvent.setClickCount(count);
                list.add(clickEvent);
                sqLiteHelper.updateClick(eventName,0);
            }
        }
        if(list.size()>0){
            clickEventModel.request(list);
        }
        List<PageInfo> listPage = new ArrayList<>();
        pageCursor = sqLiteHelper.queryPageTable();
        if(pageCursor.moveToFirst()){
            for(int i=0;i<pageCursor.getCount();i++){
                pageCursor.moveToPosition(i);
                String pageName = pageCursor.getString(pageCursor.getColumnIndex("PageName"));
                long duration = pageCursor.getInt(pageCursor.getColumnIndex("Duration"));
                PageInfo pageInfo = new PageInfo();
                pageInfo.setPageName(pageName);
                pageInfo.setDuration(duration);
                listPage.add(pageInfo);
            }
        }
        if(listPage.size()>0){
            analyticsModel.request(listPage);
            sqLiteHelper.truncatePageTable();
        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        MapBarClickAgent.INSTANCE.onPageStart("地图页");
    }

    @Override
    protected void onPause() {
        super.onPause();
        MapBarClickAgent.INSTANCE.onPageEnd("地图页");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvToMapPage:
                Toast.makeText(MapActivity.this,"当前为地图页",Toast.LENGTH_LONG).show();
                break;
            case R.id.tvToSettingPage:
                Intent intentSetting = new Intent(MapActivity.this, SettingActivity.class);
                startActivity(intentSetting);
                break;
            case R.id.tvToCollectionPage:
                Intent intentCollection = new Intent(MapActivity.this, SearchActivity.class);
                startActivity(intentCollection);
                break;
            default:
                break;
        }
    }
}
