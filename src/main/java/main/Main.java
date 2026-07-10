package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) throws Exception{
        
        JFrame ventana = new JFrame();

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setResizable(false);
        ventana.setTitle("2D Adventure");

        Panel panel = new Panel();
        ventana.add(panel);

        ventana.pack();

        ventana.setLocationRelativeTo(null);
        ventana.setVisible(true);
        

        panel.startGame();
    }

}
