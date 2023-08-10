package com.example.testforcoders;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.BreakIterator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import com.example.testforcoders.TaskListAdapter;
public class interviewprep extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TaskListAdapter adapter;
    private List<Task> taskList = new ArrayList<>();
    private TaskDbHelper dbHelper;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RecyclerView taskRecyclerView;
        //TaskAdapter taskAdapter;
        FloatingActionButton addTaskButton;


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interviewprep);
              fab=findViewById(R.id.fab);

        dbHelper = new TaskDbHelper(this);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new TaskListAdapter(this,taskList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // showDatePicker();
                if (v.getId() == R.id.fab) {
                    // Get the current date
                    final Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    int hour = c.get(Calendar.HOUR_OF_DAY);
                    int minute = c.get(Calendar.MINUTE);
                    // Show DatePickerDialog to allow user to select a date
                    DatePickerDialog datePickerDialog = new DatePickerDialog(interviewprep.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    // Show TimePickerDialog to allow user to select a time
                                    TimePickerDialog timePickerDialog = new TimePickerDialog(interviewprep.this,
                                            new TimePickerDialog.OnTimeSetListener() {
                                                @Override
                                                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                                    // Create a new task with the selected date and time
                                                    Calendar calendar = Calendar.getInstance();
                                                    calendar.set(Calendar.YEAR, year);
                                                    calendar.set(Calendar.MONTH, monthOfYear);
                                                    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                                                    calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                                    calendar.set(Calendar.MINUTE, minute);

                                                    //Storing time in string
                                                    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a", Locale.getDefault());
                                                    String selectedTime = timeFormat.format(calendar.getTime());

                                                    //date
                                                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                                                    String selecteddate = sdf.format(calendar.getTime());


                                                    //adddialogboc for tasks
                                                   // showAddTaskDialog(calendar.getTimeInMillis());

                                                    final View view1 = LayoutInflater.from(interviewprep.this).inflate(R.layout.dialog_add_task,null);

                                                    AlertDialog.Builder builder = new AlertDialog.Builder(interviewprep.this);
                                                    builder.setTitle("Add Task");
                                                    builder.setView(view1);
                                                    builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialogInterface, int i) {

                                                            EditText nameEditText = view1.findViewById(R.id.nameEditText);
                                                            EditText descriptionEditText = view1.findViewById(R.id.descriptionEditText);
                                                            String name = nameEditText.getText().toString();
                                                            String description = descriptionEditText.getText().toString();


                                                            Task task = new Task(name, description, selecteddate,selectedTime );
                                                            dbHelper.addTask(task);
                                                            taskList.add(task);
                                                            adapter.notifyDataSetChanged();

                                                        }
                                                    });
                                                    builder.setNegativeButton("Cancel", null);

                                                    AlertDialog dialog = builder.create();
                                                    dialog.show();



                                                    // Update the RecyclerView
                                                    adapter.updateList(dbHelper.getAllTasks());



                                                }
                                            }, c.get(Calendar.HOUR_OF_DAY), c.get(Calendar.MINUTE), false);
                                    timePickerDialog.show();
                                }
                            }, year, month, day);
                    datePickerDialog.show();
                }
            }
        });



    }
//    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
//        @Override
//        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//            // Do something when the user sets the date
//        }
//    };
//
//
//
//    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, year);
//        calendar.set(Calendar.MONTH, month);
//        calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
//        showTimePicker(calendar);
//    }

//    private TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
//        @Override
//        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//            // Do something when the user sets the time
//        }
//    };

//    private void showTimePicker(Calendar calendar) {
//        int hourOfDay = calendar.get(Calendar.HOUR_OF_DAY);
//        int minute = calendar.get(Calendar.MINUTE);
//        TimePickerDialog timePickerDialog = new TimePickerDialog(
//                this,
//                timeSetListener,
//                hourOfDay,
//                minute,
//                DateFormat.is24HourFormat(this));
//        timePickerDialog.show();
//    }


//    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
//        calendar.set(Calendar.MINUTE, minute);
////        showAddTaskDialog(calendar.getTimeInMillis());
//    }

//    private void showAddTaskDialog(final long timestamp) {
//        final View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_task,null);
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Add Task");
//        builder.setView(view);
//        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//                EditText nameEditText = view.findViewById(R.id.nameEditText);
//                EditText descriptionEditText = view.findViewById(R.id.descriptionEditText);
//                String name = nameEditText.getText().toString();
//                String description = descriptionEditText.getText().toString();
//                Task task = new Task(timestamp , name, description );
//                dbHelper.addTask(task);
//                taskList.add(task);
//                adapter.notifyDataSetChanged();
//            }
//        });
//        builder.setNegativeButton("Cancel", null);
//
//        AlertDialog dialog = builder.create();
//        dialog.show();
//    }


    @Override
    protected void onResume() {
        super.onResume();
        taskList.clear();
        taskList.addAll(dbHelper.getAllTasks());
        adapter.notifyDataSetChanged();
    }
    }
