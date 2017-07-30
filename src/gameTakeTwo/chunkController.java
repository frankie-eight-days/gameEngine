package gameTakeTwo;

import java.awt.Graphics;

public class chunkController 
{

	int numberOfChunks = 9;
	double north = Game.HEIGHT*Game.SCALE/2, east = Game.WIDTH*Game.SCALE/2;
	chunk chunks[] = new chunk[numberOfChunks];
	
	public void init()
	{
		for(int i = 0; i < numberOfChunks; i++)
		{
			chunks[i] = new chunk();
			chunks[i].init();
		}
		chunkPlacement();
	}
	
	public void tick(Game game)
	{
		for(int i = 0; i < numberOfChunks; i++)
		{
			chunks[i].tick();
		}
		chunkReplacement(game.p);
	}
	
	public void paint(Graphics g)
	{
		for(int i = 0; i < numberOfChunks; i++)
		{
			chunks[i].paint(g);
		}
	}
	
	private void chunkReplacement(player p)
	{
		north = p.coordY / (Game.HEIGHT*Game.SCALE);
		east = p.coordX / (Game.WIDTH*Game.SCALE);
		
		//System.out.println("Far Up: " + (chunks[1].north - north));
		//System.out.println("Far Down: " + (chunks[7].north - north));
		
		for(int i = 0; i < numberOfChunks; i++)
		{
			if(chunks[i].east - east > 1)
			{
				chunks[i].moveTiles('l', Game.WIDTH*Game.SCALE*3);
			}
			if(chunks[i].east - east < -2)
			{
				chunks[i].moveTiles('r', Game.WIDTH*Game.SCALE*3);
			}
			if(chunks[i].north - north > 1)
			{
				chunks[i].moveTiles('u', Game.HEIGHT*Game.SCALE*3);
			}
			if(chunks[i].north - north < -2)
			{
				chunks[i].moveTiles('d', Game.HEIGHT*Game.SCALE*3);
			}
		}
	}
	
	public void moveChunks(double movementSpeed, char direction)
	{
		switch(direction)
		{
			case 'u':
				for(int i = 0; i < numberOfChunks; i++)
				{
					for(int j = 0; j < chunks[i].numberOfTiles; j++)
					{
						chunks[i].tiles[j].y += movementSpeed;
					}
					chunks[i].y += movementSpeed;
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
	
	private void chunkPlacement()
	{
		for(int i = 0; i < numberOfChunks; i++)
		{
			chunks[i].x = ((i%3)*Game.WIDTH*Game.SCALE)-Game.WIDTH*Game.SCALE;
			chunks[i].y = ((i/3)*Game.HEIGHT*Game.SCALE)-Game.HEIGHT*Game.SCALE;
			chunks[i].makeGrid();
		}
	}
	
}
