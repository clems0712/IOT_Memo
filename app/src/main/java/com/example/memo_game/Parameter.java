package com.example.memo_game;


////SINGLETON DES PARAMETRES
public class Parameter {

    private Parameter(){}

    private static Parameter INSTANCE = new Parameter();

    public static Parameter getInstance()
    {   return INSTANCE;
    }


    //////PARAMETRE DU JEUX :

    public static final int LED_LOOP = 10;
    public static final int DISPLAY_LOOP = 6;

    public static final long TIME_STARTUP_DISPLAY = 500;
    public static final long TIME_STARTUP_BTNLED = 1000;

}
