package view;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import model.Grid;

public class TileView extends ImageView implements Observer
{
	private int width;
	private int height;
	public TileView(int h, int w)
	{
		super();
		height = h;
		width = w;
		this.setViewport(new Rectangle2D(0,0,32,32));
	}

	@Override
	public void update(Observable o, Object arg) {
		Grid g = (Grid) o;
		Point p = (Point) arg;
		
		if(p.x != height || p.y != width) return;
		
		if(g.isWall(height, width))
		{
			this.setViewport(new Rectangle2D(32,32,32,32));
		}
		else this.setVisible(false);
			
    }
}
