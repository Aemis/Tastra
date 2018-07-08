/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacetecnologia.tastra.model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Leticia
 */
public class Move {
    private Date timestamp;
    private MoveType type;

    public Move(MoveType type){
        this.type = type;
        timestamp = new Date();
    }
    
    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public MoveType getType() {
        return type;
    }

    public void setType(MoveType type) {
        this.type = type;
    }
    
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        return "["+sdf.format(timestamp)+"] "+type;
    }
    
}
