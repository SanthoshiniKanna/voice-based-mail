package com.example.anusri.voicerecgnition;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManagerCompat myManager=NotificationManagerCompat.from(context);
        NotificationCompat.Builder mynoti=new NotificationCompat.Builder(context,"myManager`");
        mynoti.setContentTitle("Stand up notifaction");
        mynoti.setContentText("hii");
        mynoti.setSmallIcon(R.drawable.ic_launcher_background);

        Toast.makeText(context, "Alarm received!", Toast.LENGTH_LONG).show();
        Vibrator vibrator = (Vibrator) context
                .getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(200000);

    }
}
