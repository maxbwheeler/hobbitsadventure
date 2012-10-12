package com.hobbitsadventure.ui;

import java.awt.Component;
import java.awt.Graphics;

import com.hobbitsadventure.model.GameMap;
import com.hobbitsadventure.ui.tiles.DesertTile;
import com.hobbitsadventure.ui.tiles.ForestTile;
import com.hobbitsadventure.ui.tiles.GrassTile;
import com.hobbitsadventure.ui.tiles.HouseTile;
import com.hobbitsadventure.ui.tiles.MountainTile;
import com.hobbitsadventure.ui.tiles.Tile;
import com.hobbitsadventure.ui.tiles.WaterTile;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@SuppressWarnings("serial")
public class MapPane extends Component {
	private static final int TILE_WIDTH = 36;
	private static final int TILE_HEIGHT = 48;
	
	private GameMap gameMap;
	private int currI = 30;
	private int currJ = 30;
	
	private Tile grassTile = new GrassTile();
	private Tile waterTile = new WaterTile();
	private Tile mountainTile = new MountainTile();
	private Tile forestTile = new ForestTile();
	private Tile desertTile = new DesertTile();
	private Tile houseTile = new HouseTile();
	
	public MapPane(GameMap gameMap) {
		this.gameMap = gameMap;
	}
	
	public void incrI() { currI++; }
	
	public void decrI() { currI--; }
	
	public void incrJ() { currJ++; }
	
	public void decrJ() { currJ--; }
	
	public void paint(Graphics g) {
		
		// TODO Dynamically decide how much of the terrain map to render. Too much and its too slow.
		
		int[][] matrix = gameMap.getTerrain();
		int minI = currI - 10;
		int maxI = currI + 10;
		int minJ = currJ - 10;
		int maxJ = currJ + 10;
		for (int i = minI; i < maxI; i++) {
			int[] row = matrix[i];
			for (int j = minJ; j < maxJ; j++) {
				int x = (j - minJ) * TILE_WIDTH;
				int y = (i - minI) * TILE_HEIGHT;
				
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
				case GameMap.FOREST:
					tile = forestTile;
					break;
				case GameMap.DESERT:
					tile = desertTile;
					break;
				case GameMap.HOUSE:
				    tile = houseTile;
				    break;
				}
				
				g.setColor(tile.getColor());
				g.fillRect(x, y, TILE_WIDTH, TILE_HEIGHT);
			}
		}
	}
}
