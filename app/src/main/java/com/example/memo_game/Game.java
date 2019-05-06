package com.example.memo_game;

import android.util.Log;


import java.io.IOException;
import java.util.Vector;

import static android.content.ContentValues.TAG;


public class Game {

    ////CONSTRUCTEUR

    public Game(){

        this.score=0;
        //DOIT ETRE INITIALISE PAR UNE RESSOURCE
        this.record=9;

        this.memo_lvl=DEFAULT_lvl;
        this.index_verification =0;
    }

    //// ATTRIBUTS


    protected int score;
    protected int record;


    ///Nb de * à mémoriser
    int memo_lvl;
    int DEFAULT_lvl=3;

    //Vector des series de couleurs random
    Vector Serie = new Vector();

    // verification avec la serie variable globale pour les boutons
    int index_verification;



    /// ENTRE/SORTIE
    IO Carte = new IO();


//////////////////////////////////////////////////////////////////////////////////////////////////////////////

    ///METHODES JEUX

    ////////////////////DEBUT DU JEUX///////////////////
    protected void startup() throws IOException {

        //CALCUL DU TEMPS
        long start = System.currentTimeMillis();

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
                    Carte.diplay_chaine_pause("PLAY");
                } catch (IOException e) {
                    e.printStackTrace();
                }





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


        ////TIMER
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


    ////////////////////////MEMO GENERATION////////////////////////////////
    protected void memo() throws IOException {


        Serie.clear();
        Vector L_Serie = new Vector();

        for (int index_memo = 0; index_memo< memo_lvl; index_memo++){

            ///RANDOM : de 65 a 67 ASCII de A, B et C
            final int Random_LED = (int)( 65+ (Math.random()*(68-65)));


            Thread buttn_led = new Thread(new Runnable() {
                public void run() {
                    try {
                        Carte.buttn_ledone((char)Random_LED);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });

          Thread rainbowthread = new Thread(new Runnable() {
                public void run() {
                    try {
                        Carte.rainbow_colors((char)Random_LED);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            });


            buttn_led.start();
            rainbowthread.start();



            try {
                rainbowthread.join(); // Waiting thread to finish
                buttn_led.join();// Waiting thread to finish
            }
            catch (InterruptedException ie) {
            }




            //SON A B C à ajouter

            //Pour éviter bug d'appui sur le bouton pdt l'injection
            L_Serie.add((char)Random_LED);
            

            ///Debug RED ONLY
            //Carte.buttn_ledone('A');
            //Serie.add('A');
        }

        Serie=L_Serie;
    }


    ////////////////////////VERIFICATION ENTREE////////////////////////////////
    protected void verification(final char P_Button) throws IOException {


        if(!Serie.isEmpty()){

            if(index_verification <Serie.size()){


                Thread buttn_led = new Thread(new Runnable() {
                    public void run() {
                        try {
                            Carte.buttn_ledone(P_Button);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });

                Thread rainbowthread = new Thread(new Runnable() {
                    public void run() {
                        try {
                            Carte.rainbow_colors(P_Button);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });

                Thread soundbox = new Thread(new Runnable() {
                    public void run() {
                        try {
                            Carte.note_button(P_Button);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                    }
                });


                buttn_led.start();
                rainbowthread.start();
                //soundbox.start();



                if(P_Button!=((char)Serie.get(index_verification))){
                    Log.i(TAG, "u lose");

                    ///REMISE A 0


                    try {
                        rainbowthread.join(); // Waiting thread to finish
                        buttn_led.join();// Waiting thread to finish
                       // soundbox.join();
                    }
                    catch (InterruptedException ie) {
                    }

                    try {


                        lose();


                        if(score>record){
                            save_record();
                        }

                        index_verification =0;
                        Serie.clear();
                        score=0;
                        memo_lvl=DEFAULT_lvl;

                        play();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                else if(P_Button==((char)Serie.get(index_verification))){
                    Log.i(TAG, "Succes " + P_Button+ " == " + Serie.get(index_verification));
                    index_verification++;



                    if(index_verification ==Serie.size()){
                        Log.i(TAG, "u win");


                        try {
                            rainbowthread.join(); // Waiting thread to finish
                            buttn_led.join();// Waiting thread to finish
                           // soundbox.join();
                        }
                        catch (InterruptedException ie) {
                        }

                        ///REMISE A 0 et level up
                        index_verification =0;
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


    ////////////////////////SAUVEGARDE RECORD////////////////////////////////
    protected void save_record(){
        //SAUVEGARDER RECORD
    }


}


