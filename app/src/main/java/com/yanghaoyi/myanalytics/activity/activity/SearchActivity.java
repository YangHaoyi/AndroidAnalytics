package com.yanghaoyi.myanalytics.activity.activity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.yanghaoyi.analytics.MapBarClickAgent;
import com.yanghaoyi.db.SQLiteHelper;
import com.yanghaoyi.myanalytics.R;

/**
 * @author : YangHaoYi on 2018/6/7.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/6/7.
 *         Version : V 1.0
 */
public class SearchActivity extends FragmentActivity implements View.OnClickListener{

    private TextView tvToMapPage;
    private TextView tvToSettingPage;
    private TextView tvToCollectionPage;


    private TextView tvOil;
    private TextView tvWc;
    private TextView tvAids;

    private TextView tvPark;
    private TextView tvBank;
    private TextView tv4s;


    private int oilCount;
    private int wcCount;
    private int aidsCount;

    private int parkCount;
    private int bankCount;
    private int sCount;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
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

        tvOil = findViewById(R.id.tvOil);
        tvWc = findViewById(R.id.tvWc);
        tvAids = findViewById(R.id.tvAids);
        tvPark = findViewById(R.id.tvPark);
        tvBank = findViewById(R.id.tvBank);
        tv4s = findViewById(R.id.tv4s);
    }

    private void initEvent(){
        tvToMapPage.setOnClickListener(this);
        tvToSettingPage.setOnClickListener(this);
        tvToCollectionPage.setOnClickListener(this);

        tvOil.setOnClickListener(this);
        tvWc.setOnClickListener(this);
        tvAids.setOnClickListener(this);
        tvPark.setOnClickListener(this);
        tvBank.setOnClickListener(this);
        tv4s.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MapBarClickAgent.INSTANCE.onPageStart("搜索页");
    }

    @Override
    protected void onPause() {
        super.onPause();
        MapBarClickAgent.INSTANCE.onPageEnd("搜索页");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tvToMapPage:
                Intent intentSetting = new Intent(SearchActivity.this, MapActivity.class);
                startActivity(intentSetting);
                break;
            case R.id.tvToSettingPage:
                Intent intentCollection = new Intent(SearchActivity.this, SettingActivity.class);
                startActivity(intentCollection);
                break;
            case R.id.tvToCollectionPage:
                Toast.makeText(SearchActivity.this,"当前为搜索页",Toast.LENGTH_LONG).show();
                break;
            case R.id.tvOil:
                oilCount++;
                tvOil.setText("加油站"+oilCount);
                MapBarClickAgent.INSTANCE.onEvent("加油站");
                break;
            case R.id.tvWc:
                wcCount++;
                tvWc.setText("厕所"+wcCount);
                MapBarClickAgent.INSTANCE.onEvent("厕所");
                break;
            case R.id.tvAids:
                aidsCount++;
                tvAids.setText("急救中心"+aidsCount);
                MapBarClickAgent.INSTANCE.onEvent("急救中心");
                break;
            case R.id.tvPark:
                parkCount++;
                tvPark.setText("停车场"+parkCount);
                MapBarClickAgent.INSTANCE.onEvent("停车场");
                break;
            case R.id.tvBank:
                bankCount++;
                tvBank.setText("银行"+bankCount);
                MapBarClickAgent.INSTANCE.onEvent("银行");
                break;
            case R.id.tv4s:
                sCount++;
                tv4s.setText("4S店"+sCount);
                MapBarClickAgent.INSTANCE.onEvent("4S店");
                break;
            default:
                break;
        }
    }
}
