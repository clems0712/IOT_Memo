package com.example.memo_game;


////SINGLETON DES PARAMETRES
public class Parameter {

    private Parameter(){}

    private static Parameter INSTANCE = new Parameter();

    public static Parameter getInstance()
    {   return INSTANCE;
    }


    //////PARAMETRE DU JEUX :


//    ////FINAL
    public static final int LED_LOOP = 30;
    public static final int DISPLAY_LOOP = 3;

   public static final long TIME_STARTUP_DISPLAY = 500;
   public static final long TIME_STARTUP_BTNLED = 3000;
    public static final long TIME_DISPLAY = 300;



    //////TEST
//    public static final int LED_LOOP = 5;
//    public static final int DISPLAY_LOOP = 0;
//    public static final long TIME_STARTUP_DISPLAY = 0;
//    public static final long TIME_STARTUP_BTNLED = 0;


    public static final int RAINBOW_BLINK = 3;

    public static final long TIME_LED_MEMO = 800;





}
