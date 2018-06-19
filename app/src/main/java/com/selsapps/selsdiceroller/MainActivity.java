package com.selsapps.selsdiceroller;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView rollDice = findViewById(R.id.pickDice);
        TextView rollStats = findViewById(R.id.pickStats);

        rollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent diceIntent = new Intent(MainActivity.this, RollActivity.class);
                startActivity(diceIntent);
            }
        });

        rollStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent statsIntent = new Intent(MainActivity.this, StatsActivity.class);
                startActivity(statsIntent);
            }
        });
    }

}
