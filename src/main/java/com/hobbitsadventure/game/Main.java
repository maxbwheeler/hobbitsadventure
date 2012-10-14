package com.hobbitsadventure.game;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import com.hobbitsadventure.game.ui.CharacterPane;
import com.hobbitsadventure.game.ui.CommandPane;
import com.hobbitsadventure.game.ui.MapPane;
import com.hobbitsadventure.io.AudioFactory;
import com.hobbitsadventure.model.GameState;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@SuppressWarnings("serial")
public class Main extends Frame {
	private static final Dimension SIZE = new Dimension(1200, 1000);
	
	// IO
	private AudioFactory audioFactory;
	
	// UI
	private CharacterPane characterPane;
	private MapPane mapPane;
	private CommandPane commandPane;
	
	// Model
	private GameState gameState;
	
	public static void main(String[] args) {
		new Main();
	}
	
	public Main() {
		super("Hobbit's Adventure");
		
		// Hm, this is really glitchy.
//		initAudio();
		
		initDefaults();
		initModel();
		initComponents();
		initListeners();
		setVisible(true);
	}
	
	private void initDefaults() {
		setSize(SIZE);
		setResizable(false);
		setLayout(new BorderLayout());
	}
	
	private void initModel() {
		try {
			this.gameState = new GameState();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private void initComponents() {
		this.characterPane = new CharacterPane(gameState);
		this.mapPane = new MapPane(gameState);
		this.commandPane = new CommandPane(gameState);
		
		add(characterPane, BorderLayout.EAST);
		add(mapPane, BorderLayout.CENTER);
		add(commandPane, BorderLayout.SOUTH);
	}
	
	private void initListeners() {
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent event) { System.exit(0); }
		});
	}
	
	private void initAudio() {
		this.audioFactory = AudioFactory.instance();
		audioFactory.playBackgroundMusic("ff4fores");
	}
}
