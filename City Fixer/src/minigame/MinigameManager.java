package minigame;

import minigame.lake.LakeMinigame;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.Arrays;

import minigame.factory.FactoryMinigame;
import minigame.house.IntroductionGUI;
import minigame.trees.TreeMinigame;
import main.Map;

/**
 * @author Lucas Namba
 * This class serves as the transition between the main game and the minigames
 */
public class MinigameManager{
    
    //The following variables are for loading minigames
    private Map m;
    private DialogueBox dialogB;
    private int minigame = -1;
    private boolean[] minigameCompleted = {false, false, false, false, false};
	//0 House
	//1 Lake
	//2 Tree
	//3 Factory
	//4 Cell Tower

    private LakeMinigame lakeM;
    private TreeMinigame treeM;
    private IntroductionGUI solarPanelM;
    private FactoryMinigame factoryM; 

	/**
     * The contructor is only used so it can draw the dialogue box in the map and for the tile_size 
     * @param map
     */
    public MinigameManager(Map map){
        m = map;
    }

	/**
     * This method is key to the game, as it is what run each minigame depending on the 
	 * @param minigameID 
     */
    public void runMinigame(int minigameID){
        switch(minigameID){
            case 0:
        		EventQueue.invokeLater(new Runnable() {
        			public void run() {
        				try {
        					solarPanelM = new IntroductionGUI(MinigameManager.this);
        					solarPanelM.setVisible(true);
        					solarPanelM.requestFocusInWindow();
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
        			}
        		});                
                break;
            case 1:
        		EventQueue.invokeLater(new Runnable() {
        			public void run() {
        				try {
        	                lakeM = new LakeMinigame(MinigameManager.this);
        	                lakeM.createAndShowGUI();      					
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
        			}
        		}); 

                break;
            case 2:
        		EventQueue.invokeLater(new Runnable() {
        			public void run() {
        				try {
        	                treeM = new TreeMinigame(MinigameManager.this);
        	                treeM.runMinigame();        					
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
        			}
        		}); 
                break;
            case 3:
        		EventQueue.invokeLater(new Runnable() {
        			public void run() {
        				try {
        					factoryM = new FactoryMinigame(MinigameManager.this);        					
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
        			}
        		}); 
				break;
            case 4:
                setMinigameComplete(minigameID, true);
                break;
        }
    }

	/**
     * This getter is used by the mouse class to decide which minigame dialogue box should be displayed
     * @return minigame
     */
    public int getMinigame(){
		return minigame;
	}
    
	/**
     * This getter is used by the map class to draw the dialogue box
     * @return
     */
    public DialogueBox getDialogueBox(){
        return dialogB;
    }
    
	/**
     * This method is used to determine if all the minigames have been completed
     * @param id of the minigame
     * @return true if the minigame has been completed
     */
    public boolean[] getMinigameCompleted(){
        return minigameCompleted;
    }
    
	/**
     * This method sets a minigame to be completed
     * @param id of the minigame that has been completed
     */
    public void setMinigameComplete(int id, boolean state){
        minigameCompleted[id] = state;
        m.fixComponent(id);
    }
    
	/**
     * This method is what creates a dialogue box
     */
    public void setDialogueBox(){
        dialogB = new DialogueBox(m.tileSize*2, m.tileSize, m.screenWidth - (m.tileSize*4), m.tileSize*4, minigame);
    }
    
	/**
     * This method is used by the mouse class to set what minigame/dialogue box should be displayed
     * @param id of the minigame
     */
	public void setMinigame(int mini){
		minigame = mini;
	}
	
	/**
     * This method is used by the map class so it can paint/"dispaint" the dialogue box
     */
    public void repaint(){
        m.repaint();
    }

	public void gameOver(Graphics g){
		//g.setFont(null);
		g.setColor(new Color(0,0,0));
		g.drawString("Game complete, the city has been restored", m.tileSize*2, m.tileSize);
	}
}
