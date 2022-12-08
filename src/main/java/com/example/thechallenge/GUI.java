package com.example.thechallenge;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;


public class GUI implements ActionListener, AncestorListener {

    int count = 0;

    public GUI() {

        JFrame frame = new JFrame ();

        JButton button = new JButton("Click me!");
        button.addAncestorListener(this);

        JLabel label = new JLabel("Number of clicks: 0");

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(30, 10, 30, 30));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(button);
        panel.add(label);

        frame.add(panel, BorderLayout.CENTER);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("The Challenge");
        frame.pack();
        frame.setVisible(true);

    }
    public static void main(String[] args){
       new GUI();


    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void ancestorAdded(AncestorEvent event) {

    }

    @Override
    public void ancestorRemoved(AncestorEvent event) {

    }

    @Override
    public void ancestorMoved(AncestorEvent event) {

    }
}
