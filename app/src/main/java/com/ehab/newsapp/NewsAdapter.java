package com.ehab.newsapp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ehab.newsapp.model.Results;

import java.util.List;

/**
 * Created by ehabhamdy on 3/23/18.
 */

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    List<Results> data;

    private RecyclerViewClickListener mListener;

    public interface RecyclerViewClickListener {
        void onClick(Results result);
    }

    public NewsAdapter( List<Results> results , RecyclerViewClickListener listener) {
        this.data = results;
        mListener = listener;
    }

    @Override
    public NewsHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item, parent,false);
        return new NewsHolder(itemView);
    }

    @Override
    public void onBindViewHolder(NewsHolder holder, int position) {
        Results results = data.get(position);
        holder.titleTextView.setText(results.getSectionName());
        holder.descTextView.setText(results.getWebTitle());
        String publicationDate = results.getWebPublicationDate();
        publicationDate = publicationDate.substring(0,publicationDate.indexOf('T'));
        holder.dateTextView.setText(publicationDate);
        holder.authorTextView.setText(results.getTags()[0].getWebTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setData(List<Results> results){
        this.data = results;
        notifyDataSetChanged();
    }

    public class NewsHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView titleTextView;
        public TextView descTextView;
        public TextView dateTextView;
        public TextView authorTextView;

        public NewsHolder(View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.titleTextView);
            descTextView = itemView.findViewById(R.id.descriptionTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            authorTextView = itemView.findViewById(R.id.authorTextView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Results clickedItem = data.get(getAdapterPosition());
            mListener.onClick(clickedItem);
        }
    }
}
