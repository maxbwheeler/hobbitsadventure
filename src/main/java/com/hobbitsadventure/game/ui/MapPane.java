package com.hobbitsadventure.game.ui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;

import com.hobbitsadventure.io.AudioFactory;
import com.hobbitsadventure.io.ImageFactory;
import com.hobbitsadventure.model.TerrainMap;
import com.hobbitsadventure.model.Tile;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@SuppressWarnings("serial")
public class MapPane extends Component {
	private static final int TILE_WIDTH = 101;
	private static final int TILE_HEIGHT = 81;
	private static final int SIGHT_RADIUS = 4;
	
	private TerrainMap terrainMap;
	private int numRows;
	private int numCols;
	private BufferedImage playerImg;
	private BufferedImage speechImg;
	private boolean ouch = false;
	
	/** Player row in world coordinates */
	private int playerRow = 30;
	
	/** Player column in world coordinates */
	private int playerCol = 30;
	
	public MapPane(TerrainMap terrainMap) {
		this.terrainMap = terrainMap;
		this.numRows = terrainMap.getNumRows();
		this.numCols = terrainMap.getNumCols();
		
		ImageFactory imgFactory = new ImageFactory();
		this.playerImg = imgFactory.getImage("tiles/character_boy");
		this.speechImg = imgFactory.getImage("tiles/speech_bubble");
	}
	
	public void moveNorth() {
		// http://stackoverflow.com/questions/5385024/mod-in-java-produces-negative-numbers
		int newPlayerRow = (numRows + playerRow - 1) % numRows;
		moveIfPossible(newPlayerRow, playerCol);
	}
	
	public void moveSouth() {
		int newPlayerRow = (playerRow + 1) % numRows;
		moveIfPossible(newPlayerRow, playerCol);
	}
	
	public void moveEast() {
		int newPlayerCol = (playerCol + 1) % numCols;
		moveIfPossible(playerRow, newPlayerCol);
	}
	
	public void moveWest() {
		// http://stackoverflow.com/questions/5385024/mod-in-java-produces-negative-numbers
		int newPlayerCol = (numCols + playerCol - 1) % numCols;
		moveIfPossible(playerRow, newPlayerCol);
	}
	
	private void moveIfPossible(int toRow, int toCol) {
		Tile[][] terrainMatrix = terrainMap.getTerrain();
		Tile tile = terrainMatrix[toRow][toCol];
		boolean traversible = tile.isTraversable();
		if (traversible) {
			this.playerRow = toRow;
			this.playerCol = toCol;
			AudioFactory.instance().playClickSound();
		} else {
			Toolkit.getDefaultToolkit().beep();
		}
		ouch = !traversible;
		repaint();
	}
	
	public void paint(Graphics g) {
		
		// FIXME Need to fix the render order. First render the rows above the player (both layers), then render the
		// player row (first layer), then the player, then the player row (second layer), then the rows below the
		// player. Something like that.
		
		// Paint in layers to get the overlapping right
		paintLayer(g, 1);
		paintLayer(g, 2);
		
		Tile[][] tileMatrix = terrainMap.getTerrain();
		Tile playerTile = tileMatrix[playerRow][playerCol];
		
		// Draw player
		int x = SIGHT_RADIUS * TILE_WIDTH;
		int y = SIGHT_RADIUS * TILE_HEIGHT;
		g.drawImage(playerImg, x, y - 65 + playerTile.getYOffset(), null);
		
		// Draw ouch
		if (ouch) {
			x += TILE_WIDTH;
			y -= TILE_WIDTH;
			g.drawImage(speechImg, x, y - 65 + playerTile.getYOffset(), null);
			g.drawString("Ouch!", x + 35, y + 55 + playerTile.getYOffset());
		}
	}
	
	private void paintLayer(Graphics g, int layer) {
		Tile[][] tileMatrix = terrainMap.getTerrain();
		int minI = playerRow - SIGHT_RADIUS;
		int maxI = playerRow + SIGHT_RADIUS;
		int minJ = playerCol - SIGHT_RADIUS;
		int maxJ = playerCol + SIGHT_RADIUS;
		
		for (int i = minI; i <= maxI; i++) {
			
			// Get the current row of tiles
			int rowIndex = (numRows + i) % numRows;
			Tile[] row = tileMatrix[rowIndex];
			
			// Get the screen y position
			int y = (i - minI) * TILE_HEIGHT;
			
			for (int j = minJ; j <= maxJ; j++) {
				
				// Get the current tile
				int colIndex = (numCols + j) % numCols;
				Tile tile = row[colIndex];
				
				// Get the screen x position
				int x = (j - minJ) * TILE_WIDTH;
				
				// Render the tile
				tile.paint(g, x, y, layer);
			}
		}
	}
}
