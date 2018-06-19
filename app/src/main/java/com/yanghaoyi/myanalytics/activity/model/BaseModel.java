package com.yanghaoyi.myanalytics.activity.model;

import com.yanghaoyi.net.AdvanceApi;
import com.yanghaoyi.net.client.ApiClient;

/**
 * @author : YangHaoYi on 2018/6/11.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/6/11.
 *         Version : V 1.0
 */
public class BaseModel {

    protected AdvanceApi api;
    private ApiClient client;

    public BaseModel() {
        initClient();
    }

    private void initClient(){
        client = ApiClient.INSTANCE;
        client.init();
        api = client.create(AdvanceApi.class);
    }



}
