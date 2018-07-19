package com.example.anusri.voicerecgnition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;

import java.text.DateFormat;
import java.util.Calendar;

import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

public class Reminder extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    Button b1;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);
        tv=(TextView) findViewById(R.id.textView);




        b1 = (Button) findViewById(R.id.selectButton);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                // Get the date from our datepicker
                b1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DialogFragment datepicker=new DatePickerFragment();
                        datepicker.show(getSupportFragmentManager(),"datepicker");
                    }
                });


                // Ask our service to set an alarm for that date, this activity talks to the client that talks to the service

            }

        });
    }



    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Calendar c=(Calendar.getInstance());
        c.set(Calendar.YEAR,i);
        c.set(Calendar.MONTH,i1);
        c.set(Calendar.DAY_OF_MONTH,i2);
        String current= DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
        TextView txtview=(TextView)findViewById(R.id.textView);
        txtview.setText(current);
        Intent in=new Intent(Reminder.this,Time1.class);
        in.putExtra("year",i);
        in.putExtra("month",i1);
        in.putExtra("day",i2);
        startActivity(in);



    }

    }

