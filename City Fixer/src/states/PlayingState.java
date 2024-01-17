package states;

import java.awt.Component;

import inputs.MouseInputHandler;
import main.Game;
import main.GameState;
import main.Map;
import tile.TileManager;

public class PlayingState implements GameState {
    private Map map;
    private TileManager tileM; 


    public PlayingState(Game game) {
        map = new Map(game);
        this.tileM = new TileManager(map); 
        MouseInputHandler mouseHandler = new MouseInputHandler(game, map, tileM);
        map.addMouseListener(mouseHandler);
    }

    @Override
    public void update() {
        // Update game logic, check for state transitions
    }

    @Override
    public Component getComponent() {
        return map;
    }
}
