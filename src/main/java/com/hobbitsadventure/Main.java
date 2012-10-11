package com.hobbitsadventure;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import com.hobbitsadventure.io.MapReader;
import com.hobbitsadventure.model.GameMap;
import com.hobbitsadventure.ui.CharacterPane;
import com.hobbitsadventure.ui.GameMapPane;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@SuppressWarnings("serial")
public class Main extends Frame {
	private static final Dimension SIZE = new Dimension(1024, 800);
	
	// IO
	private MapReader mapReader;
	
	// UI
	private CharacterPane characterPane;
	private GameMapPane mapPane;
	
	// Model
	private GameMap gameMap;
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		super("Hobbit's Adventure");
		setSize(SIZE);
		setLayout(new BorderLayout());
		initModel();
		initComponents();
		initListeners();
		setVisible(true);
	}
	
	private void initModel() {
		try {
			this.mapReader = new MapReader();
			this.gameMap = mapReader.readGameMap();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void initComponents() {
		this.characterPane = new CharacterPane();
		this.mapPane = new GameMapPane(gameMap);
		
		add(characterPane, BorderLayout.EAST);
		add(mapPane, BorderLayout.CENTER);
	}
	
	private void initListeners() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) { System.exit(0); }
		});
	}
}
