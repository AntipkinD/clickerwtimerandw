package com.example.myapplication;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class HubscreenActivity extends AppCompatActivity {
    Intent intent = new Intent(this, MainActivity.class);
    Bundle args = getIntent().getExtras();
    Integer strikenumber = args.getInt("strikenumber", 5);
    Integer time = args.getInt("time", 1000);
    Integer money = args.getInt("money", (time/100)*strikenumber);
    Integer enemiesnumber = args.getInt("enemiesnumber", 25);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hubscreen);
    }
    public void onClickRestart(){
        this.intent.putExtra("strikenumber", strikenumber);
        this.intent.putExtra("time", time);
        this.intent.putExtra("money", money);
        this.intent.putExtra("enemiescounter", enemiesnumber);
    }
    public void onNextLevel(){
        this.intent.putExtra("strikenumber", strikenumber);
        this.intent.putExtra("time", time);
        this.intent.putExtra("money", money);
        this.intent.putExtra("enemiescounter", enemiesnumber);
    }
}