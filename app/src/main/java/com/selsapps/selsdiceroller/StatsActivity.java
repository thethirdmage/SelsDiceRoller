package com.selsapps.selsdiceroller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        String description = "This function rolls stats for a D&D 5th edition game. " +
                "It rolls 4d6 then drops the lowest." + "\n"
                + "This can be used for other games too, but it returns 5e modifiers.";
        TextView descripView = findViewById(R.id.descriptionView);
        descripView.setText(description);
    }

    public int[] rollDice () {
       int[] results = new int[6];

       for (int i = 0; i < 6; i++) {
           int[] diceResults = new int[4];
           int lowestRoll = 6;
           int statTotal = 0;
           for (int j = 0; j < 4; j++) {
               diceResults[j] = (int) (Math.random() * 6) + 1;
           }
           for (int k = 0; k < 4; k++) {
               if (diceResults[k] <= lowestRoll) {
                   lowestRoll = diceResults[k];
               }
               statTotal += diceResults[k];
           }
           statTotal -= lowestRoll;
           results[i] = statTotal;
       }
       return results;
    }

    public String getMod (int stat) {
        String modString = "";
        switch (stat) {
            case 3:
                return "-4";
            case 4: case 5:
                return "-3";
            case 6: case 7:
                return "-2";
            case 8: case 9:
                return "-1";
            case 10: case 11:
                return "+0";
            case 12: case 13:
                return "+1";
            case 14: case 15:
                return "+2";
            case 16: case 17:
                return "+3";
            case 18:
                return "+4";
        }
        return modString;
    }

    public void generateStats (View view) {
        int[] diceResults = rollDice();
        StringBuilder resultString = new StringBuilder();
        String modString;
        for (int i = 0; i < 6; i++) {
            modString = getMod(diceResults[i]);
            if (diceResults[i] >= 10) {
                resultString.append("Stat ").append(i + 1).append(": ").append(diceResults[i]).append(" (").append(modString).append(")\n");
            } else {
                resultString.append("Stat ").append(i + 1).append(": ").append(diceResults[i]).append("   (").append(modString).append(")\n");
            }
        }
        TextView resultsView = findViewById(R.id.results);

        resultsView.setText(resultString.toString());

    }
}
