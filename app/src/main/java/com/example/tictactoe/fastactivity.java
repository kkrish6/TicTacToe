package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class fastactivity extends AppCompatActivity {
 TextView tv_clicks,tv__time;
 Button b_click,b_start;
CountDownTimer timer;
int time=10;
int clicks=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fastactivity);
        tv__time=findViewById(R.id.tv_time);
        tv_clicks=findViewById(R.id.tv_clicks);
        b_click=findViewById(R.id.b_click);
        b_start =findViewById((R.id.b_start));
        b_start.setEnabled(true);
        b_click.setEnabled(false);

        timer=new CountDownTimer(10000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                time--;
                tv__time.setText("Time:"+time);

            }

            @Override
            public void onFinish() {
                b_start.setEnabled(true);
                b_click.setEnabled(false);
                tv__time.setText("Time:0");


            }
        };
b_click.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        clicks++;
        tv_clicks.setText("clicks:"+clicks);

    }
});
     b_start.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             timer.start();
             b_start.setEnabled(false);
             b_click.setEnabled(true);
             clicks=0;
             time=10;
             tv__time.setText("Time"+time);
             tv_clicks.setText("Clicks"+clicks);


         }
     });
    }
}
