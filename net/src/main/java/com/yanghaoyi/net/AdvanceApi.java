package com.yanghaoyi.net;


import com.yanghaoyi.net.bean.EmptyData;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * @author : YangHaoYi on 2018/6/11.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/6/11.
 *         Version : V 1.0
 */
public interface AdvanceApi {


    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("avi/v1/analytics/duration")
    Observable<EmptyData> sendPageData(@Body RequestBody param);

    @Headers({"Content-Type: application/json", "Accept: application/json"})
    @POST("avi/v1/analytics/click")
    Observable<EmptyData> sendClickEvent(@Body RequestBody param);


}
