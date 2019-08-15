package com.lordxeres.dice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView dice1, dice2;
    private Button rollDice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dice1 = findViewById(R.id.dice1);
        dice2 = findViewById(R.id.dice2);
        rollDice = findViewById(R.id.rollDice);
    }

    public void RollDice(View view) {
        int diceOneValue = (int) (Math.random() * 6) + 1;
        int diceTwoValue = (int) (Math.random() * 6) + 1;

        switch (diceOneValue) {
            case 1:
                dice1.setImageResource(R.drawable.dice_1);
                break;
            case 2:
                dice1.setImageResource(R.drawable.dice_2);
                break;
            case 3:
                dice1.setImageResource(R.drawable.dice_3);
                break;
            case 4:
                dice1.setImageResource(R.drawable.dice_4);
                break;
            case 5:
                dice1.setImageResource(R.drawable.dice_5);
                break;
            case 6:
                dice1.setImageResource(R.drawable.dice_6);
                break;
        }

        switch (diceTwoValue) {
            case 1:
                dice2.setImageResource(R.drawable.dice_1);
                break;
            case 2:
                dice2.setImageResource(R.drawable.dice_2);
                break;
            case 3:
                dice2.setImageResource(R.drawable.dice_3);
                break;
            case 4:
                dice2.setImageResource(R.drawable.dice_4);
                break;
            case 5:
                dice2.setImageResource(R.drawable.dice_5);
                break;
            case 6:
                dice2.setImageResource(R.drawable.dice_6);
                break;
        }
    }
}
