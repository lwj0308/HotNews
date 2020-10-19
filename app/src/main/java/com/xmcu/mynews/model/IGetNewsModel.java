package com.xmcu.mynews.model;

/**
 * 获取新闻业务处理
 */
public interface IGetNewsModel extends IModel {
//    /**
//     * 获取新闻信息
//     *
//     * @param type         新闻类型
//     * @param newsCallback 请求后的回调事件
//     */
//    void getNewsInfo(String type, Callback<News> newsCallback);

    /**
     * 获取新闻列表
     *
     * @param type
     * @param callback
     */
    void getNewsInfo(String type, AsyncCallback callback);
}
