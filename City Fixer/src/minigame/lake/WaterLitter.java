package minigame.lake;

import javax.swing.*;

import minigame.MinigameManager;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// The Garbage class represents the main panel for the lake cleanup game
public class WaterLitter extends JPanel {

    MinigameManager miniM;

    // Array to keep track of whether each piece of trash is in the water
    private boolean[] trashInWater = {true, true, true, true, true};

    // Index of the trash being dragged; -1 if no trash is being dragged
    private int draggingTrashIndex = -1;

    // Coordinates and dimensions of the trash bin
    private int trashBinX = 500, trashBinY = 250, trashBinWidth = 70, trashBinHeight = 70;

    // Position of the dragging trash
    private int draggingTrashX, draggingTrashY;
    
    // Constructor for the Garbage class
    public WaterLitter(JFrame mainFrame, MinigameManager miniM) {
        this.miniM = miniM;

        // Add mouse listeners to handle user interactions
        addMouseListener(new MouseAdapter() {
            // Handle mouse press event
            public void mousePressed(MouseEvent e) {
                // Check if the mouse press is inside any piece of trash
                for (int i = 0; i < trashInWater.length; i++) {
                    if (trashInWater[i] && isInsideTrash(e.getX(), e.getY(), i)) {
                        draggingTrashIndex = i; // Set the index of the dragging trash
                        draggingTrashX = e.getX() - 15; // Offset to center the dragged trash
                        draggingTrashY = e.getY() - 15;
                        break; // Break out of the loop after finding the first piece of trash
                    }
                }
            }

            // Handle mouse release event
            public void mouseReleased(MouseEvent e) {
                // Check if the released position is inside the trash bin
                if (draggingTrashIndex != -1 && isInsideTrashBin(e.getX(), e.getY())) {
                    trashInWater[draggingTrashIndex] = false; // Move the trash out of the water
                    // Check if all trash is cleaned, show a message, and exit the game
                    if (allTrashCleaned()) {
                        JOptionPane.showMessageDialog(mainFrame, "Congratulations! You have cleaned the lake!");
                        mainFrame.dispose(); // Exit the game after cleaning the lake
                    }
                }
                draggingTrashIndex = -1; // Reset the dragging index
                repaint(); // Redraw the panel
            }
        });

        // Add mouse motion listener to handle dragging
        addMouseMotionListener(new MouseAdapter() {
            // Handle mouse dragged event
            public void mouseDragged(MouseEvent e) {
                if (draggingTrashIndex != -1) {
                    // Update the position of the dragging trash
                    draggingTrashX = e.getX() - 15;
                    draggingTrashY = e.getY() - 15;
                    repaint(); // Redraw the panel
                }
            }
        });
    }

    // Check if a point (x, y) is inside a specific piece of trash
    public boolean isInsideTrash(int x, int y, int trashIndex) {
        // Assuming each piece of trash is a square of size 30x30
        int trashX = 50 + trashIndex * 100;
        int trashY = 100 + trashIndex * 60;
        return x >= trashX && x <= trashX + 30 && y >= trashY && y <= trashY + 30;
    }

    // Check if a point (x, y) is inside the trash bin
    private boolean isInsideTrashBin(int x, int y) {
        return x >= trashBinX && x <= trashBinX + trashBinWidth &&
               y >= trashBinY && y <= trashBinY + trashBinHeight;
    }

    // Check if all pieces of trash are cleaned
    private boolean allTrashCleaned() {
        for (boolean trash : trashInWater) {
            if (trash) {
                return false; // There is still trash in the water
            }
        }
        miniM.setMinigameComplete(1, true);
        return true; // All trash has been cleaned
    }

    // Method to paint the component (override from JPanel)
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw the water background with a simple gradient
        Graphics2D g2d = (Graphics2D) g;
        GradientPaint gradient = new GradientPaint(0, 0, new Color(0, 102, 204), 0, getHeight(), new Color(0, 51, 102));
        g2d.setPaint(gradient);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        // Draw the trash in the water
        g.setColor(new Color(34, 139, 34)); // Forest green trash color
        for (int i = 0; i < trashInWater.length; i++) {
            if (trashInWater[i]) {
                int trashX = 50 + i * 100;
                int trashY = 100 + i * 60;
                g.fillRoundRect(trashX, trashY, 30, 30, 5, 5); // Rounded rectangle for a more realistic look
            }
        }

        // Draw the dragging trash
        if (draggingTrashIndex != -1) {
            g.setColor(new Color(34, 139, 34)); // Forest green color for dragging trash
            g.fillRoundRect(draggingTrashX, draggingTrashY, 30, 30, 5, 5);
        }

        g.setColor(Color.BLACK); // Black trash bin color
        g.fillRoundRect(trashBinX, trashBinY, trashBinWidth, trashBinHeight, 10, 10);
    }
}

