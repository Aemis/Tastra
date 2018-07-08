/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacetecnologia.tastra.model;

/**
 *
 * @author Leticia
 */
public enum MoveType {
    START("Start"),STOP("Stop"),PAUSE("Pause");
    
    private String name;
    
    MoveType(String name){
        this.name = name;
    }
    
    @Override
    public String toString(){
        return name;
    }
}
