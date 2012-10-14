package com.hobbitsadventure.model;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class PlayerCharacter {
	private int level;
	private int experience;
	private int strength;
	private int intelligence;
	private int health;
	
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
}
