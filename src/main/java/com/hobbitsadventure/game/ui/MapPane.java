package com.hobbitsadventure.game.ui;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import com.hobbitsadventure.io.AudioFactory;
import com.hobbitsadventure.io.ImageFactory;
import com.hobbitsadventure.model.GameCharacter;
import com.hobbitsadventure.model.GameState;
import com.hobbitsadventure.model.RealmMap;
import com.hobbitsadventure.model.Thing;
import com.hobbitsadventure.model.Tile;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@SuppressWarnings("serial")
public class MapPane extends Component {
	private static final int TILE_WIDTH = 101;
	private static final int TILE_HEIGHT = 81;
	private static final int SIGHT_RADIUS = 4;
	
	private AudioFactory audioFactory;
	private PropertyChangeListener propChangeHandler;
	
	private GameState gameState;
	private BufferedImage speechImg;
	private boolean ouch = false;
	
	public MapPane(GameState gameState) {
		this.gameState = gameState;
		
		ImageFactory imgFactory = new ImageFactory();
		this.speechImg = imgFactory.getImage("tiles/speech_bubble");
		
		this.audioFactory = AudioFactory.instance();
		
		this.propChangeHandler = new PropertyChangeHandler();
		gameState.addPropertyChangeListener(propChangeHandler);
	}
	
	public void paint(Graphics g) {
		paintTiles(g);
		paintThingsAndCharacters(g);
		
//		// Draw player
//		Tile playerTile = gameState.getTile(gameState.getPlayerRow(), gameState.getPlayerCol());
//		int x = SIGHT_RADIUS * TILE_WIDTH;
//		int y = SIGHT_RADIUS * TILE_HEIGHT;
//		g.drawImage(playerImg, x, y - 65 + playerTile.getYOffset(), null);
//		
//		// Draw ouch
//		if (ouch) {
//			x += TILE_WIDTH;
//			y -= TILE_WIDTH;
//			g.drawImage(speechImg, x, y - 65 + playerTile.getYOffset(), null);
//			g.drawString("Ouch!", x + 35, y + 55 + playerTile.getYOffset());
//		}
	}
	
	private void paintTiles(Graphics g) {
		RealmMap realmMap = gameState.getRealmMap();
		int numRows = realmMap.getNumRows();
		int numCols = realmMap.getNumCols();
		int playerRow = gameState.getPlayerRow();
		int playerCol = gameState.getPlayerCol();
		
		int minI = playerRow - SIGHT_RADIUS;
		int maxI = playerRow + SIGHT_RADIUS;
		int minJ = playerCol - SIGHT_RADIUS;
		int maxJ = playerCol + SIGHT_RADIUS;
		
		for (int i = minI; i <= maxI; i++) {
			int rowIndex = (numRows + i) % numRows;
			int y = (i - minI) * TILE_HEIGHT;
			for (int j = minJ; j <= maxJ; j++) {
				int colIndex = (numCols + j) % numCols;
				Tile tile = realmMap.getTile(rowIndex, colIndex);
				int x = (j - minJ) * TILE_WIDTH;
				tile.paint(g, x, y);
			}
		}
	}
	
	private void paintThingsAndCharacters(Graphics g) {
		RealmMap realmMap = gameState.getRealmMap();
		int numRows = realmMap.getNumRows();
		int numCols = realmMap.getNumCols();
		int playerRow = gameState.getPlayerRow();
		int playerCol = gameState.getPlayerCol();
		
		int minI = playerRow - SIGHT_RADIUS;
		int maxI = playerRow + SIGHT_RADIUS;
		int minJ = playerCol - SIGHT_RADIUS;
		int maxJ = playerCol + SIGHT_RADIUS;
		
		for (int i = minI; i <= maxI; i++) {
			int rowIndex = (numRows + i) % numRows;
			int baseY = (i - minI) * TILE_HEIGHT;
			for (int j = minJ; j <= maxJ; j++) {
				int colIndex = (numCols + j) % numCols;
				Tile tile = realmMap.getTile(rowIndex, colIndex);
				int x = (j - minJ) * TILE_WIDTH;
				int y = baseY - 65 + tile.getYOffset();
				
				// Paint thing
				Thing thing = realmMap.getThing(rowIndex, colIndex);
				if (thing != null) {
					g.drawImage(thing.getSprite(), x, y, null);
					
					// Adjust this for the character below
					y += thing.getYOffset();
				}
				
				// Paint character
				GameCharacter character = realmMap.getCharacter(rowIndex, colIndex);
				if (character != null) {
					g.drawImage(character.getSprite(), x, y, null);
				}
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
				audioFactory.playClickSound();
			}
			repaint();
		}
	}
}
