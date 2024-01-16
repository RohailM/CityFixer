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
	            
	            for(int col = 0; col < cols; col++) {
	                int num = Integer.parseInt(numbers[col]);
	                layerData[col][row] = num;
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
}
