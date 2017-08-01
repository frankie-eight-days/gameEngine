package gameTakeTwo;

import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class chunk 
{

	int x = 0, y = 0;				//Coordinates of the chunk
	int north = 0, east = 0;		//North and south are basically the coordinate system for the chunks
	int numberOfTiles = 1200;		//Since the tiles are 15x15 px and the game window is 600x450 px there are 1200 tiles
	int chunkWidth, chunkHeight;	//The width and height of the chunk, unassigned so that it can be dynamic
	
	tile tiles[] = new tile[numberOfTiles];	//Array of tiles. 
	
	/*	init
	 * 	initilizes the chunk and it's array of tiles
	 */
	public void init()
	{
		for(int i = 0; i < numberOfTiles; i++)	//Loops through each one of the tiles
		{
			tiles[i] = new tile();				//Creates the tiles 
			tiles[i].tileNumber = i;			//Assigns the tile number of map purposes, counts horizontally from top corner
		}
	}
	/* tick
	 * updates chunk and it's tiles every tick of the game loop
	 */
	public void tick(player p)
	{
		for(int i = 0; i < numberOfTiles; i++)	//Loops through all tiles
		{
			tiles[i].tick(p);					//Updates each tile with every tick
		}
	}
	/* paint
	 * Contains function that updates the graphics 60 times every second
	 */
	public void paint(Graphics g)
	{
		for(int i = 0; i < numberOfTiles; i++)	//Loops through all tiles
		{	
			tiles[i].paint(g);					//Refreshes the tile's graphics every loop
		}
		
		//Developer debugging graphics 
		g.setColor(Color.red);
		g.drawRect(tiles[0].x, tiles[0].y, chunkWidth, chunkHeight);
		
		g.drawString("North: " + north, tiles[0].x + 300, tiles[0].y + 225);
		g.drawString("East: " + east, tiles[0].x + 300, tiles[0].y + 235);
	}
	
	/*	setTiles
	 * 	Function that loads and sets the maps from text files.
	 * 	Maps can be found in the maps folder under src. 
	 * 	Map file structure goes line by line.
	 * 	First line starts with the number of tiles to update
	 * 	Next line is the number of the tile that needs to have it's tileType updated
	 * 	Next line is the tileType that it should be updated to. Eg:
	 * 			1  (one tiles to update)
	 * 			2  (tile two needs to be updated)
	 * 			1  (tile one will be updated to tileType one)
	 */
	public void setTiles()
	{
		refreshTiles();
		int numberOfLines;	//numberOfLines in the text loop to update
		File mapFile = new File("src/maps/n" + north + "e" + east + ".txt"); //Creates file object for the scanner to read
		
		if(mapFile.exists())	//Checks to see if map file exists, if not it'll just be skipped
		{
			try 
			{
				Scanner fileScanner = new Scanner(mapFile);	//Creates scanner to scan lines of text
				numberOfLines = fileScanner.nextInt();		//First line is loaded into numberOfLines variable for loop
				for(int i = 0; i < numberOfLines; i++)		//Counts through the rest of the tiles, numberOfLines should be half the number of tiles to be updated
				{
					int tileToSet = fileScanner.nextInt();	
					int tileTypeToSet = fileScanner.nextInt();
					tiles[tileToSet].tileType = tileTypeToSet;	//Sets the tileType accordingly
				}
			}catch (FileNotFoundException e) {e.printStackTrace();}	
		}
	}
	
	/* makeGrid
	 * 
	 */
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
			setTiles();
		break;
		case 'd':
			for(int i = 0; i < numberOfTiles; i++)
			{
				tiles[i].y += amount;
			}
			north+=3;
			setTiles();
		break;
		case 'l':
			for(int i = 0; i < numberOfTiles; i++)
			{
				tiles[i].x -= amount;
			}
			east -= 3;
			setTiles();
		break;
		case 'r':
			for(int i = 0; i < numberOfTiles; i++)
			{
				tiles[i].x += amount;
			}
			east += 3;
			setTiles();
		break;
		}
	}
	
	/*
	 * 
	 */
	public void refreshTiles()
	{
		for(int i = 0; i < numberOfTiles; i++)
		{
			tiles[i].tileType = 0;
		}
	}
	
}
