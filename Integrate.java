package com.example.anusri.voicerecgnition;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.preference.PreferenceManager;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Integrate extends AppCompatActivity {
    TextView in;
    Button send;
    ImageButton b1;
    TextToSpeech t1;
    String from,to,subject,body;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integrate);
        in=(TextView) findViewById(R.id.integrate);
        b1=(ImageButton)findViewById(R.id.button);
        SharedPreferences data= PreferenceManager.getDefaultSharedPreferences(getBaseContext());
        to=data.getString("to","");
        subject=data.getString("subject","");
        body=data.getString("body","");
        result="To:\n\n"+to+"\n\n"+"Subject:\n\n"+subject+"\n\n"+"Body:\n\n"+body;
        in.setText(result);
        send=(Button) findViewById(R.id.send);
        send.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent email=new Intent(Intent.ACTION_SENDTO);
                                        String mail="mailto:"+ Uri.encode(to)+"?subject="+Uri.encode(subject)+"&body="+Uri.encode(body);
                                        Uri uri=Uri.parse(mail);
                                        email.setData(uri);

                                        startActivity(Intent.createChooser(email,"Select mail here"));

                                    }
                                }
        );
        t1=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    t1.setLanguage(Locale.UK);
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toSpeak = in.getText().toString();
                Toast.makeText(getApplicationContext(), toSpeak,Toast.LENGTH_SHORT).show();
                if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP) {
                    t1.speak(toSpeak, TextToSpeech.QUEUE_FLUSH, null, null);
                }else
                    {t1.speak(toSpeak,TextToSpeech.QUEUE_FLUSH,null);}
            }
        });
    }

    public void onPause(){
        if(t1 !=null){
            t1.stop();
            t1.shutdown();
        }
        super.onPause();
    }


}


