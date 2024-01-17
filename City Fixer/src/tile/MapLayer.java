package tile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MapLayer {
    private int[][] layerData;

    public MapLayer(String filePath, int cols, int rows) {
        layerData = new int[cols][rows];
        loadLayerData(filePath, cols, rows);
    }

    private void loadLayerData(String filePath, int cols, int rows) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
	        String line;
	        int row = 0;

	        while((line = br.readLine()) != null && row < rows) {
	            String[] numbers = line.split(" ");
	            if (numbers.length != cols) {
	                System.err.println("Row " + (row + 1) + " in " + filePath + " does not have the expected " + cols + " columns.");
	                continue; 
	            }
	            
	            for(int col = 0; col < cols; col++) {
	            	try {
	                    int num = Integer.parseInt(numbers[col]);
	                    layerData[col][row] = num;
	            	} catch (NumberFormatException e) {
	                    System.err.println("Invalid number format in " + filePath + " at row " + (row + 1) + ", col " + (col + 1));
	            	}

	            }
	            row++;
	        }
			
			br.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
    }

    public int getTileIndex(int col, int row) {
        return layerData[col][row];
    }
    
    public void setTileIndex(int col, int row, int tileIndex) {
        if (col >= 0 && col < layerData.length && row >= 0 && row < layerData[col].length) {
            layerData[col][row] = tileIndex;
        }
    }
}
