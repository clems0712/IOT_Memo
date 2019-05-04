package com.example.memo_game;

import android.graphics.Color;

import com.google.android.things.contrib.driver.apa102.Apa102;
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;

import java.io.IOException;

public class Game {


    public Game(){
        this.score=0;
        //this.record= getResources().getString(R.string.)

    }

    protected int score;
    // int record;

    Parameter Parameters;



    ////////////////////DEBUT DU JEUX///////////////////
    protected void startup() throws IOException {

        rainbow_blink();


    }




    /////////////////////FIN DU JEUX /////////////////////////
    public void ending() throws IOException {



        // Light up the rainbow
        Apa102 ledstrip = RainbowHat.openLedStrip();
        ledstrip.setBrightness(31);
        int[] rainbow = new int[RainbowHat.LEDSTRIP_LENGTH];


        for (int index_rainbow = 0; index_rainbow < rainbow.length; index_rainbow++) {
            rainbow[index_rainbow] = Color.RED;
            rainbow[index_rainbow] = Color.GREEN;
        }
        ledstrip.write(rainbow);
        // Close the device when done.
        ledstrip.close();


    }


    ////////////////////////JEUX////////////////////////////////
    public void play(){

    }






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


}
