package com.stefan;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        JFrame frame = new JFrame("BomberMan");
        PaintPanel panel  = new PaintPanel();
        frame.add(panel);
        frame.addKeyListener(panel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setResizable(false);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Thread t = new Thread(() -> {
            while(true) {
                try {
                    frame.repaint();
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}
