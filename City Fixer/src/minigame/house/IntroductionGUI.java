package minigame.house;

import javax.swing.*;

import minigame.MinigameManager;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

 /**
 * Create and initialize the introduction GUI of this minigame
 * @author Brianna
 */
public class IntroductionGUI extends JFrame {

    // Needs to be public in order to be acessed by other classes, static so that it belongs to the class rather than a speific instance and final as the value does not change
    public static final Color LIGHTBLUE = new Color(195, 223, 237);
    public static final Color BLUE = new Color(3,132,252);
    private Color DARKBLUE = new Color(6,99,186);
    MinigameManager miniM;

    /*
     * Set up constructor of SolarPanelMinigame
     */
    public IntroductionGUI(MinigameManager miniM){
    	this.miniM = miniM;
        setBasicFrameFeatures();

        JPanel panel = createPanel();
        panel.add(createTitleLabel());
        
        JLabel minigameDescription = createMinigameDescription();
        minigameDescription.setBounds(0, 50, 50, 50);
        panel.add(minigameDescription);

        panel.add(new JLabel("")); 

        JLabel engineerLabel = createEngineerLabel();
        panel.add(engineerLabel);

        JTextField engineerType = enterEngineerType();
        JTextField engineerName = enterEngineerName();
        JTextField engineerGender = enterEngineerGender();
        panel.add(engineerType);
        panel.add(engineerName);
        panel.add(engineerGender);

        JButton startButton = createStartButton();
        panel.add(startButton);

        JLabel imageLabel = createImageLabel();
        imageLabel.setBounds(55, 150, 500, 500);
        getContentPane().add(imageLabel);

        add(panel);

        // From ChatGPT
        startButton.addActionListener(new ActionListener() {
            /**
             * When the button is pushed the following actions will be peformed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredEngineerType = engineerType.getText();
                String enteredEngineerName = engineerName.getText();
                String enteredEngineerGender = engineerGender.getText();
                MinigameGUI mainMinigame = new MinigameGUI(enteredEngineerType, enteredEngineerName, enteredEngineerGender, miniM);
                dispose();
                mainMinigame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                mainMinigame.setVisible(true);
            }
        });
    }
    
    /**
     * Apply basic features of frame (size, name, location, etc.)
     */
    private void setBasicFrameFeatures(){
        setTitle("Solar Panel Minigame Intro");
        setSize(622, 685);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                requestFocusInWindow();
            }
        });
        
        requestFocusInWindow();
    }

