package inputs;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import tile.TileManager;

public class WindowListener extends WindowAdapter {
    
    TileManager tileM;
    String username;

    public WindowListener(TileManager tileM, String username) {
        this.tileM = tileM;
        this.username = username;
    }
    
    @Override
    public void windowClosing(WindowEvent l) {
        System.out.println("Window is closing");
        String filePath = "res/saves/" + username + "_savedMap.txt";
        saveMapToFile(filePath);
    }

    public void saveMapToFile(String path) {
        try {
            new File("res/saves").mkdir();
    
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
                for (int row = 0; row < 21; row++) {
                    for (int col = 0; col < 19; col++) {
                        int storedTileType = tileM.getTopLayer().getTileType(col, row);
                        writer.write(storedTileType + " ");
                    }
                    writer.newLine();
                }
            } 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}