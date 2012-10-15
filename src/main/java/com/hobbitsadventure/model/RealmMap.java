package com.hobbitsadventure.model;


/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class RealmMap {
	private RealmCell[][] cells;
	
	public RealmMap(int numRows, int numCols) {
		this.cells = new RealmCell[numRows][numCols];
	}
	
	public int getNumRows() { return cells.length; }
	
	public int getNumCols() { return cells[0].length; }
	
	public RealmCell getCell(int rowIndex, int colIndex) {
		return cells[rowIndex][colIndex];
	}
	
	public void setCell(int rowIndex, int colIndex, RealmCell cell) {
		this.cells[rowIndex][colIndex] = cell;
	}
	
	public Tile getTile(int rowIndex, int colIndex) {
		return getCell(rowIndex, colIndex).getTile();
	}
	
	public void setTile(int rowIndex, int colIndex, Tile tile) {
		getCell(rowIndex, colIndex).setTile(tile);
	}
	
	public Thing getThing(int rowIndex, int colIndex) {
		return getCell(rowIndex, colIndex).getThing();
	}
	
	public void setThing(int rowIndex, int colIndex, Thing thing) {
		getCell(rowIndex, colIndex).setThing(thing);
	}
	
	public GameCharacter getCharacter(int rowIndex, int colIndex) {
		return getCell(rowIndex, colIndex).getCharacter();
	}
	
	public void setCharacter(int rowIndex, int colIndex, GameCharacter character) {
		getCell(rowIndex, colIndex).setCharacter(character);
	}
}
