package view;

import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import model.Grid;
import model.entity.Entity;

public class EntitiesView extends Pane
{
	private Grid grid;
	
	private static final Image ENTITYMAP = new Image("file:ressources/pacmanMap.png");
	
	public EntitiesView(Grid grid)
	{
		super();
		this.grid = grid;
		initialize();
	}
	
	@SuppressWarnings("deprecation")
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
