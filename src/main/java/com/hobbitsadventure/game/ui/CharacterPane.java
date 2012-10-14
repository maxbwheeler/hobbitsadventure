package com.hobbitsadventure.game.ui;

import java.awt.Label;
import java.awt.Panel;

import com.hobbitsadventure.model.GameState;
import com.hobbitsadventure.model.PlayerCharacter;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@SuppressWarnings("serial")
public class CharacterPane extends Panel {
	private GameState gameState;
	
	public CharacterPane(GameState gameState) {
		this.gameState = gameState;
		PlayerCharacter pc = gameState.getPlayerCharacter();
		
//		add(new Label("Character Panel"));
		add(new Label("Lev: " + pc.getLevel()));
		add(new Label("Str: " + pc.getStrength()));
		add(new Label("Int: " + pc.getIntelligence()));
		add(new Label("Hea: " + pc.getHealth()));
		add(new Label("Exp: " + pc.getExperience()));
	}
}
