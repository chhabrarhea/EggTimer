package com.example.eggtimer;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    @RequiresApi(api = Build.VERSION_CODES.O)
    private Button b;
    CountDownTimer timer;
    boolean active=false;
    SeekBar s;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView edit=(TextView) findViewById(R.id.textView);

           s=(SeekBar) findViewById(R.id.seekBar);
        s.setMax(600);
        s.setProgress(0);
        s.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                final int min=progress/60;
                final int s=progress%60;
                String ss=String.valueOf(s);
                if(ss.length()==1)
                {
                    if(s<=9)
                        ss="0"+ss;
                    else
                        ss=ss+"0";

                }
                edit.setText(String.valueOf(min)+":"+ss);
                b=(Button) findViewById(R.id.button2);
                b.setOnClickListener(new View.OnClickListener() {
                     @Override
                    public void onClick(View v) {

                        time(min,s,edit);
                    }
                });


            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }

        });



        }
        @RequiresApi(api = Build.VERSION_CODES.O)
        public void time(int min, int sec, final TextView edit)
        {
           if(!active){
               s.setEnabled(false);
               b.setText("Stop");
            int x=(min*60)+sec;
            timer=new CountDownTimer(x*1000,1000) {
                @Override
                public void onTick(long millisUntilFinished) {

                    Button b=(Button) findViewById(R.id.button2);
                    int m=(int)millisUntilFinished/60/1000;
                    int s=(int)(millisUntilFinished/1000)%60;
                    String ss=String.valueOf(s);
                    if(ss.length()==1)
                    {
                        if(s<=9)
                            ss="0"+ss;
                        else
                            ss=ss+"0";

                    }
                    edit.setText(String.valueOf(m)+":"+ss);
                    active=true;

                }

                @Override
                public void onFinish() {
                    MediaPlayer mp=MediaPlayer.create(getBaseContext(),R.raw.rooster);
                       mp.start();
                    edit.setText("0:00");
                    active=true;


                }
            }.start();  }
            if(active)
           {
             active=false;
             s.setEnabled(true);
             timer.cancel();
             edit.setText("0:00");
             b.setText("Go");
             s.setProgress(0);

           }
        }
    public void stop(CountDownTimer t)
    {

    }


    }


