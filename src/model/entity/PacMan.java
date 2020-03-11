package model.entity;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import model.Direction;
import model.Grid;
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
        if (nextDir != Direction.NONE && nextDir != getDirection()) {
        	Vector2f p = getGrid().getPosition(this);
            if (getGrid().isPath(p.toVector2i(), nextDir)) {
                setDirection(nextDir);
                nextDir = Direction.NONE;
            }
        }
        Entity.frameCount = (Entity.frameCount + 1) % 100;
    }

    public static void setRoot(Pane p) {
        root = p;

    }
}
