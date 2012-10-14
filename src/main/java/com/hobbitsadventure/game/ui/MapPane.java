package com.hobbitsadventure.game.ui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.hobbitsadventure.io.ImageFactory;
import com.hobbitsadventure.model.GameState;
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
	
	private GameState gameState;
	private BufferedImage playerImg;
	private BufferedImage speechImg;
	private boolean ouch = false;
	
	private PropertyChangeListener propChangeHandler;
	
	public MapPane(GameState gameState) {
		this.gameState = gameState;
		
		ImageFactory imgFactory = new ImageFactory();
		this.playerImg = imgFactory.getImage("tiles/character_boy");
		this.speechImg = imgFactory.getImage("tiles/speech_bubble");
		
		this.propChangeHandler = new PropertyChangeHandler();
		gameState.addPropertyChangeListener(propChangeHandler);
	}
	
	public void paint(Graphics g) {
		
		// FIXME Need to fix the render order. First render the rows above the player (both layers), then render the
		// player row (first layer), then the player, then the player row (second layer), then the rows below the
		// player. Something like that.
		
		// Paint in layers to get the overlapping right
		paintLayer(g, 1);
		paintLayer(g, 2);
		
		Tile[][] tileMatrix = gameState.getTerrainMap().getTerrain();
		Tile playerTile = tileMatrix[gameState.getPlayerRow()][gameState.getPlayerCol()];
		
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
		TerrainMap terrainMap = gameState.getTerrainMap();
		Tile[][] tileMatrix = terrainMap.getTerrain();
		int numRows = terrainMap.getNumRows();
		int numCols = terrainMap.getNumCols();
		int playerRow = gameState.getPlayerRow();
		int playerCol = gameState.getPlayerCol();
		
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
	
	private class PropertyChangeHandler implements PropertyChangeListener {
		
		@Override
		public void propertyChange(PropertyChangeEvent event) {
			String propName = event.getPropertyName();
			if ("ouch".equals(propName)) {
				ouch = true;
				Toolkit.getDefaultToolkit().beep();
			} else {
				ouch = false;
				repaint();
			}
		}
	}
}
