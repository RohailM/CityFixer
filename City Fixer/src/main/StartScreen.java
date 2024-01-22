package main;

import javax.swing.*;

//import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * The StartScreen class represents the initial login screen
 * where the user can input their username and start the game.
 * It checks for saved game data and initializes the game accordingly.
 * @author Brianna Nguyen (GUI) and Rohail Memon (Implementation and User Interaction)
 */
public class StartScreen {

    private Color darkGreen = new Color(46, 135, 28);
    private Color sage = new Color(144, 238, 144);
    private JFrame frame;
    private BiConsumer<String, Boolean> onGameStart; // Callback for when the game starts (from ChatGPT)

    /**
     * Constructs the start screen and sets the callback for when the game starts
     * @param onGameStart Callback function to handle game start event
     */
    public StartScreen(BiConsumer<String, Boolean> onGameStart) {
        this.onGameStart = onGameStart; // From ChatGPT
        createAndShowGUI();
    }
    
    /**
     * 
     * @author Brianna Nguyen
     * Creates the GUI components of the start screen and sets up their properties and layouts
     */
    private void createAndShowGUI() {
        frame = createFrame();
        JPanel panel = createPanel();
        JLabel label = createLabel();
        JLabel imageLabel = createImageLabel();
        JTextField login = createLoginField();
        JButton button = createButton();

        panel.add(label);
        panel.add(login);
        panel.add(button);

        // Components are positioned explicitly using their absolute coordinates (x, y)
        panel.setLayout(null);

        // Moves and resizes these objects
        label.setBounds(120, 50, 400, 50);
        login.setBounds(195, 110, 200, 30);
        button.setBounds(195, 150, 200, 30);

        // Set the bounds for the imageLabel to position it at the bottom middle of the frame
        imageLabel.setBounds(55, 150, 500, 500);

        // Add the imageLabel directly to the frame's content pane (where everything happens in your JFrame)
        frame.getContentPane().add(imageLabel);


        // Add the panel directly to the frame's content pane
        frame.getContentPane().add(panel); 

        /**
         * Set action listener for the button
         * @author Rohail Memon
         */
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	handleLoginAction(login);
            }
        });


        frame.setVisible(true);
        
        // Ensure the frame is focused when clicked
        frame.getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.requestFocusInWindow();
            }
        });
        
        frame.requestFocusInWindow();


    }
    
    /**
     * Handles the action when the login button is pressed.
     * Checks the entered username and proceeds with starting the game.
     * @author Rohail Memon
     * @param login The JTextField containing the entered username.
     */
    private void handleLoginAction(JTextField login) {
        String enteredLogin = login.getText().trim();
        String filePath = "res/saves/" + enteredLogin + "_savedMap.txt";
        File savedMapFile = new File(filePath);

        if (enteredLogin.isEmpty() || enteredLogin.equals("Enter username here")) {
            JOptionPane.showMessageDialog(frame, "Please enter a username.", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (enteredLogin.length() < 3) {
            JOptionPane.showMessageDialog(frame, "Username must be at least 3 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Check if a saved map file exists for the user
            boolean hasSavedMap = savedMapFile.exists();
            onGameStart.accept(enteredLogin, hasSavedMap);
            frame.dispose(); // Close the current frame
        }
    }

    /**
     * 
     * @author Brianna Nguyen
     *
     */
    private JFrame createFrame() {
        JFrame frame = new JFrame("City Fixer");
        frame.setSize(622, 685);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true); // make the frame itself focusable
        frame.setFocusableWindowState(true); // ensure it can request focus when window state is focused
        return frame;
    }

    /**
     * 
     * @author Brianna Nguyen
     *
     */
    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(sage);
        return panel;
    }

    /**
     * 
     * @author Brianna Nguyen
     *
     */
    private JLabel createLabel() {
        JLabel label = new JLabel("Welcome to City Fixer!");
        label.setFont(new Font("Dialog", Font.BOLD, 35));
        label.setForeground(darkGreen);
        return label;
    }

    /**
     * 
     * @author Brianna Nguyen
     *
     */
    private JLabel createImageLabel() {
        ImageIcon imageIcon = new ImageIcon("res/images/city.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        return imageLabel;
    }

    /**
     * 
     * @author Brianna Nguyen
     *
     */
    private JTextField createLoginField() {
        JTextField loginField = new JTextField("Enter username here");
        loginField.setForeground(Color.GRAY);
    
        // Add a focus listener to handle the behaviour of the placeholder text
        loginField.addFocusListener(new FocusListener() {
            /**
             * Invoked when the login field gains focus
             * Clears the placeholder text when the field is focused if the placeholder text is currently displayed.
             * @author Rohail Memon
             * @param e The focus event
             */
            public void focusGained(FocusEvent e) {
                if (loginField.getText().equals("Enter username here")) {
                    loginField.setText("");
                    loginField.setForeground(Color.BLACK);
                }
            }
            
            /**
             * Invoked when the login field loses focus
             * Sets the placeholder text if the field is empty when focus is lost.
             * @author Rohail Memon
             * @param e The focus event
             */
            public void focusLost(FocusEvent e) {
                if (loginField.getText().isEmpty()) {
                    loginField.setForeground(Color.GRAY);
                    loginField.setText("Enter username here");
                }
            }
        });
    
        return loginField;
    }
    
    /**
     * 
     * @author Brianna Nguyen
     *
     */
    private JButton createButton() {
        JButton button = new JButton();
        button.setText("Start Game!");
        return button;
    }
}

