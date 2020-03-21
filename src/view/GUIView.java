package view;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Grid;

public class GUIView extends Text implements Observer
{
	private Grid grid;
	
	public GUIView(Grid grid)
	{
		super("niveau : 1   score : 0");
		this.setFill(Color.WHITE);
		this.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 20));
		this.grid = grid;
	}

	@Override
	public void update(Observable arg0, Object arg) 
	{
		if(!(arg instanceof String)) return;
		this.setText("niveau : "+grid.getLevel()+"   score : "+grid.getScore());
	}
	

}
