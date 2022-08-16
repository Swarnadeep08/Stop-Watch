package com.stopwatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame implements ActionListener
{
    private JButton startButton = new JButton("Start");
    private JButton resetButton = new JButton("Reset");
    private JLabel timeLabel = new JLabel();
    int elapsedTime = 0, seconds = 0, minutes = 0, hours = 0;
    boolean started = false;

    String seconds_str = String.format("%02d", seconds);
    String minutes_str = String.format("%02d", minutes);
    String hours_str = String.format("%02d", hours);

    Timer timer = new Timer(1000, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            elapsedTime += 1000;
            hours = elapsedTime/3600000;
            minutes = (elapsedTime/60000)%60;
            seconds = (elapsedTime/1000)%60;
            seconds_str = String.format("%02d", seconds);
            minutes_str = String.format("%02d", minutes);
            hours_str = String.format("%02d", hours);

            timeLabel.setText(hours_str+":"+minutes_str+":"+seconds_str);
        }
    });

    MyFrame()
    {
        super.setTitle("Stop Watch");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(350, 200);
        this.setResizable(false);
        this.setLocation(300, 200);
        this.setLayout(null);

        timeLabel.setText(hours_str+":"+minutes_str+":"+seconds_str);
        timeLabel.setBounds(0, 0, 350, 100);
        timeLabel.setFont(new Font("Monospaced", Font.BOLD, 40));
        timeLabel.setBorder(BorderFactory.createBevelBorder(1));
        timeLabel.setBackground(Color.MAGENTA);
        timeLabel.setOpaque(true);
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(timeLabel);

        startButton.setBounds(0,100,175,100);
        startButton.setFont(new Font("Monospaced", Font.BOLD, 32));
        startButton.setBorder(BorderFactory.createBevelBorder(2));
        startButton.setFocusable(false);
        startButton.addActionListener(this);
        startButton.setVerticalAlignment(SwingConstants.NORTH);
        this.add(startButton);

        resetButton.setBounds(175,100,175,100);
        resetButton.setFont(new Font("Monospaced", Font.BOLD, 32));
        resetButton.setBorder(BorderFactory.createBevelBorder(2));
        resetButton.setFocusable(false);
        resetButton.addActionListener(this);
        resetButton.setVerticalAlignment(SwingConstants.NORTH);
        this.add(resetButton);

        this.setVisible(true);
    }

    void start(){
        timer.start();
    }

    void stop(){
        timer.stop();
    }

    void reset(){
        timer.stop();
        elapsedTime = 0;
        seconds = 0;
        minutes = 0;
        hours = 0;
        seconds_str = String.format("%02d", seconds);
        minutes_str = String.format("%02d", minutes);
        hours_str = String.format("%02d", hours);
        timeLabel.setText(hours_str+":"+minutes_str+":"+seconds_str);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==startButton){
            if(!started){
                started=true;
                startButton.setText("Stop");
                this.start();
            }
            else{
                started = false;
                startButton.setText("Start");
                this.stop();
            }
        }

        if(e.getSource()==resetButton){
            started = false;
            startButton.setText("Start");
            this.reset();
        }
    }
}
