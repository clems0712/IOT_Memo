package com.example.memo_game;

import android.util.Log;

import com.google.android.things.contrib.driver.button.Button;
import com.google.android.things.contrib.driver.rainbowhat.RainbowHat;


import java.io.IOException;
import java.util.Vector;

import static android.content.ContentValues.TAG;


public class Game {


    public Game(){

        this.Alive=true;

        this.score=0;
        this.record=9;
        //this.record= getResources().getString(R.string.)

        this.Memo_lvl=3;

        this.Verificator ='\0';
        this.index_verificator=0;
    }

    ///Joueur vivant
    boolean Alive;

    protected int score;
    protected int record;


    ///Nb de * à mémoriser
    int Memo_lvl;

    //Vector des series de couleurs random
    Vector Serie = new Vector();

    // verification avec la serie variable globale pour les boutons
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



    ////////////////////WIN/////////////////////////

    public void win() throws IOException {

        Carte.rainbow_win();


    }

    /////////////////////FIN DU JEUX /////////////////////////
    public void lose() throws IOException {


        Carte.rainbow_lose();



    }


    ////////////////////NEW RECCORD/////////////////////////

    public void Succes() throws IOException {

        //rainbow clignotant et display GG et new reccord clignotant musique



    }

    ////////////////////////JEUX////////////////////////////////
    public void play() throws IOException {

        Carte.diplay_demi(Integer.toString(this.record),Integer.toString(this.score));

        memo();




    }





    protected void memo() throws IOException {



        for (int index_memo=0; index_memo<Memo_lvl;index_memo++){

            ///RANDOM : de 65 a 67 ASCII de A, B et C
            int Random_LED = (int)( 65+ (Math.random()*(68-65)));

            Carte.buttn_ledone((char)Random_LED);

            //SON A B C à ajouter

            Serie.add((char)Random_LED);


            ///Debug RED ONLY
            //Carte.buttn_ledone('A');
           //Serie.add('A');
        }

    }


    protected void verification() throws IOException {

        if(!Serie.isEmpty()){
            if(index_verificator<Serie.size()){

                if(Verificator!=((char)Serie.get(index_verificator))){
                    Log.i(TAG, "u lose");
                    try {
                        lose();
                        // memo();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                else if(Verificator==((char)Serie.get(index_verificator))){
                    Log.i(TAG, "Succes " + Verificator+ " == " + Serie.get(index_verificator));
                    index_verificator++;

                    if(index_verificator==Serie.size()){
                        Log.i(TAG, "u win");

                        try {
                            win();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        else{

            Log.i(TAG, "Serie is empty");

        }

    }


}


