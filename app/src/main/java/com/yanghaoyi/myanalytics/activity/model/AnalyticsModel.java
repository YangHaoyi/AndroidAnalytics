package com.yanghaoyi.myanalytics.activity.model;

import com.google.gson.Gson;
import com.yanghaoyi.myanalytics.activity.model.bean.PageInfo;
import com.yanghaoyi.net.bean.EmptyData;

import java.util.List;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


/**
 * @author : YangHaoYi on 2018/6/11.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/6/11.
 *         Version : V 1.0
 */
public class AnalyticsModel extends BaseModel{

    public void request(List<PageInfo> list){
        Gson gson = new Gson();
        String json = gson.toJson(list);
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Observable<EmptyData> response = api.sendPageData(requestBody);
        response.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<EmptyData>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println(""+e.getLocalizedMessage());
                    }

                    @Override
                    public void onNext(EmptyData emptyData) {
                        System.out.println("Send______________:success");
                    }
                });
    }



}
