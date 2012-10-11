package com.hobbitsadventure.ui.tiles;

import java.awt.Color;

/**
 * Would be good to make these tiles data-driven instead of hardcoded classes.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class Tile {
	private Color color;
	
	public Tile(Color color) { this.color = color; }
	
	public Color getColor() { return color; }
}
