package model.entity;

import java.util.Observable;

import model.Direction;
import model.Grid;

public abstract class Entity extends Observable implements Runnable
{
	private Thread thread;
	private Grid grid;
	private float speed = 1;
	private Direction direction = Direction.NONE;
	
	private int frame = 0;
	
	public Entity(Grid g) 
	{ 
		grid = g; 
	}
	
	public void start() 
	{
       	thread = new Thread(this);
        thread.setDaemon(true);
        thread.start();
    }
	
	@Override
	public void run()
	{
		while(true)
		{
			frame++;
			frame %= 2;
			update();
			getGrid().move(getDirection(), this);
			try { Thread.sleep((long)(1000.0f/speed)); } catch (InterruptedException ex) { }
		}
	}
	
	public abstract void update();
	
	public void setSpeed(float s)
	{
		speed = s;
	}
	
	public int getFrame()
	{
		return frame;
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
	
	public synchronized void refresh()
	{
		setChanged();
		notifyObservers(grid);
	}
}
