package com.example.shaheed.p2papp;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by shaheed on 4/21/18.
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    public List<QuotesClass> mList;

    public ListAdapter(List<QuotesClass> mList){

        this.mList = mList;

    }

    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.listview_model, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {

        holder.quote.setText(mList.get(position).getquote());
        holder.author.setText(mList.get(position).getauthor());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        View theView;

        public TextView quote, author;

        public ViewHolder(View itemView) {
            super(itemView);

            theView = itemView;

            quote = theView.findViewById(R.id.quote);
            author = theView.findViewById(R.id.author);
        }
    }
}