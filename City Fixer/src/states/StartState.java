package states;
import main.Game;
import main.GameState;

import java.awt.Component;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class StartState implements GameState {
    private Game game;
    private JPanel panel;

    public StartState(Game game) {
        this.game = game;
        initUI();
    }

    private void initUI() {
        // Initialize the panel and set its background to black
        panel = new JPanel() {
            @Override
            protected void paintComponent(java.awt.Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        panel.setPreferredSize(null);
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Transition to PlayingState when the screen is clicked
                game.setState(new PlayingState(game));
            }
        });
    }

    @Override
    public void update() {
        // No update logic for the start screen
    }

    @Override
    public Component getComponent() {
        return panel;
    }
}
