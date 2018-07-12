package com.myapp.todolist.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.myapp.todolist.R;
import com.myapp.todolist.activities.ListActivity;
import com.myapp.todolist.network.ToDoItem;
import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{
    private List<ToDoItem> itemList;

    public CustomAdapter(ArrayList<ToDoItem> items) {
        this.itemList = items;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_to_do, parent, false);
        view.setOnClickListener(ListActivity.myOnClickListener);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.tvId.setText(String.valueOf(itemList.get(position).id));
        holder.tvTitle.setText(itemList.get(position).title);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public static  class MyViewHolder extends RecyclerView.ViewHolder {

        TextView tvId;
        TextView tvTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvId = itemView.findViewById(R.id.tv_id);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
