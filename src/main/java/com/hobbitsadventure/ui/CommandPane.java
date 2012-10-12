package com.hobbitsadventure.ui;

import java.awt.BorderLayout;
import java.awt.Panel;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@SuppressWarnings("serial")
public class CommandPane extends Panel implements KeyListener {
	private MapPane mapPane;
	private TextArea textArea;
	
	// FIXME CommandPane should fire events rather than knowing about MapPane directly. Will take care of that in a bit
	// but first just want to get the basic behavior working.
	public CommandPane(MapPane mapPane) {
		this.mapPane = mapPane;
		
		setLayout(new BorderLayout());
		this.textArea = new TextArea();
		add(textArea, BorderLayout.CENTER);
		textArea.addKeyListener(this);
	}
	
	/* (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch (code) {
		case KeyEvent.VK_UP:
			System.out.println("up");
			mapPane.decrI();
			break;
		case KeyEvent.VK_DOWN:
			System.out.println("down");
			mapPane.incrI();
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("left");
			mapPane.decrJ();
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("right");
			mapPane.incrJ();
			break;
		}
		mapPane.repaint();
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
