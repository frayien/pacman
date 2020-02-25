package controller;

import model.Grid;
import view.ApplicationView;

public class InputHandler 
{
	private Grid grid;
	
	public InputHandler(String[] args) 
	{
		grid = new Grid();
		ApplicationView.setGrid(grid);
		ApplicationView.uwu(args);
		ApplicationView.getRoot().setOnKeyPressed(grid.getPlayer());
	}
	
	public static void main(String[] args) 
	{
		InputHandler ih = new InputHandler(args);
	}

}
