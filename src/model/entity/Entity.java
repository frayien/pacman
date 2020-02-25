package model.entity;

import model.Direction;
import model.Grid;

public abstract class Entity implements Runnable
{
	private Grid grid;
	public Entity(Grid g) {grid = g;}
	private Direction direction = Direction.NONE;
	
	private Thread thread;
	
	public void start() 
	{
       	thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }
	
	public Direction getDirection()
	{
		return direction;
	}
	public void setDirection(Direction d)
	{
		direction = d;
	}
	
	public Grid getGrid() { return grid; }
}
