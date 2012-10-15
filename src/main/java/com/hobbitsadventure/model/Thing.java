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
	
	public Thing(String id, boolean traversable, int yOffset) {
		ImageFactory imgFactory = new ImageFactory();
		this.sprite = imgFactory.getImage(id);
		this.traversable = traversable;
		this.yOffset = yOffset;
	}
	
	public BufferedImage getSprite() { return sprite; }
	
	public boolean isTraversable() { return traversable; }
	
	public int getYOffset() { return yOffset; }
}
