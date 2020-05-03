package controller;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import model.Grid;
import model.entity.PacMan;
import view.EntitiesView;
import view.GUIView;
import view.GridView;
import view.GumsView;
import view.TitleView;

public class PacManApplication extends Application
{
	private static Grid grid;
	private GridView gridView;
	private GumsView gumView;
	private EntitiesView entityView;
	private BorderPane root;
	private StackPane game;
	private GUIView gui;
	private TitleView titleView;

	@Override
	public void start(Stage stage) 
	{
            root = new BorderPane();
            root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
            PacMan.setRoot(root);

            game = new StackPane();

            grid = new Grid();
            gridView = new GridView(grid);
            entityView = new EntitiesView(grid);
            gumView = new GumsView(grid);	
            titleView = new TitleView(grid);

            game.getChildren().add(gridView);
            game.getChildren().add(gumView);
            game.getChildren().add(entityView);
            game.getChildren().add(titleView);

            gui = new GUIView(grid);
            grid.addObserver(gui);

            root.setCenter(game);
            root.setTop(gui);
        
            Scene scene = new Scene(root, root.getMaxWidth(), root.getMaxHeight());
            stage.setTitle("Pac Man");
            stage.setScene(scene);
            stage.show();
            root.requestFocus();
	}
	
	public static void main(String[] args) 
	{
		launch(args);
	}
        
        public TitleView getTitleView() {
            return titleView;
        }
}
