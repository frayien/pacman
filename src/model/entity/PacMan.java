package model.entity;

import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import model.Direction;
import model.Grid;
import model.tileentity.SuperPacGum;
import utils.Vector2f;

public class PacMan extends Entity implements EventHandler<KeyEvent> {

    private Direction nextDir = Direction.NONE;

    private static Pane root;

    public PacMan(Grid g) {
        super(g);
        setSpeed(4);
        root.setOnKeyPressed(this);
    }

    @Override
    public void handle(KeyEvent event) {

        switch (event.getCode()) {
            case UP:
                nextDir = Direction.UP;
                break;
            case DOWN:
                nextDir = Direction.DOWN;
                break;
            case LEFT:
                nextDir = Direction.LEFT;
                break;
            case RIGHT:
                nextDir = Direction.RIGHT;
                break;
            default:
                break;

        }
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
        	getGrid().getTile(p.toVector2i()).setTileEntity(null);
        	Platform.runLater(()->this.getGrid().refresh(p.toVector2i()));
        }
        Entity.frameCount = (Entity.frameCount + 1) % 40;
        if(Entity.ghostsAfraidFrameCount > 0)
        {
            Entity.ghostsAfraidFrameCount = (Entity.ghostsAfraidFrameCount + 1) % 25;
        }
    }

    public static void setRoot(Pane p) {
        root = p;

    }
}
