package minigame.house;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import minigame.MinigameManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI of the main portion of the Minigame
 * @author Brianna
 */
public class MinigameGUI extends JFrame {

    ToolBox toolbox = new ToolBox();
    Materials materials;
    private int count;
    MinigameManager miniM;

    /**
     * Set up constructor
     * @param engineerType
     * @param engineerName
     * @param engineerGender
     */
    public MinigameGUI(String engineerType, String engineerName, String engineerGender, MinigameManager miniM) {
    	this.miniM = miniM;
        setBasicFrameFeatures();
        getContentPane().setBackground(IntroductionGUI.LIGHTBLUE);

        JPanel infoPanel = createInfoPanel(engineerType, engineerName, engineerGender);
        infoPanel.setBounds(150, 0, 200, 100);
        infoPanel.setLayout(new GridLayout(3, 1));
        add(infoPanel);

        JPanel imagePanel = createImagePanel(engineerGender);
        imagePanel.setBounds(0,0,150, 100);
        add(imagePanel);

        JPanel instructionsPanel = createIntructionsPanel();
        instructionsPanel.setBounds(350, 0, 272, 100);
        instructionsPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        add(instructionsPanel);

        JPanel solarCellsPanel = createSiliconSolarCellsPanel();
        solarCellsPanel.setBounds(0, 100, 207, 220);
        add(solarCellsPanel);

        JPanel magnesiumPanel = createMagnsiumPanel();
        magnesiumPanel.setBounds(207, 100, 207, 220);
        add(magnesiumPanel);

        JPanel aluminumFramePanel = createAluminumFramePanel();
        aluminumFramePanel.setBounds(414, 100, 207, 220);
        add(aluminumFramePanel);

        JPanel glassSheetPanel = createGlassSheetPanel();
        glassSheetPanel.setBounds(0, 320, 207, 220);
        add(glassSheetPanel);

        JPanel standardWirePanel = createStandardWirePanel();
        standardWirePanel.setBounds(207, 320, 207, 220);
        add(standardWirePanel);

        JPanel busWirePanel = createBusWirePanel();
        busWirePanel.setBounds(414, 320, 207, 220);
        add(busWirePanel);

        // new BorderLayout() from ChatGPT
        // Organizes the layout into 5 sections (NORTH, SOUTH, WEST, EAST and CENTER) 
        setLayout(new BorderLayout());
        JButton submitButton = createSubmitButton();

        // From ChatGPT
        // Sets the submitButton to your specific dimensions
        submitButton.setPreferredSize(new Dimension(100, 30));

        // BorderLayout.SOUTH from ChatGPT
        // Places the submitButton in the SOUTH section
        add(submitButton, BorderLayout.SOUTH);

    }

    /**
     * Sets the basic featurs of the frame
     */
    private void setBasicFrameFeatures() {
        setTitle("Solar Panel Minigame");
        setSize(622, 685); // Adjusted size for better visibility
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Creates the panel that displays the engineer's type, name and gender
     * @param engineerType
     * @param engineerName
     * @param engineerGender
     * @return JPanel
     */
    private JPanel createInfoPanel(String engineerType, String engineerName, String engineerGender) {
        JPanel infoPanel = new JPanel(new FlowLayout());
        infoPanel.setBackground(Color.WHITE);

        JLabel nameLabel = new JLabel("Name: " + engineerName);
        JLabel typeLabel = new JLabel("Engineer Type: " + engineerType);
        JLabel genderLabel = new JLabel("Gender: " + engineerGender );

        infoPanel.add(nameLabel);
        infoPanel.add(typeLabel);
        infoPanel.add(genderLabel);

        return infoPanel;
    }

    /**
     * Creates a JLabel from an image
     * @param filename
     * @param width
     * @param height
     * @return JLabel
     */
    private JLabel createImage(String filename, int width, int height){
        ImageIcon imageIcon = new ImageIcon(filename);
        Image image = imageIcon.getImage();
        Image newImage = image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(newImage);
        JLabel imageLabel = new JLabel(imageIcon);
        return imageLabel;
    }

    /**
     * Adds engineer image to a panel according to the gender that the user picks
     * @param engineerGender
     * @return JPanel
     */
    private JPanel createImagePanel(String engineerGender) {
        JPanel imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);

        // Display the female engineer image if the gender is "Female"
        if (engineerGender.equalsIgnoreCase("Female")) {
            JLabel imageLabel = createImage("res/images/solarPanel/female_engineer.png", 200, 120);
            imagePanel.add(imageLabel);
        }
        // Display the male engineer image if the gender is "Male"
        else if (engineerGender.equalsIgnoreCase("Male")) {
            JLabel imageLabel = createImage("res/images/solarPanel/male_engineer.png", 200, 120);
            imagePanel.add(imageLabel);
        }

        return imagePanel;
    }

