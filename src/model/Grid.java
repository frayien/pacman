package model;

import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;

import model.entity.Entity;
import model.entity.Ghost;
import model.entity.PacMan;
import model.tile.Path;
import model.tile.Tile;
import model.tile.Wall;
import model.tileentity.PacGum;

public class Grid extends Observable 
{
	private static final String MAP_PATH = "ressources/map.txt";
	private int width = 10;
	private int height = 10;
	
	private Tile tileMap[];
	private Map<Entity, Point> entityMap = new HashMap<>();
	
	private PacMan player = new PacMan(this);
	
	public Grid() 
	{
		buildGridFromFile(MAP_PATH);
	}

	public void buildGridFromFile(String path)
	{
		BufferedReader br = null;
        try
        {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
            String line = br.readLine();
            String[] couple = line.split(" ");
            
            height = Integer.parseInt(couple[0]);
            width = Integer.parseInt(couple[1]);
            
            tileMap = new Tile[width*height];
            entityMap.clear();
            
            for (int i = 0; i<height; i++) 
            {
            	line = br.readLine();
               String[] linesplit = line.split(" ");
                for(int j = 0; j<width; j++)
                {
                	if(linesplit[j].contains("w"))
                	{
                		tileMap[i*height+j] = new Wall();
                	}
                	else
                	{
                		tileMap[i*height+j] = new Path();
                	}
                	if(linesplit[j].contains("g"))
                	{
                		tileMap[i*height+j].setTileEntity(new PacGum());
                	}
                	if(linesplit[j].contains("f"))
                	{
                		entityMap.put(new Ghost(this), new Point(i,j));
                	}
                	if(linesplit[j].contains("m"))
                	{
                		entityMap.put(player, new Point(i,j));
                	}
                }

            }

        } 
        catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } 
        finally { try { br.close(); } catch (IOException e) { } catch (NullPointerException e) { } }
	}
	
	public void move(Direction dir, Entity e)
	{
		Point p = entityMap.get(e);
		switch(dir) 
		{
    	case UP:
    		p.y = (p.y-1+height)%height;
    		break;
    	case DOWN:
    		p.y = (p.y+1)%height;
    		break;
    	case LEFT:
    		p.x = (p.x-1+width)%width;
    		break;
    	case RIGHT:
    		p.x = (p.x+1)%width;
    		break;
		}
		setChanged(); 
		notifyObservers(p);
	}
	
	private Tile getTile(int h, int w)
	{
		return tileMap[((h%height+height)%height )  *height+((w%width+width)%width)];
	}
	
	public boolean isWall(int h, int w)
	{
		return getTile(h, w) instanceof Wall;
	}
	
	public boolean isPath(int h, int w)
	{
		return getTile(h, w) instanceof Path;
	}
	
	public boolean hasEntity(int h, int w)
	{
		return entityMap.containsValue(new Point(h,w));
	}
	
	public PacMan getPlayer()
	{
		return player;
	}
	
	public int getHeight() { return height; }
	public int getWidth() { return width; }
	
}
