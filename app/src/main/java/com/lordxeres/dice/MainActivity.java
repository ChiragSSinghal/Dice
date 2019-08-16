package com.lordxeres.dice;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.support.annotation.RawRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView dice1, dice2;
    private Button rollDice;

    private SensorManager sensorManager;
    private Sensor accelerometer;
    private ShakeDetector shakeDetector;

    private int ctr = 0;

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dice1 = findViewById(R.id.dice1);
        dice2 = findViewById(R.id.dice2);
        rollDice = findViewById(R.id.rollDice);

        ShakeDetectorInit();

        mediaPlayer = MediaPlayer.create(this, R.raw.shake_and_roll_dice);
        rollDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ctr == 4) {
                    RollDice();
                    ctr = 0;
                }
                else {
                    ctr++;
                    RollDice();
                }
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        sensorManager.registerListener(shakeDetector, accelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        sensorManager.unregisterListener(shakeDetector);
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(mediaPlayer != null) {
            mediaPlayer.release();
        }
    }

    public void ShakeDetectorInit() {
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        shakeDetector = new ShakeDetector();
        shakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake(int count) {
                if (count % 5 == 0){
                    RollDice();
                }
                else {
                    RollDice();
                }
            }
        });
    }

    public void RollDice() {
        mediaPlayer.start();
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