    /**
     * Creates the instructions panel 
     * @return JPanel
     */
    private JPanel createIntructionsPanel() {
        JPanel instructionsPanel = new JPanel();

        // <html> from ChatGPT - reformats your text in Java Swing components
        // <br> moves the text after to the next line
        JLabel instructions = new JLabel("<html>There are 5 CORRECT materials needed<br>to build a solar panel. Click on the item that<br>you think is needed to constuct a solar panel and<br>it will be added to your toolbox! You may also<br> read the characteristics of each materials to<br> see which is the most suitable!<html>");
        instructions.setFont(new Font("Dialog", Font.PLAIN, 11));
        instructionsPanel.add(instructions);

        return instructionsPanel;
    }

    /**
     * Creates a button to learn more about a certain material
     * @return JButton
     */
    private JButton createMaterialsInfoButton() {
        JButton button = new JButton();
        button.setText("Learn More");
        return button;
    }

    /**
     * Creates the panel with the solar cells material
     * @return JPanel
     */
    private JPanel createSiliconSolarCellsPanel(){
        JPanel siliconSolarCellsPanel = new JPanel();
        siliconSolarCellsPanel.setBackground(IntroductionGUI.LIGHTBLUE);

        Materials siliconSolarCells = new Materials("Silicon Solar Cells", 0.28, true, "low", "Convert the Sun's light into electricity using the photovoltaic effect" );

        JLabel materialNameLabel = new JLabel(siliconSolarCells.getName());
        materialNameLabel.setFont(new Font("Dialog", Font.BOLD, 12));
        siliconSolarCellsPanel.add(materialNameLabel);

        JLabel image = createImage("res/images/solarPanel/solar_cell.jpg", 100, 90);
        siliconSolarCellsPanel.add(image);

        JButton learnMoreButton = createMaterialsInfoButton();

        // From ChatGPT
        learnMoreButton.addActionListener(new ActionListener() {
            @Override
            /**
             * When the learnMoreButton is clicked the following actions occur
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                LearnMore frame = new LearnMore();
                JPanel panel = new JPanel();
                JLabel costLabel = new JLabel("Cost: " + "$" + siliconSolarCells.getCost());
                JLabel durabilityLabel = new JLabel("Durability: " + "" + siliconSolarCells.getDurability());
                JLabel conductivityLabel = new JLabel("Conductivity: " + siliconSolarCells.getConductivity());
                JLabel descriptionLabel = new JLabel("Description: " + siliconSolarCells.getDescription());
                panel.add(costLabel);
                panel.add(durabilityLabel);
                panel.add(conductivityLabel);
                panel.add(descriptionLabel);
                panel.setLayout(new GridLayout(4, 1));
                frame.add(panel);
                frame.setVisible(true);
            }
        });
        siliconSolarCellsPanel.add(learnMoreButton);

        JButton addMaterial = new JButton("Add to ToolBox");
        addMaterial.setFont(new Font("Dialog", Font.BOLD, 9));

        // From ChatGPT
        addMaterial.addActionListener(new ActionListener() {
            @Override
            /**
             * When the addMaterial is clicked the following actions occur
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                // Adds this Materials object to the toolbox 
                toolbox.addMaterial(siliconSolarCells);
                AddToToolBox frameAddedMaterial = new AddToToolBox();
                JPanel panel = new JPanel();
                panel.setBackground(Color.GREEN);
                JLabel sucessfullyAdded = new JLabel("Your material has been sucessfully added!");

                // <html> from ChatGPT - reformats your text in Java Swing components
                // <br> moves the text after to the next line
                JLabel importance = new JLabel("<html>Solar cells provide more energy than other conventional sources with<br> an additional advantage of being light weight and cost effective. Developing<br> cheaper alternatives to solar cells such as amorphous silicon and polycrystalline<br> silicon are also in the pipeline.");
                panel.add(sucessfullyAdded);
                panel.add(importance);
                frameAddedMaterial.add(panel);
                frameAddedMaterial.setVisible(true);
            }
        });
        siliconSolarCellsPanel.add(addMaterial);

        return siliconSolarCellsPanel;
    }

    /**
     * Creates the panel with the magnesium material
     * @return JPanel
     */
    private JPanel createMagnsiumPanel(){
        JPanel magnesiumPanel = new JPanel();
        magnesiumPanel.setBackground(IntroductionGUI.LIGHTBLUE);

        Materials magnesium = new Materials("      Magnesium    ", 2.55, true, "low", "A mineral that when used as part of a cement mixture." );

        JLabel materialNameLabel = new JLabel(magnesium.getName());
        materialNameLabel.setFont(new Font("Dialog", Font.BOLD, 12));
        magnesiumPanel.add(materialNameLabel);

        JLabel image = createImage("res/images/solarPanel/magnesium.jpg", 100, 90);
        magnesiumPanel.add(image);

        JButton learnMoreButton = createMaterialsInfoButton();

        // From ChatGPT
        learnMoreButton.addActionListener(new ActionListener() {
            @Override
            /**
             * When the learnMoreButton is clicked the following actions occur
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                LearnMore frame = new LearnMore();
                JPanel panel = new JPanel();
                JLabel costLabel = new JLabel("Cost: " + "$" + magnesium.getCost());
                JLabel durabilityLabel = new JLabel("Durability: " + "" + magnesium.getDurability());
                JLabel conductivityLabel = new JLabel("Conductivity: " + magnesium.getConductivity());
                JLabel descriptionLabel = new JLabel("Description: " + magnesium.getDescription());
                panel.add(costLabel);
                panel.add(durabilityLabel);
                panel.add(conductivityLabel);
                panel.add(descriptionLabel);
                panel.setLayout(new GridLayout(4, 1));
                frame.add(panel);
                frame.setVisible(true);
            }
        });
        magnesiumPanel.add(learnMoreButton);

        JButton addMaterial = new JButton("Add to ToolBox");
        addMaterial.setFont(new Font("Dialog", Font.BOLD, 9));

        // From ChatGPT
        addMaterial.addActionListener(new ActionListener() {
            @Override
            /**
             * When the addMaterial button is clicked the follow actions occur
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                AddToToolBox frameAddedMaterial = new AddToToolBox();
                JPanel panel = new JPanel();
                panel.setBackground(Color.RED);
                JLabel sucessfullyAdded = new JLabel("This material is NOT NEEDED to construct a solar panel!");
                JLabel importance = new JLabel("<html>Magnesium would be used to make solar cells but it is not ideal<br> to use this material because it does not give efficiency.<br> High-purity silicon gives an efficiency of 10 % to 12 %.");
                panel.add(sucessfullyAdded);
                panel.add(importance);
                frameAddedMaterial.add(panel);
                frameAddedMaterial.setVisible(true);   
            }
        });
        magnesiumPanel.add(addMaterial);

        return magnesiumPanel;
    }

     /**
     * Creates the panel with the aluminum frame material
     * @return JPanel
     */
    private JPanel createAluminumFramePanel(){
        JPanel aluminumFramePanel = new JPanel();
        aluminumFramePanel.setBackground(IntroductionGUI.LIGHTBLUE);

        Materials aluminumFrame = new Materials("Aluminum Frame", 591.25, true, "high", "Used for door frames, window frames,railing frames and roof trusses" );

        JLabel materialNameLabel = new JLabel(aluminumFrame.getName());
        materialNameLabel.setFont(new Font("Dialog", Font.BOLD, 12));
        aluminumFramePanel.add(materialNameLabel);

        JLabel image = createImage("res/images/solarPanel/aluminum_frame.jpg", 100, 90);
        aluminumFramePanel.add(image);

        JButton learnMoreButton = createMaterialsInfoButton();

        // From ChatGPT
        learnMoreButton.addActionListener(new ActionListener() {
            @Override
            /**
             * When the learnMoreButton is clicked the following actions occur
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                LearnMore frame = new LearnMore();
                JPanel panel = new JPanel();
                JLabel costLabel = new JLabel("Cost: " + "$" + aluminumFrame.getCost());
                JLabel durabilityLabel = new JLabel("Durability: " + "" + aluminumFrame.getDurability());
                JLabel conductivityLabel = new JLabel("Conductivity: " + aluminumFrame.getConductivity());
                JLabel descriptionLabel = new JLabel("Description: " + aluminumFrame.getDescription());
                panel.add(costLabel);
                panel.add(durabilityLabel);
                panel.add(conductivityLabel);
                panel.add(descriptionLabel);
                panel.setLayout(new GridLayout(4, 1));
                frame.add(panel);
                frame.setVisible(true);
            }
        });
        aluminumFramePanel.add(learnMoreButton);

        JButton addMaterial = new JButton("Add to ToolBox");
        addMaterial.setFont(new Font("Dialog", Font.BOLD, 9));

        // From ChatGPT
        addMaterial.addActionListener(new ActionListener() {
            @Override
            /**
             * When the addMaterial button is clicked the following actions occur
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                // Adds Materials object to toolbox
                toolbox.addMaterial(aluminumFrame);
                AddToToolBox frameAddedMaterial = new AddToToolBox();
                JPanel panel = new JPanel();
                panel.setBackground(Color.GREEN);
                JLabel sucessfullyAdded = new JLabel("Your material has been sucessfully added!");
                JLabel importance = new JLabel("<html>Benefits to using aluminum frames are protecting against<br> inclement weather conditions or otherwise dangerous scenarios and<br> helping mount the solar panel at the desired angle");
                panel.add(sucessfullyAdded);
                panel.add(importance);
                frameAddedMaterial.add(panel);
                frameAddedMaterial.setVisible(true);
            }
        });
        aluminumFramePanel.add(addMaterial);

        return aluminumFramePanel;
    }

    /**
     * Creates the panel with the glass sheet material
     * @return JPanel
     */
    private JPanel createGlassSheetPanel(){
        JPanel glassSheetPanel = new JPanel();
        glassSheetPanel.setBackground(IntroductionGUI.LIGHTBLUE);

        Materials glassSheet = new Materials("     Glass Sheet     ", 19.97, true, "low", "Glass sheets are made from tempered glass Tempered solar panel glass also provides high strength, excellent transmissivity, and low reflection." );

        JLabel materialNameLabel = new JLabel(glassSheet.getName());
        materialNameLabel.setFont(new Font("Dialog", Font.BOLD, 12));
        glassSheetPanel.add(materialNameLabel);

        JLabel image = createImage("res/images/solarPanel/glass_sheet.jpg", 100, 90);
        glassSheetPanel.add(image);

        JButton learnMoreButton = createMaterialsInfoButton();

        // From ChatGPT
        learnMoreButton.addActionListener(new ActionListener() {
            @Override
            /**
             * When the learnMoreButton is clicked the following actions occur
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                LearnMore frame = new LearnMore();
                JPanel panel = new JPanel();
                JLabel costLabel = new JLabel("Cost: " + "$" + glassSheet.getCost());
                JLabel durabilityLabel = new JLabel("Durability: " + "" + glassSheet.getDurability());
                JLabel conductivityLabel = new JLabel("Conductivity: " + glassSheet.getConductivity());
                JLabel descriptionLabel = new JLabel("Description: " + glassSheet.getDescription());
                panel.add(costLabel);
                panel.add(durabilityLabel);
                panel.add(conductivityLabel);
                panel.add(descriptionLabel);
                panel.setLayout(new GridLayout(4, 1));
                frame.add(panel);
                frame.setVisible(true);
            }
        });
        glassSheetPanel.add(learnMoreButton);

        JButton addMaterial = new JButton("Add to ToolBox");
        addMaterial.setFont(new Font("Dialog", Font.BOLD, 9));

        // From ChatGPT
        addMaterial.addActionListener(new ActionListener() {
            @Override
            /**
             * When the addMaterial is clicked the following actions occur
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                // Adds Materials object to toolbox
                toolbox.addMaterial(glassSheet);
                AddToToolBox frameAddedMaterial = new AddToToolBox();
                JPanel panel = new JPanel();
                panel.setBackground(Color.GREEN);
                JLabel sucessfullyAdded = new JLabel("Your material has been sucessfully added!");
                JLabel importance = new JLabel("<html>Tempered glass offers up to four times more strength<br> than standard glass. This strength is critical as<br> the solar panel's front sheet requires lasting<br> protection against the elements.<html>");
                panel.add(sucessfullyAdded);
                panel.add(importance);
                frameAddedMaterial.add(panel);
                frameAddedMaterial.setVisible(true);
            }
        });
        glassSheetPanel.add(addMaterial);

        return glassSheetPanel;
    }

    /**
    * Creates the panel with the standard wire material
    * @return JPanel
    */
    private JPanel createStandardWirePanel(){
        JPanel standardWirePanel = new JPanel();
        standardWirePanel.setBackground(IntroductionGUI.LIGHTBLUE);

        Materials standardWire = new Materials("Standard 12V Wire", 19.36, true, "high", "Suitable for shorter installations with minimal distance between components" );

        JLabel materialNameLabel = new JLabel(standardWire.getName());
        materialNameLabel.setFont(new Font("Dialog", Font.BOLD, 12));
        standardWirePanel.add(materialNameLabel);

        JLabel image = createImage("res/images/solarPanel/standard_12v_wire.jpg", 100, 90);
        standardWirePanel.add(image);

        JButton learnMoreButton = createMaterialsInfoButton();

        // From ChatGPT
        learnMoreButton.addActionListener(new ActionListener() {
            @Override
            /**
             * When the learnMore button is clicked the following actions occur
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                LearnMore frame = new LearnMore();
                JPanel panel = new JPanel();
                JLabel costLabel = new JLabel("Cost: " + "$" + standardWire.getCost());
                JLabel durabilityLabel = new JLabel("Durability: " + "" + standardWire.getDurability());
                JLabel conductivityLabel = new JLabel("Conductivity: " + standardWire.getConductivity());
                JLabel descriptionLabel = new JLabel("Description: " + standardWire.getDescription());
                panel.add(costLabel);
                panel.add(durabilityLabel);
                panel.add(conductivityLabel);
                panel.add(descriptionLabel);
                panel.setLayout(new GridLayout(4, 1));
                frame.add(panel);
                frame.setVisible(true);
            }
        });
        standardWirePanel.add(learnMoreButton);

        JButton addMaterial = new JButton("Add to ToolBox");
        addMaterial.setFont(new Font("Dialog", Font.BOLD, 9));

        // From ChatGPT
        addMaterial.addActionListener(new ActionListener() {
            @Override
            /**
             * When the addMaterial is clicked the following actions occur
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                // Adds Materials object to toolbox
                toolbox.addMaterial(standardWire);
                AddToToolBox frameAddedMaterial = new AddToToolBox();
                JPanel panel = new JPanel();
                panel.setBackground(Color.GREEN);
                JLabel sucessfullyAdded = new JLabel("Your material has been sucessfully added!");
                JLabel importance = new JLabel("<html>A 12V wire helps regulate the amount of energy being<br> transferred into your inverter, aiding with the sustainability and<br> efficiency of the solar module.<html>");
                panel.add(sucessfullyAdded);
                panel.add(importance);
                frameAddedMaterial.add(panel);
                frameAddedMaterial.setVisible(true);
            }
        });
        standardWirePanel.add(addMaterial);

        return standardWirePanel;
    }

    /**
    * Creates the panel with the bus  wire material
    * @return JPanel
    */
    private JPanel createBusWirePanel(){
        JPanel busWirePanel = new JPanel();
        busWirePanel.setBackground(IntroductionGUI.LIGHTBLUE);

        Materials busWire = new Materials("               Bus Wire              ", 17.98, true, "high", "Flexible and easily shaped/contoured into place, and easily welded and soldered" );

        JLabel materialNameLabel = new JLabel(busWire.getName());
        materialNameLabel.setFont(new Font("Dialog", Font.BOLD, 12));
        busWirePanel.add(materialNameLabel);

        JLabel image = createImage("res/images/solarPanel/bus_wire.jpg", 100, 90);
        busWirePanel.add(image);

        JButton learnMoreButton = createMaterialsInfoButton();

        // From ChatGPT
        learnMoreButton.addActionListener(new ActionListener() {
            @Override
            /**
             * When the learnMore button is clicked the following actions occur
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                LearnMore frame = new LearnMore();
                JPanel panel = new JPanel();
                JLabel costLabel = new JLabel("Cost: " + "$" + busWire.getCost());
                JLabel durabilityLabel = new JLabel("Durability: " + "" + busWire.getDurability());
                JLabel conductivityLabel = new JLabel("Conductivity: " + busWire.getConductivity());
                JLabel descriptionLabel = new JLabel("Description: " + busWire.getDescription());
                panel.add(costLabel);
                panel.add(durabilityLabel);
                panel.add(conductivityLabel);
                panel.add(descriptionLabel);
                panel.setLayout(new GridLayout(4, 1));
                frame.add(panel);
                frame.setVisible(true);
            }
        });
        busWirePanel.add(learnMoreButton);

        JButton addMaterial = new JButton("Add to ToolBox");
        addMaterial.setFont(new Font("Dialog", Font.BOLD, 9));

        // From ChatGPT
        addMaterial.addActionListener(new ActionListener() {
            @Override
            /**
             * When the addMaterial button is clicked the following actions occur
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                // Adds Materials object to toolbox
                toolbox.addMaterial(busWire);
                AddToToolBox frameAddedMaterial = new AddToToolBox();
                JPanel panel = new JPanel();
                panel.setBackground(Color.GREEN);
                JLabel sucessfullyAdded = new JLabel("Your material has been sucessfully added!");
                JLabel importance = new JLabel("<html>Bus wires are used to connect the silicon solar cells in parallel. Bus<br> wires are covered in a thin layer for easy soldering and are<br> thick enough to carry electrical currents.<html>");
                panel.add(sucessfullyAdded);
                panel.add(importance);
                frameAddedMaterial.add(panel);
                frameAddedMaterial.setVisible(true);
            }
        });
        busWirePanel.add(addMaterial);

        return busWirePanel;
    }

    /**
     * Creates the submit button for users to submit their material choices
     * @return JButton
     */
    private JButton createSubmitButton(){
        JButton submitButton = new JButton("Submit Choices");

        // From ChatGPT
        submitButton.addActionListener(new ActionListener() {
            @Override
            /**
             * Performs the follow actions 
             * @param e
             */
            public void actionPerformed(ActionEvent e) {
                // Initializes the correct Materials object
                Materials firstMaterial = new Materials("Silicon Solar Cells", 0.28, true, "low", "Convert the Sun's light into electricity using the photovoltaic effect" );
                Materials secondMaterial = new Materials("      Magnesium    ", 2.55, true, "low", "A mineral that when used as part of a cement mixture." );
                Materials thirdMaterial = new Materials("Aluminum Frame", 591.25, true, "high", "Used for door frames, window frames,railing frames and roof trusses" );
                Materials fourthMaterial = new Materials("Standard 12V Wire", 19.36, true, "high", "Suitable for shorter installations with minimal distance between components" );
                Materials fifthMaterial = new Materials("               Bus Wire              ", 17.98, true, "high", "Flexible and easily shaped/contoured into place, and easily welded and soldered" );

                // For each object in the toolbox ArrayList
                for (Materials m : toolbox.getMaterialChoices()){
                    // Checks if that Materials object is one of the correct objects
                    if (m.getName().equals(firstMaterial.getName()) || m.getName().equals(secondMaterial.getName()) || m.getName().equals(thirdMaterial.getName()) || m.getName().equals(fourthMaterial.getName()) || m.getName().equals(fifthMaterial.getName())){
                        // Keeps track of how many correct items there are
                    	System.out.println(count);
                        count++;
                    }
                }
                // Verifies that there are at least 5 correct items (one or more of each)
                if (count >= 5){
                    JOptionPane.showMessageDialog(MinigameGUI.this, "Congratulations you have successfully built solar panels!\n" + 
                            "Here is a fact about our SDG:\n" +
                            "In developing countries, 2.6 billion people lack access to constant electricity");
                    MinigameGUI.this.dispose();
                    miniM.setMinigameComplete(0, true);
                }
            }
        });
        return submitButton;
    }
}

