package com.example.myapplication;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HubscreenActivity extends AppCompatActivity {
    Intent intent;
    Bundle args;
    Integer strikenumber;
    Integer time;
    Integer money;
    Integer enemiesnumber;
    boolean winstate;
    Button next_lvl;
    Button restart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hubscreen);
        this.restart = (Button) findViewById(R.id.button_restart);
        this.next_lvl = (Button) findViewById(R.id.button_nextlevel);
        this.next_lvl.setEnabled(false);
        this.restart.setEnabled(false);
        this.intent = new Intent(this, MainActivity.class);
        this.args = getIntent().getExtras();
        if (this.args != null){
            this.strikenumber = this.intent.getIntExtra("strike_number", 5);
            this.time = this.intent.getIntExtra("time", 10000);
            this.money = this.intent.getIntExtra("money", (this.time/100)*this.strikenumber);
            this.winstate = this.intent.getBooleanExtra("win_state", winstate);
            this.enemiesnumber = this.intent.getIntExtra("enemies_number", 25);
        }
        if (this.winstate == true){
            restart.setEnabled(true);
        }
        if (this.winstate == false){
            next_lvl.setEnabled(true);
        }
    }
    public void onClickRestart(View view){
        this.intent.putExtra("strike_number", this.strikenumber);
        this.intent.putExtra("time", this.time);
        this.intent.putExtra("money", this.money);
        this.intent.putExtra("enemies_counter", this.enemiesnumber);
        startActivity(this.intent);
    }
    public void onNextLevel(View view){
        this.intent.putExtra("strike_number", this.strikenumber);
        this.intent.putExtra("time", this.time);
        this.intent.putExtra("money", this.money);
        this.intent.putExtra("enemies_counter", this.enemiesnumber);
        startActivity(this.intent);
    }
    public void onUpgrade1(View view){

    }
    public void onUpgrade2(View view){

    }
}