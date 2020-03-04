package model.entity;

import java.awt.Point;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import model.Direction;
import model.Grid;

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
            Point p = getGrid().getPosition(this);
            if (getGrid().isPath(p.x, p.y, nextDir)) {
                setDirection(nextDir);
                nextDir = Direction.NONE;
            }
        }
    }

    public static void setRoot(Pane p) {
        root = p;

    }
}
