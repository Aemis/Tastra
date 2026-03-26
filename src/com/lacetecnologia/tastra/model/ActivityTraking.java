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
    private String id;
    private String name;
    private List<Move> moves;
    
    public ActivityTraking(String id,String name){
        this.id = id;
        this.name = name;
        moves = new ArrayList<Move>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return id + " - " + name +"\n"+ getListMoves();
    }

    public List<Move> getMoves() {
        return moves;
    }
}
