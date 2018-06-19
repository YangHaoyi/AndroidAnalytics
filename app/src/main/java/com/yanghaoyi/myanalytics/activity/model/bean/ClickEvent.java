package com.yanghaoyi.myanalytics.activity.model.bean;

/**
 * @author : YangHaoYi on 2018/6/12.
 *         Email  :  yang.haoyi@qq.com
 *         Description :
 *         Change : YangHaoYi on 2018/6/12.
 *         Version : V 1.0
 */
public class ClickEvent {

    private String eventName;
    private int clickCount;

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public int getClickCount() {
        return clickCount;
    }

    public void setClickCount(int clickCount) {
        this.clickCount = clickCount;
    }
}
