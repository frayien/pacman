package model.entity;

import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import model.Direction;
import model.Grid;

public class PacMan extends Entity implements EventHandler<KeyEvent>
{
	public PacMan(Grid g) {
		super(g);
	}

	@Override
	public void handle(KeyEvent event) {
		switch(event.getCode()) 
		{
    	case UP:
        	this.setDirection(Direction.UP);
        	break;
    	case DOWN:
        	this.setDirection(Direction.DOWN);
        	break;
    	case LEFT:
        	this.setDirection(Direction.LEFT);
        	break;
    	case RIGHT:
        	this.setDirection(Direction.RIGHT);
        	break;
    	default:
    		break;
		
		}
	}

	@Override
	public void run() 
	{
        while(true) {
            
        	getGrid().move(getDirection(), this);
           
            try {
                Thread.sleep(1000); // pause
            } catch (InterruptedException ex) { }
           
        }
	}
}
