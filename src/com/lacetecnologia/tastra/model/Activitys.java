/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacetecnologia.tastra.model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leticia
 */
public class Activitys {
    private static Activitys activitys = null;
    private List<ActivityTraking> activityList;
    
    private Activitys(){
        
        activityList = new ArrayList<ActivityTraking>();
    }
    
    public static Activitys getInstance(){
        if(activitys == null){
            activitys = new Activitys();
        }
        return activitys;
    }
    
    public void addActivity(ActivityTraking a){
        ActivityTraking at = getActivity(a.getNumber());
        if(at == null)
            activityList.add(a);
    }
    
    public ActivityTraking getActivity(int number){
       for(ActivityTraking a:activityList){
           if(a.getNumber() == number){
               return a;
           }
       }
       return null;
    }
    
    public void addMoveToActivity(int number,Move m){
        ActivityTraking a = getActivity(number);
        a.addMove(m);
    }
    
    @Override
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("[dd/MM/yyyy HH:mm:ss] ");
        String ret = "";
        for(ActivityTraking at:activityList){
            for(Move m: at.getMoves()){
                ret += sdf.format(m.getTimestamp()) + at.getNumber()+" - "+at.getName() +": "+ m.getType();
            }
        }
        return ret;
    }
    
}
