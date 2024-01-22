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
import java.util.function.Consumer;

public class StartScreen {

    private Color DARKGREEN = new Color(46, 135, 28);
    private Color SAGE = new Color(144, 238, 144);
    private JFrame frame;
    private Consumer<String> onGameStart;


    public StartScreen(Consumer<String> onGameStart) {
        this.onGameStart = onGameStart;
        createAndShowGUI();
    }

    // Creating the GUI
    private void createAndShowGUI() {
        JFrame frame = createFrame();
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

        // Add ActionListener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String enteredLogin = login.getText().trim();

                if (enteredLogin.isEmpty() || enteredLogin.equals("Enter username here")) {
                    JOptionPane.showMessageDialog(frame, "Please enter a username.", "Error", JOptionPane.ERROR_MESSAGE);
                } else if (enteredLogin.length() < 3) {
                    JOptionPane.showMessageDialog(frame, "Username must be at least 3 characters long.", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Username is valid, proceed to open new frame / start game
                    onGameStart.accept(enteredLogin);
                    frame.dispose(); // Close the current frame
                }

            }
        });


        frame.setVisible(true);

        frame.getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                frame.requestFocusInWindow();
            }
        });
        
        frame.requestFocusInWindow();


    }


    private void openNewFrame(String enteredLogin) {
        onGameStart.accept(enteredLogin);   
    }

    private JFrame createFrame() {
        JFrame frame = new JFrame("City Fixer");
        frame.setSize(622, 685);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true); // make the frame itself focusable
        frame.setFocusableWindowState(true); // ensure it can request focus when window state is focused
        return frame;
    }


    private JPanel createPanel() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(SAGE);
        return panel;
    }


    private JLabel createLabel() {
        JLabel label = new JLabel("Welcome to City Fixer!");
        label.setFont(new Font("Dialog", Font.BOLD, 35));
        label.setForeground(DARKGREEN);
        return label;
    }


    private JLabel createImageLabel() {
        ImageIcon imageIcon = new ImageIcon("res/images/city.png");
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(400, 400, java.awt.Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setBounds(0, 0, imageIcon.getIconWidth(), imageIcon.getIconHeight());
        return imageLabel;
    }


    private JTextField createLoginField() {
        JTextField loginField = new JTextField("Enter username here");
        loginField.setForeground(Color.GRAY);
    
        loginField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (loginField.getText().equals("Enter username here")) {
                    loginField.setText("");
                    loginField.setForeground(Color.BLACK);
                }
            }
    
            public void focusLost(FocusEvent e) {
                if (loginField.getText().isEmpty()) {
                    loginField.setForeground(Color.GRAY);
                    loginField.setText("Enter username here");
                }
            }
        });
    
        return loginField;
    }

    private JButton createButton() {
        JButton button = new JButton();
        button.setText("Start Game!");
        return button;
    }
}

