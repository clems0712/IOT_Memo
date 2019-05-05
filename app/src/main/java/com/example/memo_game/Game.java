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
import java.util.Vector;

import static android.content.ContentValues.TAG;


public class Game {


    public Game(){

        Alive=true;

        this.score=0;
        this.record=9;
        //this.record= getResources().getString(R.string.)


        Verificator ='\0';
        index_verificator=0;
    }

    boolean Alive;

    protected int score;
    protected int record;

    Vector Serie = new Vector();
    char Verificator;
    int index_verificator;



    IO Carte = new IO();




    ////////////////////DEBUT DU JEUX///////////////////
    protected void startup() throws IOException {

        //CALCUL DU TEMPS
        long start = System.currentTimeMillis();

        ///THREADS POUR LA SYNCHRO

        //ETAPE 1 LED RAINBOW CLIGNOTANTE

        new Thread(new Runnable() {
            public void run() {
                try {
                    Carte.rainbow_blink();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();



        //ETAPE 2 DISPLAY AFFICHAGE

        new Thread(new Runnable() {
            public void run() {
                try {
                    Carte.diplay_chaine_blink("MEMO");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        //ETAPE 3 LED BOUTONS CLIGNOTANTE

        new Thread(new Runnable() {
            public void run() {
                try {
                    Carte.buttn_led(true,true,true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //ETAPE 4 MUSIQUE

//        new Thread(new Runnable() {
//            public void run() {
//                try {
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();

        long elapsedTime = System.nanoTime() - start;
    }




    /////////////////////FIN DU JEUX /////////////////////////
    public void ending() throws IOException {


        Carte.rainbow_lose();



    }


    ////////////////////////JEUX////////////////////////////////
    public void play() throws IOException {

        Carte.diplay_demi(Integer.toString(this.record),Integer.toString(this.score));

        memo();




    }

    protected void memo() throws IOException {

        int memo_loop=3;





        for (int index_memo=0; index_memo<memo_loop;index_memo++){

            ///RANDOM : de 65 a 67 ASCII de A, B et C
            int Random_LED = (int)( 65+ (Math.random()*(68-65)));
            Carte.buttn_ledone((char)Random_LED);
            //SON A B C
            Serie.add((char)Random_LED);

        }


        //Log.i(TAG, "Serie de LED" + Serie);


            char button_return = '\0';




            Button buttonA = null;
            try {
                buttonA = RainbowHat.openButtonA();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Button buttonB = null;
            try {
                buttonB = RainbowHat.openButtonB();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Button buttonC = null;
            try {
                buttonC = RainbowHat.openButtonC();
            } catch (IOException e) {
                e.printStackTrace();
            }


        final boolean[] lose = new boolean[1];


            buttonA.setOnButtonEventListener(new Button.OnButtonEventListener() {
                @Override
                public void onButtonEvent(Button buttonA, boolean pressed) {

                    Log.i(TAG, "Button A" + pressed);
                    Verificator = 'A';

                    if(Verificator==(char)Serie.get(index_verificator)){
                        Log.i(TAG, "Succes " + Verificator+ " et " + Serie.get(index_verificator));
                        index_verificator++;
                    }
                    else{
                        //lose[0] =true;
                    }
                }

            });


            buttonB.setOnButtonEventListener(new Button.OnButtonEventListener() {
                @Override
                public void onButtonEvent(Button buttonB, boolean pressed) {

                    Log.i(TAG, "Button B" + pressed);
                    Verificator = 'B';

                    if(Verificator==(char)Serie.get(index_verificator)){
                        Log.i(TAG, "Succes " + Verificator+ " et " + Serie.get(index_verificator));
                        index_verificator++;
                    }
                    else{
                        //lose[0] =true;
                    }
                }
            });


            buttonC.setOnButtonEventListener(new Button.OnButtonEventListener() {
                @Override
                public void onButtonEvent(Button buttonC, boolean pressed) {

                    Log.i(TAG, "Button C" + pressed);
                    Verificator = 'C';

                    if(Verificator==(char)Serie.get(index_verificator)){
                        Log.i(TAG, "Succes " + Verificator+ " et " + Serie.get(index_verificator));
                        index_verificator++;
                    }
                    else{
                        //lose[0] =true;
                    }
                }
            });




            if (index_verificator==Serie.size()) {

                Log.i(TAG, "Succes " + Verificator+ " et " + (char)Serie.get(index_verificator));

            }








    }



}


