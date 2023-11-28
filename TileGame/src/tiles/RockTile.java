package tiles;

import gfx.Assets;

public class RockTile extends Tile
{

	public RockTile(int id) {
		super(Assets.tile3, id);
		// TODO Auto-generated constructor stub
	}

	//Overwriting
	public boolean isSolid()
	{
		return true;
	}
	
}
