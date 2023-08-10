package com.example.testforcoders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

        private List<Task> taskList;

        public TaskAdapter(List<Task> taskList) {
            this.taskList = taskList;
        }

        @NonNull
        @Override
        public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_task, parent, false);
            return new TaskViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
            Task task = taskList.get(position);
            holder.taskNameTextView.setText(task.getName());
            holder.taskDescriptionTextView.setText(task.getDescription());
        }

        @Override
        public int getItemCount() {
            return taskList.size();
        }

        public class TaskViewHolder extends RecyclerView.ViewHolder {

            public TextView taskNameTextView;
            public TextView taskDescriptionTextView;

            public TaskViewHolder(View itemView) {
                super(itemView);
                taskNameTextView = itemView.findViewById(R.id.nameEditText);
                taskDescriptionTextView = itemView.findViewById(R.id.descriptionEditText);
            }

        }
    public void updateList(List<Task> newTaskList) {
        taskList.clear();
        taskList.addAll(newTaskList);
        notifyDataSetChanged();
    }
    }


