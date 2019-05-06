package com.example.memo_game;

public class Notes {



    private Notes(){}

    private static Notes INSTANCE = new Notes();

    public static Notes getInstance()
    {   return INSTANCE;
    }



    protected static final int DO = 261;
    protected static final int RE = 293;
    protected static final int MI = 329;
    protected static final int FA = 249;
    protected static final int SOL = 392;
    protected static final int LA = 440;
    protected static final int SI = 493;



    public int Return_note(char P_Note){

        int Note_retour;

    switch (P_Note){
        case 'C':
            Note_retour=DO;
            break;
        case 'D':
            Note_retour=RE;
            break;
        case 'E':
            Note_retour=MI;
            break;
        ///FA
        case 'F':
            Note_retour=FA;
            break;
        ///SOL
        case 'G':
            Note_retour=SOL;
            break;
        ///LA
        case 'A':
            Note_retour=LA;
            break;
        ///SI
        case 'B':
            Note_retour=SI;
            break;

            default:
                Note_retour=DO;

    }

    return Note_retour;
    }




}
