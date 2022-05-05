package com.example.campus.ui.notice;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campus.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewAdapter> {
    private Context context;
    private ArrayList<NoticeData> list;

    public NoticeAdapter(Context context, ArrayList<NoticeData> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public NoticeViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.notice_item_layout, parent, false);
        return new NoticeViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoticeViewAdapter holder, @SuppressLint("RecyclerView") int position) {
        NoticeData cur_data = list.get(position);
        holder.tv_notice_time.setText(cur_data.getTime());
        holder.tv_notice_date.setText(cur_data.getDate());
        holder.tv_news_item_synopsis.setText(cur_data.getTitle());
        try {
            if (cur_data.getImage() != null)
                Picasso.get().load(cur_data.getImage()).into(holder.iv_news_item_image);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeViewAdapter extends RecyclerView.ViewHolder {
        private TextView tv_news_item_synopsis,tv_notice_date,tv_notice_time;
        private ImageView iv_news_item_image;

        public NoticeViewAdapter(@NonNull View itemView) {
            super(itemView);
            tv_news_item_synopsis = itemView.findViewById(R.id.tv_news_item_synopsis);
            tv_notice_time = itemView.findViewById(R.id.tv_notice_time);
            tv_notice_date = itemView.findViewById(R.id.tv_notice_date);
            iv_news_item_image = itemView.findViewById(R.id.iv_news_item_image);
        }
    }
}
