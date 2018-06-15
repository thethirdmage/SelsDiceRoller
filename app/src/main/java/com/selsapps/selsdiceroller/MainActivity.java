package com.selsapps.selsdiceroller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToDice (View view) {
        Intent toDiceIntent = new Intent(this, RollActivity.class);
        startActivity(toDiceIntent);
    }

    public void goToStats (View view) {
        Intent toStatsIntent = new Intent(this, StatsActivity.class);
        startActivity(toStatsIntent);
    }
}
