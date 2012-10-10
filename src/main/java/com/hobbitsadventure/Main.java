package com.hobbitsadventure;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.hobbitsadventure.ui.AdventurePanel;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@SuppressWarnings("serial")
public class Main extends Frame {
	private static final Dimension SIZE = new Dimension(1024, 800);
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		super("Hobbit's Adventure");
		setDefaults();
		buildUi();
		addListeners();
		setVisible(true);
	}
	
	private void setDefaults() {
		setSize(SIZE);
		setLayout(new BorderLayout());
	}
	
	private void buildUi() {
		add(new AdventurePanel(), BorderLayout.EAST);
		add(new Button("PUSH ME"), BorderLayout.CENTER);
	}
	
	private void addListeners() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) { System.exit(0); }
		});
	}
}
