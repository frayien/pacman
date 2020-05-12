package model.entity;

import model.Direction;
import model.Grid;
import model.tileentity.SuperPacGum;
import utils.Vector2f;

public class PacMan extends Entity {

    private Direction nextDir = Direction.NONE;

    public PacMan(Grid g) {
        super(g);
        setSpeed(4);
    }
    
    public void setNextDir(Direction d)
    {
    	nextDir = d;
    }
    
    
    @Override
    public void update() {
    	Vector2f p = getGrid().getPosition(this);
        if (nextDir != Direction.NONE && nextDir != getDirection()) {
            if (getGrid().isPath(p.toVector2i(), nextDir)) {
                setDirection(nextDir);
                nextDir = Direction.NONE;
            }
        }
        if(getGrid().getTile(p.toVector2i()).hasTileEntity())
        {
                if(getGrid().getTile(p.toVector2i()).getTileEntity() instanceof SuperPacGum)
                {
                        Entity.ghostsAfraidFrameCount = 50; //TODO
                }
                getGrid().incScore();
                getGrid().getTile(p.toVector2i()).setTileEntity(null);
                getGrid().asyncRefresh(p.toVector2i());
        }
        Entity.frameCount = (Entity.frameCount + 1) % 40;
        if(Entity.ghostsAfraidFrameCount > 0)
        {
            Entity.ghostsAfraidFrameCount = (Entity.ghostsAfraidFrameCount + 1) % 25;
        }
    }

}
