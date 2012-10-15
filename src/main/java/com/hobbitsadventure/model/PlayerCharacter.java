package com.hobbitsadventure.model;

import java.awt.image.BufferedImage;

import com.hobbitsadventure.io.ImageFactory;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class PlayerCharacter implements GameCharacter {
	private BufferedImage sprite;
	
	private int level;
	private int experience;
	private int strength;
	private int intelligence;
	private int health;
	
	// Position
	private int rowIndex;
	private int colIndex;
	
	public PlayerCharacter() {
		ImageFactory imgFactory = new ImageFactory();
		this.sprite = imgFactory.getImage("images/characters/character_boy");
	}
	
	@Override
	public BufferedImage getSprite() { return sprite; }
	
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getExperience() {
		return experience;
	}

	public void setExperience(int experience) {
		this.experience = experience;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getRowIndex() { return rowIndex; }
	
	public void setRowIndex(int rowIndex) { this.rowIndex = rowIndex; }
	
	public int getColIndex() { return colIndex; }
	
	public void setColIndex(int colIndex) { this.colIndex = colIndex; }
}
