package com.example.memo_game;

import java.util.ArrayList;

public class Music {


    public Music(ArrayList<String> Tableau_note){

        for (int index_tableau=0; index_tableau<Tableau_note.size();index_tableau++){
            Notes.add(Tableau_note.get(index_tableau));

        }

    }

    ArrayList<String> Notes = new ArrayList<>();



}
