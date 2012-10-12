package com.hobbitsadventure.model;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class GameMap {
	public static final int GRASS = 0;
	public static final int FOREST = 1;
	public static final int DESERT = 2;
	public static final int DIRT_PATH = 3;
	public static final int BRIDGE = 4;
	public static final int WATER = 5;
	public static final int MOUNTAIN = 6;
	public static final int WALL = 7;
	public static final int HOUSE = 8;
	
	private int[][] terrain;
	
	public GameMap(int[][] terrain) {
		this.terrain = terrain;
	}
	
	public int[][] getTerrain() { return terrain; }
}
