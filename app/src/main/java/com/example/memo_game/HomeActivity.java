package com.example.memo_game;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;
import com.google.android.things.pio.Gpio;

import java.io.IOException;

public class HomeActivity extends Activity {
    private static final String TAG = HomeActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        Gpio led = null;
        try {
            led = RainbowHat.openLedRed();
            led.setValue(false);

            led = RainbowHat.openLedBlue();
            led.setValue(false);

            led = RainbowHat.openLedGreen();
            led.setValue(false);
// Close the device when done.
            led.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}
