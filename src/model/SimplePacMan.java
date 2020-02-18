/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Observable;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author fred
 */
public class SimplePacMan extends Observable implements Runnable {

    private int x, y, sizeX, sizeY;
    
    private Direction direction = Direction.NONE;
    
    private static Random RANDOM_GENERATOR = new Random();
    
    
    public SimplePacMan(int _sizeX, int _sizeY) {
        x = 0; y = 0;
        
        sizeX = _sizeX;
        sizeY = _sizeY;
       
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void start() {
        Thread th = new Thread(this);
        th.setDaemon(true);
        th.start();
    }
    
    public void initXY() {
        x = 0;
        y = 0;
    }
    
    public void setDirection(Direction _dir) {
    	direction = _dir;
    }
    
    @Override
    public void run() {
        while(true) { // spm descent dans la grille à chaque pas de temps
            
        	switch(direction) {
        	case UP:
        		y = (y-1+sizeY)%sizeY;
        		break;
        	case DOWN:
        		y = (y+1)%sizeY;
        		break;
        	case LEFT:
        		x = (x-1+sizeX)%sizeX;
        		break;
        	case RIGHT:
        		x = (x+1)%sizeX;
        		break;
        	}
           
           setChanged(); 
           notifyObservers(); // notification de l'observer
           
            try {
                Thread.sleep(300); // pause
            } catch (InterruptedException ex) {
                Logger.getLogger(SimplePacMan.class.getName()).log(Level.SEVERE, null, ex);
            }
           
        }
    
    }
    
    
}
