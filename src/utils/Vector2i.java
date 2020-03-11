package utils;

public class Vector2i implements Cloneable
{
	public int x = 0;
	public int y = 0;
	
	public Vector2i()
	{
		
	}
	
	public Vector2i(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public int distanceTo(Vector2i other)
	{
		return distanceTo(other.x, other.y);
	}
	public int distanceTo(int x, int y)
	{
		return Math.abs(x - this.x) + Math.abs(y - this.y);
	}
	
	public Vector2i add(Vector2i other)
	{
		return add(other.x, other.y);
	}
	
	public Vector2i add(int x, int y)
	{
		this.x += x;
		this.y += y;
		return this;
	}
	
	public Vector2i mult(int a)
	{
		x *= a;
		y *= a;
		return this;
	}
	
	public float dot(Vector2i other)
	{
		return x*other.y + y*other.x;
	}
	
	@Override
	public Vector2i clone()
	{
		return new Vector2i(x,y);
	}
	
	public void copy(Vector2i other)
	{
		x = other.x;
		y = other.y;
	}
	
	public boolean equals(Vector2i other)
	{
		return x == other.x && y == other.y;
	}
	
	public Vector2f toVector2f()
	{
		return new Vector2f(x, y);
	}
	
	@Override
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
}
