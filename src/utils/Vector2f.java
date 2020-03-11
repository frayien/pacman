package utils;

public class Vector2f implements Cloneable
{
	public float x = 0;
	public float y = 0;
	
	public Vector2f()
	{
		
	}
	
	public Vector2f(float x, float y)
	{
		this.x = x;
		this.y = y;
	}
	
	public float distanceTo(Vector2f other)
	{
		return (float) Math.sqrt((x - other.x)*(x - other.x) + (y - other.y)*(y - other.y));
	}
	
	public Vector2f add(Vector2f other)
	{
		x += other.x;
		y += other.y;
		return this;
	}
	
	public Vector2f mult(float a)
	{
		x *= a;
		y *= a;
		return this;
	}
	
	public float dot(Vector2f other)
	{
		return x*other.y + y*other.x;
	}
	
	@Override
	public Vector2f clone()
	{
		return new Vector2f(x,y);
	}
	
	public void copy(Vector2f other)
	{
		x = other.x;
		y = other.y;
	}
	
	public boolean equals(Vector2f other)
	{
		return x == other.x && y == other.y;
	}
	
	public Vector2i toVector2i()
	{
		return new Vector2i((int) x, (int) y);
	}
	
	@Override
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
}
