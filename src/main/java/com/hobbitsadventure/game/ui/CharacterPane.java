package com.hobbitsadventure.game.ui;

import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Panel;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@SuppressWarnings("serial")
public class CharacterPane extends Panel {
	
	public CharacterPane() {
		setLayout(new BorderLayout());
		add(new Label("Adventure Panel"), BorderLayout.NORTH);
	}
}
