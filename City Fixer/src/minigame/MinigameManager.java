package minigame;

import minigame.lake.LakeMinigame;

import java.awt.EventQueue;
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
    private boolean[] minigameCompleted = {false, false, false, false, false, false};
	//0 House
	//1 Lake
	//2 Tree
	//3 Factory
	//4 Cell Tower

    private LakeMinigame lakeM;
    private TreeMinigame treeM;
    private IntroductionGUI solarPanelM;
    private FactoryMinigame factoryM; 

    public MinigameManager(Map map){
        m = map;
    }

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
            case 4:
                System.out.println("cell tower");
                break;

        }
    }

    public int getMinigame(){
		return minigame;
	}
    
    public DialogueBox getDialogueBox(){
        return dialogB;
    }
    
    public boolean getMinigameCompleted(int id){
        return minigameCompleted[id];
    }
    
    public void setMinigameComplete(int id, boolean state){
        minigameCompleted[id] = state;
        m.fixComponent(id);
    }
    
    public void setDialogueBox(){
        dialogB = new DialogueBox(m.tileSize*2, m.tileSize, m.screenWidth - (m.tileSize*4), m.tileSize*4, minigame);
    }
    
	public void setMinigame(int mini){
		minigame = mini;
	}
	
    public void repaint(){
        m.repaint();
    }
}
