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

protected Random rnd = new Random();
protected Integer enemiescounter;
protected Integer enemiesnumber = 25;
protected TextView counter;
public Intent intent;
protected Integer strikenumber = 3;
protected TextView timerView;
protected Integer mlseconds = 10000;
protected String winordefeat = "Поражение";
protected boolean start = false;
protected boolean finish = false;
protected boolean winstate = false;
protected Integer money = 0;
protected Bundle args;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.intent = new Intent(this, HubscreenActivity.class);
        this.args = getIntent().getExtras();
        if (this.args != null){
            this.strikenumber = this.args.getInt("strike_number", 3);
            this.enemiesnumber = this.args.getInt("enemies_number", 25);
            this.mlseconds = this.args.getInt("time", 10000);
            this.money = this.args.getInt("money", 0);
        }
        setEnemies();
        timerView = findViewById(R.id.textView2);
    }
    //Метод таймер
    public CountDownTimer timer = new CountDownTimer(this.mlseconds, 1000) {
        @Override
        public void onTick(long miliseconds) {
            timerView.setText(""+miliseconds/1000);
        }

        @Override
        public void onFinish() {
            timerView.setText(winordefeat);
            finish = true;
            toKill(findViewById(R.id.redbutton));
        }
    };
    //Метод для объявления количества захватчиков (рандомно)
    protected void setEnemies(){
        this.counter = findViewById(R.id.textView);
        this.enemiescounter = this.rnd.nextInt(this.enemiesnumber);
        this.counter.setText(Integer.toString(this.enemiescounter));
    }
    //Метод для избавления от захватчиков
    public void toKill(View view){
        if (this.start == true){
            if (this.enemiescounter > 0){
                this.winstate = false;
                if (this.finish == false)
                this.enemiescounter=this.enemiescounter-this.rnd.nextInt(this.strikenumber);
                this.counter.setText(Integer.toString(this.enemiescounter));
                this.winordefeat = "Поражение";
                if (this.finish == true) {
                    this.intent.putExtra("strike_number", this.strikenumber);
                    this.intent.putExtra("enemies_number", this.enemiesnumber);
                    this.intent.putExtra("time", this.mlseconds);
                    this.intent.putExtra("money", this.money);
                    this.intent.putExtra("win_state", this.winstate);
                    startActivity(this.intent);
                }

            }
            if (this.enemiescounter <= 0){
                this.winstate = true;
                this.enemiescounter = 0;
                this.counter.setText(Integer.toString(this.enemiescounter));
                this.timer.cancel();
                this.winordefeat = "Победа!";
                this.finish = true;
                if (this.finish == true){
                    this.intent.putExtra("strike_number", this.strikenumber);
                    this.intent.putExtra("enemies_number", this.enemiesnumber);
                    this.intent.putExtra("time", this.mlseconds);
                    this.intent.putExtra("money", this.money);
                    this.intent.putExtra("win_state", this.winstate);
                    startActivity(this.intent);
                }

            }
        }
    }
    //Метод для старта таймер
    public void onStartTimer(View view){
        this.timer.start();
        Button button = (Button)findViewById(R.id.startbutton);
        button.setEnabled(false);
        this.start = true;
    }


}

