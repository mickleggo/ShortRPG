package core;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import framework.TileManager;
import objects.Player;


public class GamePanel extends JPanel implements Runnable {

	//SCREEN SETTINGS
	public final int originalTileSize = 32; //sets tile size to 32x32 tiles, other common sizes 16x16 or 64x64
	final int scale = 2;
	
	public final int tileSize = originalTileSize * scale; //64x64 tile
	public final int maxScreenCol = 20;
	public final int maxScreenRow = 15;
	public final int screenWidth = tileSize * maxScreenCol;	//1280
	public final int screenHeight = tileSize * maxScreenRow;	//960
	
	//GAME SETTINGS
	int FPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyH);
	TileManager tileM = new TileManager(this, player);
	
	public GamePanel() {
		
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.DARK_GRAY);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		
	}
	
	public void initiate() {
		gameThread = new Thread(this);
		gameThread.start();
		
	}
	
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double delta = 0;
		long lastTime = System.nanoTime();
		long currentTime;
		
		while (gameThread != null) {
			currentTime = System.nanoTime();
			delta += (currentTime - lastTime)/drawInterval;
			lastTime = currentTime;
			
			if(delta > 1) {
				update();
				repaint(); //calls the paintComponent() method
				delta--;
			}
			
		}
	}
	
	public void update() {
		player.update();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		tileM.draw(g2d);		
		player.draw(g2d);
		
		g2d.dispose();
	}
	
}
