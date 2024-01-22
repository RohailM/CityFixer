package minigame;

import java.awt.*;

public class DialogueBox {

    private int x;
    private int y;
    private int width;
    private int height;
    private int index;
    private final int DIALOGUE_SIZE = 20;
    private String[] dialogue = new String[DIALOGUE_SIZE];
    private String[] option = new String[6];

    public DialogueBox(int x, int y, int w, int h, int minigameIndex){
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        index = minigameIndex;
        setDialogue();
    }

    private void setDialogue(){
        dialogue[0] = "The houses in this city use inefficient and \nharmful energy, help by using renewable and \neco-friendly energy";
        option[0] = "add solar panels";

        dialogue[1] = "The lake's water was polluted with toxic \nquemicals and litter, help by cleaning the \nwater";
        option[1] = "Clean the lake";

        dialogue[2] = "Deforestation is a big problem, help to bring \nback the flora of the city";
        option[2] = "Plant new trees";

        dialogue[3] = "placeholder garbage";
        option[3] = "placeholder";

        dialogue[4] = "placeholder factory";
        option[4] = "placeholder";

        dialogue[5] = "placeholder cell tower";
        option[5] = "placeholder";
    }
    
    public void drawBox(Graphics2D g2){

        Color c = new Color(0, 0, 0,180);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 32, 32);

        Color b = new Color(255,255,255);
        g2.setColor(b);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x+5, y+5, width-10, height-10, 22, 22);

        g2.drawLine(x+(32*9), y+5, x+(32*9), y+118);
        g2.drawLine(x+(32*9), y+60, x+470, y+60);

        int yy = y;
        for(String line : dialogue[index].split("\n")){
            g2.drawString(line, x+32, yy+32);
            yy += 15;
        }
        g2.drawString(option[index], x+(32*10), y+35);
        g2.drawString("exit", x+(32*10), y+95);
    }    
}
