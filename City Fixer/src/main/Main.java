package main;

import javax.swing.SwingUtilities;

public class Main {
	public static void main(String[] args) {
        /*SwingUtilities.invokeLater(() -> {
            new StartScreen(username -> {
                Frame window = new Frame("City Fixer", 622, 685, false, username);
                window.requestFocusInWindow();
                window.pack();
                window.setVisible(true);
            });
        });*/
		
        Frame window = new Frame("City Fixer", 622, 685, false, "rohail");
        window.requestFocusInWindow();
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
	}
}
