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
import com.hobbitsadventure.ui.CommandPane;
import com.hobbitsadventure.ui.MapPane;

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
	private MapPane mapPane;
	private CommandPane commandPane;
	
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
		this.mapPane = new MapPane(gameMap);
		this.commandPane = new CommandPane(mapPane);
		
		add(characterPane, BorderLayout.EAST);
		add(mapPane, BorderLayout.CENTER);
		add(commandPane, BorderLayout.SOUTH);
	}
	
	private void initListeners() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) { System.exit(0); }
		});
	}
}
