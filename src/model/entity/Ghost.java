package model.entity;

import model.Direction;
import model.Grid;
import utils.Vector2f;
import utils.Vector2i;

public class Ghost extends Entity {

    private int id;
    protected Vector2i defaultTarget = new Vector2i(0, 22);
    protected Vector2i currentTarget = new Vector2i(0, 0);

    public Ghost(Grid g, int id) {
        super(g);
        this.id = id;
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        //If Chase
        if (false) {
            this.chaseTarget(currentTarget);
        } //Else Scatter
        else {
            this.chaseTarget(defaultTarget);
        }
    }
    
    public void chaseTarget(Vector2i target)
    {
        Vector2i p = getGrid().getPosition(this).toVector2i();
        int distUp, distLeft, distDown, distRight;
        Vector2i upTile,leftTile,downTile,rightTile;
        upTile = new Vector2i(p.x - 1,p.y);
        leftTile = new Vector2i(p.x,p.y - 1);
        downTile = new Vector2i(p.x + 1,p.y);
        rightTile = new Vector2i(p.x,p.y + 1);
        //UP
        distUp = target.distanceTo(upTile);
        //LEFT
        distLeft = target.distanceTo(leftTile);
        //DOWN
        distDown = target.distanceTo(downTile);
        //RIGHT
        distRight = target.distanceTo(rightTile);

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
    
    
    public void affraid()
    {
        
    }

    public int getId() {
        return id;
    }

}
