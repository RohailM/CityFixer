package minigame.house;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;

/**
 * GUI of JFrame when the user adds a Materials object to the their toolbox
 * @author Brianna
 */
public class AddToToolBox extends JFrame{

    /**
     * Set up constructor
     */
    public AddToToolBox(){
        setTitle("Add to ToolBox");
        setSize(500, 150);
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

