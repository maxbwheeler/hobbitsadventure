package com.hobbitsadventure.model.factory;

import com.hobbitsadventure.model.Thing;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class Things {
	public static final Thing BRUSH = new Thing("tiles/tree_short", true, 0, false);
	public static final Thing CHEST = new Thing("images/objects/chest_closed", false, 0, false);
	public static final Thing DOCK = new Thing("tiles/wall_block", true, -20, true);
	public static final Thing DOOR_CLOSED = new Thing("images/objects/door_tall_closed", true, 0, false);
	public static final Thing ROCK = new Thing("images/objects/rock", false, 0, false);
	public static final Thing TREE_TALL = new Thing("images/objects/tree_tall", true, 0, false);
	public static final Thing WALL_TALL = new Thing("images/objects/stone_block_tall", false, -30, false);
}
