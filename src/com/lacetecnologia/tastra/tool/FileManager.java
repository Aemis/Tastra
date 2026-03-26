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
    private static final String SYSTEM_PATH = getSystemPath();
    private static String logPath;

    private static String getSystemPath() {
        if(SYSTEM_PATH == null || SYSTEM_PATH.isEmpty()) {
            String mySystemPath = "" ;
            try {
                mySystemPath = System.getProperty("user.dir");
                File filePath = new File(Main.class.getProtectionDomain().getCodeSource().getLocation().toURI());

                System.out.println("(1)"+filePath);
                System.out.println("(2)"+mySystemPath);

                mySystemPath = filePath.getAbsolutePath();

            } catch (URISyntaxException ex) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
            }
            return mySystemPath;
        }
        return SYSTEM_PATH;
    }
    
    public static void saveActivity(ActivityTraking a,Move m,String addText){
        String filename = createFilename();
        createFile(filename,a,m,addText);
        //TODO Integrações
    }

    private static String createFilename() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        Date d = new Date();
        logPath = "";
        logPath += SYSTEM_PATH+File.separator+"logs"+File.separator;
        System.out.println("(3)"+logPath);
        File dirTrack = new File(logPath);
        if (!dirTrack.exists()) {
            boolean created = dirTrack.mkdirs();
            if (!created && !dirTrack.exists()) {
                Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, "Failed to create log directory: " + logPath);
                System.exit(0);
            }
        }
        return logPath + "LogActivities_"+sdf.format(d)+".log";
    }

    public static String getLogPath(){
        return logPath;
    }

    private static void createFile(String filename, ActivityTraking a, Move m,String addText) {
        createANewFile(filename,a,m,addText);
    }

    private static  void createANewFile(String filename,ActivityTraking a, Move m,String addText) {
        System.out.println("[m=createANewFile] Starting creating a new file with filename ="+ filename);
        try {
            File fileTest = new File(filename);
            if (fileTest.exists()) {
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
                    bw.write(gerarRegistroMovimento(a, m, addText));
                    bw.close();

                } catch (IOException ex) {
                    Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
                    System.exit(0);
                }
            } else {
                try {
                    fileTest.createNewFile();
                } catch (IOException ex) {
                    Logger.getLogger(FileManager.class.getName()).log(Level.SEVERE, null, ex);
                    System.exit(0);
                }
                createANewFile(filename, a, m, addText);
            }
        }catch (Exception e){
            System.out.println("ERRO!"+e.getLocalizedMessage());
            System.exit(0);
        }
    }

    private static String gerarRegistroMovimento(ActivityTraking a, Move m,String addText) {
        SimpleDateFormat sdf = new SimpleDateFormat("[dd/MM/yyyy HH:mm:ss] ");
        return sdf.format(m.getTimestamp()) + a.getId()+" - "+a.getName() +": "+ m.getType()+ " "+ addText+ "\n";
    }


}
