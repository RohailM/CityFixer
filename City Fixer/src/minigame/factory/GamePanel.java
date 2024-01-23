package minigame.factory;

import javax.swing.*;

import minigame.MinigameManager;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.imageio.ImageIO;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The GamePanel class represents the game panel of the Sustainability Factory Restoration Game.
 * This class extends the JPanel class from the Swing framework, serving as the primary area for the game's graphical user interface (GUI) components and gameplay elements.
 * It includes a progress bar, timer, and mouse event handling for building the factory.
 * The panel is responsible for updating the construction progress, managing the game timer, displaying game tutorials, and handling the finishing of the game.
 * @author Wahab Sattar
 */
public class GamePanel extends JPanel implements Interactable {
    private JProgressBar progressBar;
    private JLabel infoLabel;
    private JLabel timerLabel;
    private JPanel timerPanel;

    private int constructionProgress = 0;
    private int clicksRequired = 30;
    private boolean gameFinished = false;

    private int timerSeconds = 30;
    private Timer timer;

    // Counter for tracking clicks
    private int clickCounter = 0;

    // Educational content about SDG 9
    private final String[] sdg9Facts = {
            "SDG 9 Fact 1: Sustainable development goal 9 aims to build resilient infrastructure, promote inclusive and sustainable industrialization, and foster innovation.",
            "SDG 9 Fact 2: Access to technology and innovation is crucial for achieving economic growth and improving living standards.",
            "SDG 9 Fact 3: In 2021, 0.33 tonnes of greenhouse gas emissions were emitted per $1000 of infrastructure assets production",
            "SDG 9 Fact 4: After dropping 1.3 per cent in 2020, global manufacturing production grew by 7.2 per cent in 2021",
            "SDG 9 Fact 5: In developing countries, barely 30% of agricultural products undergo industrial processing, compared to 98% high-income countries",
    };
    
    MinigameManager miniM;

    /**
     * Constructor for the GamePanel class.
     */
    public GamePanel(MinigameManager miniM) {
    	this.miniM = miniM;
        setLayout(new BorderLayout());

        // Initialize and configure the progress bar
        progressBar = new JProgressBar(0, clicksRequired);
        progressBar.setValue(0);
        progressBar.setStringPainted(true);
        progressBar.setForeground(new Color(0, 255, 0));
        Dimension progressBarSize = progressBar.getPreferredSize();
        progressBarSize.height = 40; // Larger height
        progressBar.setPreferredSize(progressBarSize); // Larger size
        add(progressBar, BorderLayout.SOUTH);

        // Create a new panel for the timer and configure its appearance
        timerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        timerPanel.setBackground(new Color(135, 206, 250)); // Bright blue color
        timerLabel = new JLabel("Time Left: " + timerSeconds);
        timerLabel.setForeground(Color.BLACK);
        timerLabel.setFont(new Font("Calibri", Font.BOLD, 24)); // Larger font size
        timerPanel.add(timerLabel);
        add(timerPanel, BorderLayout.NORTH);

        // Initialize and configure the info label
        infoLabel = new JLabel("Construction Progress: " + constructionProgress);
        infoLabel.setForeground(Color.BLACK);
        infoLabel.setFont(new Font("Calibri", Font.BOLD, 20)); // Larger font size
        add(infoLabel, BorderLayout.WEST);

        // Add a mouse listener to respond to mouse clicks
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                interact(); // Utilize the interact method
            }
        });

        // Start the timer and display the game tutorial
        startTimer();
        showTutorial();
    }

    /**
     * Display the game tutorial using a JOptionPane.
     */
    private void showTutorial() {
        JOptionPane.showMessageDialog(this,
                "Welcome to the Sustainability Factory Restoration Game!\n" +
                        "Click anywhere on the factory to build and restore it.\n" +
                        "Complete 30 clicks to finish the game.\n" +
                        "You have 30 seconds to finish.",
                "Game Tutorial",
                JOptionPane.INFORMATION_MESSAGE); 
    }

    /**
     * Start the timer to countdown the remaining time.
     */
    private void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (timerSeconds > 0) {
                    timerSeconds--;
                    updateTimerLabel();
                } else {
                    timer.cancel();
                    resetGame();
                }
            }
        }, 1000, 1000);
    }

    /**
     * Show educational content after every 8 clicks.
     * Shows an educational fact every 8 clicks
     * This method is from chatGPT
     */
    private void showEducationalContent() {
        if (clickCounter % 8 == 0 && clickCounter > 0 && clickCounter < clicksRequired) {
            int factIndex = (clickCounter / 8) - 1;
            if (factIndex < sdg9Facts.length) {
                JOptionPane.showMessageDialog(this, sdg9Facts[factIndex], "SDG 9 Fact", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * Handle building the factory when the user clicks.
     */
    public void interact() {
        if (!gameFinished) {
            constructionProgress++;
            progressBar.setValue(constructionProgress);
            updateInfoLabel();

            // Show educational content after every 5 clicks
            showEducationalContent();

            if (constructionProgress == clicksRequired) {
                finishGame();
            }

            clickCounter++;
        }
    }

    /**
     * Update the information label with the current construction progress.
     */
    private void updateInfoLabel() {
        infoLabel.setText("Construction Progress: " + constructionProgress);
    }

    /**
     * Finish the game, display a message, cancel the timer, and reset the game.
     */
    private void finishGame() {
        gameFinished = true;
        repaint();
        JOptionPane.showMessageDialog(this, "Congratulations! You have successfully restored the factory.");
        timer.cancel();
        resetGame();
    }

    /**
     * Reset the game, either closing the window or showing a message based on completion status.
     */
    private void resetGame() {
        if (constructionProgress == clicksRequired) {
            // User completed the game, close the window
            SwingUtilities.getWindowAncestor(this).dispose();
            miniM.setMinigameComplete(3, true);
        } else {
            // User did not finish in time, show message and close the window
            JOptionPane.showMessageDialog(this, "Time's up! You did not finish in time. Try again.");
            SwingUtilities.getWindowAncestor(this).dispose();
        }
    }

    /**
     * Update the timer label with the remaining time.
     */
    private void updateTimerLabel() {
        timerLabel.setText("Time Left: " + timerSeconds);
    }

    /**
 * Draw the background factory image.
 */
private void drawBackground(Graphics g) {
    try {
        // Load image from resources folder
        Image factoryImage = ImageIO.read(getClass().getResourceAsStream("/images/factory/factory.jpg"));
        g.drawImage(factoryImage, 0, 0, getWidth(), getHeight(), this);
    } catch (Exception e) {
        e.printStackTrace();
    }
}

/**
 * Draw the updated factory image.
 */
private void drawUpdatedFactory(Graphics g) {
    try {
        // Load image from resources folder
        Image updatedFactoryImage = ImageIO.read(getClass().getResourceAsStream("/images/factory/updated_factory.jpg"));
        g.drawImage(updatedFactoryImage, 0, 0, getWidth(), getHeight(), this);
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    /**
     * Draw the factory image based on the game state.
     */
    private void drawFactory(Graphics g) {
        if (!gameFinished) {
            drawBackground(g);
        } else {
            drawUpdatedFactory(g);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawFactory(g);
    }
}