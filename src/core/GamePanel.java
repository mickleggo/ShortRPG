package core;
import objects.Player;
import java.awt.*;
import javax.swing.JPanel;


public class GamePanel extends JPanel implements Runnable {

	//SCREEN SETTINGS
	final int originalTileSize = 32; //sets tile size to 32x32 tiles, other common sizes 16x16 or 64x64
	final int scale = 2;
	
	public final int tileSize = originalTileSize * scale; //64x64 tile
	final int maxScreenCol = 20;
	final int maxScreenRow = 15;
	final int screenWidth = tileSize * maxScreenCol;	//1280
	final int screenHeight = tileSize * maxScreenRow;	//960
	
	int FPS = 60;
	
	KeyHandler keyH = new KeyHandler();
	Thread gameThread;
	Player player = new Player(this, keyH);
	
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
		
		player.draw(g2d);
		
		g2d.dispose();
	}
	
}
