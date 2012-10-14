package com.hobbitsadventure.game.util;

import java.util.Random;

import com.hobbitsadventure.model.PlayerCharacter;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class CharacterGenerator {
	private Random random = new Random();
	
	public PlayerCharacter generatePlayerCharacter() {
		PlayerCharacter pc = new PlayerCharacter();
		
		pc.setLevel(1);
		pc.setExperience(0);
		
		int strength = rollDice();
		pc.setStrength(strength);
		
		int intelligence = rollDice();
		pc.setIntelligence(intelligence);
		
		int health = rollDice();
		pc.setHealth(health);
		
		return pc;
	}
	
	private int rollDice() {
		int die1 = random.nextInt(5) + 1;
		int die2 = random.nextInt(5) + 1;
		int die3 = random.nextInt(5) + 1;
		int total = die1 + die2 + die3;
		return total;
	}
}
