/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacetecnologia.tastra.visual;

import com.lacetecnologia.tastra.tool.RecoverImage;
import java.awt.AWTException;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Leticia
 */
public class TrayIconTasTra implements ActionListener{
    private final PopupMenu popup = new PopupMenu();
    private final TrayIcon trayIcon = new TrayIcon(RecoverImage.getImage("images/planning.png", "Icon"));
    private final SystemTray tray = SystemTray.getSystemTray();
    private final MainWindow tp;
    
    
    public TrayIconTasTra() {
        
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TrayIconTasTra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TrayIconTasTra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TrayIconTasTra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TrayIconTasTra.class.getName()).log(Level.SEVERE, null, ex);
        }
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        makeIcon();
        tp = new MainWindow();

    }

    private void makeIcon() {
        if (!SystemTray.isSupported()) {
            System.out.println("Tray Icon is not supported!");
        }
        
        try {
            tray.add(trayIcon);
        } catch (AWTException e) {
            System.out.println("The icon is not found.");
        }
        
        MenuItem menu = new MenuItem("Exit");
        menu.setActionCommand("close");
        menu.addActionListener(this);
        
        popup.add(menu);
        
        trayIcon.setPopupMenu(popup);
        trayIcon.addActionListener(this);
        
    }
    
   

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand() == "close"){
            System.exit(0);
        }
        else{
                tp.setVisible(true);
                tp.setLocationRelativeTo(null);
                tp.setState(JFrame.NORMAL);
           
        }
    }
}
