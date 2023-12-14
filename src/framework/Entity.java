package framework;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
	
	public int worldX, worldY;
	public int speed;
	public BufferedImage image;
	public String direction;
	public Rectangle collisionArea;
	public boolean collisionOn = false;

	public int spriteCounter = 0;
	public int spriteNum = 1;
	
	public int getX() { return this.worldX; }
	public int getY() { return this.worldY; }
	
	public void setX(int x) { this.worldX = x; }
	public void setY(int y) { this.worldY = y; }
	
}
