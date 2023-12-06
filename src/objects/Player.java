package objects;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import core.GamePanel;
import core.KeyHandler;
import framework.Entity;

public class Player extends Entity {

	GamePanel gp;
	KeyHandler keyH;

	private BufferedImage dn1, dn2, dn3, dn4;
	
	public Player(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		x = 100;
		y = 100;
		speed = 4;
		direction = "down";
	}
	
	public void getPlayerImage() {
		
		try {
			dn1 = ImageIO.read(getClass().getResourceAsStream("/player/player_dn1.png"));
			dn2 = ImageIO.read(getClass().getResourceAsStream("/player/player_dn2.png"));
			dn3 = ImageIO.read(getClass().getResourceAsStream("/player/player_dn3.png"));
			dn4 = ImageIO.read(getClass().getResourceAsStream("/player/player_dn4.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {		//currently all directions set to down as I have not bad other sprites yet	
		if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
			if(keyH.upPressed == true) { 
				direction = "down";
				y -= speed;  
			}
			if(keyH.downPressed == true) { 
				direction = "down";
				y += speed; 
			}
			if(keyH.leftPressed == true) { 
				direction = "down";
				x -= speed; 
			}
			if(keyH.rightPressed == true) { 
				direction = "down";
				x += speed; 
			}
			
			spriteCounter++;		
			
			if(spriteCounter > 15) {
				if(spriteNum == 1) spriteNum = 2;
				else if (spriteNum == 2) spriteNum = 3;
				else if (spriteNum == 3) spriteNum = 4;
				else if (spriteNum == 4) spriteNum = 1;
				spriteCounter = 0;
			}
		} else {
			spriteNum = 1;
		}
	}
	
	public void draw(Graphics2D g2d) {
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			image = dn1;
			break;
		case "down":
			if (spriteNum == 1) image = dn1;
			if (spriteNum == 2) image = dn2;
			if (spriteNum == 3) image = dn3;
			if (spriteNum == 4) image = dn4;
			break;
		case "left":
			image = dn1;
			break;
		case "right":
			image = dn1;
			break;
		}
		
		g2d.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
}
