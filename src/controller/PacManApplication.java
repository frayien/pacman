package controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Grid;
import view.GridView;

public class PacManApplication extends Application
{
	private Grid grid;
	private GridView gridView;
	private StackPane root;

	@Override
	public void start(Stage stage) 
	{
		grid = new Grid();
		gridView = new GridView(grid);
		root = new StackPane();
        root.getChildren().add(gridView);
        
        root.setOnKeyPressed(grid.getPlayer());
        grid.getPlayer().start();
        
        Scene scene = new Scene(root, 700, 620);
        stage.setTitle("Pac Man");
        stage.setScene(scene);
        stage.show();
        gridView.requestFocus();
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
