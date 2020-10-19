package com.xmcu.mynews.presenter.impl;

import com.xmcu.mynews.entity.NewsInfo;
import com.xmcu.mynews.model.IGetNewsModel;
import com.xmcu.mynews.model.IModel;
import com.xmcu.mynews.model.impl.IGetNewsModelImpl;
import com.xmcu.mynews.presenter.IGetNewsPresenter;
import com.xmcu.mynews.view.ivew.INewsView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IGetNewsPresenterImpl implements IGetNewsPresenter {
    private static final String TAG = "IGetNewsPresenterImpl";

    private IGetNewsModel newsModel;//获取新闻
    private INewsView newsView;//显示新闻

    /**
     * 构造方法 创建实例时，将view层传入
     *
     * @param newsView view层
     */
    public IGetNewsPresenterImpl(INewsView newsView) {
        this.newsView = newsView;
        newsModel = new IGetNewsModelImpl();//创建model实例
    }

//    @Override
//    public void getNews(String type) {
//        newsModel.getNewsInfo(type, new Callback<News>() {
//            /**
//             * 请求成功的处理结果
//             * @param call 请求
//             * @param response 返回 结果
//             */
//            @Override
//            public void onResponse(Call<News> call, Response<News> response) {
//                News news = response.body();//获取返回结果的数据
//                Log.i(TAG, "onResponse: news->" + news.toString());
//                if (news.getErrorCode() != 0) {
//                    newsView.error(news.getReason());//view层显示错误信息
//                } else {
//                    NewsInfo[] newsInfos = news.getResult().getData();//得到新闻列表
//                    loadNewsInfo(newsInfos);//加载新闻信息
//                }
//            }
//
//            /**
//             * 请求失败的处理结果
//             * @param call 请求
//             * @param t 异常
//             */
//            @Override
//            public void onFailure(Call<News> call, Throwable t) {
//                Log.e(TAG, "onFailure: " + t.getMessage());
//                newsView.error(t.getMessage());//view层显示错误信息
//            }
//        });
//
//
//        newsModel.getNewsInfo(type, new IModel.AsyncCallback() {
//                        @Override
//                        public void onSuccess(Object success) {
//                            loadNewsInfo((NewsInfo[]) success);
//                        }
//
//                        @Override
//                        public void onError(Object error) {
//                            newsView.error((String) error);
//                        }
//        });
//
//
//    }

    @Override
    public void getNews(String type) {
        newsModel.getNewsInfo(type, new IModel.AsyncCallback() {
                        @Override
                        public void onSuccess(Object success) {
                            loadNewsInfo((NewsInfo[]) success);
                        }

                        @Override
                        public void onError(Object error) {
                            newsView.error((String) error);
                        }
        });
    }

    @Override
    public void loadNewsInfo(NewsInfo[] newsInfos) {
        if (newsInfos.length > 0) {
            List<NewsInfo> newslist = new ArrayList<>(Arrays.asList(newsInfos));//把数组转换成集合
            newsView.loadNewsList(newslist);//在view层显示
        } else
            newsView.error("抱歉，暂无数据！");//view层显示错误信息
    }

}
