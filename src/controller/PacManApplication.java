package controller;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import model.Grid;
import model.entity.PacMan;
import view.EntitiesView;
import view.GridView;

public class PacManApplication extends Application
{
	private Grid grid;
	private GridView gridView;
	private EntitiesView entityView;
	private StackPane root;

	@Override
	public void start(Stage stage) 
	{
		root = new StackPane();
		root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		PacMan.setRoot(root);
		
		grid = new Grid();
		gridView = new GridView(grid);
		entityView = new EntitiesView(grid);
		
        root.getChildren().add(gridView);
        root.getChildren().add(entityView);
        
        Scene scene = new Scene(root, 700, 620);
        stage.setTitle("Pac Man");
        stage.setScene(scene);
        stage.show();
        root.requestFocus();
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
}
