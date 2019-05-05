package com.example.memo_game;

import android.support.annotation.RestrictTo;
import android.util.Log;


import java.io.IOException;
import java.util.Vector;

import static android.content.ContentValues.TAG;


public class Game {


    public Game(){

        this.Alive=true;

        this.score=0;
        this.record=9;
        //this.record= getResources().getString(R.string.)

        this.memo_lvl=DEFAULT_lvl;
        this.index_verificator=0;
    }

    ///Joueur vivant
    boolean Alive;

    protected int score;
    protected int record;


    ///Nb de * à mémoriser
    int memo_lvl;
    int DEFAULT_lvl=3;
    //Vector des series de couleurs random
    Vector Serie = new Vector();

    // verification avec la serie variable globale pour les boutons
    int index_verificator;



    IO Carte = new IO();




    ////////////////////DEBUT DU JEUX///////////////////
    protected void startup() throws IOException {

        //CALCUL DU TEMPS


        ///THREADS POUR LA SYNCHRO

        //ETAPE 1 LED BOUTONS CLIGNOTANTE
        new Thread(new Runnable() {
            public void run() {
                try {
                    Carte.buttn_led(true,true,true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        //ETAPE 2 LED RAINBOW CLIGNOTANTE
        new Thread(new Runnable() {
            public void run() {
                try {
                    Carte.rainbow_blink();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();



        //ETAPE 3 DISPLAY AFFICHAGE
                try {
                    Carte.diplay_chaine_blink("MEMO");
                } catch (IOException e) {
                    e.printStackTrace();
                }


        long start = System.currentTimeMillis();


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

        Log.i(TAG, " time : " + elapsedTime);
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }



    ////////////////////WIN/////////////////////////

    public void win() throws IOException {


        Carte.diplay_chaine("WIN");
        Carte.rainbow_win();

    }

    /////////////////////FIN DU JEUX /////////////////////////
    public void lose() throws IOException {

        Carte.diplay_chaine("LOSE");
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


        Serie.clear();
        Vector L_Serie = new Vector();

        for (int index_memo = 0; index_memo< memo_lvl; index_memo++){

            ///RANDOM : de 65 a 67 ASCII de A, B et C
            int Random_LED = (int)( 65+ (Math.random()*(68-65)));
            Carte.buttn_ledone((char)Random_LED);

            //SON A B C à ajouter

            //Pour éviter bug d'appui sur le bouton pdt l'injection
            L_Serie.add((char)Random_LED);
            

            ///Debug RED ONLY
            //Carte.buttn_ledone('A');
            //Serie.add('A');
        }

        Serie=L_Serie;
    }


    protected void verification(char P_Button) throws IOException {


        if(!Serie.isEmpty()){

            if(index_verificator<Serie.size()){

                if(P_Button!=((char)Serie.get(index_verificator))){
                    Log.i(TAG, "u lose");

                    ///REMISE A 0


                    try {
                        lose();

                        index_verificator=0;
                        Serie.clear();
                        score=0;
                        memo_lvl=DEFAULT_lvl;

                        play();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                else if(P_Button==((char)Serie.get(index_verificator))){
                    Log.i(TAG, "Succes " + P_Button+ " == " + Serie.get(index_verificator));
                    index_verificator++;



                    if(index_verificator==Serie.size()){
                        Log.i(TAG, "u win");

                        ///REMISE A 0 et level up
                        index_verificator=0;
                        Serie.clear();
                        score++;
                        memo_lvl++;

                        try {

                            win();
                            play();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

            }



        }
        else{
            Log.i(TAG, "Serie is empty, no memo in queue");

        }

    }


}


