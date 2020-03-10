package view;

import java.awt.Point;
import java.util.Observable;
import java.util.Observer;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import model.Grid;
import model.entity.Entity;
import model.entity.Ghost;
import model.entity.PacMan;

public class TileView extends ImageView implements Observer
{
	private int width;
	private int height;
	
	private static Rectangle2D[] viewports = 
		{
			new Rectangle2D(7*32, 1*32,32,32), //
			
			new Rectangle2D(6*32, 2*32,32,32), //U
			
			new Rectangle2D(6*32, 0*32,32,32), //D
			new Rectangle2D(6*32, 1*32,32,32), //UD
			
			new Rectangle2D(9*32, 0*32,32,32), //L
			new Rectangle2D(5*32, 2*32,32,32), //LU
			new Rectangle2D(5*32, 0*32,32,32), //LD
			new Rectangle2D(5*32, 1*32,32,32), //LDU
			
			new Rectangle2D(7*32, 0*32,32,32), //R
			new Rectangle2D(3*32, 2*32,32,32), //RU
			new Rectangle2D(3*32, 0*32,32,32), //RD
			new Rectangle2D(3*32, 1*32,32,32), //RUD
			new Rectangle2D(8*32, 0*32,32,32), //RL
			new Rectangle2D(4*32, 2*32,32,32), //RLU
			new Rectangle2D(4*32, 0*32,32,32), //RLD
			new Rectangle2D(4*32, 1*32,32,32), //RLDU
			
			new Rectangle2D(2*32, 2*32,32,32), //UL ?
			new Rectangle2D(0*32, 2*32,32,32), //UR ?
			new Rectangle2D(2*32, 0*32,32,32), //DL ?
			new Rectangle2D(0*32, 0*32,32,32), //DR ?
			
			new Rectangle2D(2*32, 1*32,32,32), //UDL ?
			new Rectangle2D(0*32, 1*32,32,32), //UDR ?
			new Rectangle2D(1*32, 2*32,32,32), //LRU ?
			new Rectangle2D(1*32, 0*32,32,32), //LRD ?
			new Rectangle2D(1*32, 1*32,32,32)  //LRUD ?
		};
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
			int id = 0;
			if(height-1 >= 0 && g.isWall(height-1, width)) id += 1;
			if(height+1 < g.getHeight() && g.isWall(height+1, width)) id += 2;
			if(width-1 >= 0 && g.isWall(height, width-1)) id += 4;
			if(width+1 < g.getWidth() && g.isWall(height, width+1)) id += 8;
			
			switch(id)
			{
			case 5:
				if(g.isWall(height-1, width-1)) id = 16;
				break;
			case 9:
				if(g.isWall(height-1, width+1)) id = 17;
				break;
			case 6:
				if(g.isWall(height+1, width-1)) id = 18;
				break;
			case 10:
				if(g.isWall(height+1, width+1)) id = 19;
				break;
				
			case 7:
				if(g.isWall(height-1, width-1) && g.isWall(height+1, width-1)) id = 20;
				break;
			case 11:
				if(g.isWall(height-1, width+1) && g.isWall(height+1, width+1)) id = 21;
				break;
			case 13:
				if(g.isWall(height-1, width-1) && g.isWall(height-1, width+1)) id = 22;
				break;
			case 14:
				if(g.isWall(height+1, width+1) && g.isWall(height+1, width-1)) id = 23;
				break;
				
			case 15:
				if(g.isWall(height+1, width+1) && g.isWall(height+1, width-1) &&
					g.isWall(height-1, width+1) && g.isWall(height-1, width-1)) id = 24;
				break;
			}
			
			this.setViewport(viewports[id]);
		}
		else setVisible(false);
    }
}
