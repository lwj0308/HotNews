package com.xmcu.mynews.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xmcu.mynews.R;
import com.xmcu.mynews.entity.NewsInfo;

import java.util.List;

public class NewsFragmentAdapter extends RecyclerView.Adapter<NewsFragmentAdapter.ViewHolder> {
    private Context context;
    private List<NewsInfo> newsList;
    private LayoutInflater inflater;
    private OnItemClick onItemClick;

    public NewsFragmentAdapter(Context context, List<NewsInfo> newsList) {
        this.context = context;
        this.newsList = newsList;
        inflater = LayoutInflater.from(context);
    }

    //!!!!
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.item_recyclerview_news, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        if (onItemClick != null) {
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClick.onClick(view);
                }
            });
        }

        return holder;
    }

    //!!!!
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        NewsInfo newsInfo = newsList.get(i);
        viewHolder.textView.setText(String.format("%s %s", newsInfo.getTitle(), newsInfo.getDate()));
        viewHolder.textView2.setText(String.format("%s",newsInfo.getAuthor_name()));
        Glide.with(context).load(newsInfo.getThumbnail_pic_s()).into(viewHolder.imageView);
        if (newsInfo.getThumbnail_pic_s02() != null)
            Glide.with(context).load(newsInfo.getThumbnail_pic_s02()).into(viewHolder.imageView2);
        else viewHolder.imageView2.setVisibility(View.GONE);
        if (newsInfo.getThumbnail_pic_s03() != null)
            Glide.with(context).load(newsInfo.getThumbnail_pic_s03()).into(viewHolder.imageView3);
        else viewHolder.imageView3.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView, imageView2, imageView3;
        TextView textView, textView2;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.iv_news_pic_thumb);
            imageView2 = itemView.findViewById(R.id.iv_news_pic_thumb2);
            imageView3 = itemView.findViewById(R.id.iv_news_pic_thumb3);
            textView = itemView.findViewById(R.id.tv_news_info);
            textView2 = itemView.findViewById(R.id.tv_news_authoer);
            /*RequestOptions requestoptions = new RequestOptions()//gilde4后使用options配置参数
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_launcher)
                    .override(20, 20);
            Glide.with(context)
                    .asGif()//指定为gif图片
                    .load(R.mipmap.ic_launcher_round).apply(requestoptions)
//                    .placeholder()//占位图，指定图片加载未完成时显示
//                    .override(20, 20)//裁剪，指定大小
//                    .error(R.mipmap.ic_launcher)//错误图，图片加载失败时显示
                    .apply(requestoptions)
                    .into(imageView);*/
        }
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void onClick(View view);
    }
}
