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
public class Blinky extends Ghost {

	Vector2i defaultTarget = new Vector2i(0, 22);

    public Blinky(Grid g) {
        super(g, 0);
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
            else if (Entity.frameCount > 7) {
                Vector2i pac = this.getGrid().getPacManPosition().toVector2i();
                currentTarget = new Vector2i(pac.x,pac.y);
                this.chaseTarget(currentTarget);
            } //Else Scatter
            else {
                this.chaseTarget(defaultTarget);
            }
        }
    }
    

}
