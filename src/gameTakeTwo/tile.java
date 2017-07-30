package gameTakeTwo;

import java.awt.Color;
import java.awt.Graphics;

public class tile
{

	int x, y;
	int width = 15, height = 15;
	int tileType = 0, tileNumber;
	
	public void tick()
	{
		
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.green);
		g.drawRect(x, y, width, height);
		
		//g.setColor(Color.red);
		//g.drawString("" + tileNumber, x, y + 10);
	}
	
}
