/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.awt.Point;
import model.Direction;
import model.Grid;

/**
 *
 * @author CoolPC
 */
public class Blinky extends Ghost {
    
    public Blinky(Grid g) {
        super(g, 0);
    }
    
    @Override
    public void update() {
        
        Point p = getGrid().getPosition(this);
        
        //If Chase
        //Else Scatter
    }
    
}
