package framework;

import core.GamePanel;
import objects.OBJ_Chest;
import objects.OBJ_Door;
import objects.OBJ_Key;

public class ObjectManager {

	GamePanel gp;
	
	public ObjectManager(GamePanel gp) {
		this.gp = gp;
	}
	
	public void setObject() {
		gp.obj[0] = new OBJ_Key();
		gp.obj[0].worldX = 41*gp.tileSize;
		gp.obj[0].worldY = 2*gp.tileSize;
		
		gp.obj[1] = new OBJ_Door();
		gp.obj[1].worldX = 45*gp.tileSize;
		gp.obj[1].worldY = 10*gp.tileSize;
		
		gp.obj[2] = new OBJ_Chest();
		gp.obj[2].worldX = 45*gp.tileSize;
		gp.obj[2].worldY = 4*gp.tileSize;
	}
	
	public void removeObject() {
		
	}
	
}
