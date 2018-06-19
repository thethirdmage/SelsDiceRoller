package com.selsapps.selsdiceroller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class RollActivity extends AppCompatActivity {

    // Set up variables
    int modifier = 0;
    int numberOfDice = 1;
    int dieSides = 20;
    boolean isFate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roll);
    }

    // Dice roll function
    public void changeSides (View view) {
        isFate = false;

        switch (view.getId()){
            case R.id.rollD2:
                dieSides = 2;
                break;
            case R.id.rollD4:
                dieSides = 4;
                break;
            case R.id.rollD6:
                dieSides = 6;
                break;
            case R.id.rollD8:
                dieSides = 8;
                break;
            case R.id.rollD10:
                dieSides = 10;
                break;
            case R.id.rollD12:
                dieSides = 12;
                break;
            case R.id.rollD20:
                dieSides = 20;
                break;
            case R.id.rollD100:
                dieSides = 100;
                break;
            case R.id.rollDF:
                dieSides = 3;
                isFate = true;
                break;
            default:
                dieSides = 20;
                break;
        }
        displayDieToRoll(numberOfDice, modifier, dieSides);
    }

    // Increment and decrement number of dice
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

    /**
     * Grabs just the number of dice
     * @param numOfDice the number of dice to display
     */
    public void displayNumberOfDice (int numOfDice) {
        TextView diceNum = findViewById(R.id.diceNumber);
        diceNum.setText(String.valueOf(numOfDice));
        displayDieToRoll(numberOfDice, modifier, dieSides);
    }

    /**
     * Grabs the modifier for the roll
     * @param view the modifier view
     */
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

    /**
     * Displays the die notation for the roll
     * @param numOfDice the number of dice to roll
     * @param mod       the modifier to the roll
     * @param sides     the number of sides per die
     */
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

        // If standard polyhedral dice, e.g., d6, d8, d20
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

    /**
     * Resets the dice view
     * @param view
     */
    public void reset (View view) {
        EditText modView = findViewById(R.id.modField);
        modView.setText("");
        TextView diceView = findViewById(R.id.resultField);
        diceView.setText(" 0 ");
        modifier = 0;
        numberOfDice = 1;
        displayNumberOfDice(1);
        changeSides(view);
    }

}
