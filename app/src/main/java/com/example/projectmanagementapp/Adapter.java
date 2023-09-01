package com.example.projectmanagementapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder>{
    private List<Task> tasks;
    private Context context;

    public Adapter(List<Task> tasks, Context context) {
        this.tasks = tasks;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.tid.setText(tasks.get(position).getTid());
        holder.title.setText(tasks.get(position).getTitle());
        holder.description.setText(tasks.get(position).getDescription());
        holder.member_email.setText(tasks.get(position).getMemberEmail());
        holder.status.setText(tasks.get(position).getStatus());
        holder.priority.setText(tasks.get(position).getPriority());
        holder.deadline.setText(tasks.get(position).getDeadline());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tid,title,description,member_email,status,priority,deadline;
        public MyViewHolder(View itemView) {
            super(itemView);
            tid = itemView.findViewById(R.id.tid);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            member_email = itemView.findViewById(R.id.member_email);
            status = itemView.findViewById(R.id.status);
            priority = itemView.findViewById(R.id.priority);
            deadline = itemView.findViewById(R.id.deadline);
        }
    }
}
