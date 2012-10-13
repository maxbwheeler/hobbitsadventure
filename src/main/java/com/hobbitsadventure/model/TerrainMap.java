package com.hobbitsadventure.model;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class TerrainMap {
	private Tile[][] terrain;
	
	public TerrainMap(Tile[][] terrain) {
		this.terrain = terrain;
	}
	
	public Tile[][] getTerrain() { return terrain; }
	
	public int getNumRows() { return terrain.length; }
	
	public int getNumCols() { return terrain[0].length; }
}
