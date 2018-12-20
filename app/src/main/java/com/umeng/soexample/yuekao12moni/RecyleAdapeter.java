package com.umeng.soexample.yuekao12moni;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.umeng.soexample.yuekao12moni.bean.Porduct;

import java.util.List;
import java.util.Random;

/**
 * Created by W on 2018/12/19.
 */

public class RecyleAdapeter extends RecyclerView.Adapter<RecyleAdapeter.ViewHolder> {
    private Context context;
    private List<Porduct.DataBean> list;

    public RecyleAdapeter(Context context, List<Porduct.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyleAdapeter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyle_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyleAdapeter.ViewHolder holder, int position) {
        Porduct.DataBean porduct = list.get(position);
        Glide.with(context).load(porduct.getPic_url()).into(holder.imgLogo);
        holder.txtTitle.setText(porduct.getNews_title()+"");


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgLogo;
        private TextView txtTitle;
        public ViewHolder(View itemView) {
            super(itemView);
            imgLogo = itemView.findViewById(R.id.imglogo);
            txtTitle = itemView.findViewById(R.id.img_title);
        }
    }
}
