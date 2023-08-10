package com.example.testforcoders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.List;
import java.util.Locale;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder> {

    private final List<Task> tasks;
    private final SimpleDateFormat dateFormat;
    private final LayoutInflater inflater;

    public TaskListAdapter(Context context, List<Task> newTaskList) {
        tasks = new ArrayList<>();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_task, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.textViewName.setText(task.getName());
        holder.textViewDescription.setText(task.getDescription());
        holder.textViewTimestamp.setText(dateFormat.format(task.getdate()));
        holder.textViewTimestamp.setText(dateFormat.format(task.getTime()));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void updateList(List<Task> newTaskList) {
        tasks.clear();
        tasks.addAll(newTaskList);
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewName;
        public TextView textViewDescription;
        public TextView textViewTimestamp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.nameEditText);
            textViewDescription = itemView.findViewById(R.id.descriptionEditText);
//            textViewTimestamp = itemView.findViewById(R.id.text_view_timestamp);
        }
    }
}