package com.example.anusri.voicerecgnition;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Time1 extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener {
    TimePickerDialog timePickerDialog;

    TimePicker myTimePicker;
    Button buttonstartSetDialog;
    TextView textAlarmPrompt;
    final static int RQS_1 = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time1);
        textAlarmPrompt = (TextView)findViewById(R.id.alarmprompt);

        buttonstartSetDialog = (Button)findViewById(R.id.startSetDialog);
        buttonstartSetDialog.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                DialogFragment timepicker=new TimePickerFragment();
                timepicker.show(getSupportFragmentManager(),"timepicker");



            }});
    }



    @Override
    public void onTimeSet(TimePicker timePicker, int i, int i1) {
        Calendar calSet = Calendar.getInstance();



        Intent in=getIntent();
        int day=in.getIntExtra("day",0);
        int month=in.getIntExtra("month",0);
        int year=in.getIntExtra("year",0);
        calSet.set(Calendar.DATE,day);
        calSet.set(Calendar.MONTH,month);
        calSet.set(Calendar.YEAR,year);
        calSet.set(Calendar.HOUR_OF_DAY, i);
        calSet.set(Calendar.MINUTE, i1);
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);



        // Notify the user what they just did

        setAlarm(calSet);
    }
    private void setAlarm(Calendar targetCal){

        textAlarmPrompt.setText(
                "\n\n***\n"
                        + "Alarm is set@ " + targetCal.getTime() + "\n"
                        + "***\n");

        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(), pendingIntent);

    }

    }

