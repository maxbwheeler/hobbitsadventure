package com.hobbitsadventure.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.hobbitsadventure.io.ImageFactory;

/**
 * Would be good to make these tiles data-driven instead of hardcoded classes.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class Tile {
	private BufferedImage sprite;
	private boolean traversable;
	private int yOffset = 0;
	
	public Tile(String id, boolean traversable, int yOffset) {
		ImageFactory imgFactory = new ImageFactory();
		this.sprite = imgFactory.getImage(id);
		this.traversable = traversable;
		this.yOffset = yOffset;
	}
	
	public boolean isTraversable() { return traversable; }
	
	public int getYOffset() { return yOffset; }
	
	public void paint(Graphics g, int x, int y) {
		g.drawImage(sprite, x, y - 45 + yOffset, null);
	}
}
