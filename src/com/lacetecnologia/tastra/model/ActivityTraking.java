package com.lacetecnologia.tastra.model;

import java.util.ArrayList;
import java.util.List;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Leticia
 */
public class ActivityTraking {
    private int number;
    private String name;
    private List<Move> moves;
    
    public ActivityTraking(int number,String name){
        this.number = number;
        this.name = name;
        moves = new ArrayList<Move>();
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addMove(Move m){
        this.moves.add(m);
    }
    
    public String getListMoves(){
        String ret = "Moves{\n";
        for(Move m:moves){
            ret += m + "\n";
        }
        ret += "}\n";
        return ret;
    }
    
    public String toString(){
        return number + " - " + name +"\n"+ getListMoves();
    }

    public List<Move> getMoves() {
        return moves;
    }
}
