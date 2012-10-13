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
	private BufferedImage layer1Img;
	private BufferedImage layer2Img;
	private boolean traversable;
	private int yOffset = 0;
	
	public Tile(String layer1Id, String layer2Id, boolean traversable, int yOffset) {
		ImageFactory imgFactory = new ImageFactory();
		this.layer1Img = imgFactory.getImage(layer1Id);
		if (layer2Id != null) {
			this.layer2Img = imgFactory.getImage(layer2Id);
		}
		
		this.traversable = traversable;
		this.yOffset = yOffset;
	}
	
	public boolean isTraversable() { return traversable; }
	
	public int getYOffset() { return yOffset; }
	
	public void paint(Graphics g, int x, int y, int layer) {
		if (layer == 1) {
			g.drawImage(layer1Img, x, y - 45 + yOffset, null);
		} else if (layer == 2 && layer2Img != null) {
			g.drawImage(layer2Img, x, y - 65 + yOffset, null);
		}
	}
}
