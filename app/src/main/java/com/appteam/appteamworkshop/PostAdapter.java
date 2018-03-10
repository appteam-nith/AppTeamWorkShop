package com.appteam.appteamworkshop;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by sahil on 10/3/18.
 */

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder> {

    private ArrayList<Post> list;

    public PostAdapter(ArrayList<Post> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Post post = list.get(position);

        if(post!= null){
            holder.titleTextView.setText(post.getTitle());
            holder.messageTextView.setText(post.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView, messageTextView;
        public ViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.item_title);
            messageTextView = itemView.findViewById(R.id.item_message);
        }
    }
}
