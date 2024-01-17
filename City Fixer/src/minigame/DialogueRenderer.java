package minigame;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import main.Map;

public class DialogueRenderer {
    private Map map;
    private DialogueManager dialogueManager;

    public DialogueRenderer(Map map, DialogueManager dialogueManager) {
        this.map = map;
        this.dialogueManager = dialogueManager;
    }

    public void drawBox(Graphics2D g2) {
        int x = map.tileSize * 2;
        int y = map.tileSize;
        int width = map.screenWidth - (map.tileSize * 4);
        int height = map.tileSize * 4;

        Color semiTransparentBlack = new Color(0, 0, 0, 180);
        g2.setColor(semiTransparentBlack);
        g2.fillRoundRect(x, y, width, height, 32, 32);

        Color white = new Color(255, 255, 255);
        g2.setColor(white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x + 5, y + 5, width - 10, height - 10, 22, 22);
        g2.drawLine(x + (map.tileSize * 9), y + 5, x + (map.tileSize * 9), y + 118);
        g2.drawLine(x + (map.tileSize * 9), y + 60, x + 470, y + 60);

        String currentDialogue = dialogueManager.getCurrentDialogue();
        g2.drawString(currentDialogue, x + map.tileSize, y + map.tileSize);
    }
}
