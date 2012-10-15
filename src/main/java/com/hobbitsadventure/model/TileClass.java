package com.hobbitsadventure.model;

import java.awt.image.BufferedImage;

import com.hobbitsadventure.io.ImageFactory;

/**
 * Would be good to make these tiles data-driven instead of hardcoded classes.
 * 
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class TileClass {
	private String id;
	private BufferedImage sprite;
	private boolean traversable;
	private int yOffset = 0;
	
	public TileClass(String id, boolean traversable, int yOffset) {
		this.id = id;
		ImageFactory imgFactory = new ImageFactory();
		this.sprite = imgFactory.getImage(id);
		this.traversable = traversable;
		this.yOffset = yOffset;
	}
	
	public String getId() { return id; }
	
	public BufferedImage getSprite() { return sprite; }
	
	public boolean isTraversable() { return traversable; }
	
	public int getYOffset() { return yOffset; }
}
