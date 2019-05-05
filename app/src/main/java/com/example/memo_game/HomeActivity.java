package com.example.memo_game;

import android.app.Activity;
import android.provider.Telephony;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.things.contrib.driver.button.Button;
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;
import com.google.android.things.pio.Gpio;

import java.io.IOException;

import static android.content.ContentValues.TAG;

public class HomeActivity extends Activity {
    private static final String TAG = HomeActivity.class.getSimpleName();

    ///INITIALISE GAME GLOBAL
    Game Party = new Game();

    Button buttonA = null;
    Button buttonB = null;
    Button buttonC = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);






        try {
            //Party.startup();
            Party.play();
        } catch (IOException e) {
            e.printStackTrace();
        }




        try {
            buttonA = RainbowHat.openButtonA();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            buttonB = RainbowHat.openButtonB();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            buttonC = RainbowHat.openButtonC();
        } catch (IOException e) {
            e.printStackTrace();
        }


        buttonA.setOnButtonEventListener(new Button.OnButtonEventListener() {
            @Override
            public void onButtonEvent(Button buttonA, boolean pressed) {
                Log.i(TAG, "Button A" + pressed);
                Party.Verificator = 'A';

                try {
                    Party.verification();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });

        buttonB.setOnButtonEventListener(new Button.OnButtonEventListener() {
            @Override
            public void onButtonEvent(Button buttonB, boolean pressed) {
                Log.i(TAG, "Button B" + pressed);
                Party.Verificator = 'B';

                try {
                    Party.verification();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

        });

        buttonC.setOnButtonEventListener(new Button.OnButtonEventListener() {
            @Override
            public void onButtonEvent(Button buttonC, boolean pressed) {

                Log.i(TAG, "Button C" + pressed);
                Party.Verificator = 'C';

                try {
                    Party.verification();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        });


    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            buttonA.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            buttonB.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            buttonC.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
