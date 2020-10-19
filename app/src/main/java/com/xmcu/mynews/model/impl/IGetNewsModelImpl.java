package com.xmcu.mynews.model.impl;

import com.xmcu.mynews.entity.News;
import com.xmcu.mynews.model.IGetNewsModel;
import com.xmcu.mynews.util.Constant;
import com.xmcu.mynews.util.NewsAPI;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class IGetNewsModelImpl implements IGetNewsModel {
    private static final String TAG = "IGetNewsModelImpl";

//    public void getNewsInfo(String type, Callback<News> newsCallback) {
//        Retrofit retrofit = new Retrofit.Builder()
//                //设置数据解析器
//                .addConverterFactory(GsonConverterFactory.create())
//                .baseUrl(Constant.JUHE_URL)
//                .build();//网络请求
//        // 创建网络请求接口的实例
//        NewsAPI newsAPI = retrofit.create(NewsAPI.class);
//        //对发送请求进行封装
//        Call<News> newsCall = newsAPI.getNews(type, Constant.JUHE_APP_KEY);//传入请求参数
//        newsCall.enqueue(newsCallback);//异步数据请求
//    }

    @Override
    public void getNewsInfo(String type, final AsyncCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                //设置数据解析器
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(Constant.JUHE_URL)
                .build();//网络请求
        // 创建网络请求接口的实例
        NewsAPI newsAPI = retrofit.create(NewsAPI.class);
        //对发送请求进行封装
        Call<News> newsCall = newsAPI.getNews(type, Constant.JUHE_APP_KEY);//传入请求参数
        newsCall.enqueue(new Callback<News>() {
            @Override
            public void onResponse(@NotNull Call<News> call, @NotNull Response<News> response) {
                callback.onSuccess(response.body().getResult().getData());
            }

            @Override
            public void onFailure(@NotNull Call<News> call, @NotNull Throwable t) {
                callback.onError(t.getMessage());
            }
        });//异步数据请求
    }

}
