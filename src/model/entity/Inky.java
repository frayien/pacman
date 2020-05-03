/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.entity;

import java.util.ArrayList;

import model.Direction;
import model.Grid;
import utils.Vector2i;

/**
 *
 * @author CoolPC
 */
public class Inky extends Ghost {
    
	Vector2i defaultTarget = new Vector2i(19, 22);
    Vector2i currentTarget;
    
    public Inky(Grid g) {
        super(g, 3);
        setSpeed(4);
    }
    
    @Override
    public void update() {
        if(!dead)
        {
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
				default:
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
            else if (Entity.frameCount > 12) {
                Vector2i pac = this.getGrid().getPacManPosition().toVector2i();
                if(this.getGrid().blinky != null)
                {
                    Vector2i blink = this.getGrid().getPosition(this.getGrid().blinky).toVector2i();
                    switch (this.getGrid().player.direction)
                    {
                        case NONE : 
                        case UP : 
                            pac = new Vector2i(pac.x - 2,pac.y);
                            break;
                        case DOWN : 
                            pac = new Vector2i(pac.x + 2,pac.y);
                            break;
                        case RIGHT : 
                            pac = new Vector2i(pac.x,pac.y + 2);
                            break;
                        case LEFT : 
                            pac = new Vector2i(pac.x,pac.y - 2);
                            break;
                    }
                currentTarget = new Vector2i(pac.x + 2*(pac.x-blink.x),pac.y + 2*(pac.y-blink.y));
                }
                else
                {
                    currentTarget = pac;
                }

                this.chaseTarget(currentTarget);
            } //Else Scatter
            else {
                this.chaseTarget(defaultTarget);
            }
        }
    }
}
