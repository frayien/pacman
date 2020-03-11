package view;

import java.awt.Point;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import model.Grid;

public class GridView extends GridPane
{
	public static final double TILE_SIZE = 29;
	private Grid grid;
	
	private static final Image WORLDMAP = new Image("file:ressources/worldMap.png");
	
	public GridView(Grid g)
	{
		super();
		grid = g;
		initialize();
		
	}
	
	@SuppressWarnings("deprecation")
	public void initialize()
	{
		for(int h = 0; h<grid.getHeight(); h++)
		{
			for(int w = 0; w<grid.getWidth(); w++)
			{
				TileView tv = new TileView(h,w);
				tv.setImage(WORLDMAP);
				grid.addObserver(tv);
				tv.update(grid, new Point(h,w));
				this.add(tv, w, h);
			}
		}
	}

}
