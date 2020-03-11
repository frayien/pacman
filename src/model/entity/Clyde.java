/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import model.Direction;
import model.Grid;
import utils.Vector2i;

/**
 *
 * @author CoolPC
 */
public class Clyde extends Ghost {
    
	Vector2i defaultTarget = new Vector2i(19, 4);
	Vector2i currentTarget;
    
    public Clyde(Grid g) {
        super(g, 1);
        setSpeed(4);
    }
    
    @Override
    public void update() {
    	Vector2i p = getGrid().getPosition(this).toVector2i();
        int distUp, distLeft, distDown, distRight;
        //If Chase
        if (false) {
        } //Else Scatter
        else {
            
        	Vector2i upTile,leftTile,downTile,rightTile;
            upTile = new Vector2i(p.x - 1,p.y);
            leftTile = new Vector2i(p.x,p.y - 1);
            downTile = new Vector2i(p.x + 1,p.y);
            rightTile = new Vector2i(p.x,p.y + 1);
            //UP
            distUp = defaultTarget.distanceTo(upTile);
            //LEFT
            distLeft = defaultTarget.distanceTo(leftTile);
            //DOWN
            distDown = defaultTarget.distanceTo(downTile);
            //RIGHT
            distRight = defaultTarget.distanceTo(rightTile);
            
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
