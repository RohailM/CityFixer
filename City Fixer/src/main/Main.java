package main;

public class Main {
	public static void main(String[] args) {
		Frame window = new Frame("City Fixer", 750, 750, false);
		window.requestFocusInWindow();
		window.pack();
		window.setVisible(true);
	}
}
