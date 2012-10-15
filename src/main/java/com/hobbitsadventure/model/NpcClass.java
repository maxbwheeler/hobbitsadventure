package com.hobbitsadventure.model;

import java.awt.image.BufferedImage;

import com.hobbitsadventure.io.ImageFactory;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class NpcClass {
	private BufferedImage sprite;
	
	public NpcClass(String id) {
		ImageFactory imgFactory = new ImageFactory();
		this.sprite = imgFactory.getImage(id);
	}
	
	public BufferedImage getSprite() { return sprite; }
}
