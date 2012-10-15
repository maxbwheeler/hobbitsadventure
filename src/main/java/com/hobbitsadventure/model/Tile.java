package com.hobbitsadventure.model;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class Tile {
	private TileClass tileClass;
	private int height;
	
	public Tile(TileClass tileClass, int height) {
		this.tileClass = tileClass;
		this.height = height;
	}
	
	public TileClass getTileClass() { return tileClass; }
	
	public String getTileClassId() { return tileClass.getId(); }
	
	public BufferedImage getSprite() { return tileClass.getSprite(); }
	
	public boolean isTraversable() { return tileClass.isTraversable(); }
	
	public int getHeight() { return height; }
	
	public void setHeight(int height) { this.height = height; }
	
	public void paint(Graphics g, int x, int y) {
		g.drawImage(tileClass.getSprite(), x, y - 45 + height, null);
	}
}
