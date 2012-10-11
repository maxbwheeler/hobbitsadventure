package com.hobbitsadventure.ui;

import java.awt.Component;
import java.awt.Graphics;

import com.hobbitsadventure.model.GameMap;
import com.hobbitsadventure.ui.tiles.GrassTile;
import com.hobbitsadventure.ui.tiles.MountainTile;
import com.hobbitsadventure.ui.tiles.Tile;
import com.hobbitsadventure.ui.tiles.WaterTile;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@SuppressWarnings("serial")
public class GameMapPane extends Component {
	private static final int SIZE = 32;
	
	private GameMap gameMap;
	
	private Tile grassTile = new GrassTile();
	private Tile waterTile = new WaterTile();
	private Tile mountainTile = new MountainTile();
	
	public GameMapPane(GameMap gameMap) {
		this.gameMap = gameMap;
	}
	
	public void paint(Graphics g) {
		
		// TODO Dynamically decide how much of the terrain map to render. Too much and its too slow.
		
		int[][] matrix = gameMap.getTerrain();
		for (int i = 0; i < 40; i++) {
			int[] row = matrix[i];
			for (int j = 0; j < 40; j++) {
				int x = j * SIZE;
				int y = i * SIZE;
				
				Tile tile = null;
				
				switch (row[j]) {
				case GameMap.WATER:
					tile = waterTile;
					break;
				case GameMap.MOUNTAIN:
					tile = mountainTile;
					break;
				case GameMap.GRASS:
					tile = grassTile;
					break;
				}
				g.setColor(tile.getColor());
				g.fillRect(x, y, SIZE, SIZE);
			}
		}
	}
}
