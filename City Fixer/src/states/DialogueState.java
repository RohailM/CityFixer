package states;

import java.awt.Component;
import java.awt.Graphics2D;

import main.Game;
import main.GameState;
import main.Map;
import minigame.DialogueManager;
import minigame.DialogueRenderer;

public class DialogueState implements GameState {
    private Map map;
    private Game game;
    private DialogueManager dialogueManager;
    private DialogueRenderer dialogueRenderer;
    private int dialogueIndex; // index of the current dialogue to display

    public DialogueState(Game game, int dialogueIndex) {
        this.game = game;
        this.map = new Map(game); // Initialize the map
        this.dialogueIndex = dialogueIndex;
        this.dialogueManager = new DialogueManager(); // Initialize the dialogue manager
        this.dialogueRenderer = new DialogueRenderer(map, dialogueManager); // Initialize the dialogue renderer

        // Set the initial dialogue based on the provided index
        dialogueManager.speak(dialogueIndex);
    }

    @Override
    public void update() {
        // Update the dialogue logic
        // This could involve waiting for user input, progressing through the dialogue, etc.
        
        // Example: If the dialogue is over, transition back to the PlayingState
        // if (dialogueManager.isDialogueOver()) {
        //     game.setState(new PlayingState(game));
        // }
        
        // Example: Handle user input to progress through the dialogue or make choices
        // if (userPressedNext) {
        //     dialogueManager.next(); // Move to the next piece of dialogue
        // }
    }

    @Override
    public Component getComponent() {
        return map;
    }
    
    public void draw(Graphics2D g2) {
    
    }
}
