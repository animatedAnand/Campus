package com.example.campus.pdf;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.campus.R;

import java.util.List;

public class PdfAdapter extends RecyclerView.Adapter<PdfAdapter.PdfViewAdapter> {

    private List<PdfData> list;
    private Context context;

    public PdfAdapter(List<PdfData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public PdfViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.pdf_item_layout,parent,false);
        return new PdfViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PdfViewAdapter holder, int position) {
        PdfData cur_pdf=list.get(position);
        holder.tv_pdf_title.setText(cur_pdf.getTitle());
        holder.iv_pdf_download_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(cur_pdf.getURL().toString()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class PdfViewAdapter extends RecyclerView.ViewHolder {

        private ImageView iv_pdf_download_icon;
        private TextView tv_pdf_title;
        public PdfViewAdapter(@NonNull View itemView) {
            super(itemView);
            iv_pdf_download_icon=itemView.findViewById(R.id.iv_pdf_download_icon);
            tv_pdf_title=itemView.findViewById(R.id.tv_pdf_title);
        }
    }
}
