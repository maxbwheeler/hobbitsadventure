package com.hobbitsadventure.ui;

import java.awt.BorderLayout;
import java.awt.Label;
import java.awt.Panel;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@SuppressWarnings("serial")
public class AdventurePanel extends Panel {
	
	public AdventurePanel() {
		setLayout(new BorderLayout());
		add(new Label("Adventure Panel"), BorderLayout.NORTH);
	}
}
