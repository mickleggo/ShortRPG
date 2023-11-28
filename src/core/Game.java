package core;

import javax.swing.JFrame;

public class Game {

	private static int scr_width = 1344, scr_height = 1024;
	private static String title = "Short RPG Adventure";
	
	
	
	public static void main(String[] args) {

		JFrame window = new JFrame();
		window.setBounds(0, 0, scr_width, scr_height);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle(title);
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.initiate();
		
	}

}
