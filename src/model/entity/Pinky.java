/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.util.ArrayList;
import model.Direction;
import model.Grid;
import utils.Vector2f;
import utils.Vector2i;

/**
 *
 * @author CoolPC
 */
public class Pinky extends Ghost {

    Vector2i defaultTarget = new Vector2i(3, 0);
    Vector2i currentTarget;
    
    public Pinky(Grid g) {
        super(g, 2);
        setSpeed(4);
    }
    
    @Override
    public void update() {
        if(!dead)
        {
            //If ghosts are afraid, they turn back on their first move
            if(Entity.ghostsAfraidFrameCount == 1)
            {
                switch(this.direction)
                {
                    case UP :
                        this.direction = Direction.DOWN;
                        break;
                    case DOWN :
                        this.direction = Direction.UP;
                        break;
                    case RIGHT :
                        this.direction = Direction.LEFT;
                        break;
                    case LEFT :
                        this.direction = Direction.RIGHT;
                        break;
                }
            }
            //Random move when afraid
            else if(Entity.ghostsAfraidFrameCount > 0 && Entity.ghostsAfraidFrameCount < 25)
            {
                Vector2i p = getGrid().getPosition(this).toVector2i();
                Vector2i upTile,leftTile,downTile,rightTile;
                upTile = new Vector2i(p.x - 1,p.y);
                leftTile = new Vector2i(p.x,p.y - 1);
                downTile = new Vector2i(p.x + 1,p.y);
                rightTile = new Vector2i(p.x,p.y + 1);
                ArrayList<Direction> possibleDirections = new ArrayList<>();
                int rand;

                if(getGrid().isPath(upTile) && this.direction != Direction.DOWN) {
                    possibleDirections.add(Direction.UP);
                }
                if(getGrid().isPath(downTile) && this.direction != Direction.UP) {
                    possibleDirections.add(Direction.DOWN);
                }
                if(getGrid().isPath(leftTile) && this.direction != Direction.RIGHT) {
                    possibleDirections.add(Direction.LEFT);
                }
                if(getGrid().isPath(rightTile) && this.direction != Direction.LEFT) {
                    possibleDirections.add(Direction.RIGHT);
                }

                rand = (int) (Math.random() * possibleDirections.size());
                this.direction = possibleDirections.get(rand);


            }
            //If Chase
            else if (Entity.frameCount > 9) {
                //Targets 2 tiles in front of pacman
                Vector2i pac = this.getGrid().getPacManPosition().toVector2i();
                switch (this.getGrid().player.direction)
                {
                    case NONE : 
                    case UP : 
                        currentTarget = new Vector2i(pac.x - 2,pac.y);
                        break;
                    case DOWN : 
                        currentTarget = new Vector2i(pac.x + 2,pac.y);
                        break;
                    case RIGHT : 
                        currentTarget = new Vector2i(pac.x,pac.y + 2);
                        break;
                    case LEFT : 
                        currentTarget = new Vector2i(pac.x,pac.y - 2);
                        break;
                }
                this.chaseTarget(currentTarget);
            } //Else Scatter
            else {
                this.chaseTarget(defaultTarget);
            }
        }
        
    }

}
