package core;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {

	//SCREEN SETTINGS
	final int originalTileSize = 32; //sets tile size to 32x32 tiles, other common sizes 16x16 or 64x64
	final int scale = 2;
	
	final int tileSize = originalTileSize * scale; //64x64 tile
	final int maxScreenCol = 20;
	final int maxScreenRow = 15;
	final int screenWidth = tileSize * maxScreenCol;	//1280
	final int screenHeight = tileSize * maxScreenRow;	//960
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	
	//Set players default position
	int playerX = 100;
	int playerY = 100;
	int playerSpeed = 4;
	
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
		while (gameThread != null) {
			
			//1. UPDATE: update information such as positions
			update();
			
			//2. DRAW: draw to screen with updated info
			repaint(); //calls the paintComponent() method
			
		}
	}
	
	public void update() {
		
		if(keyH.upPressed == true) {
			playerY -= playerSpeed;
		}
		if(keyH.downPressed == true) {
			playerY += playerSpeed;
		}
		if(keyH.leftPressed == true) {
			playerX -= playerSpeed;
		}
		if(keyH.rightPressed == true) {
			playerX += playerSpeed;
		}
		
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D)g;
		
		g2d.setColor(Color.BLUE);
		g2d.fillRect(playerX, playerY, tileSize, tileSize);
		g2d.dispose();
	}
	
}
