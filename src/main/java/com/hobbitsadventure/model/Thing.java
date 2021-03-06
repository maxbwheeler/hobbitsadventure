package com.hobbitsadventure.model;

import java.awt.image.BufferedImage;

import com.hobbitsadventure.io.ImageFactory;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class Thing {
	private BufferedImage sprite;
	private boolean traversable;
	private int yOffset = 0;
	private boolean occludedByTerrain;
	
	public Thing(String id, boolean traversable, int yOffset, boolean occludedByTerrain) {
		ImageFactory imgFactory = new ImageFactory();
		this.sprite = imgFactory.getImage(id);
		this.traversable = traversable;
		this.yOffset = yOffset;
		this.occludedByTerrain = occludedByTerrain;
	}
	
	public BufferedImage getSprite() { return sprite; }
	
	public boolean isTraversable() { return traversable; }
	
	public int getYOffset() { return yOffset; }
	
	public boolean isOccludedByTerrain() { return occludedByTerrain; }
}
