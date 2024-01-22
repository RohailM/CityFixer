package main;

import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import inputs.Mouse;
import inputs.WindowListener;

<<<<<<< HEAD
import inputs.MouseInputHandler;

=======
/** 
 * The Frame class represents the main game window.
 * It initializes the game environment including the map and input listeners.
 * @author Rohail Memon
 */
>>>>>>> branch 'main' of https://github.com/RohailM/CityFixer.git
public class Frame extends JFrame {
	/**
	 * Constructs the main game window and initializes the game components
	 * @param title	The title of the game window
	 * @param x	The width of the game window
	 * @param y	The height of the game window
	 * @param resizeable Indicates whether or not the window can be resized
	 * @param username The username of the player
	 * @param hasSavedMap Indicates if there is a saved game for the user 	
	 */
	Frame(String title, int x, int y, boolean resizeable, String username, boolean hasSavedMap) {
		super(title);
		setPreferredSize(new Dimension(x,y));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(resizeable);
		
		Map map;
		
		if (hasSavedMap) {
			String savedMapPath = "res/saves/" + username + "_savedMap.txt";
			JOptionPane.showMessageDialog(this, "Welcome back, " + username + "! Your progress has been restored.", "Welcome Back", JOptionPane.INFORMATION_MESSAGE);

            map = new Map(savedMapPath, username); // Create a Map with the saved map path
		} else {
			map = new Map(null, username);
		}
		
		add(map);
		
<<<<<<< HEAD
		MouseInputHandler mouse = new MouseInputHandler(map, map.tileM);
		addMouseListener(mouse);
=======
		Mouse mouse = new Mouse(map, map.tileM, map.miniM);
		addMouseListener(mouse);

		WindowListener windowListener = new WindowListener(map.tileM, username);
		addWindowListener(windowListener);
>>>>>>> branch 'main' of https://github.com/RohailM/CityFixer.git
	}
	
}
