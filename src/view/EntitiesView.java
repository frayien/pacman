package view;

import javafx.scene.Group;
import javafx.scene.image.Image;
import model.Grid;
import model.entity.Entity;

public class EntitiesView extends Group
{
	private Grid grid;
	
	private static final Image ENTITYMAP = new Image("file:ressources/pacmanMap.png");
	
	public EntitiesView(Grid grid)
	{
		super();
		this.grid = grid;
		initialize();
	}
	
	public void initialize()
	{
        for(Entity e : grid.getEntities())
        {
        	EntityView ev = new EntityView();
        	ev.setImage(ENTITYMAP);
        	e.addObserver(ev);
        	getChildren().add(ev);
        }
        
	}
}
