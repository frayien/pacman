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
public class Inky extends Ghost {
    
    Point defaultTarget = new Point(19, 19);
    Point currentTarget;
    
    public Inky(Grid g) {
        super(g, 3);
        setSpeed(4);
    }
    
    @Override
    public void update() {
        Point p = getGrid().getPosition(this);
        int distUp, distLeft, distDown, distRight;
        //If Chase
        if (false) {
        } //Else Scatter
        else {
            
            Point upTile,leftTile,downTile,rightTile;
            upTile = new Point(p.x - 1,p.y);
            leftTile = new Point(p.x,p.y - 1);
            downTile = new Point(p.x + 1,p.y);
            rightTile = new Point(p.x,p.y + 1);
            //UP
            distUp = getGrid().getDistanceFromPoint(defaultTarget, upTile);
            //LEFT
            distLeft = getGrid().getDistanceFromPoint(defaultTarget, leftTile);
            //DOWN
            distDown = getGrid().getDistanceFromPoint(defaultTarget, downTile);
            //RIGHT
            distRight = getGrid().getDistanceFromPoint(defaultTarget, rightTile);
            
            if (getGrid().isPath(upTile.x,upTile.y)
                    && (distUp <= distLeft || !getGrid().isPath(leftTile.x,leftTile.y) || this.direction == Direction.RIGHT)
                    && (distUp <= distDown || !getGrid().isPath(downTile.x,downTile.y) || this.direction == Direction.UP)
                    && (distUp <= distRight || !getGrid().isPath(rightTile.x,rightTile.y) || this.direction == Direction.LEFT)
                    && this.direction != Direction.DOWN) {
                this.direction = Direction.UP;
            }
            else if (getGrid().isPath(leftTile.x,leftTile.y)
                    && (distLeft <= distDown || !getGrid().isPath(downTile.x,downTile.y) || this.direction == Direction.UP)
                    && (distLeft <= distRight || !getGrid().isPath(rightTile.x,rightTile.y) || this.direction == Direction.LEFT)
                    && this.direction != Direction.RIGHT) {
                this.direction = Direction.LEFT;
            }
            else if (getGrid().isPath(downTile.x,downTile.y)
                    && (distDown <= distRight || !getGrid().isPath(rightTile.x,rightTile.y) || this.direction == Direction.LEFT)
                    && this.direction != Direction.UP) {
                this.direction = Direction.DOWN;
            }
            else if (getGrid().isPath(rightTile.x,rightTile.y)
                    && this.direction != Direction.LEFT) {
                this.direction = Direction.RIGHT;
            }
        }
    }
    
}
