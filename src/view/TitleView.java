package view;

import java.util.Observable;
import java.util.Observer;

import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.Grid;
import model.entity.Entity;

public class TitleView extends BorderPane implements Observer
{
	private Grid grid;
	private Text text;
	public TitleView(Grid g)
	{
		super();
		grid = g;
		initialize();
	}
	
	public void initialize()
	{
		grid.addObserver(this);
		text = new Text();
		text.setText("Press P to start");
		text.setFill(Color.WHITESMOKE);
		text.setFont(Font.font("arial", FontWeight.BOLD, FontPosture.REGULAR, 40));
		this.setCenter(text);
	}
	
	@Override
	public void update(Observable ob, Object arg)
	{
		if(!(arg instanceof Character)) return;
		setText("Press P to resume");
		setVisible(!Entity.isRunning());
	}
	
	public void setText(String str)
	{
		text.setText(str);
		show();
	}
	public void show()
	{
		setVisible(true);
	}
	public void hide()
	{
		setVisible(false);
	}

}
