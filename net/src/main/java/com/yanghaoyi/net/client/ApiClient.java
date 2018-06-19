package com.yanghaoyi.net.client;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author : YangHaoYi on 2018/6/11.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/6/11.
 *         Version : V 1.0
 */
public enum ApiClient {

    INSTANCE;
    private static final String BASE_URL = "http://10.30.10.207:8080/";
    private OkHttpClient delegateClient;
    private Retrofit retrofit;

    public <T> T create(Class<T> service) {
        T t = retrofit.create(service);
        return t;
    }


    public ApiClient init(){
        delegateClient = new OkHttpClient()
                .newBuilder()
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(delegateClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return this;
    }

}
