package com.hobbitsadventure.model.factory;

import com.hobbitsadventure.model.TileClass;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class TileClasses {
	public static final TileClass BEACH = new TileClass("tiles/dirt_block", true, 10);
    public static final TileClass DOOR_CLOSED = new TileClass("tiles/door_tall_closed", true, -20);
	public static final TileClass FLOOR_STONE = new TileClass("tiles/plain_block", true, 0);
	public static final TileClass FLOOR_WOOD = new TileClass("tiles/wood_block", true, 0);
	public static final TileClass GRASS = new TileClass("tiles/grass_block", true, 0);
	public static final TileClass HILL = new TileClass("tiles/grass_block", true, -10);
	public static final TileClass ROAD = new TileClass("tiles/stone_block", true, 0);
	public static final TileClass STAIRS_DOWN = new TileClass("tiles/ramp_west", true, 0);
	public static final TileClass STAIRS_UP = new TileClass("tiles/ramp_west", true, -35);
	public static final TileClass WATER = new TileClass("tiles/water_block", false, 15);
}
