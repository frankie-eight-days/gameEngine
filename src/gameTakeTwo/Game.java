package gameTakeTwo;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	public static final int WIDTH = 200;
	public static final int HEIGHT = WIDTH * 9/12;
	public static final int SCALE = 3;
	public static final String NAME = "Game";
	
	private JFrame frame;
	
	public boolean running = false;
	public int tickCount = 0;
	
	chunkController cc;
	player p;
	
	public Game()
	{
		setMinimumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setMaximumSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
		
		frame = new JFrame(NAME);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		
		//frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);	
	}
	
	public synchronized void start() 
	{
		running = true;
		new Thread(this).start();
		
		cc = new chunkController();
		cc.init();
		
		p = new player();
		p.init();
		addKeyListener(p);
	}
	
	public synchronized void stop() 
	{
		running = false;
	}
	
	public void run() 
	{		
		long lastTime = System.nanoTime();
		double nsPerTick = 1000000000D/60D;
		
		int frames = 0;
		int ticks = 0;
		
		long lastTimer = System.currentTimeMillis();
		double delta = 0;
		
		while (running){
			long now = System.nanoTime();
			delta += (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender = true;
			while(delta >= 1)
			{
				ticks++;
				tick();
				delta -= 1;
				shouldRender = true;
			}
			
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (shouldRender)
			{
				frames++;
				render();
			}
			
			if(System.currentTimeMillis() - lastTimer >= 1000){
				lastTimer += 1000;
				System.out.println(ticks + " ticks, " + frames + " frames");
				frames = 0;
				ticks = 0;
			}
		}
	}
	
	public void tick()
	{
		tickCount++;
		cc.tick(this);
		p.tick(this);
	}
	
	private void gameGraphics(Graphics g)
	{
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		cc.paint(g);
		p.paint(g);
	}
	
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if (bs == null){
			createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();

		gameGraphics(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args)
	{ 
		new Game().start();		
	}
}