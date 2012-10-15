package com.hobbitsadventure.model.factory;

import com.hobbitsadventure.model.Tile;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class Tiles {
	public static final Tile BEACH = new Tile("tiles/dirt_block", true, 10);
    public static final Tile DOOR_CLOSED = new Tile("tiles/door_tall_closed", true, -20);
	public static final Tile FLOOR_STONE = new Tile("tiles/plain_block", true, 0);
	public static final Tile FLOOR_WOOD = new Tile("tiles/wood_block", true, 0);
	public static final Tile GRASS = new Tile("tiles/grass_block", true, 0);
	public static final Tile HILL = new Tile("tiles/grass_block", true, -10);
	public static final Tile ROAD = new Tile("tiles/stone_block", true, 0);
	public static final Tile STAIRS_DOWN = new Tile("tiles/ramp_west", true, 0);
	public static final Tile STAIRS_UP = new Tile("tiles/ramp_west", true, -35);
	public static final Tile WATER = new Tile("tiles/water_block", false, 15);
}
