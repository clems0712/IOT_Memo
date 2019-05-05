package com.example.memo_game;

import android.graphics.Color;
import android.util.Log;

import com.google.android.things.contrib.driver.apa102.Apa102;
import com.google.android.things.contrib.driver.button.Button;
import com.google.android.things.contrib.driver.ht16k33.AlphanumericDisplay;
import com.google.android.things.contrib.driver.ht16k33.Ht16k33;
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;
import com.google.android.things.pio.Gpio;

import java.io.IOException;

import static android.content.ContentValues.TAG;

public class IO {

    public IO() {

        boolean Buttons_pressed = false;

        ButtonA = false;
        ButtonB = false;
        ButtonC = false;

    }

    Parameter parameters;

    boolean Buttons_pressed;

    boolean ButtonA;
    boolean ButtonB;
    boolean ButtonC;


    //////RAINBOW LED 6-0
    protected void rainbow_blink() throws IOException {

        //////////// LED RAIBOW COLOR
        Apa102 ledstrip = RainbowHat.openLedStrip();
        ledstrip.setBrightness(2);
        int[] rainbow = new int[RainbowHat.LEDSTRIP_LENGTH];


        ////CHANGEMENT LED
        for (int index_color = 0; index_color < parameters.LED_LOOP; index_color++) {

            ////ETEIN A LA FIN
            if ((index_color + 1) == parameters.LED_LOOP) {
                for (int index_rainbow = 0; index_rainbow < rainbow.length; index_rainbow++) {
                    rainbow[index_rainbow] = Color.TRANSPARENT;
                }
            } else {
                for (int index_rainbow = 0; index_rainbow < rainbow.length; index_rainbow++) {
                    ///RANDOM DES COULEURS CODE SUR 360
                    float Nb_random = (float) (Math.random() * (360 - 1));
                    rainbow[index_rainbow] = Color.HSVToColor(255, new float[]{Nb_random, 1.0f, 1.0f});
                }
            }


            ledstrip.write(rainbow);

            ///PAUSE
            try {
                Thread.sleep(80);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        // Close the device when done.
        ledstrip.close();

    }

    protected void rainbow_win() throws IOException {

        // Light up the rainbow
        Apa102 ledstrip = RainbowHat.openLedStrip();
        ledstrip.setBrightness(1);
        int[] rainbow = new int[RainbowHat.LEDSTRIP_LENGTH];

    for(int index_blink = 0; index_blink < parameters.RAINBOW_BLINK; index_blink++) {

        for (int index_rainbow = 0; index_rainbow < rainbow.length; index_rainbow++) {
            rainbow[index_rainbow] = Color.GREEN;
        }
        ledstrip.write(rainbow);


        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        for (int index_rainbow = 0; index_rainbow < rainbow.length; index_rainbow++) {
            rainbow[index_rainbow] = Color.TRANSPARENT;
        }
        ledstrip.write(rainbow);


    }
        // Close the device when done.
        ledstrip.close();
    }

    protected void rainbow_lose() throws IOException {


        // Light up the rainbow
        Apa102 ledstrip = RainbowHat.openLedStrip();
        ledstrip.setBrightness(1);
        int[] rainbow = new int[RainbowHat.LEDSTRIP_LENGTH];


        for(int index_blink = 0; index_blink < parameters.RAINBOW_BLINK; index_blink++) {

            for (int index_rainbow = 0; index_rainbow < rainbow.length; index_rainbow++) {
                rainbow[index_rainbow] = Color.RED;
            }
            ledstrip.write(rainbow);

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            for (int index_rainbow = 0; index_rainbow < rainbow.length; index_rainbow++) {
                rainbow[index_rainbow] = Color.TRANSPARENT;
            }
            ledstrip.write(rainbow);

        }

        // Close the device when done.
        ledstrip.close();
    }

    protected void rainbow_score() throws IOException {


        // Light up the rainbow
        Apa102 ledstrip = RainbowHat.openLedStrip();
        ledstrip.setBrightness(1);
        int[] rainbow = new int[RainbowHat.LEDSTRIP_LENGTH];


        for (int index_rainbow = 0; index_rainbow < rainbow.length; index_rainbow++) {

            rainbow[index_rainbow] = Color.GREEN;
        }
        ledstrip.write(rainbow);
        // Close the device when done.
        ledstrip.close();
    }




    //////DISPLAY ALPHANUM
    protected void diplay_chaine(String P_Chaine) throws IOException {


        if (P_Chaine.length() > 4) {
            AlphanumericDisplay segment = RainbowHat.openDisplay();
            segment.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX);
            segment.display("ERR");
            segment.setEnabled(true);
        }
        else {
            AlphanumericDisplay segment = RainbowHat.openDisplay();
            segment.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX);

            String Chaine = new String(P_Chaine);

            segment.display(Chaine);
            segment.setEnabled(true);
            segment.close();


        }

    }


