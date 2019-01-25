package com.proudactivity.proudactivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskList extends AppCompatActivity {

    private Button returnMainPageButton;
    DbHelper dbHelper;
    ListView taskToDo;
    ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);

        //Return main page button code
        /////////////////////////////////////////////////////////
        returnMainPageButton = (Button) findViewById(R.id.returnMainPageButton);
        returnMainPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TaskList.this, MainActivity.class);
                startActivity(intent);
            }
        });

        //Database db code
        ///////////////////////////////////////////////////////////
        dbHelper = new DbHelper(this);

        //ListView code
        ///////////////////////////////////////////////////////////
        taskToDo = (ListView) findViewById(R.id.list_todo);
        loadTaskList();



    }


    private void loadTaskList(){
        ArrayList<String> taskList = dbHelper.getTaskList();
        if(mAdapter == null){
            mAdapter = new ArrayAdapter<String>(this, R.layout.list_view, R.id.task_name,taskList);
            taskToDo.setAdapter(mAdapter);
        }else{
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);

        //Change the menu icon colour
        Drawable icon = menu.getItem(0).getIcon();
        icon.mutate();
        icon.setColorFilter(getResources().getColor(android.R.color.white), PorterDuff.Mode.SRC_IN);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.add_task:
                final EditText taskEditText = new EditText(this);
                AlertDialog aDiolog = new AlertDialog.Builder(this)
                        .setTitle("New Task")
                        .setMessage("What do you want to accomplish next?")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String task = String.valueOf(taskEditText.getText());
                                dbHelper.addNewTask(task);
                                loadTaskList();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                aDiolog.show();
                return true;



        }

        return super.onOptionsItemSelected(item);
    }

    public void deleteTask(View view){
        View parent = (View)view.getParent();
        TextView taskTextView = (TextView)parent.findViewById(R.id.task_name);
        Log.e("String", (String) taskTextView.getText());
        String task = String.valueOf(taskTextView.getText());
        dbHelper.deleteTask(task);
        loadTaskList();
    }
}
