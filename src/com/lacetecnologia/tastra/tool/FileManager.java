/*
 * Copyright (C) 2018 Leticia
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.lacetecnologia.tastra.tool;

import com.lacetecnologia.tastra.Main;
import com.lacetecnologia.tastra.model.ActivityTraking;
import com.lacetecnologia.tastra.model.Move;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leticia
 */
public class FileManager {
    private static String path;
    
    public static void saveActivity(ActivityTraking a,Move m,String addText){
        String filename = createFilename();
        createFile(filename,a,m,addText);
    }

    private static String createFilename() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date d = new Date();
        //creating log directory
        path = System.getProperty("user.dir");
        try {
            File filePath = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());
            path = filePath.getPath().replace(filePath.getName(), "");
        } catch (URISyntaxException ex) {
            Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
        }
        path += "\\tracking\\";
        File dirTrack = new File(path);
        if(!dirTrack.exists()){
           dirTrack.mkdir(); 
        }
        return path + "Log_"+sdf.format(d)+".log";
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
    
    public static String getPath(){
        return path;
    }
}