    //////DISPLAY ALPHANUM
    protected void diplay_chaine_pause(String P_Chaine) throws IOException {


        if (P_Chaine.length() > 4) {
            AlphanumericDisplay segment = RainbowHat.openDisplay();
            segment.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX);
            segment.display("ERR");
            segment.setEnabled(true);
        }
        else {
            AlphanumericDisplay segment = RainbowHat.openDisplay();
            segment.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX);

            String Chaine = new String(P_Chaine);

            segment.display(Chaine);
            segment.setEnabled(true);

            try {
                Thread.sleep(parameters.TIME_DISPLAY);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


            segment.close();



        }
    }

    protected void diplay_chaine_blink(String P_Chaine) throws IOException {


        if (P_Chaine.length() > 4) {
            AlphanumericDisplay segment = RainbowHat.openDisplay();
            segment.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX);
            segment.display("ERR");
            segment.setEnabled(true);
        } else {
            AlphanumericDisplay segment = RainbowHat.openDisplay();
            segment.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX);

            String Chaine = new String(P_Chaine);


            for (int index_display = 0; index_display < parameters.DISPLAY_LOOP; index_display++) {


                try {
                    Thread.sleep(parameters.TIME_STARTUP_DISPLAY);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                segment.clear();

                segment.display(Chaine);
                segment.setEnabled(true);


                ////A AMELIORER
                try {
                    Thread.sleep(parameters.TIME_STARTUP_DISPLAY);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                segment.clear();


            }

            segment.close();
        }


    }

    protected void diplay_demi(String P_Record, String P_Score) throws IOException {


        if ((P_Record.length() + P_Score.length()) > 4) {
            AlphanumericDisplay segment = RainbowHat.openDisplay();
            segment.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX);
            segment.display("ERR");
            segment.setEnabled(true);
        } else {
            AlphanumericDisplay segment = RainbowHat.openDisplay();
            segment.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX);


            if (P_Score.length() == 1) {
                P_Score = "0" + P_Score;
            }

            if (P_Record.length() == 1) {
                P_Record = "0" + P_Record;
            }

            String Chaine = new String(P_Record + P_Score);

            segment.display(Chaine);
            segment.setEnabled(true);
            segment.close();


        }


    }


    //////BUTTON & LED
    protected void buttn_led(boolean P_RED, boolean P_GREEN, boolean P_BLUE) throws IOException {


        if (P_RED) {
            Gpio ledR;
            ledR = RainbowHat.openLedRed();
            ledR.setValue(true);


            ledR.close();
        }

        if (P_GREEN) {
            Gpio ledG;
            ledG = RainbowHat.openLedGreen();
            ledG.setValue(true);


            ledG.close();
        }

        if (P_BLUE) {
            Gpio ledB;
            ledB = RainbowHat.openLedBlue();
            ledB.setValue(true);


            ledB.close();
        }


        ////A AMELIORER
        try {
            Thread.sleep(parameters.TIME_STARTUP_BTNLED);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        if (P_RED) {
            Gpio ledR;
            ledR = RainbowHat.openLedRed();
            ledR.setValue(false);


            ledR.close();
        }

        if (P_GREEN) {
            Gpio ledG;
            ledG = RainbowHat.openLedGreen();
            ledG.setValue(false);


            ledG.close();
        }

        if (P_BLUE) {
            Gpio ledB;
            ledB = RainbowHat.openLedBlue();
            ledB.setValue(false);


            ledB.close();
        }


    }


    protected void buttn_ledone(char P_LED) throws IOException {

        switch (P_LED) {

            //A - Red
            case 'A':

                try {
                    Thread.sleep(parameters.TIME_LED_MEMO);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


                Gpio ledR;
                ledR = RainbowHat.openLedRed();
                ledR.setValue(true);


                try {
                    Thread.sleep(parameters.TIME_LED_MEMO);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                ledR.setValue(false);
                ledR.close();

                break;

            //B - Green
            case 'B':

                try {
                    Thread.sleep(parameters.TIME_LED_MEMO);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                Gpio ledG;
                ledG = RainbowHat.openLedGreen();
                ledG.setValue(true);
                try {
                    Thread.sleep(parameters.TIME_LED_MEMO);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                ledG.setValue(false);


                ledG.close();
                break;

            //C - Blue
            case 'C':
                try {
                    Thread.sleep(parameters.TIME_LED_MEMO);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                Gpio ledB;
                ledB = RainbowHat.openLedBlue();
                ledB.setValue(true);

                ////A AMELIORER
                try {
                    Thread.sleep(parameters.TIME_LED_MEMO);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                ledB.setValue(false);


                ledB.close();
                break;


        }

    }



    //////BUZZER
    protected void led_sound(char P_LED){

        switch (P_LED){
            case 'A':
                //DO
                break;

            case 'B':
                //RE
                break;

            case 'C':
                //MI
                break;
        }
    }


    protected void music_start(){

        //JETER UN OEIL
        //https://github.com/Nilhcem/uartfun-androidthings/blob/master/app/src/main/java/com/nilhcem/uart/RainbowHatHelper.java

    }

    protected void sound_lose(){

    }

    protected void sound_win(){

    }

    protected void music_record(){

    }


}




