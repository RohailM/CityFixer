package tile;
import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import main.Map;

public class TileManager {
	Map m;
	Tile[] tiles;
	int[][] mapTileNum;
	
	public TileManager(Map m) {
		this.m = m;
		tiles = new Tile[3];
		mapTileNum = new int[m.screenCol][m.screenRow];
		loadTileImages();
		loadMap("/maps/map.txt");
	}
	
	public void loadTileImages() {
		try {
			tiles[0] = new Tile();
			tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile000.png"));
			
			tiles[1] = new Tile();
			tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile001.png"));
			
			tiles[2] = new Tile();
			tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tile002.png"));
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void loadMap(String filePath) {
		try {
			InputStream is = getClass().getResourceAsStream(filePath);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			
			int col = 0;
			int row = 0;
			
			while(col < m.screenCol && row < m.screenCol) {
				String line = br.readLine();
				
				while (col < m.screenCol) {
					String numbers[] = line.split(" ");
					
					int num = Integer.parseInt(numbers[col]);
					
					mapTileNum[col][row] = num;
					col++;
				}
				
				if (col == m.screenCol) {
					col = 0;
					row++;
				}
			}
			
			br.close();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics2D g2) {
		
		int col = 0;
		int row = 0;
		int x = 0;
		int y = 0;
		
		while (col < m.screenCol && row < m.screenRow) {
			
			int tileNum = mapTileNum[col][row];
			
			g2.drawImage(tiles[tileNum].image, x, y, m.tileSize, m.tileSize, null);
			col++;
			x += m.tileSize;
			
			if (col == m.screenCol) {
				col = 0;
				x = 0;
				row++;
				y += m.tileSize;
			}
		}

	}
}
