package com.example.myapplication;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
    TextView money_counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hubscreen);
        this.restart = (Button) findViewById(R.id.button_restart);
        this.next_lvl = (Button) findViewById(R.id.button_nextlevel);
        this.money_counter = (TextView) findViewById(R.id.moneycount_TextView);
        this.next_lvl.setEnabled(false);
        this.restart.setEnabled(false);
        this.intent = new Intent(this, MainActivity.class);
        this.args = getIntent().getExtras();
        if (this.args != null){
            this.strikenumber = this.args.getInt("strike_number", 5);
            this.time = this.args.getInt("time", 10000);
            this.winstate = this.args.getBoolean("win_state", winstate);
            if (winstate == true){
                this.money = this.args.getInt("money", 0)+(this.time/1000)*this.strikenumber;
            }
            else this.money = this.args.getInt("money", 0);
            this.enemiesnumber = this.args.getInt("enemies_number", 25);
        }
        this.money_counter.setText(this.money.toString());
        if (this.winstate == false){
            restart.setEnabled(true);
        }
        if (this.winstate == true){
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
        if (this.money >= 400) {
            this.money -= 400;
            this.strikenumber += 2;
            this.money_counter.setText(this.money.toString());
        }
    }
    public void onUpgrade2(View view){
        if (this.money >= 1000) {
            this.money -= 1000;
            this.time += 100;
            this.money_counter.setText(this.money.toString());
        }
    }
}