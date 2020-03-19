package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Observable;
import java.util.Set;

import javafx.application.Platform;
import model.entity.Blinky;
import model.entity.Clyde;
import model.entity.Entity;
import model.entity.Inky;
import model.entity.PacMan;
import model.entity.Pinky;
import model.tile.Path;
import model.tile.Tile;
import model.tile.Wall;
import model.tileentity.PacGum;
import utils.Vector2f;
import utils.Vector2i;

@SuppressWarnings("deprecation")
public class Grid extends Observable {

    private static final String MAP_PATH = "ressources/map.txt";
    public static Entity player;
    public static Entity blinky;
    private int width = 10;
    private int height = 10;

    private Tile tileMap[];
    private Map<Entity, Vector2f> entityMap = new HashMap<>();

    public Grid() {
        buildGridFromFile(MAP_PATH);
    }

    public void buildGridFromFile(String path) {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
            String line = br.readLine();
            String[] couple = line.split(" ");

            height = Integer.parseInt(couple[0]);
            width = Integer.parseInt(couple[1]);

            tileMap = new Tile[width * height];
            entityMap.clear();

            for (int i = 0; i < height; i++) {
                line = br.readLine();
                String[] linesplit = line.split(" ");
                for (int j = 0; j < width; j++) {
                    if (linesplit[j].contains("w")) {
                        tileMap[i * width + j] = new Wall();
                    } else {
                        tileMap[i * width + j] = new Path();
                    }
                    if (linesplit[j].contains("g")) {
                        tileMap[i * width + j].setTileEntity(new PacGum());
                    }
                    if (linesplit[j].contains("f")) {
                        int gid = Integer.parseInt("" + linesplit[j].charAt(linesplit[j].length() - 1));
                        switch (gid) {
                            case 1:
                                //Clyde
                                entityMap.put(new Clyde(this), new Vector2f(i, j));
                                break;
                            case 2:
                                //Pinky
                                entityMap.put(new Pinky(this), new Vector2f(i, j));
                                break;
                            case 3:
                                //Inky
                                entityMap.put(new Inky(this), new Vector2f(i, j));
                                break;
                            default:
                            case 0:
                                //Blinky
                                blinky = new Blinky(this);
                                entityMap.put(blinky, new Vector2f(i, j));
                                break;

                        }

                    }
                    if (linesplit[j].contains("m")) {
                        player = new PacMan(this);
                        entityMap.put(player, new Vector2f(i, j));
                    }
                }

            }
        } 
        catch (FileNotFoundException e) { e.printStackTrace(); } catch (IOException e) { e.printStackTrace(); } 
        finally { try { br.close(); } catch (IOException e) { } catch (NullPointerException e) { } }
        
        for(Entity e : entityMap.keySet()) e.start();
	}
	
	public void move(Direction dir, Entity e, long time)
	{
		Vector2f p = entityMap.get(e);
		Vector2f mov = new Vector2f(0,0);
		switch(dir) 
		{
    	case UP:
    		mov.x = -1;
    		break;
    	case DOWN:
    		mov.x = 1;
    		break;
    	case LEFT:
    		mov.y = -1;
    		break;
    	case RIGHT:
    		mov.y = 1;
    		break;
		default:
			break;
		}
		if(isWall(p.clone().add(mov).toVector2i()))
		{
			mov.x = 0;
			mov.y = 0;
		}
		long pas = 1000/60;
		long iter = time/pas;
		mov.mult(1.f/(float)iter);
		for(long i = 0; i < iter; i++)
		{
			p.add(mov);
			p.x = (p.x+height+0.5f)%height -0.5f;
			p.y = (p.y+width+0.5f)%width - 0.5f;
			Platform.runLater(()->e.refresh());
			try { Thread.sleep(pas); } catch (InterruptedException e1) { }
		}
		
	}
	
	private Tile getTile(int h, int w)
	{
		return tileMap[((h%height+height)%height )  *width+((w%width+width)%width)];
	}
	
	public boolean isWall(int h, int w)
	{
		return getTile(h, w) instanceof Wall;
	}
	
	public boolean isWall(Vector2i pos)
	{
		return isWall(pos.x,pos.y);
	}
	
	public boolean isPath(int h, int w)
	{
		return getTile(h, w) instanceof Path;
	}
	
	public boolean isPath(Vector2i pos)
	{
		return isPath(pos.x,pos.y);
	}
	
	public boolean isWall(int h, int w, Direction dir)
	{
		switch(dir)
		{
		case UP:
			return isWall(h-1,w);
		case DOWN:
			return isWall(h+1,w);
		case LEFT:
			return isWall(h,w-1);
		case RIGHT:
			return isWall(h,w+1);
		default:
			return isWall(h,w);
		}
	}
	
	public boolean isWall(Vector2i pos, Direction dir)
	{
		return isWall(pos.x,pos.y,dir);
	}
	
	public boolean isPath(int h, int w, Direction dir)
	{
		switch(dir)
		{
		case UP:
			return isPath(h-1,w);
		case DOWN:
			return isPath(h+1,w);
		case LEFT:
			return isPath(h,w-1);
		case RIGHT:
			return isPath(h,w+1);
		default:
			return isPath(h,w);
		}
	}
	
	public boolean isPath(Vector2i pos, Direction dir)
	{
		return isPath(pos.x,pos.y,dir);
	}
	
	public boolean hasEntity(int h, int w)
	{
		return entityMap.containsValue(new Vector2f(h,w));
	}
	
	public Entity getEntity(Vector2f p)
	{
		for(Entry<Entity,Vector2f> e : entityMap.entrySet())
		{
			if(e.getValue().equals(p)) return e.getKey();
		}
		return null;
	}
	
	public Entity getEntity(float h, float w)
	{
		return getEntity(new Vector2f(h,w));
	}
	
	public Set<Entity> getEntities()
	{
		return entityMap.keySet();
	}
	
	public Vector2f getPosition(Entity e)
	{
		return entityMap.get(e);
	}
	
    public Vector2f getPacManPosition() 
    {
        return getPosition(player);
    }
	
	public int getHeight() { return height; }
	public int getWidth() { return width; }
}
