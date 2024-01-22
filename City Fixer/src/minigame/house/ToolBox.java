package minigame.house;

import java.util.ArrayList;

/**
 * Creating a ToolBox to store the materials the user thinks should be included to build a solar panel
 * @author Brianna
 */
public class ToolBox {

    ArrayList<Materials> materialChoices;

    /**
     * Set up contructor 
     */
    public ToolBox() {
        materialChoices = new ArrayList<>();
    }

    /**
     * Adds Materials object to the ArrayList
     * @param material
     */
    public void addMaterial(Materials material){
        materialChoices.add(material);
    }

    /**
     * Returns the ArrayList
     * @return ArrayList<Materials>
     */
    public ArrayList<Materials> getMaterialChoices() {
        return materialChoices;
    }
}