package com.selsapps.selsdiceroller;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RollActivity extends AppCompatActivity {

    int modifier = 0;
    int numberOfDice = 1;
    int dieSides = 20;
    boolean isFate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll);
    }

    public void rollD2 (View view) {
        dieSides = 2;
        isFate = false;
        displayDieToRoll(numberOfDice, modifier, dieSides);
    }

    public void rollD4 (View view) {
        dieSides = 4;
        isFate = false;
        displayDieToRoll(numberOfDice, modifier, dieSides);
    }

    public void rollD6 (View view) {
        dieSides = 6;
        isFate = false;
        displayDieToRoll(numberOfDice, modifier, dieSides);
    }

    public void rollD8 (View view) {
        dieSides = 8;
        isFate = false;
        displayDieToRoll(numberOfDice, modifier, dieSides);
    }

    public void rollD10 (View view) {
        dieSides = 10;
        isFate = false;
        displayDieToRoll(numberOfDice, modifier, dieSides);
    }

    public void rollD12 (View view) {
        dieSides = 12;
        isFate = false;
        displayDieToRoll(numberOfDice, modifier, dieSides);
    }

    public void rollD20 (View view) {
        dieSides = 20;
        isFate = false;
        displayDieToRoll(numberOfDice, modifier, dieSides);
    }

    public void rollD100 (View view) {
        dieSides = 100;
        isFate = false;
        displayDieToRoll(numberOfDice, modifier, dieSides);
    }

    public void rollFate (View view) {
        dieSides = 3;
        isFate = true;
        displayDieToRoll(numberOfDice, modifier, dieSides);
    }

    public void incrementDice (View view) {
        numberOfDice++;
        displayNumberOfDice(numberOfDice);
    }

    public void decrementDice (View view) {
        if(numberOfDice > 1) {
            numberOfDice--;
        }
        displayNumberOfDice(numberOfDice);
    }

    public void displayNumberOfDice (int numOfDice) {
        TextView diceNum = findViewById(R.id.diceNumber);
        diceNum.setText(String.valueOf(numOfDice));
        displayDieToRoll(numberOfDice, modifier, dieSides);
    }

    public void displayModifier (View view) {
        final EditText modField = findViewById(R.id.modField);
        TextWatcher modWatcher = new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            public void afterTextChanged(Editable editable) {
                if (!(editable.toString().equals("-")) && !(editable.toString().equals("")) &&
                        !(Math.abs(Integer.valueOf(editable.toString())) > 100)) {
                    modifier = Integer.valueOf(editable.toString());
                } else if ((editable.toString().equals("-")) || (editable.toString().equals(""))) {
                    modifier = 0;
                } else if (Integer.valueOf(editable.toString()) > 100) {
                    modifier = 100;
                } else if (Integer.valueOf(editable.toString()) < -100) {
                    modifier = -100;
                }
                displayDieToRoll(numberOfDice, modifier, dieSides);
            }
        };
        modField.addTextChangedListener(modWatcher);
    }

    public void displayDieToRoll (int numOfDice, int mod, int sides){
        TextView dieField = findViewById(R.id.diceField);
        String modChar = "";
        if (mod > 0) {
            modChar = "+";
        }
        String resultString = " " + numOfDice + "d" + sides + modChar + modifier + " ";
        if (mod == 0) {
            resultString = " " + numOfDice + "d" + sides + " ";
        }

        if (isFate) {
            resultString = " dF" + modChar + mod + " ";
            if(mod == 0) {
                resultString = " dF ";
            }
        }
        dieField.setText(resultString);
    }

    public void rollDice (View view) {
        TextView resultField = findViewById(R.id.resultField);
        int result = 0;
        String resultString;

        if(!isFate) {
            for (int i = 0; i < numberOfDice; i++) {
                result += (int) (Math.random() * dieSides) + 1;
            }
            result += modifier;

            if (result <= 0) {
                result = 1;
            }
            resultString = " " + result + " ";

        } else {
            for (int i = 0; i < 4; i++) {
                int dieVal = (int) (Math.random() * 3) + 1;
                if (dieVal == 1) {
                    result -= 1;
                } else if (dieVal == 3) {
                    result += 1;
                }
            }

            result += modifier;
            if (result >= 0) {
                resultString = "+" + Integer.toString(result);
            } else {
                resultString = Integer.toString(result);
            }
        }
        resultField.setText(resultString);
    }

    public void reset (View view) {
        EditText modView = findViewById(R.id.modField);
        modView.setText("");
        TextView diceView = findViewById(R.id.resultField);
        diceView.setText(" 0 ");
        modifier = 0;
        numberOfDice = 1;
        displayNumberOfDice(1);
        rollD20(view);
    }

    public void easterEgg (View view){
        AlertDialog.Builder easterEggBuilder = new AlertDialog.Builder(this);
        easterEggBuilder.setMessage("♥♥♥ Did you know: I love you! ♥♥♥");
        easterEggBuilder.setCancelable(false);

        easterEggBuilder.setPositiveButton(":D", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        AlertDialog easterEggDialog = easterEggBuilder.create();
        easterEggDialog.show();
    }

}
