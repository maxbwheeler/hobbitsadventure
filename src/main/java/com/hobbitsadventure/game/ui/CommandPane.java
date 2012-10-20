package com.hobbitsadventure.game.ui;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import com.hobbitsadventure.model.GameState;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@SuppressWarnings("serial")
public class CommandPane extends Panel implements KeyListener {
	private GameState gameState;
	private TextArea textArea;
	
	public CommandPane(GameState gameState) {
		this.gameState = gameState;
		setLayout(new BorderLayout());
		this.textArea = new TextArea();
		add(textArea, BorderLayout.CENTER);
		textArea.addKeyListener(this);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
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
				gameState.loadRealmMap("world1");
				break;
			case KeyEvent.VK_1:
				gameState.loadRealmMap("world2");
				break;
			case KeyEvent.VK_2:
				gameState.loadRealmMap("moria/level1");
				break;
			case KeyEvent.VK_3:
				gameState.loadRealmMap("moria/level2");
				break;
			case KeyEvent.VK_4:
				gameState.loadRealmMap("moria/level3");
				break;
			}
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}
}
