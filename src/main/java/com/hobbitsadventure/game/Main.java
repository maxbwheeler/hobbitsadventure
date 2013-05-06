package com.hobbitsadventure.game;

import java.awt.Color;
import java.awt.DisplayMode;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Window;
import java.io.IOException;

import com.hobbitsadventure.io.AudioFactory;
import com.hobbitsadventure.model.GameCharacter;
import com.hobbitsadventure.model.GameState;
import com.hobbitsadventure.model.RealmMap;
import com.hobbitsadventure.model.Thing;
import com.hobbitsadventure.model.Tile;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class Main {
	private static final int TILE_WIDTH = 101;
	private static final int TILE_HEIGHT = 81;
	private static final int SIGHT_RADIUS = 3;
	
	private static final int FONT_SIZE = 24;
	
	private static final DisplayMode POSSIBLE_MODES[] = {
		
		// Hm, on Willie's laptop this is the only one that's working all of the sudden...
		// Oh, the app works with Java 6, but not Java 7 (even in Java 6 mode).
//		new DisplayMode(1920, 1200, 24, 0),
		
		new DisplayMode(800, 600, 32, 0),
		new DisplayMode(800, 600, 24, 0),
		new DisplayMode(800, 600, 16, 0),
		new DisplayMode(640, 480, 32, 0),
		new DisplayMode(640, 480, 24, 0),
		new DisplayMode(640, 480, 16, 0)
	};
	
	private volatile boolean isRunning;
	
	private ScreenManager screenManager;
	private InputManager inputManager;
	
	// IO
	private AudioFactory audioFactory;
	
	// Model
	private GameState gameState;
	
	public static void main(String[] args) {
		Main main = new Main();
		main.run();
	}
	
	public void run() {
		try {
			init();
			gameLoop();
		} finally {
			screenManager.restoreScreen();
		}
	}
	
	public void stop() {
		this.isRunning = false;
	}
	
	
	// =================================================================================================================
	// Init
	// =================================================================================================================
	
	public void init() {
		this.screenManager = new ScreenManager();
		
		System.out.println("Setting to full screen");
		DisplayMode displayMode = screenManager.findFirstCompatibleMode(POSSIBLE_MODES);
		System.out.println("Got display mode: " + displayMode);
		
		if (displayMode == null) {
			throw new RuntimeException("No valid display mode; exiting");
		}
		
		screenManager.setFullScreen(displayMode);
		
		Window window = screenManager.getFullScreenWindow();
		System.out.println("Got window: " + window);
		
		// Do this before creating the InputManager since we need to initialize the game state.
		initModel();
		
		System.out.println("Setting input manager");
		this.inputManager = new InputManager(window, this, gameState);
		
		System.out.println("Setting font");
		window.setFont(new Font("Dialog", Font.PLAIN, FONT_SIZE));
		
		isRunning = true;
		
		// Hm, this is really glitchy.
//		initAudio();
	}
	
//	private void initAudio() {
//		this.audioFactory = AudioFactory.instance();
//		audioFactory.playBackgroundMusic("ff4fores");
//	}
	
	private void initModel() {
		try {
			this.gameState = new GameState();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	// =================================================================================================================
	// Game loop
	// =================================================================================================================
	
	public void gameLoop() {
		long startTime = System.currentTimeMillis();
		long currTime = startTime;
		
		while (isRunning) {
			long elapsedTime = System.currentTimeMillis() - currTime;
			currTime += elapsedTime;
			
			// Update game state
			update(elapsedTime);
			
			// Refresh screen
			Graphics2D g = screenManager.getGraphics();
			draw(g);
			g.dispose();
			screenManager.update();
			
			// Sleep
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) { }
		}
	}
	
	/**
	 * Update the game state based on the amount of elapsed time that has passed.
	 * 
	 * @param elapsedTime
	 */
	public void update(long elapsedTime) {
	}
	
	public void draw(Graphics2D g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, screenManager.getWidth(), screenManager.getHeight());
		paintTiles(g);
		paintThingsAndCharacters(g);
	}
	
	private void paintTiles(Graphics g) {
		RealmMap realmMap = gameState.getRealmMap();
		int numRows = realmMap.getNumRows();
		int numCols = realmMap.getNumCols();
		int playerRow = gameState.getPlayerRow();
		int playerCol = gameState.getPlayerCol();
		
		int minI = playerRow - SIGHT_RADIUS;
		int maxI = playerRow + SIGHT_RADIUS;
		int minJ = playerCol - SIGHT_RADIUS;
		int maxJ = playerCol + SIGHT_RADIUS;
		
		for (int i = minI; i <= maxI; i++) {
			int rowIndex = (numRows + i) % numRows;
			int baseY = (i - minI) * TILE_HEIGHT;
			for (int j = minJ; j <= maxJ; j++) {
				int colIndex = (numCols + j) % numCols;
				
				// Paint tile
				Tile tile = realmMap.getTile(rowIndex, colIndex);
				int x = (j - minJ) * TILE_WIDTH;
				tile.paint(g, x, baseY);
				
				// Paint thing if it's occluded by subsequent tile roles
				Thing thing = realmMap.getThing(rowIndex, colIndex);
				if (thing != null && thing.isOccludedByTerrain()) {
					int y = baseY - 65 + tile.getHeight();
					g.drawImage(thing.getSprite(), x, y, null);
				}
			}
		}
	}
	
	private void paintThingsAndCharacters(Graphics g) {
		RealmMap realmMap = gameState.getRealmMap();
		int numRows = realmMap.getNumRows();
		int numCols = realmMap.getNumCols();
		int playerRow = gameState.getPlayerRow();
		int playerCol = gameState.getPlayerCol();
		
		int minI = playerRow - SIGHT_RADIUS;
		int maxI = playerRow + SIGHT_RADIUS;
		int minJ = playerCol - SIGHT_RADIUS;
		int maxJ = playerCol + SIGHT_RADIUS;
		
		for (int i = minI; i <= maxI; i++) {
			int rowIndex = (numRows + i) % numRows;
			int baseY = (i - minI) * TILE_HEIGHT;
			for (int j = minJ; j <= maxJ; j++) {
				int colIndex = (numCols + j) % numCols;
				Tile tile = realmMap.getTile(rowIndex, colIndex);
				int x = (j - minJ) * TILE_WIDTH;
				int y = baseY - 65 + tile.getHeight();
				
				// Paint thing (if we haven't already)
				Thing thing = realmMap.getThing(rowIndex, colIndex);
				if (thing != null && !thing.isOccludedByTerrain()) {
					g.drawImage(thing.getSprite(), x, y, null);
					
				}
				
				// Paint character
				GameCharacter character = realmMap.getCharacter(rowIndex, colIndex);
				if (character != null) {
					if (thing != null) { y += thing.getYOffset(); }
					g.drawImage(character.getSprite(), x, y, null);
				}
			}
		}
	}
}
