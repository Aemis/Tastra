/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacetecnologia.tastra.visual;

import com.lacetecnologia.tastra.model.ActivityTraking;
import com.lacetecnologia.tastra.model.Activitys;
import com.lacetecnologia.tastra.model.Move;
import com.lacetecnologia.tastra.model.MoveType;
import com.lacetecnologia.tastra.tool.FileManager;
import com.lacetecnologia.tastra.tool.RecoverImage;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.awt.GridLayout;

/**
 *
 * @author Leticia
 */
public class MainWindow extends JFrame implements ActionListener{
    private JLabel lblAtividade;
    private JTextField txtNroAtividade;
    private JTextField txtAtividadeNome;
    private JButton play;
    private JButton stop;
    private JButton pause;
    private JLabel status;
    private JLabel lblTimeSpended;
    private int minutes;
    private int seconds;
    private boolean countingTime;
    private String timeSpended;
    private Timer hourTimer;
    

    public MainWindow(){
        super("TaskTraker");
        countingTime = false;
        configWindow();
    }
    
    private void configWindow(){
        this.setContentPane(makeWindow());
        this.pack();
        this.setIconImage(RecoverImage.getImage("images/planning.png", "TaskTraker"));
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private Container makeWindow() {
       JPanel painel = new JPanel();
       
       JPanel superior = new JPanel();
       lblAtividade = new JLabel("Activity:");
       txtNroAtividade = new JTextField(10);
       txtAtividadeNome = new JTextField(50);
       lblTimeSpended = new JLabel("");
       superior.add(lblAtividade);
       superior.add(txtNroAtividade);
       superior.add(txtAtividadeNome);
       
       JPanel inferior = new JPanel();
     
       stop = new JButton(new ImageIcon(RecoverImage.getImage("images/stop.png", "Stop")));
       play = new JButton(new ImageIcon(RecoverImage.getImage("images/play.png", "Start")));
       pause = new JButton(new ImageIcon(RecoverImage.getImage("images/pause.png", "Pause")));
       
       stop.setActionCommand("stop");
       play.setActionCommand("play");
       pause.setActionCommand("pause");
      
       stop.addActionListener(this);
       play.addActionListener(this);
       pause.addActionListener(this);
       
       stop.setEnabled(false);
       pause.setEnabled(false);
       
       stop.setToolTipText("Stop Activity");
       play.setToolTipText("Start Activity");
       pause.setToolTipText("Pause Activity");
       
       Timer timer = new Timer(0, this);
       timer.setActionCommand("refresh");
       timer.setDelay(1000);
       timer.start();    
       
      inferior.add(lblTimeSpended);
      inferior.add(play);
      inferior.add(pause);
      inferior.add(stop);
      
      JPanel superInferior = new JPanel();
     
      status = new JLabel();
      Font f = new Font("Arial",Font.ITALIC,10);
      status.setFont(f);
      
      superInferior.setBounds(100, 10, 100, 50);
      superInferior.setBackground(Color.LIGHT_GRAY);
      superInferior.add(status);
      
      painel.setLayout(new BoxLayout(painel,BoxLayout.Y_AXIS));
      
      painel.add(superior);
      painel.add(inferior);
      painel.add(superInferior);

       return painel;
    }

    private void startHourTimer(){
        if(hourTimer != null && hourTimer.isRunning()){
            hourTimer.stop();
        }
        hourTimer = new Timer(3600000, this);
        hourTimer.setRepeats(false);
        hourTimer.setActionCommand("hourCheck");
        hourTimer.start();
    }

    private void stopHourTimer(){
        if(hourTimer != null){
            hourTimer.stop();
        }
    }

    private void finalizeFromPopup(){
        int number = Integer.parseInt(this.txtNroAtividade.getText());
        String desc = this.txtAtividadeNome.getText();

        Move m = new Move(MoveType.STOP);
        Activitys.getInstance().addActivity(new ActivityTraking(number, desc));
        Activitys.getInstance().addMoveToActivity(number, m);
        ActivityTraking at = Activitys.getInstance().getActivity(number);
        FileManager.saveActivity(at, m, timeSpended);

        stop.setEnabled(false);
        pause.setEnabled(false);
        play.setEnabled(true);
        txtAtividadeNome.setEnabled(true);
        txtNroAtividade.setEnabled(true);
        countingTime = false;
        minutes = 0;
        seconds = 0;
        status.setText("Stop registred on " + FileManager.getPath());
        stopHourTimer();

        txtAtividadeNome.setText("");
        txtNroAtividade.setText("");

        showNextActivityDialog();
    }

    private void showNextActivityDialog(){
        JPanel panel = new JPanel(new GridLayout(2,2));
        JTextField numberField = new JTextField();
        JTextField nameField = new JTextField();
        panel.add(new JLabel("Number:"));
        panel.add(numberField);
        panel.add(new JLabel("Name:"));
        panel.add(nameField);

        int res = JOptionPane.showConfirmDialog(null, panel, "Next Activity", JOptionPane.OK_CANCEL_OPTION);
        if(res == JOptionPane.OK_OPTION){
            txtNroAtividade.setText(numberField.getText());
            txtAtividadeNome.setText(nameField.getText());
            ActionEvent playEvent = new ActionEvent(play, ActionEvent.ACTION_PERFORMED, "play");
            actionPerformed(playEvent);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String msg = "";
        if(this.txtNroAtividade.getText().isEmpty() || this.txtAtividadeNome.getText().isEmpty()){
            msg = "Please, set the number and the activity description to track.";
        }
        else{
            boolean toSave = false;
            int number = Integer.parseInt(this.txtNroAtividade.getText());
            String desc = this.txtAtividadeNome.getText();
            Move m = null;
            
            switch(e.getActionCommand()){
                case "stop":
                    if(JOptionPane.showConfirmDialog(this,"Do you had finished this activity?")== 0){//yes

                        m = new Move(MoveType.STOP);
                        msg = "Stop registred on "+FileManager.getPath();
                        toSave = true;
                        
                        stop.setEnabled(false);
                        pause.setEnabled(false);
                        play.setEnabled(true);
                        txtAtividadeNome.setEnabled(true);
                        txtNroAtividade.setEnabled(true);
                        countingTime = false;
                        minutes=0;
                        seconds=0;

                        stopHourTimer();


                        txtAtividadeNome.setText("");
                        txtNroAtividade.setText("");
                    }
                    break;
                case "play":
                    m = new Move(MoveType.START);
                    msg = "Start registred on "+FileManager.getPath();
                    toSave = true;
                   
                    stop.setEnabled(true);
                    pause.setEnabled(true);
                    play.setEnabled(false);
                    txtAtividadeNome.setEnabled(false);
                    txtNroAtividade.setEnabled(false);
                    countingTime = true;
                    startHourTimer();

                    break;
                case "pause":
                    toSave =true;
                    m = new Move(MoveType.PAUSE);
                    msg = "Stop registred on "+FileManager.getPath();
                    
                    stop.setEnabled(false);
                    pause.setEnabled(false);
                    play.setEnabled(true);
                    countingTime = false;
                    stopHourTimer();


                    break;
                case "refresh":
                    if(countingTime){
                        seconds++;
                        if(seconds == 60){
                            minutes++;
                            seconds = 0;
                        }
                        
                        timeSpended = String.format("%02d", minutes) + ":" + String.format("%02d", seconds) + " spended";
                        lblTimeSpended.setText(timeSpended);
                    }
                    break;
                case "hourCheck":
                    Object[] options = {"Continuar", "Finalizada"};
                    int resp = JOptionPane.showOptionDialog(null,
                            "A atividade " + desc + " foi finalizada?",
                            "Hora trabalhada",
                            JOptionPane.DEFAULT_OPTION,
                            JOptionPane.QUESTION_MESSAGE,
                            null,
                            options,
                            options[0]);
                    if(resp == 1){
                        finalizeFromPopup();
                    }else{
                        startHourTimer();
                    }
                    break;
            }
            if(toSave){
                //saving
                Activitys.getInstance().addActivity(new ActivityTraking(number, desc));
                Activitys.getInstance().addMoveToActivity(number, m);
                ActivityTraking at = Activitys.getInstance().getActivity(number);
                FileManager.saveActivity(at,m,(m.getType() == MoveType.STOP)?timeSpended:"");
            }
        }
        
        status.setText(msg);
        
    }
}
