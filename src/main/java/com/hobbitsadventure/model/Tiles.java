package com.hobbitsadventure.model;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class Tiles {
	public static final Tile BEACH = new Tile("tiles/dirt_block", null, true, 10);
	public static final Tile BRUSH = new Tile("tiles/grass_block", "tiles/tree_short", true, 0);
 	public static final Tile CHEST= new Tile("tiles/plain_block", "tiles/chest_closed", false, 0);
	public static final Tile DOCK = new Tile("tiles/wall_block", null, true, -20);
    public static final Tile DOOR_CLOSED = new Tile("tiles/door_tall_closed", null, true, -20);
	public static final Tile FLOOR_STONE = new Tile("tiles/plain_block", null, true, 0);
	public static final Tile FLOOR_WOOD = new Tile("tiles/wood_block", null, true, 0);
	public static final Tile FOREST = new Tile("tiles/grass_block", "tiles/tree_tall", true, 0);
	public static final Tile GRASS = new Tile("tiles/grass_block", null, true, 0);
	public static final Tile HILL = new Tile("tiles/grass_block", null, true, -10);
	public static final Tile MOUNTAIN = new Tile("tiles/grass_block", "tiles/rock", false, 0);
	public static final Tile ROAD = new Tile("tiles/stone_block", null, true, 0);
	public static final Tile STAIRS_DOWN = new Tile("tiles/ramp_west", null, true, 0);
	public static final Tile STAIRS_UP = new Tile("tiles/ramp_west", null, true, -35);
	public static final Tile TOWN = new Tile("tiles/grass_block", "tiles/door_tall_closed", true, 0);
	public static final Tile WALL_TALL = new Tile("tiles/stone_block_tall", null, false, 0);
	public static final Tile WATER = new Tile("tiles/water_block", null, false, 15);
}
