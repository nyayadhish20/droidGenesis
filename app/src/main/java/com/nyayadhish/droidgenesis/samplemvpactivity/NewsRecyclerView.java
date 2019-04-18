package com.nyayadhish.droidgenesis.samplemvpactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nyayadhish.droidgenesis.R;
import com.nyayadhish.droidgenesis.model.News;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Nikhil Nyayadhish on 26 Mar 2019 at 11:15.
 */
public class NewsRecyclerView extends RecyclerView.Adapter<NewsRecyclerView.ViewHolder> {

    private Context mContext;
    private List<News> newsList = new ArrayList<>();

    public NewsRecyclerView (Context mContext, List<News> newsList ){
        this.mContext = mContext;
        this.newsList = newsList;
    }


    @NonNull
    @Override
    public NewsRecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_list, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsRecyclerView.ViewHolder holder, int position) {
        if (newsList != null && newsList.size()>0){
            holder.title.setText(newsList.get(holder.getAdapterPosition()).getTitle());
            holder.description.setText(newsList.get(holder.getAdapterPosition()).getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.desc);


        }
    }
}
