package com.yanghaoyi.myanalytics.activity.model.bean;

/**
 * @author : YangHaoYi on 2018/6/15.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/6/15.
 *         Version : V 1.0
 */
public class PageInfo {
    private String pageName;
    private long duration;

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }
}
