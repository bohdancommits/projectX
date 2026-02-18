/*
 *
 *
 *
 * This program displays several butterflies that fly across the screen.
 * The player controls a net that moves up and down. When the net collides
 * with a butterfly, the butterfly disappears.
 *
 */
import java.awt.*;
import java.awt.event.KeyEvent;

import acm.graphics.*;
import acm.program.GraphicsProgram;

import static java.lang.Math.random;


public class Butterflies extends GraphicsProgram {
    // constant for application window size
    public static final int APPLICATION_HEIGHT = 800;
    public static final int APPLICATION_WIDTH = 1200;
    // position for the net
//    public static final int WIDTH_FLY_SIZE = 1100;
//    public static final int HEIGHT_FLY_SIZE = 600;
    // object the player controls
    GImage net;
    // this method will contains all other methods to run it
        public void run() {
        GImage[] butterfly = drawButterflies(5);


        drawNet();
        net.setLocation( APPLICATION_WIDTH - net.getWidth(), APPLICATION_HEIGHT /2);// WIDTH_FLY_SIZE, HEIGHT_FLY_SIZE);
        addKeyListeners();
        animateButteflies(butterfly);

      }
    //Creates the player's net and adds it to the bottom-middle of the window.
    // but in the run() method i set a different location
     public void drawNet(){
        net = new GImage("net.png");
        add(net, APPLICATION_WIDTH / 2 , APPLICATION_HEIGHT - net.getHeight());

     }
        // this method creates count butterfly images and places them at random position.
        public GImage[] drawButterflies(int count ){
        GImage[] butterflies = new GImage[count];
        for(int i = 0; i < count; i++){
            butterflies[i] = new GImage("butterfly.png");
            double x = random() * (APPLICATION_WIDTH-butterflies[i].getWidth());
            double y = random() * (APPLICATION_HEIGHT-butterflies[i].getHeight());
            add(butterflies[i], x, y);
         }
        return butterflies;
        }
        // the net will move up and down , it will be not moving all to the infinity, window have borders
    public void keyPressed(KeyEvent e) {
        double netX = net.getX();
        double netY = net.getY();
        if(e.getKeyCode() == KeyEvent.VK_UP){
            if(netY > 0) {
                net.move(0, -10);
            }
        } else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            if(netY + net.getHeight() < APPLICATION_HEIGHT) {
                net.move(0, 10);
            }
        }

    }
    // this method will animate my butterflies and set location to the random if it was not caught
    public void animateButteflies(GImage[] butterflies) {
        int collied = 0;
        while (collied < butterflies.length) {
            for (int index = 0; index < butterflies.length; index++) {
                GImage butterfly = butterflies[index];
                // if the cow has collided with a hay rectangle
               if(net.getBounds().intersects(butterfly.getBounds()) && butterfly.isVisible()) {
                   collied++;
                   butterfly.setVisible(false);
               }
                butterfly.move(20, 0);
               if(butterfly.getX() > APPLICATION_WIDTH) {
                   double x = random() * (APPLICATION_WIDTH-butterfly.getWidth());
                   double y = random() * (APPLICATION_HEIGHT-butterfly.getHeight());
                   butterfly.setLocation(x, y);
                   //butterfly.setLocation(0, butterfly.getY());
               }
                // pause so the human eye can see
                pause(50);


            }
        }
        // prints the game over if all butterflies was caught
        System.out.println("Game Over");
        GLabel gameOver = new GLabel("All Butterflies Caught!!!");
        gameOver.setFont("Helvetica-42");
        gameOver.setColor(Color.BLUE);
        double x = (APPLICATION_WIDTH-gameOver.getWidth())/2;
        double y = (APPLICATION_HEIGHT-gameOver.getHeight())/2;
        add(gameOver,x,y);
    }



    // running class
        public static void main(String[] args) {

        new Butterflies().start();
     }
}



