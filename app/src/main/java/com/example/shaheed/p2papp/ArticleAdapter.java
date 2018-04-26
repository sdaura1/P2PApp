package com.example.shaheed.p2papp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shaheed on 4/26/18.
 */

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {

    public List<ArticleClass> mList;

    public ArticleAdapter(List<ArticleClass> mList){

        this.mList = mList;

    }

    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_model, parent, false);
        return new ArticleAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ArticleAdapter.ViewHolder holder, int position) {
        holder.article.setText(mList.get(position).getArticle());
        holder.author.setText(mList.get(position).getAuthor());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View theView;

        public TextView article, author;

        public ViewHolder(View itemView) {
            super(itemView);

            theView = itemView;

            article = theView.findViewById(R.id.quote);
            author = theView.findViewById(R.id.author);
        }
    }
}
