package com.xmcu.mynews.view.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.xmcu.mynews.R;
import com.xmcu.mynews.util.Constant;
import com.xmcu.mynews.view.fragment.NewsFragment;

import org.jetbrains.annotations.NotNull;

/**
 * 主页面，程序入口，加载fragment
 */
public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        loadData();
        setListener();
    }

    private void initView() {
        tabLayout = findViewById(R.id.main_tab_layout);
        viewPager = findViewById(R.id.main_view_pager);
    }

    private void loadData() {
        PagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);//在ViewPager中加载fragment
    }

    private void setListener() {
        tabLayout.setupWithViewPager(viewPager);//设置TabLayout和ViewPager联动
    }

    /**
     * 自定义fragmentPagerAdapter，适配需求。
     * 配合fragment和ViewPager使用。
     */
    private static class MyPagerAdapter extends FragmentPagerAdapter {

        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        /**
         * 获取fragment实例
         *
         * @param i 下标
         * @return fragment实例
         */
        @NotNull
        @Override
        public Fragment getItem(int i) {
            Fragment fragment = new NewsFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.TYPE, Constant.NEWS_TYPE[i]);//把对应的新闻类型传入
            fragment.setArguments(bundle);//设置参数
            return fragment;
        }

        /**
         * 获取fragment数量：有多少title就有多少fragment
         *
         * @return fragment 数量
         */
        @Override
        public int getCount() {
            return Constant.NEWS_TITLES.length;
        }

        /**
         * 得到pagetitle，在tablayou中显示
         *
         * @param position 下标
         * @return titile
         */
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return Constant.NEWS_TITLES[position];
        }
    }

    private long BACK_TIME = 0;

    @Override
    public void onBackPressed() {
        int BACK_PRESSED_INTERVAL = 2000;
        if (System.currentTimeMillis() - BACK_TIME > BACK_PRESSED_INTERVAL) {
            BACK_TIME = System.currentTimeMillis();
            Toast.makeText(this,"再按一次返回键退出程序",Toast.LENGTH_LONG).show();
        } else {
            finish();
        }
    }
}
