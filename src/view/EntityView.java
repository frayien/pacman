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

public class EntityView extends ImageView implements Observer
{
	public EntityView()
	{
		super();
		this.setViewport(new Rectangle2D(0,0,32,32));
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Entity entity = (Entity) o;
		Grid grid = (Grid) arg;
		Point p = grid.getPosition(entity);
		this.setY(p.getX());
		this.setX(p.getY());
		
		if(entity instanceof PacMan)
		{
			this.setVisible(true);
			switch(entity.getDirection())
			{
			case UP:
				this.setViewport(new Rectangle2D((entity.getFrame() + 10)*32, 3*32,32,32));
				break;
			case DOWN:
				this.setViewport(new Rectangle2D((entity.getFrame() + 10)*32, 1*32,32,32));
				break;
			case LEFT:
				this.setViewport(new Rectangle2D((entity.getFrame() + 10)*32, 2*32,32,32));
				break;
			default:
				this.setViewport(new Rectangle2D((entity.getFrame() + 10)*32, 0*32,32,32));
				break;
			}
		}
		else // instanceof Ghost
		{
			Ghost gh = (Ghost) entity;
			this.setVisible(true);
			switch(entity.getDirection())
			{
			case UP:
				this.setViewport(new Rectangle2D((gh.getFrame() + gh.getId()*2)*32, 3*32,32,32));
				break;
			case DOWN:
				this.setViewport(new Rectangle2D((gh.getFrame() + gh.getId()*2)*32, 1*32,32,32));
				break;
			case LEFT:
				this.setViewport(new Rectangle2D((gh.getFrame() + gh.getId()*2)*32, 2*32,32,32));
				break;
			default:
				this.setViewport(new Rectangle2D((gh.getFrame() + gh.getId()*2)*32, 0*32,32,32));
				break;
			}
		}
    }
}
