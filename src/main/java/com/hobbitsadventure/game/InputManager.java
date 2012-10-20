package com.hobbitsadventure.game;

import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import com.hobbitsadventure.model.GameState;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class InputManager implements KeyListener {
	private Component comp;
	
	// FIXME Temporary
	private Main main;
	private GameState gameState;
	
	public InputManager(Component comp, Main main, GameState gameState) {
		this.comp = comp;
		this.main = main;
		this.gameState = gameState;
		
		comp.addKeyListener(this);
		
		// Allow using Tab for input instead of focus traversal
		comp.setFocusTraversalKeysEnabled(false);
	}
	
	@Override
	public void keyPressed(KeyEvent event) {
		try {
			int code = event.getKeyCode();
			switch (code) {
				case KeyEvent.VK_UP:
					gameState.moveNorth();
					break;
				case KeyEvent.VK_DOWN:
					gameState.moveSouth();
					break;
				case KeyEvent.VK_LEFT:
					gameState.moveWest();
					break;
				case KeyEvent.VK_RIGHT:
					gameState.moveEast();
					break;
				case KeyEvent.VK_0:
					gameState.loadRealmMap("world");
					break;
				case KeyEvent.VK_1:
					gameState.loadRealmMap("moria/level1");
					break;
				case KeyEvent.VK_2:
					gameState.loadRealmMap("moria/level2");
					break;
				case KeyEvent.VK_X:
					main.stop();
					break;
				}
		} catch (IOException e) {
			main.stop();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) { }

	@Override
	public void keyTyped(KeyEvent e) { }
}