    /**
     * Creates and sets basic features of the JPanel
     * @return JPanel
     */
    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(LIGHTBLUE);
        return panel;
    }

    /**
     * Creates the title as a JLabel
     * @return JLabel
     */
    private JLabel createTitleLabel() {
        JLabel titleLabel = new JLabel("Welcome to the Solar Panel Minigame!");
        titleLabel.setFont(new Font("Dialog", Font.BOLD, 30));
        titleLabel.setForeground(BLUE);
        return titleLabel;
    }

    /**
     * Creates the description of the minigame as a JLabel
     * @return JLabel
     */
    private JLabel createMinigameDescription(){
        // From ChatGPT
        // <html> Formats the text in Java Swing components - <center> will center the text 
        JLabel minigameDescription = new JLabel("<html><center>In the minigame, you will work as one of the many engineers that build solar panels<br> and decide which materials are needed to create a solar panel! You will investigate<br> different materials you could use and determine which of the following<br> will make the most efficient solar panel.</center></html>");
        minigameDescription.setForeground(DARKBLUE);
        return minigameDescription;
    }

    /**
     * Creates the text "Start by creating your own engineer" as a JLabel
     * @return JLabel
     */
    private JLabel createEngineerLabel(){
        JLabel engineerLabel = new JLabel("Start by creating your own engineer!");
        engineerLabel.setForeground(DARKBLUE);
        engineerLabel.setFont(new Font("Dialog", Font.BOLD, 20));
        return engineerLabel;
    }

    /**
     * Allows the user to edit the JTextField and add the engineerType they would like to be
     * @return JTextField
     */
    private JTextField enterEngineerType() {
    	JTextField engineerType = new JTextField("Enter the engineer you would like to be (Electrical, Mechanical or Industial)               ");
    	engineerType.addFocusListener(new FocusListener() {
            /**
             * Invoked when the engineer type field gains focus
             * Clears the placeholder text when the field is focused if the placeholder text is currently displayed.
             * @author Rohail Memon
             * @param e The focus event
             */
    		public void focusGained(FocusEvent e) {
    			if (engineerType.getText().equals("Enter the engineer you would like to be (Electrical, Mechanical or Industial)               ")) {
                   engineerType.setText("");
                   engineerType.setForeground(Color.BLACK);
                }
            }
            
            /**
             * Invoked when the engineer type field loses focus
             * Sets the placeholder text if the field is empty when focus is lost.
             * @author Rohail Memon
             * @param e The focus event
             */
            public void focusLost(FocusEvent e) {
                if (engineerType.getText().isEmpty()) {
                   engineerType.setForeground(Color.GRAY);
                   engineerType.setText("Enter the engineer you would like to be (Electrical, Mechanical or Industial)               ");
                }
            }
        });
        return engineerType;
    }

    /**
     * Allows the user to edit the JTextField and add the engineerName they would like to be
     * @return JTextField
     */
    private JTextField enterEngineerName() {
        JTextField name = new JTextField("Enter name                                                                                                                   ");
        name.addFocusListener(new FocusListener() {
            /**
             * Invoked when the login field gains focus
             * Clears the placeholder text when the field is focused if the placeholder text is currently displayed.
             * @author Rohail Memon
             * @param e The focus event
             */
            public void focusGained(FocusEvent e) {
                if (name.getText().equals("Enter name                                                                                                                   ")) {
                    name.setText("");
                    name.setForeground(Color.BLACK);
                }
            }
            
            /**
             * Invoked when the login field loses focus
             * Sets the placeholder text if the field is empty when focus is lost.
             * @author Rohail Memon
             * @param e The focus event
             */
            public void focusLost(FocusEvent e) {
                if (name.getText().isEmpty()) {
                    name.setForeground(Color.GRAY);
                    name.setText("Enter name                                                                                                                   ");
                }
            }
        });
        return name;
    }

    /**
     * Allows the user to edit the JTextField and add the engineerGender they would like to be
     * @return JTextField
     */
    private JTextField enterEngineerGender() {
        JTextField gender = new JTextField("Enter gender (Male or Female)");
        gender.addFocusListener(new FocusListener() {
            /**
             * Invoked when the gender field gains focus
             * Clears the placeholder text when the field is focused if the placeholder text is currently displayed.
             * @author Rohail Memon
             * @param e The focus event
             */
            public void focusGained(FocusEvent e) {
                if (gender.getText().equals("Enter gender (Male or Female)")) {
                    gender.setText("");
                    gender.setForeground(Color.BLACK);
                }
            }
            
            /**
             * Invoked when the gender field loses focus
             * Sets the placeholder text if the field is empty when focus is lost.
             * @author Rohail Memon
             * @param e The focus event
             */
            public void focusLost(FocusEvent e) {
                if (gender.getText().isEmpty()) {
                    gender.setForeground(Color.GRAY);
                    gender.setText("Enter gender (Male or Female)");
                }
            }
        });
        
        return gender;
    }

    /**
     * Creates the start button
     * @return JButton
     */
    private JButton createStartButton() {
        JButton startButton = new JButton();
        startButton.setText("          Start Minigame!          ");
        return startButton;
    }

    /**
     * Creates solar panel image
     * @return JLabel
     */
    private JLabel createImageLabel() {
        ImageIcon imageIcon = new ImageIcon("res/images/solarPanel/solar_panel.png");
        Image image = imageIcon.getImage();

        // java.awt.Image.SCALE_SMOOTH from ChatGPT
        // Allows the image to be better quality
        Image newImage = image.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        JLabel imageLabel = new JLabel(imageIcon);
        return imageLabel;
    }
}

