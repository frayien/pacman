package model;

import java.util.Map;

import com.sun.javafx.scene.paint.GradientUtils.Point;

import model.entity.Entity;
import model.tile.Tile;

public class Grid {
	
	public static final int SIZE_X = 10;
	public static final int SIZE_Y = 10;
	
	private Tile tileMap[];
	private Map<Entity, Point> entityMap;
	
	public Grid() {
		
	}
	
	
}
