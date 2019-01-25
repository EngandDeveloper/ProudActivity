package com.proudactivity.proudactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/* GpaCalculator Activity is still under development. Mainly it will be for university 
gpa calculations but it is not limited with it.
*/

public class GpaCalculator extends AppCompatActivity {

    private Button returnMainPageButton;
    private EditText course1;
    private EditText course2;
    private EditText course3;
    private EditText course4;
    private EditText course5;
    private EditText course6;
    private EditText course7;
    private EditText course8;
    private EditText course9;
    private EditText course10;
    private EditText course11;
    private EditText course12;
    private EditText grade1;
    private EditText grade2;
    private EditText grade3;
    private EditText grade4;
    private EditText grade5;
    private EditText grade6;
    private EditText grade7;
    private EditText grade8;
    private EditText grade9;
    private EditText grade10;
    private EditText grade11;
    private EditText grade12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpa_calculator);

        //ReturnMainPageButton code
        /////////////////////////////////////////
        returnMainPageButton = (Button)findViewById(R.id.returnMainPageButton);
        returnMainPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GpaCalculator.this, MainActivity.class);
                startActivity(intent);
            }

        });

        course1 = (EditText)findViewById(R.id.course1);
        course2 = (EditText)findViewById(R.id.course2);
        course3 = (EditText)findViewById(R.id.course3);
        course4 = (EditText)findViewById(R.id.course4);
        course5 = (EditText)findViewById(R.id.course5);
        course6 = (EditText)findViewById(R.id.course6);
        course7 = (EditText)findViewById(R.id.course7);
        course8 = (EditText)findViewById(R.id.course8);
        course9 = (EditText)findViewById(R.id.course9);
        course10 = (EditText)findViewById(R.id.course10);
        course11 = (EditText)findViewById(R.id.course11);
        course12 = (EditText)findViewById(R.id.course12);
        grade1 = (EditText)findViewById(R.id.grade1);
        grade2 = (EditText)findViewById(R.id.grade2);
        grade3 = (EditText)findViewById(R.id.grade3);
        grade4 = (EditText)findViewById(R.id.grade4);
        grade5 = (EditText)findViewById(R.id.grade5);
        grade6 = (EditText)findViewById(R.id.grade6);
        grade7 = (EditText)findViewById(R.id.grade7);
        grade8 = (EditText)findViewById(R.id.grade8);
        grade9 = (EditText)findViewById(R.id.grade9);
        grade10 = (EditText)findViewById(R.id.grade10);
        grade11 = (EditText)findViewById(R.id.grade11);
        grade12 = (EditText)findViewById(R.id.grade12);






    }
}
