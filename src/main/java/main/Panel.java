package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Panel extends JPanel implements Runnable{
    final int originalTile = 16; // 16x16 pixeles
    final int scale = 3;

    final int tileSize = originalTile * scale; // 48x48 escala 
    final int maxScreenCol=16;
    final int maxScreenRow=12;
    final int screenWidth= tileSize * maxScreenCol; // 768 pixeles
    final int screenHeight = tileSize * maxScreenRow; // 576 pixeles 

    //FPS
    int FPS = 60;


    KeyHand keyH = new KeyHand();
    Thread gameThread;

    // posicion inicial del jugador

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;


    public Panel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGame(){

        gameThread = new Thread(this);
        gameThread.start(); 

    }

    @Override
    public void run() {

        double intervalo = 1000000000/FPS; // 0.01666 seconds
        double nextDrawTime = System.nanoTime() + intervalo;

        while (gameThread !=null) {

            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime/1000000;

                if (remainingTime<0) {
                    remainingTime=0;              
                }

                Thread.sleep((long) remainingTime);
                nextDrawTime+=intervalo;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }         
        }
    }
    public void update(){

        if (keyH.upPress==true) {
            playerY -= playerSpeed;
            
        }else if(keyH.downPress==true){
            playerY += playerSpeed;
        
        }else if(keyH.leftPress==true){
            playerX -= playerSpeed;
        
        }else if(keyH.rightPress==true){
            playerX += playerSpeed;
        }

        }

    public void paintComponent(Graphics g){ 
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;
        
        g2.setColor(Color.WHITE);
        g2.fillRect(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
