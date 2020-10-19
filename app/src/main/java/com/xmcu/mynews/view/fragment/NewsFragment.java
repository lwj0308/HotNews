package com.xmcu.mynews.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xmcu.mynews.R;
import com.xmcu.mynews.entity.NewsInfo;
import com.xmcu.mynews.presenter.IGetNewsPresenter;
import com.xmcu.mynews.presenter.impl.IGetNewsPresenterImpl;
import com.xmcu.mynews.util.Constant;
import com.xmcu.mynews.view.activity.NewsDetailActivity;
import com.xmcu.mynews.view.adapter.NewsFragmentAdapter;
import com.xmcu.mynews.view.ivew.INewsView;

import java.util.ArrayList;
import java.util.List;

/**
 * 显示新闻列表的fragment，实现view层的方法
 */
public class NewsFragment extends Fragment implements INewsView {

    private static final String TAG = NewsFragment.class.getSimpleName();
    private RecyclerView recyclerView;//列表控件
    private List<NewsInfo> newsList;//新闻列表集合
    private NewsFragmentAdapter adapter;//列表显示适配器
    private IGetNewsPresenter presenter;//主持者，控制流程

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        initView(view);
        loadData();
        setListeners();
        return view;
    }


    private void initView(View view) {
        recyclerView = view.findViewById(R.id.fc_recyclerview);
        newsList = new ArrayList<>();//初始化新闻集合，配合适配器
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));//设置recyclerview显示方式
        adapter = new NewsFragmentAdapter(getContext(), newsList);//创建适配器
        recyclerView.setAdapter(adapter);//为recyclerview设置适配器
        presenter = new IGetNewsPresenterImpl(this);//初始化
    }

    private void loadData() {
        assert getArguments() != null;
        String type = getArguments().getString(Constant.TYPE);//从传入的参数中获取请求新闻的类型
        presenter.getNews(type);//主持人获取新闻
    }

    private void setListeners() {
        //设置item点击事件
        adapter.setOnItemClick(new NewsFragmentAdapter.OnItemClick() {
            @Override
            public void onClick(View view) {
                int position = recyclerView.getChildAdapterPosition(view);//获取点击item的下标
                Log.i(TAG, "onClick: position=" + position);
                String newsUrl = newsList.get(position).getUrl();//通过下标，获取新闻信息
                Intent intent = new Intent(getActivity(), NewsDetailActivity.class);//点击item进入新闻详情页面
                intent.putExtra(Constant.URL, newsUrl);//把新闻的URL传入到下一个页面
                startActivity(intent);
            }
        });
    }

    //！！！！!
    @Override
    public void loadNewsList(List<NewsInfo> newsList) {
        this.newsList.addAll(newsList);//把请求到的新闻集合加入
        adapter.notifyDataSetChanged();//适配器更新显示
    }

    @Override
    public void error(String error) {
        Toast.makeText(getContext(), error, Toast.LENGTH_LONG).show();//Toast显示提示信息
    }
}
