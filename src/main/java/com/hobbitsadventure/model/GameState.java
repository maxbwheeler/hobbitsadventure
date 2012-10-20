package com.hobbitsadventure.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.IOException;

import com.hobbitsadventure.game.Config;
import com.hobbitsadventure.game.util.CharacterGenerator;
import com.hobbitsadventure.io.MapReader;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class GameState {
	
	// Dependencies
	private MapReader mapReader;
	private CharacterGenerator characterGenerator;
	private PropertyChangeSupport propChangeSupport;
	
	// Maps
	private RealmMap realmMap;
	
	// Player
	private PlayerCharacter pc;
	
	public GameState() throws IOException {
		this.mapReader = new MapReader();
		this.characterGenerator = new CharacterGenerator();
		this.propChangeSupport = new PropertyChangeSupport(this);
		this.pc = characterGenerator.generatePlayerCharacter();
		loadRealmMap("world");
	}
	
	public void addPropertyChangeListener(PropertyChangeListener listener) {
		propChangeSupport.addPropertyChangeListener(listener);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener listener) {
		propChangeSupport.removePropertyChangeListener(listener);
	}
	
	public void loadRealmMap(String id) throws IOException {
		this.realmMap = mapReader.read(id);
		
		// FIXME Hardcoded spawn
		pc.setRowIndex(28);
		pc.setColIndex(30);
		realmMap.setCharacter(28, 30, pc);
		
		// FIXME
		propChangeSupport.firePropertyChange("terrainMap", null, null);
	}
	
	public RealmMap getRealmMap() { return realmMap; }
	
	public PlayerCharacter getPlayerCharacter() { return pc; }
	
	public int getPlayerRow() { return pc.getRowIndex(); }
	
	public int getPlayerCol() { return pc.getColIndex(); }
	
	public void moveNorth() {
		// http://stackoverflow.com/questions/5385024/mod-in-java-produces-negative-numbers
		int numRows = realmMap.getNumRows();
		int newPlayerRow = (numRows + getPlayerRow() - 1) % numRows;
		moveIfPossible(newPlayerRow, getPlayerCol());
	}
	
	public void moveSouth() {
		int numRows = realmMap.getNumRows();
		int newPlayerRow = (getPlayerRow() + 1) % numRows;
		moveIfPossible(newPlayerRow, getPlayerCol());
	}
	
	public void moveEast() {
		int numCols = realmMap.getNumCols();
		int newPlayerCol = (getPlayerCol() + 1) % numCols;
		moveIfPossible(getPlayerRow(), newPlayerCol);
	}
	
	public void moveWest() {
		// http://stackoverflow.com/questions/5385024/mod-in-java-produces-negative-numbers
		int numCols = realmMap.getNumCols();
		int newPlayerCol = (numCols + getPlayerCol() - 1) % numCols;
		moveIfPossible(getPlayerRow(), newPlayerCol);
	}
	
	private void moveIfPossible(int toRow, int toCol) {
		Tile tile = realmMap.getTile(toRow, toCol);
		Thing thing = realmMap.getThing(toRow, toCol);
		GameCharacter character = realmMap.getCharacter(toRow, toCol);
		
		boolean case1 = tile.isTraversable() && (thing == null);
		
		// This supports bridges, docks, etc.
		boolean case2 = (thing != null && thing.isTraversable());
		
		boolean charTraversable = (character == null);
		boolean traversable = ((case1 || case2) && charTraversable) || Config.GOD_MODE;
		if (traversable) {
			
			// FIXME Again need to synchronize the updates here
			realmMap.setCharacter(pc.getRowIndex(), pc.getColIndex(), null);
			realmMap.setCharacter(toRow, toCol, pc);
			pc.setRowIndex(toRow);
			pc.setColIndex(toCol);
			
			// FIXME There's no "position" property yet
			propChangeSupport.firePropertyChange("position", null, null);
		} else {
			
			// FIXME This is really a "no-change", and the "ouch" isn't a property
			propChangeSupport.firePropertyChange("ouch", null, null);
		}
	}
}
