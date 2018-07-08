/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacetecnologia.tastra.tool;

import com.lacetecnologia.tastra.Main;
import com.lacetecnologia.tastra.model.ActivityTraking;
import com.lacetecnologia.tastra.model.Activitys;
import com.lacetecnologia.tastra.model.Move;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leticia
 */
public class FileManager {
    
    public static void saveActivity(ActivityTraking a,Move m,String addText){
        String filename = createFilename();
        createFile(filename,a,m,addText);
    }

    private static String createFilename() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date d = new Date();
        return System.getProperty("user.dir")+"\\tracking\\Log_"+sdf.format(d)+".log";
    }

    private static void createFile(String filename, ActivityTraking a, Move m,String addText) {
        createANewFile(filename,a,m,addText);
    }

    private static  void createANewFile(String filename,ActivityTraking a, Move m,String addText) {

        File fileTest = new File(filename);
        if(fileTest.exists()){
            try{
                BufferedWriter bw = new BufferedWriter(new FileWriter(filename,true));
                bw.write(gerarRegistroMovimento(a,m,addText));
                bw.close();

            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else{
            try {
                fileTest.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            createANewFile(filename,a,m,addText);
        };
    }

    private static String gerarRegistroMovimento(ActivityTraking a, Move m,String addText) {
        SimpleDateFormat sdf = new SimpleDateFormat("[dd/MM/yyyy HH:mm:ss] ");
        return sdf.format(m.getTimestamp()) + a.getNumber()+" - "+a.getName() +": "+ m.getType()+ " "+ addText+ "\n";
    }
    
}
