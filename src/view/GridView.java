package view;

import java.awt.Point;

import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import model.Grid;

public class GridView extends GridPane
{
	private Grid grid;
	
	private static final Image ENTITYMAP = new Image("file:ressources/pacmanMap.png");
	private static final Image WORLDMAP = new Image("file:ressources/worldMap.png");
	
	public GridView(Grid g)
	{
		super();
		grid = g;
		initialize();
		
	}
	
	public void initialize()
	{
		for(int h = 0; h<grid.getHeight(); h++)
		{
			for(int w = 0; w<grid.getWidth(); w++)
			{
				TileView tv = new TileView(h,w);
				tv.setImage(grid.hasEntity(h, w) ? ENTITYMAP : WORLDMAP);
				grid.addObserver(tv);
				tv.update(grid, new Point(h,w));
				this.add(tv, w, h);
			}
		}
	}

}
