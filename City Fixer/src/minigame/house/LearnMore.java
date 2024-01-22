package minigame.house;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 * GUI of JFrame when the user clicks on the LearnMore button
 * @author Brianna
 */
public class LearnMore extends JFrame {
    
    /**
     * Set up constructor
     */
    public LearnMore (){
        setTitle("Learn More");
        setSize(500, 170);
        setLocationRelativeTo(null);

        // From ChatGPT
        // Allows the user to close this frame without closing any of the other frames open
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }
}
