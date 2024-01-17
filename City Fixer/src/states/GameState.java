package states;

import java.awt.Component;

public interface GameState {
    void update();
    Component getComponent();
}