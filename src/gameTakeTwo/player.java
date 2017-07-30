package gameTakeTwo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class player implements KeyListener
{
	boolean up, down, left, right;
	
	double x = Game.WIDTH*3/2, y = Game.HEIGHT*3/2;
	double coordY = Game.HEIGHT*Game.SCALE/2, coordX = Game.WIDTH*Game.SCALE/2;
	int width = 15, height = 15;
	int leftBound = 150, rightBound = 450, upperBound = 100, lowerBound = 350;
	double movementSpeed = 4;
	
	public void init()
	{
		
	}
	
	public void tick(Game game)
	{
		movement(game);
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.magenta);
		g.fillRect((int) x,(int) y, width, height);
	}

	private void movement(Game game)
	{
		if(x + width <= rightBound && x >= leftBound)
		{
			if(y >= upperBound && y + height <= lowerBound)
			{
				if(left == true)
				{
					x -= movementSpeed;
					coordX -= movementSpeed;
				}
				if(right == true)
				{
					x += movementSpeed;
					coordX += movementSpeed;
				}
				if(down == true)
				{
					y += movementSpeed;
					coordY += movementSpeed;
				}
				if(up == true)
				{
					y -= movementSpeed;
					coordY -= movementSpeed;
				}
			}
		}
		if(x + width >= rightBound)
		{
			x = 450 - movementSpeed - width;
			game.cc.moveChunks(movementSpeed, 'r');
			//coordX += movementSpeed;
		}
		if(x <= leftBound)
		{
			x = 150 + movementSpeed;
			game.cc.moveChunks(movementSpeed, 'l');
			//coordX -= movementSpeed;
		}
		if(y <= upperBound)
		{
			y = 100 + movementSpeed;
			game.cc.moveChunks(movementSpeed, 'u');
			//coordY -= movementSpeed;
		}
		if(y + height >= lowerBound)
		{
			y = 350 - height - movementSpeed;
			game.cc.moveChunks(movementSpeed, 'd');
			//coordY += movementSpeed;
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) 
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_W:
			up = true;
		break;
		case KeyEvent.VK_A:
			left = true;
		break;
		case KeyEvent.VK_S:
			down = true;
		break;
		case KeyEvent.VK_D:
			right = true;
		break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{
		switch(e.getKeyCode())
		{
		case KeyEvent.VK_W:
			up = false;
		break;
		case KeyEvent.VK_A:
			left = false;
		break;
		case KeyEvent.VK_S:
			down = false;
		break;
		case KeyEvent.VK_D:
			right = false;
		break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
}
