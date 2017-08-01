package gameTakeTwo;

import java.awt.Graphics;

public class chunkController 
{

	int numberOfChunks = 9;	//Only 9 chunks needed. One on screen and 8 surrounding it
	//This instance of north and east show what part of the world the player is currently in
	double north = Game.HEIGHT*Game.SCALE/2, east = Game.WIDTH*Game.SCALE/2;
	chunk chunks[] = new chunk[numberOfChunks];	//Array of chunks
	
	public void init()
	{
		//Loops through all chunks and initilizes them.
		for(int i = 0; i < numberOfChunks; i++)
		{
			chunks[i] = new chunk();
			chunks[i].init();
		}
		chunkPlacement();	// Places the chunks in a grid after they're all set up.
	}
	
	/*	tick
	 * 	This function contains all the information that needs to be updated every tick.
	 * 	Since it's in the chunkController class it'll contain updating information for all chunks.
	 */
	public void tick(Game game)
	{
		//Loops through all chunks and updates them
		for(int i = 0; i < numberOfChunks; i++)
		{
			chunks[i].tick(game.p);
		}
		
		chunkReplacement(game.p);	//Constantly checks to see if chunks need updating
	}
	/*	Paint
	 * 	Where graphics for the chunkcontroller go. This is updated directly in the game loop
	 */
	public void paint(Graphics g)
	{
		for(int i = 0; i < numberOfChunks; i++)
		{
			chunks[i].paint(g);
		}
	}
	/*	chunkReplacement
	 * 	Checks to see if a chunk has become inactive. Then issues a command to move it to where it is likley to become
	 * 	active.
	 * 
	 * 	@param player: this function needs to know where the player's map coordinates are in order to determine
	 * 	if the chunk will be active.
	 */
	private void chunkReplacement(player p)
	{
		
		north = p.coordY / (Game.HEIGHT*Game.SCALE);
		east = p.coordX / (Game.WIDTH*Game.SCALE);
		
		for(int i = 0; i < numberOfChunks; i++)
		{
			if(chunks[i].east - east > 1)
			{
				chunks[i].moveTiles('l', Game.WIDTH*Game.SCALE*3);
				chunks[i].setTiles();
			}
			if(chunks[i].east - east < -2)
			{
				chunks[i].moveTiles('r', Game.WIDTH*Game.SCALE*3);
				chunks[i].setTiles();
			}
			if(chunks[i].north - north > 1)
			{
				chunks[i].moveTiles('u', Game.HEIGHT*Game.SCALE*3);
				chunks[i].setTiles();
			}
			if(chunks[i].north - north < -2)
			{
				chunks[i].moveTiles('d', Game.HEIGHT*Game.SCALE*3);
				chunks[i].setTiles();
			}
		}
	}
	
	/*	moveChunks
	 * 	This function is called when the player has met the screen border, then the chunks move
	 * 	instead of the player.
	 * 	The chunks move according to the direction needed.
	 * 	
	 * 	@param movementSpeed: this is the movementSpeed of the player, which inherently is the speed of the tiles
	 * 	@param direction: a char that tells what direction the tiles need to move.
	 * 					  u = up
	 * 					  d = down
	 * 					  l = left
	 * 					  r = right
	 */
	public void moveChunks(double movementSpeed, char direction)
	{
		switch(direction)	//Depending on the direction tiles need to be moved in different directions
		{
			case 'u':
				for(int i = 0; i < numberOfChunks; i++)		//Loops through all chunks
				{
					for(int j = 0; j < chunks[i].numberOfTiles; j++)	//Loops through all tiles in chunks
					{
						chunks[i].tiles[j].y += movementSpeed;			//Then moves the tiles
					}
					chunks[i].y += movementSpeed;			//This updates the coordinate of the chunk.
				}
			break;
			case 'd':
				for(int i = 0; i < numberOfChunks; i++)
				{
					for(int j = 0; j < chunks[i].numberOfTiles; j++)
					{
						chunks[i].tiles[j].y -= movementSpeed;
					}
					chunks[i].y -= movementSpeed;
				}
			break;
			case 'r':
				for(int i = 0; i < numberOfChunks; i++)
				{
					for(int j = 0; j < chunks[i].numberOfTiles; j++)
					{
						chunks[i].tiles[j].x -= movementSpeed;
					}
					chunks[i].x -= movementSpeed;
				}
			break;
			case 'l':
				for(int i = 0; i < numberOfChunks; i++)
				{
					for(int j = 0; j < chunks[i].numberOfTiles; j++)
					{
						chunks[i].tiles[j].x += movementSpeed;
					}
					chunks[i].x += movementSpeed;
				}
			break;
		}
	}
	
	/*	chunkPlacement
	 * 	Function that places the chunks into their grid. 
	 * This function is similar to tilePlacement in chunk class.
	 */
	
	private void chunkPlacement()
	{
		for(int i = 0; i < numberOfChunks; i++)	//Loops through all chunks
		{
			//The following line assigns the chunk's x coordinate
			//Literal 3 is simply how many rows there are
			//The remainder of i%3 is then multiplied by the size of the chunk
			//Then shifted left one spot so that the entire array of tiles is centered
			chunks[i].x = ((i%3)*Game.WIDTH*Game.SCALE)-Game.WIDTH*Game.SCALE;
			
			//The following line assigns the chunk's y coordinate
			//Literal 3 is simply how many rows there are
			//The remainder of i%3 is then multiplied by the size of the chunk
			//Then shifted up one spot so that the entire array of tiles is centered
			chunks[i].y = ((i/3)*Game.HEIGHT*Game.SCALE)-Game.HEIGHT*Game.SCALE;
			
			chunks[i].makeGrid(); //The chunks are then issued a command to set up their tiles now that they have coordinates
			chunks[i].setTiles(); //This call loads the map from text files
		}
	}
	
}
