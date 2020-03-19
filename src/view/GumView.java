package view;

import java.util.Observable;
import java.util.Observer;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import model.Grid;
import model.tileentity.PacGum;
import model.tileentity.SuperPacGum;
import utils.Vector2i;

public class GumView extends ImageView implements Observer
{
	private int width;
	private int height;
	
	private static Rectangle2D[] viewports = 
		{
			new Rectangle2D(9*32, 2*32,32,32), //g
			
			new Rectangle2D(7*32, 2*32,32,32), //sg1
			
			new Rectangle2D(8*32, 2*32,32,32) //sg2
		};
	public GumView(int h, int w)
	{
		super();
		height = h;
		width = w;
		this.setViewport(new Rectangle2D(0,0,32,32));
		this.setFitHeight(GridView.TILE_SIZE);
		this.setFitWidth(GridView.TILE_SIZE);
	}

	@Override
	public void update(Observable o, Object arg) {
		Grid g = (Grid) o;
		Vector2i p = (Vector2i) arg;
		
		if(p.x != height || p.y != width) return;
		
		if(g.getTile(height, width).hasTileEntity())
		{
			int id = 0;
			
			if(g.getTile(height, width).getTileEntity() instanceof PacGum) id = 0;
			else if(g.getTile(height, width).getTileEntity() instanceof SuperPacGum)
			{
				id = 1;
			}
			
			this.setViewport(viewports[id]);
		}
		else setVisible(false);
    }
}