package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import model.Grid;

public class ApplicationView extends Application
{
	private static Grid grid;
	private static GridView gridView;
	private static StackPane root;
	
	public static void setGrid(Grid g)
	{
		grid = g;
	}
	
	public static void uwu(String... args)
	{
		launch(args);
	}

	@Override
	public void start(Stage stage) 
	{
		gridView = new GridView(grid);
		root = new StackPane();
        root.getChildren().add(gridView);
        Scene scene = new Scene(root, 500, 450);
        stage.setTitle("Pac Man");
        stage.setScene(scene);
        stage.show();
        gridView.requestFocus();
	}
	
	public static StackPane getRoot()
	{
		return root;
	}

}
