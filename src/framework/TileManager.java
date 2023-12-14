package framework;
import core.GamePanel;
import objects.Player;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;


public class TileManager {
	
	GamePanel gp;
	Player player;
	public Tiles[] tile;
	final int tileTypes = 6;
	public int mapTileNum[][];

	public TileManager(GamePanel gp, Player player) {
		this.gp = gp;
		this.player = player;
		tile = new Tiles[tileTypes];
		
		loadLevel("/levels/level_test.png");
		getTileImage();
	}
	
	public void getTileImage() {
		try {
			tile[0] = new Tiles();
			tile[0].image = ImageIO.read(getClass().getResourceAsStream("/terrain/terrain_grass.png"));
			
			tile[1] = new Tiles();
			tile[1].image = ImageIO.read(getClass().getResourceAsStream("/terrain/terrain_dirt.png"));
			
			tile[2] = new Tiles();
			tile[2].image = ImageIO.read(getClass().getResourceAsStream("/terrain/terrain_water.png"));
			tile[2].collision = true;
			
			tile[3] = new Tiles();
			tile[3].image = ImageIO.read(getClass().getResourceAsStream("/terrain/terrain_tree.png"));
			tile[3].collision = true;
			
			tile[4] = new Tiles();
			tile[4].image = ImageIO.read(getClass().getResourceAsStream("/terrain/terrain_brick_grey.png"));
			
			tile[5] = new Tiles();
			tile[5].image = ImageIO.read(getClass().getResourceAsStream("/terrain/terrain_brick_red.png"));
			tile[5].collision = true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadLevel(String mapPath) {
		BufferedImage image = null;
		
		try {
			image = ImageIO.read(getClass().getResource(mapPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		gp.maxWorldCol = image.getWidth();
		gp.worldWidth = gp.maxWorldCol*gp.tileSize;
		gp.maxWorldRow = image.getHeight();
		gp.worldHeight = gp.maxWorldRow*gp.tileSize;
		
		mapTileNum = new int[gp.maxWorldCol][gp.maxWorldRow];
		
		for(int xx = 0; xx < gp.maxWorldCol; xx++) {
			for (int yy = 0; yy < gp.maxWorldRow; yy++) {
				int pixel = image.getRGB(xx, yy);
				int red = (pixel >> 16) & 0xff;
				int green = (pixel >> 8) & 0xff;
				int blue = (pixel) & 0xff;
				
				if(red == 0 && green == 255 && blue == 0) mapTileNum[xx][yy] = 0;
				else if(red == 127 && green == 127 && blue == 0) mapTileNum[xx][yy] = 1;
				else if(red == 0 && green == 127 && blue == 255) mapTileNum[xx][yy] = 2;
				else if(red == 0 && green == 127 && blue == 0) mapTileNum[xx][yy] = 3;
				else if(red == 127 && green == 127 && blue == 127) mapTileNum[xx][yy] = 4;
				else if(red == 127 && green == 0 && blue == 0) mapTileNum[xx][yy] = 5;
				else if(red == 255 && green == 255 && blue == 255) {
					mapTileNum[xx][yy] = 0;
					player.setX(xx*gp.tileSize);
					player.setY(yy*gp.tileSize);
				}
			}
		}
		
	}
	
	public void draw(Graphics2D g2d) {		
		for(int worldCol = 0; worldCol < gp.maxWorldCol; worldCol++) {
			for(int worldRow = 0; worldRow < gp.maxWorldRow; worldRow++) {
				int worldX = worldCol*gp.tileSize;
				int worldY = worldRow*gp.tileSize;
				if (	worldX + gp.tileSize > gp.player.worldX - gp.player.screenX &&
						worldX - gp.tileSize < gp.player.worldX + gp.player.screenX &&
						worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
						worldY - gp.tileSize < gp.player.worldY + gp.player.screenY   ) {
					
					int tileNum = mapTileNum[worldCol][worldRow];
					int screenX = worldX - gp.player.worldX + gp.player.screenX;
					int screenY = worldY - gp.player.worldY + gp.player.screenY;
					g2d.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
				}
			}
		}
	}
	
}
