package com.example.thechallenge;

import java.awt.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.*;


public class GUI {
    public GUI() {

        JFrame frame = new JFrame ();

        JButton button = new JButton("Click me!")

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 30));
        panel.setLayout(new GridLayout(0, 1));

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("The Challenge");
        frame.pack();
        frame.setVisible(true);

    }
    public static void main(String[] args){
       new GUI();


    }
}
