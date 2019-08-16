package com.lordxeres.dice;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.FloatMath;

public class ShakeDetector implements SensorEventListener {

    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7f;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;

    private OnShakeListener listener;
    private long shakeTimeStamp;
    private int shakeCount;

    public void setOnShakeListener(OnShakeListener listener) {
        this.listener = listener;
    }

    public interface OnShakeListener {
        public void onShake(int count);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (listener != null) {
            float x = event.values[0];
            float y = event.values[1];
            float z = event.values[2];

            float gX = x / SensorManager.GRAVITY_EARTH;
            float gY = y / SensorManager.GRAVITY_EARTH;
            float gZ = z / SensorManager.GRAVITY_EARTH;

            float gForce = (float) Math.sqrt(gX * gX + gY * gY + gZ * gZ);

            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                final long now = System.currentTimeMillis();

                if ((shakeTimeStamp + SHAKE_SLOP_TIME_MS) > now) {
                    return;
                }

                if (shakeTimeStamp + SHAKE_COUNT_RESET_TIME_MS < now) {
                    shakeCount = 0;
                }

                shakeTimeStamp = now;
                shakeCount++;

                listener.onShake(shakeCount);
            }
        }
    }
}
