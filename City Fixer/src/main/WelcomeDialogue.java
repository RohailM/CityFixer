package main;

import java.awt.*;

import javax.swing.JComponent;
import javax.swing.Timer;

/**
 * The WelcomeDialogue class is responsible for displaying a welcome message to the user
 * It uses a typewriter effect to display the message
 * @author Rohail Memon
 */
public class WelcomeDialogue {
	
    private JComponent parentComponent;
	public String username;
	private int x;
	private int y;
	private int width;
	private int height;
    private String fullMessage;
    private StringBuilder displayedMessage = new StringBuilder();
    private int messageIndex = 0;
    private int maxWidth;
    private Timer typewriterTimer;
    private Font font = new Font("Arial", Font.BOLD, 14);
	
    /**
     * Constructs a WelcomeDialogue with a specific message and position
     * @param parentComponent The parent component where this dialogue will be drawn
     * @param x The x coordinate of the top-left corner of the dialogue box
     * @param y The y coordinate of the top-left corner of the dialogue box
     * @param w The width of the dialogue box
     * @param h The height of the dialogue box
     * @param username The username of the player
     */
	public WelcomeDialogue(JComponent parentComponent, int x, int y, int w, int h, String username) {
		this.parentComponent = parentComponent;
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
		this.username = username;
		this.maxWidth = w - 64;
		this.fullMessage = "Welcome to City Fixer, " + username + ". It looks like it's your first time playing. The city is greatly damaged and has very poor infrastructure due to rushed design. Your objective is to renovate the entire city and shift it towards a more sustainable framework.";
        
		typewriterTimer = new Timer(40, e -> nextCharacter());
        typewriterTimer.start();

	}
	
	/**
	 * Adds the next character from the full message to the displayed message
	 * Repaints parent component to update the displayed message
	 */
    private void nextCharacter() {        
        if (messageIndex < fullMessage.length()) {
            char nextChar = fullMessage.charAt(messageIndex);
            displayedMessage.append(nextChar);
            messageIndex++;
        } else {
            typewriterTimer.stop();  // Timer stopped if the entire message has been displayed
        }
        
        // Repaint the parent component (Map) to display the updated message
        if (parentComponent != null) {
            parentComponent.repaint();
        }

    }
    
    /**
     * Wraps the given text to fit within a specified width
     * @author This entire method was developed by ChatGPT and adapted by Rohail.
     * @param text The text to wrap
     * @param fm The FontMetrics used to measure the width of the text
     * @param maxWidth The maximum width of the text area
     * @return A string with newline characters inserted to wrap the text
     */
    private String wrapText(String text, FontMetrics fm, int maxWidth) {
        StringBuilder wrappedText = new StringBuilder();
        StringBuilder line = new StringBuilder();
        
        for (String word : text.split(" ")) {
            if (fm.stringWidth(line + word) < maxWidth) {
                line.append(word).append(" ");
            } else {
                wrappedText.append(line).append("\n");
                line = new StringBuilder(word).append(" ");
            }
        }
        
        if (line.length() > 0) {
            wrappedText.append(line);
        }

        return wrappedText.toString().trim();
    }
	
    /**
     * Draws the welcome message box with the typewriter effect on the given Graphics2D plane.
     * @param g2 The Graphics2D context to draw on.
     */
	public void drawWelcomeBox(Graphics2D g2) {
        Color c = new Color(0, 0, 0,180);
        g2.setColor(c);
        g2.fillRoundRect(x, y - 20, width, height, 32, 32);

        Color b = new Color(255,255,255);
        g2.setColor(b);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y - 15, width - 10, height - 2, 22, 22);
        
        g2.setFont(font);
        int yy = y + 11;    
        
        // Calculate the wrapped text
        FontMetrics fm = g2.getFontMetrics();
        String wrappedText = wrapText(displayedMessage.toString(), fm, maxWidth);

        // Draw the text that has been "typed out" so far with wrapping
        // This loop was made by ChatGPT with slight adjustments
        for (String line : wrappedText.split("\n")) {
            g2.drawString(line, x + 32, yy);
            yy += fm.getHeight() + 5;  // Adjust the spacing between lines
        }
	}
}
