package com.example.myapplication;
import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
public class MainActivity extends AppCompatActivity {
    //Рандома
protected Random enemiesrnd = new Random();
protected Integer enemiescounter;
protected Integer enemiesnumber = 25;
protected TextView counter;
Intent intent;
//Bundle args = getIntent().getExtras();
protected Integer strikenumber = 5;
protected Random strikernd = new Random();
protected TextView timerView;
protected Integer mlseconds = 1000;
protected String winordefeat = "Поражение";
protected boolean start = false;

Integer money = (mlseconds/100)*enemiesnumber;
//Метод таймер
    public CountDownTimer timer = new CountDownTimer(10000, 1) {
        @Override
        public void onTick(long miliseconds) {
            timerView.setText(""+miliseconds/mlseconds);
        }

        @Override
        public void onFinish() {
            timerView.setText(winordefeat);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        intent = new Intent(this, HubscreenActivity.class);
        strikenumber = intent.getIntExtra("strikenumber", 5);
        enemiesnumber = intent.getIntExtra("enemiesnumber", 25);
        mlseconds = intent.getIntExtra("time", 1000);
        setEnemies();
        timerView = findViewById(R.id.textView2);

    }
    //Метод для объявления количества захватчиков (рандомно)
    protected void setEnemies(){
        counter = findViewById(R.id.textView);
        enemiescounter = enemiesrnd.nextInt(enemiesnumber);
        counter.setText(Integer.toString(enemiescounter));
    }
    //Метод для избавления от захватчиков
    public void toKill(View view){
        if (this.start == true){
            if (enemiescounter > 0){
                enemiescounter=enemiescounter-strikernd.nextInt(strikenumber);
                counter.setText(Integer.toString(enemiescounter));
                winordefeat = "Поражение";
            }
            if (enemiescounter <= 0){
                enemiescounter = 0;
                counter.setText(Integer.toString(enemiescounter));
                //this.intent.putExtra("strikenumber", strikenumber);
                timer.cancel();
                winordefeat = "Победа!";
                timer.onFinish();
                //startActivity(intent);
            }
        }
    }
    //Метод для старта таймер
    public void onStartTimer(View view){
        timer.start();
        timer.onFinish();
        Button button = (Button)findViewById(R.id.startbutton);
        button.setEnabled(false);
        this.start = true;
    }


}

