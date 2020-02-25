package model.tile;

import model.tileentity.TileEntity;

public abstract class Tile 
{
	private TileEntity tileEntity = null;

	public void setTileEntity(TileEntity te)
	{
		tileEntity = te;
	}

}
