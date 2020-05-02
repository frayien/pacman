package view;

import java.util.Observable;
import java.util.Observer;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import model.Grid;
import model.entity.Entity;
import model.entity.Ghost;
import model.entity.PacMan;
import utils.Vector2f;

@SuppressWarnings("deprecation")
public class EntityView extends ImageView implements Observer
{
	public EntityView()
	{
		super();
		this.setViewport(new Rectangle2D(0,0,32,32));
		this.setFitHeight(GridView.TILE_SIZE);
		this.setFitWidth(GridView.TILE_SIZE);
	}
	
	@Override
	public void update(Observable o, Object arg) {
		Entity entity = (Entity) o;
		Grid grid = (Grid) arg;
		Vector2f p = grid.getPosition(entity);
                if(p == null) return;
		this.setTranslateX(GridView.TILE_SIZE*p.y);
		this.setTranslateY(GridView.TILE_SIZE*p.x);
		
		if(entity instanceof PacMan)
		{
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
                        if(gh.isDead())
                        {
                            this.setViewport(new Rectangle2D((12)*32, 2*32,32,32));
                            return;
                        }
                        if(Entity.ghostsAfraidFrameCount > 0 && Entity.ghostsAfraidFrameCount < 25)
                        {
                            this.setViewport(new Rectangle2D((gh.getFrame() + 12)*32, 0*32,32,32));
                            return;
                        }
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
