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
        ActivityTraking at = getActivity(a.getId());
        if(at == null)
            activityList.add(a);
    }
    
    public ActivityTraking getActivity(String id){
       for(ActivityTraking active:activityList){
           if(active.getId().equalsIgnoreCase(id)){
               return active;
           }
       }
       return null;
    }
    
    public void addMoveToActivity(String id,Move m){
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Activity id must not be null or empty");
        }
        ActivityTraking a = getActivity(id);
        if (a == null) {
            throw new IllegalArgumentException("Activity not found for id: " + id);
        }
        a.addMove(m);
    }
    
    @Override
    public String toString(){
        SimpleDateFormat sdf = new SimpleDateFormat("[dd/MM/yyyy HH:mm:ss] ");
        String ret = "";
        for(ActivityTraking at:activityList){
            for(Move m: at.getMoves()){
                ret += sdf.format(m.getTimestamp()) + at.getId()+" - "+at.getName() +": "+ m.getType();
            }
        }
        return ret;
    }
    
}
