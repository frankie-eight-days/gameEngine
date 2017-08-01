package gameTakeTwo;

import java.awt.Color;
import java.awt.Graphics;

public class tile
{

	int x, y;
	int width = 15, height = 15;
	int tileType = 0, tileNumber;
	boolean leftSector = false, rightSector = false, topSector = false, downSector = false;
	
	public void tick(player p)
	{
		if(tileType == 1)
		{
			playerHitDetections(p);	
		}
	}
	
	public void paint(Graphics g)
	{
		switch(tileType)
		{
		case 0:
			g.setColor(Color.green);
			g.drawRect(x, y, width, height);
		break;
		case 1:
			g.setColor(Color.red);
			g.fillRect(x, y, width, height);
		break;
		case 2:
			g.setColor(Color.CYAN);
			g.fillRect(x, y, width, height);
		}
	}
	
	public void playerHitDetections(player p)
	{
		if(p.x + p.width >= x && p.x <= x + width)
		{
			if(p.y + p.height >= y && p.y <= y + height)
			{
				if(leftSector == true)
				{
					p.x = x - p.width - p.movementSpeed;
					p.coordX -= p.movementSpeed;
				}
				
				if(rightSector == true)
				{
					p.x = x + width + p.movementSpeed;
					p.coordX += p.movementSpeed;
				}
				
				if(topSector == true)
				{
					p.y = y - p.height - p.movementSpeed;
					p.coordY -= p.movementSpeed;
				}
				if(downSector == true)
				{
					p.y = y + height + p.movementSpeed;
					p.coordY += p.movementSpeed;
				}
			}
		}
		playerSectionFinder(p);
	}
	
	public void playerSectionFinder(player p)
	{
		if(p.x < x && p.y + p.height > y && p.y < y + height)
		{
			leftSector = true;
		}else
		{
			leftSector = false;
		}
		
		if(p.x > x && p.y + p.height > y && p.y < y + height)
		{
			rightSector = true;
		}else
		{
			rightSector = false;
		}
		
		if(p.y < y && p.x + p.width > x && p.x < x + width)
		{
			topSector = true;
		}else
		{
			topSector = false;
		}
		
		if(p.y > y + height && p.x + p.width > x && p.x < x + width)
		{
			downSector = true;
		}else
		{
			downSector = false;
		}
		

	}
	
}
