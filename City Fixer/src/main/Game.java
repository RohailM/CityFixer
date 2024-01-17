package main;

import javax.swing.JFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

import states.StartState;

public class Game extends JFrame implements ActionListener {
    private Timer timer;
    private final int DELAY = 15; // Delay in milliseconds (about 60 FPS)
    private GameState currentState;

    public Game() {
        initUI();
        initState();
        initGameLoop();
        setLocationRelativeTo(null);
    }

    private void initUI() {
        setTitle("City Fixer");
        setSize(622, 685);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
    }

    private void initState() {
        // Initialize the starting state of the game
    	currentState = new StartState(this);
        add(currentState.getComponent()); // Assuming each state provides a JPanel for rendering
    }

    private void initGameLoop() {
        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        update();
        repaint();
    }

    private void update() {
        currentState.update(); // Delegates the update logic to the current state
        repaint();
    }
    
    public GameState getGameState() {
        return currentState;
    }

    public void setState(GameState newState) {
        remove(currentState.getComponent());
        currentState = newState;
        add(currentState.getComponent());
        revalidate();
        repaint();
    }
}
