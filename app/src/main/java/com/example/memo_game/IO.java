package com.example.memo_game;

import android.graphics.Color;
import android.util.Log;

import com.google.android.things.contrib.driver.apa102.Apa102;
import com.google.android.things.contrib.driver.ht16k33.AlphanumericDisplay;
import com.google.android.things.contrib.driver.ht16k33.Ht16k33;
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;
import com.google.android.things.pio.Gpio;

import java.io.IOException;

import static android.content.ContentValues.TAG;

public class IO {

    private IO(){}

    private static com.example.memo_game.IO INSTANCE = new com.example.memo_game.IO();

    public static com.example.memo_game.IO getInstance()
        {   return INSTANCE;
        }


    Parameter Parameters;



    protected void rainbow_blink() throws IOException {

        //////////// LED RAIBOW COLOR
        Apa102 ledstrip = RainbowHat.openLedStrip();
        ledstrip.setBrightness(31);
        int[] rainbow = new int[RainbowHat.LEDSTRIP_LENGTH];


        ////CHANGEMENT LED
        for (int index_color=0; index_color<Parameters.LED_LOOP; index_color++) {

            ////ETEIN A LA FIN
            if((index_color+1)==Parameters.LED_LOOP){
                for (int index_rainbow = 0; index_rainbow < rainbow.length; index_rainbow++) {
                    rainbow[index_rainbow] = Color.TRANSPARENT;
                }
            }
            else {
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
            }
            catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        // Close the device when done.
        ledstrip.close();

    }

    protected void diplay_chaine(String P_Chaine) throws IOException {


        if(P_Chaine.length()>4){
            AlphanumericDisplay segment = RainbowHat.openDisplay();
            segment.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX);
            segment.display("ERR");
            segment.setEnabled(true);
        }

        else{
            AlphanumericDisplay segment = RainbowHat.openDisplay();
            segment.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX);

            String Chaine = new String(P_Chaine);

            segment.display(Chaine);
            segment.setEnabled(true);


        }






    }

    protected void diplay_chaine_blink(String P_Chaine) throws IOException {


        if(P_Chaine.length()>4){
            AlphanumericDisplay segment = RainbowHat.openDisplay();
            segment.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX);
            segment.display("ERR");
            segment.setEnabled(true);
        }

        else{
            AlphanumericDisplay segment = RainbowHat.openDisplay();
            segment.setBrightness(Ht16k33.HT16K33_BRIGHTNESS_MAX);

            String Chaine = new String(P_Chaine);


            for (int index_display=0;index_display<Parameter.DISPLAY_LOOP;index_display++){


                try {
                    Thread.sleep(Parameter.TIME_STARTUP_DISPLAY);
                }
                catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                segment.clear();

                segment.display(Chaine);
                segment.setEnabled(true);


                ////A AMELIORER
                try {
                    Thread.sleep(Parameter.TIME_STARTUP_DISPLAY);
                }
                catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                segment.clear();



            }

            segment.close();
        }




    }

    protected void buttn_led(boolean P_RED, boolean P_GREEN, boolean P_BLUE) throws IOException {



        if (P_RED){
            Gpio ledR;
            ledR = RainbowHat.openLedRed();
            ledR.setValue(true);


            ledR.close();
        }

        if (P_GREEN){
            Gpio ledG;
            ledG = RainbowHat.openLedGreen();
            ledG.setValue(true);


            ledG.close();
        }

        if (P_BLUE){
            Gpio ledB;
            ledB = RainbowHat.openLedBlue();
            ledB.setValue(true);



            ledB.close();
        }



        ////A AMELIORER
        try {
            Thread.sleep(Parameter.TIME_STARTUP_BTNLED);
        }
        catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



        if (P_RED){
            Gpio ledR;
            ledR = RainbowHat.openLedRed();
            Log.d(TAG, "Getvalue !!!!!!!!!!!!!!!!!!!!!! : "+ledR.getValue());
            ledR.setValue(false);
            Log.d(TAG, "Getvalue !!!!!!!!!!!!!!!!!!!!!!: "+ledR.getValue());



            ledR.close();
        }

        if (P_GREEN){
            Gpio ledG;
            ledG = RainbowHat.openLedGreen();
            ledG.setValue(false);


            ledG.close();
        }

        if (P_BLUE){
            Gpio ledB;
            ledB = RainbowHat.openLedBlue();
            ledB.setValue(false);



            ledB.close();
        }




    }






    }
