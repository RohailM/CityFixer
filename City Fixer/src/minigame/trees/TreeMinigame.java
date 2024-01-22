package minigame.trees;

import javax.swing.*;

import minigame.MinigameManager;

/**
 * @author Lucas Namba
 * This class serves as the bridge between the minigameManager and the tree minigame
 * it extends JFrame so it can create all the window of the minigame
 */
public class TreeMinigame extends JFrame{

    MinigameManager miniM;
    TreePanel panel;

    /**
     * Since this class already extends JFrame I do not need to create a JFrame object, and do need to call super() of Jframe
     * The panel (the minigame itself) is also create here
     * @param miniM so that the TreePanel also gets access to it and is able to change the state of the game to complete
     */
    public TreeMinigame(MinigameManager miniM){
        super("City Reforestation Game");
        this.miniM = miniM;
        panel = new TreePanel(this, miniM);
    }

    /**
     * this method is what creates the minigame
     * It load the window and places the panel in it
     */
    public void runMinigame() {
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(600, 500);

        this.add(panel);

        this.setLocationRelativeTo(null);
        this.setResizable(false);

        // tutorial to the game
        JOptionPane.showMessageDialog(this,
                "Welcome to the City Reforestation Game!\n" +
                "Drag the right tools to the tree at the right time.",

                "Game Tutorial",
                JOptionPane.INFORMATION_MESSAGE);
        
        this.setVisible(true);
    }
}

