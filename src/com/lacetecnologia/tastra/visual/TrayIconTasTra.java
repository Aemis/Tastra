/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacetecnologia.tastra.visual;

import com.lacetecnologia.tastra.tool.RecoverImage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
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
    private final MainWindow tp;

    public TrayIconTasTra() {
        
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            UIManager.put("swing.boldMetal", Boolean.FALSE);
            makeIcon();

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TrayIconTasTra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(TrayIconTasTra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(TrayIconTasTra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(TrayIconTasTra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedOperationException uox){
            Logger.getLogger(TrayIconTasTra.class.getName()).log(Level.SEVERE, null, uox);
        } finally {
            tp = new MainWindow();
        }

    }

    private void makeIcon() {
        System.out.println("CAN?"+canReallyUseTray());
        if (!canReallyUseTray()) {
            System.out.println("Tray Icon is not supported!");
            return;
        }

        try {
            SystemTray tray = SystemTray.getSystemTray();

            TrayIcon trayIcon = getTrayIcon();
            if (trayIcon == null) {
                Logger.getLogger(TrayIconTasTra.class.getName()).log(Level.SEVERE, "Failed to create tray icon.");
                return;
            }
            tray.add(trayIcon);

            MenuItem menu = new MenuItem("Exit");
            menu.setActionCommand("close");
            menu.addActionListener(this);

            popup.add(menu);

            trayIcon.setPopupMenu(popup);
            trayIcon.addActionListener(this);
        } catch (AWTException e) {
            System.out.println("The icon is not found.");
        }catch(Exception e){
            System.out.println("Error on generate Icon.");
        }
    }

    public static boolean canReallyUseTray() {
        if (GraphicsEnvironment.isHeadless()) {
            return false;
        }

        if (!SystemTray.isSupported()) {
            return false;
        }

        try {
            BufferedImage image = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
            TrayIcon trayIcon = new TrayIcon(image, "Teste");
            SystemTray tray = SystemTray.getSystemTray();

            // não precisa adicionar de verdade se quiser só testar criação
            trayIcon.setImageAutoSize(true);

            return tray != null && trayIcon != null;
        } catch (UnsupportedOperationException e) {
            return false;
        } catch (Exception e) {
            return false;
        }
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

    private TrayIcon getTrayIcon() {
        TrayIcon icon = null;
        try {
            icon = new TrayIcon(RecoverImage.getImage("images/planning.png", "Icon"));
        }catch (Exception exception){
            Logger.getLogger(TrayIconTasTra.class.getName()).log(Level.SEVERE, null, exception);
        }
        return icon;
    }
}
