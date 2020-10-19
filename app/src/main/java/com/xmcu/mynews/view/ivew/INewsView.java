package com.xmcu.mynews.view.ivew;

import com.xmcu.mynews.entity.NewsInfo;

import java.util.List;

/**
 * view层，显示内容
 */
public interface INewsView {
    /**
     * 加载新闻列表
     *
     * @param newsList 新闻列表
     */
    void loadNewsList(List<NewsInfo> newsList);

    /**
     * 显示错误信息
     *
     * @param error 错误信息
     */
    void error(String error);
}
