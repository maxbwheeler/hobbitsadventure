package com.hobbitsadventure.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

import com.hobbitsadventure.game.Config;
import com.hobbitsadventure.game.util.CharacterGenerator;
import com.hobbitsadventure.io.TerrainMapReader;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class GameState {
	private TerrainMapReader mapReader;
	private CharacterGenerator characterGenerator;
	private PropertyChangeSupport propChangeSupport;
	
	private TerrainMap terrainMap;
	private PlayerCharacter pc;
	private int playerRow;
	private int playerCol;
	
	public GameState() throws IOException {
		this.mapReader = new TerrainMapReader();
		this.characterGenerator = new CharacterGenerator();
		this.propChangeSupport = new PropertyChangeSupport(this);
		loadTerrainMap("world");
		this.pc = characterGenerator.generatePlayerCharacter();
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propChangeSupport.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propChangeSupport.removePropertyChangeListener(listener);
	}
	
	public void loadTerrainMap(String id) throws IOException {
		this.terrainMap = mapReader.read(id);
		
		// FIXME Hardcoded spawn
		this.playerRow = 30;
		this.playerCol = 30;
		
		// FIXME
		propChangeSupport.firePropertyChange("terrainMap", null, null);
	}
	
	public TerrainMap getTerrainMap() { return terrainMap; }
	
	public PlayerCharacter getPlayerCharacter() { return pc; }
	
	public int getPlayerRow() { return playerRow; }
	
	public int getPlayerCol() { return playerCol; }
	
	public void moveNorth() {
		// http://stackoverflow.com/questions/5385024/mod-in-java-produces-negative-numbers
		int numRows = getTerrainMap().getNumRows();
		int newPlayerRow = (numRows + playerRow - 1) % numRows;
		moveIfPossible(newPlayerRow, playerCol);
	}
	
	public void moveSouth() {
		int numRows = getTerrainMap().getNumRows();
		int newPlayerRow = (playerRow + 1) % numRows;
		moveIfPossible(newPlayerRow, playerCol);
	}
	
	public void moveEast() {
		int numCols = getTerrainMap().getNumCols();
		int newPlayerCol = (playerCol + 1) % numCols;
		moveIfPossible(playerRow, newPlayerCol);
	}
	
	public void moveWest() {
		// http://stackoverflow.com/questions/5385024/mod-in-java-produces-negative-numbers
		int numCols = getTerrainMap().getNumCols();
		int newPlayerCol = (numCols + playerCol - 1) % numCols;
		moveIfPossible(playerRow, newPlayerCol);
	}
	
	private void moveIfPossible(int toRow, int toCol) {
		Tile[][] terrainMatrix = terrainMap.getTerrain();
		Tile tile = terrainMatrix[toRow][toCol];
		boolean traversible = tile.isTraversable() || Config.GOD_MODE;
		if (traversible) {
			this.playerRow = toRow;
			this.playerCol = toCol;
			
			// FIXME There's no "position" property yet
			propChangeSupport.firePropertyChange("position", null, null);
		} else {
			
			// FIXME This is really a "no-change", and the "ouch" isn't a property
			propChangeSupport.firePropertyChange("ouch", null, null);
		}
	}
}
