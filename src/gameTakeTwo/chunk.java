package gameTakeTwo;

import java.awt.Color;
import java.awt.Graphics;

public class chunk 
{

	int x = 0, y = 0;
	int north = 0, east = 0;
	int numberOfTiles = 1200;
	int chunkWidth, chunkHeight;
	
	tile tiles[] = new tile[numberOfTiles];
	
	public void init()
	{
		for(int i = 0; i < numberOfTiles; i++)
		{
			tiles[i] = new tile();
			tiles[i].tileNumber = i;
		}
		
		makeGrid();
	}
	
	public void tick()
	{
		for(int i = 0; i < numberOfTiles; i++)
		{
			tiles[i].tick();
		}
	}
	
	public void paint(Graphics g)
	{
		for(int i = 0; i < numberOfTiles; i++)
		{
			tiles[i].paint(g);
		}
		g.setColor(Color.red);
		g.drawRect(tiles[0].x, tiles[0].y, chunkWidth, chunkHeight);
		
		g.drawString("North: " + north, tiles[0].x + 300, tiles[0].y + 225);
		g.drawString("East: " + east, tiles[0].x + 300, tiles[0].y + 235);
	}
	
	public void makeGrid()
	{
		for(int i = 0; i < numberOfTiles; i++)
		{
			tiles[i].x = i%(Game.WIDTH*Game.SCALE/tiles[i].width)*tiles[i].height;
			tiles[i].y = i/(Game.WIDTH*Game.SCALE/tiles[i].width)*tiles[i].width;
			tiles[i].x += x;
			tiles[i].y += y;
		}
		chunkWidth = Game.WIDTH*Game.SCALE;
		chunkHeight = Game.HEIGHT*Game.SCALE;
		
		north = y/(Game.HEIGHT*Game.SCALE);
		east = x/(Game.WIDTH*Game.SCALE);
	}
	
	public void moveTiles(char direction, int amount)
	{
		switch(direction)
		{
		case 'u':
			for(int i = 0; i < numberOfTiles; i++)
			{
				tiles[i].y -= amount;
			}
			north -= 3;
		break;
		case 'd':
			for(int i = 0; i < numberOfTiles; i++)
			{
				tiles[i].y += amount;
			}
			north+=3;
		break;
		case 'l':
			for(int i = 0; i < numberOfTiles; i++)
			{
				tiles[i].x -= amount;
			}
			east -= 3;
		break;
		case 'r':
			for(int i = 0; i < numberOfTiles; i++)
			{
				tiles[i].x += amount;
			}
			east += 3;
		break;
		}
	}
	
}
