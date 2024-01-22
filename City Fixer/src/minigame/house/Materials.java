package minigame.house;

/**
 * Creating new Materials the user can choose from in order to build a solar panel
 * @author Brianna
 */
public class Materials {

    String name;
    double cost;
    boolean durability;
    String conductivity;
    String description;

    /**
     * Set up constructor for Materials
     * @param name
     * @param cost
     * @param durability
     * @param conductivity
     * @param description
     */
    public Materials(String name, double cost, boolean durability, String conductivity, String description){
        this.name = name;
        this.cost = cost;
        this.durability = durability;
        this.conductivity = conductivity;
        this.description = description;
    }

    /**
     * Gets the name of that Materials object
     * @return name
     */
    public String getName(){
        return name;
    }

    /**
     * Gets the cost of that Materials object
     * @return cost
     */
    public double getCost(){
        return cost;
    }

    /**
     * Gets the durability of that Materials object
     * @return durability
     */
    public boolean getDurability(){
        return durability;
    }

    /**
     * Gets the conductivity of that Materials object
     * @return conductivity
     */
    public String getConductivity(){
        return conductivity;
    }

    /**
     * Gets the description of that Materials object
     * @return description
     */
    public String getDescription(){
        return description;
    }

    
}


