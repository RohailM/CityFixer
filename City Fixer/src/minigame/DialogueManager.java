package minigame;

public class DialogueManager {
    private String currentDialogue;
    private final String[] dialogue = new String[20];

    public DialogueManager() {
        setDialogue();
        speak(0); // Initialize the dialogue to the first message.
    }

    private void setDialogue() {
        dialogue[0] = "Welcome to the city fixer game!";
        // ... Set other dialogues as needed.
    }

    public void speak(int dialogueIndex) {
        if (dialogueIndex >= 0 && dialogueIndex < dialogue.length) {
            currentDialogue = dialogue[dialogueIndex];
        }
    }

    public String getCurrentDialogue() {
        return currentDialogue;
    }
}
