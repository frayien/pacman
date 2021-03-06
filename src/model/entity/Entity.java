package model.entity;

import java.util.Observable;

import model.Direction;
import model.Grid;

@SuppressWarnings("deprecation")
public abstract class Entity extends Observable implements Runnable
{
	private Thread thread;
	private Grid grid; // use getGrid() if needed
	private float speed = 1;
	protected Direction direction = Direction.NONE;
    protected static int frameCount;
    public static int ghostsAfraidFrameCount = 0;
    
    private static boolean running = false;

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
			while(!isRunning()) { try { Thread.sleep(1); } catch (InterruptedException e) { } }
			frame++;
			frame %= 2;
			update();
			getGrid().move(getDirection(), this, (long) (1000.f/speed));
		}
	}
	
	public abstract void update();
	
	public void setSpeed(float s)
	{
		speed = s;
	}
	
	public float getSpeed()
	{
		return speed;
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
	
	public void refresh()
	{
		setChanged();
		notifyObservers(grid);
	}
	
	public static boolean isRunning()
	{
		return running;
	}
	public static void setRunning(boolean r)
	{
		running = r;
	}
}
