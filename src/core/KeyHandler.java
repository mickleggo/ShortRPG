package core;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
	
	public boolean upPressed = false, downPressed = false, leftPressed = false, rightPressed = false;
	
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = true;
			downPressed = false;
		}
		
		if(code == KeyEvent.VK_S) {
			downPressed = true;
			upPressed = true;
		}
		
		if(code == KeyEvent.VK_A) {
			leftPressed = true;
			rightPressed = false;
		}
		
		if(code == KeyEvent.VK_D) {
			rightPressed = true;
			leftPressed = false;
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			upPressed = false;
		}
		
		if(code == KeyEvent.VK_S) {
			downPressed = false;
		}
		
		if(code == KeyEvent.VK_A) {
			leftPressed = false;
		}
		
		if(code == KeyEvent.VK_D) {
			rightPressed = false;
		}
	}
	
	public void keyTyped(KeyEvent e) {} //not gunna be used
	
}
