package minigame.trees;

/**
 * @author Lucas
 * This class is used for the seed object, since it has the same functions of any other tool in the minigame it is a child class of the parent class "Tools"
 */
public class Seeds extends Tools {

    /**
     * parameters are not set here and in the treePanel to make it easier to change any value later (together with any other tool object)
     * @param x
     * @param y
     * @param imageName
     */
    public Seeds(int x, int y, String imageName) {
        super(x, y, imageName);
    }
    
}
