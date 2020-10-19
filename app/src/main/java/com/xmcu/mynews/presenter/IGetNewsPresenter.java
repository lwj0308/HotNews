package com.xmcu.mynews.presenter;

import com.xmcu.mynews.entity.NewsInfo;

/**
 * 获取新闻的主持者
 */
public interface IGetNewsPresenter {

    /**
     * 获取新闻
     *
     * @param type 类型
     */
    void getNews(String type);

    /**
     * 加载新闻列表
     *
     * @param newsInfos 新闻信息
     */
    void loadNewsInfo(NewsInfo[] newsInfos);
}
