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

}
