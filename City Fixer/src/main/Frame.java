package main;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import inputs.Mouse;
import inputs.WindowListener;

public class Frame extends JFrame {
	Frame(String title, int x, int y, boolean resizeable, String username) {
		super(title);
		setPreferredSize(new Dimension(x,y));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(resizeable);
		
		Map map = new Map();
		add(map);
		
		//map.showWelcomeMessage(username);

		Mouse mouse = new Mouse(map.tileM, map.miniM);
		addMouseListener(mouse);

		WindowListener windowListener = new WindowListener(map.tileM, username);
		addWindowListener(windowListener);
	}
	
}
