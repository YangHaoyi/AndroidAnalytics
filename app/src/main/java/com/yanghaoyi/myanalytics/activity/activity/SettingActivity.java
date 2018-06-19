package com.yanghaoyi.myanalytics.activity.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yanghaoyi.analytics.MapBarClickAgent;
import com.yanghaoyi.myanalytics.R;

/**
 * @author : YangHaoYi on 2018/6/7.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/6/7.
 *         Version : V 1.0
 */
public class SettingActivity extends FragmentActivity implements View.OnClickListener{

    private TextView tvToMapPage;
    private TextView tvToSettingPage;
    private TextView tvToCollectionPage;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
    }


    private void init(){
        initView();
        initEvent();
    }

    private void initView(){
        tvToMapPage = findViewById(R.id.tvToMapPage);
        tvToSettingPage = findViewById(R.id.tvToSettingPage);
        tvToCollectionPage = findViewById(R.id.tvToCollectionPage);
    }

    private void initEvent(){
        tvToMapPage.setOnClickListener(this);
        tvToSettingPage.setOnClickListener(this);
        tvToCollectionPage.setOnClickListener(this);
    }



    @Override
    protected void onResume() {
        super.onResume();
        MapBarClickAgent.INSTANCE.onPageStart("设置页");
    }

    @Override
    protected void onPause() {
        super.onPause();
        MapBarClickAgent.INSTANCE.onPageEnd("设置页");
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvToMapPage:
                Intent intentSetting = new Intent(SettingActivity.this, MapActivity.class);
                startActivity(intentSetting);
                break;
            case R.id.tvToSettingPage:
                Toast.makeText(SettingActivity.this,"当前为设置页",Toast.LENGTH_LONG).show();
                break;
            case R.id.tvToCollectionPage:
                Intent intentCollection = new Intent(SettingActivity.this, SearchActivity.class);
                startActivity(intentCollection);
                break;
            default:
                break;
        }
    }
}
