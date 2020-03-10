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
import java.util.Map.Entry;
import java.util.Observable;
import model.entity.Blinky;
import model.entity.Clyde;

import model.entity.Entity;
import model.entity.Ghost;
import model.entity.Inky;
import model.entity.PacMan;
import model.entity.Pinky;
import model.tile.Path;
import model.tile.Tile;
import model.tile.Wall;
import model.tileentity.PacGum;

public class Grid extends Observable {

    private static final String MAP_PATH = "ressources/map.txt";
    private static Entity player;
    private int width = 10;
    private int height = 10;

    private Tile tileMap[];
    private Map<Entity, Point> entityMap = new HashMap<>();

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
                        tileMap[i * height + j] = new Wall();
                    } else {
                        tileMap[i * height + j] = new Path();
                    }
                    if (linesplit[j].contains("g")) {
                        tileMap[i * height + j].setTileEntity(new PacGum());
                    }
                    if (linesplit[j].contains("f")) {
                        int gid = Integer.parseInt("" + linesplit[j].charAt(linesplit[j].length() - 1));
                        switch (gid) {
                            case 1:
                                //Clyde
                                entityMap.put(new Clyde(this), new Point(i, j));
                                break;
                            case 2:
                                //Pinky
                                entityMap.put(new Pinky(this), new Point(i, j));
                                break;
                            case 3:
                                entityMap.put(new Inky(this), new Point(i, j));
                                break;
                            default:
                            case 0:
                                //Blinky
                                entityMap.put(new Blinky(this), new Point(i, j));
                                break;

                        }

                    }
                    if (linesplit[j].contains("m")) {
                        player = new PacMan(this);
                        entityMap.put(player, new Point(i, j));
                    }
                }

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                br.close();
            } catch (IOException e) {
            } catch (NullPointerException e) {
            }
        }

        for (Entity e : entityMap.keySet()) {
            e.start();
        }
    }

    public void move(Direction dir, Entity e) {
        Point p = entityMap.get(e);
        Point pp = (Point) p.clone();
        switch (dir) {
            case UP:
                p.x = (p.x - 1 + height) % height;
                break;
            case DOWN:
                p.x = (p.x + 1) % height;
                break;
            case LEFT:
                p.y = (p.y - 1 + width) % width;
                break;
            case RIGHT:
                p.y = (p.y + 1) % width;
                break;
        }
        if (isWall(p.x, p.y)) {
            p.x = pp.x;
            p.y = pp.y;
        }
        synchronized (this) {
            setChanged();
            notifyObservers(p);
            setChanged();
            notifyObservers(pp);
        }
    }

    private Tile getTile(int h, int w) {
        return tileMap[((h % height + height) % height) * height + ((w % width + width) % width)];
    }

    public boolean isWall(int h, int w) {
        return getTile(h, w) instanceof Wall;
    }

    public boolean isPath(int h, int w) {
        return getTile(h, w) instanceof Path;
    }

    public boolean isWall(int h, int w, Direction dir) {
        switch (dir) {
            case UP:
                return isWall(h - 1, w);
            case DOWN:
                return isWall(h + 1, w);
            case LEFT:
                return isWall(h, w - 1);
            case RIGHT:
                return isWall(h, w + 1);
            default:
                return isWall(h, w);
        }
    }

    public boolean isPath(int h, int w, Direction dir) {
        switch (dir) {
            case UP:
                return isPath(h - 1, w);
            case DOWN:
                return isPath(h + 1, w);
            case LEFT:
                return isPath(h, w - 1);
            case RIGHT:
                return isPath(h, w + 1);
            default:
                return isPath(h, w);
        }
    }

    public boolean hasEntity(int h, int w) {
        return entityMap.containsValue(new Point(h, w));
    }

    public Entity getEntity(Point p) {
        for (Entry<Entity, Point> e : entityMap.entrySet()) {
            if (e.getValue().equals(p)) {
                return e.getKey();
            }
        }
        return null;
    }

    public Entity getEntity(int h, int w) {
        return getEntity(new Point(h, w));
    }

    public Point getPosition(Entity e) {
        return entityMap.get(e);
    }

    public int getDistanceFromPacMan(Point p) {
        Point positionPac = getPosition(player);
        return Math.abs(p.x - positionPac.x) + Math.abs(p.y - positionPac.y);
    }
    
    public int getDistanceFromPoint(Point p1,Point p2) {
        return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y);
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

}
