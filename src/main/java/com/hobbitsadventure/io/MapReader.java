package com.hobbitsadventure.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.hobbitsadventure.model.NonPlayerCharacter;
import com.hobbitsadventure.model.RealmCell;
import com.hobbitsadventure.model.RealmMap;
import com.hobbitsadventure.model.Thing;
import com.hobbitsadventure.model.Tile;
import com.hobbitsadventure.model.TileClass;
import com.hobbitsadventure.model.factory.NpcClasses;
import com.hobbitsadventure.model.factory.Things;
import com.hobbitsadventure.model.factory.TileClasses;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class MapReader {
	private Random random = new Random();
	
	public RealmMap read(String mapId) throws IOException {
		List<RealmCell[]> rowList = new ArrayList<RealmCell[]>();
		InputStream is = ClassLoader.getSystemResourceAsStream("maps/" + mapId + ".txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String rowStr;
		while ((rowStr = br.readLine()) != null) {
			int numCols = rowStr.length();
			RealmCell[] row = new RealmCell[numCols];
			for (int j = 0; j < numCols; j++) {
				TileClass tileClass = null;
				Thing thing = null;
				int heightNoise = 0;
				
				char ch = rowStr.charAt(j);
				switch (ch) {
				case '.':
					tileClass = TileClasses.BEACH;
					heightNoise = random.nextInt(2);
					break;
				case '@':
					tileClass = TileClasses.FLOOR_STONE;
					thing = Things.BLUE_GEM;
					break;
				case '$':
					tileClass = TileClasses.FLOOR_STONE;
					thing = Things.CHEST;
					break;
				case '%':
					tileClass = TileClasses.FLOOR_STONE;
					thing = Things.CHEST_OPEN;
					break;
				case ':':
					tileClass = TileClasses.GRASS;
					thing = Things.BRUSH;
					heightNoise = random.nextInt(2);
					break;
				case '#':
					tileClass = TileClasses.WATER;
					thing = Things.DOCK;
					break;
				case '[':
					tileClass = TileClasses.FLOOR_STONE;
					thing = Things.DOOR_CLOSED;
					break;
				case '`':
					tileClass = TileClasses.FLOOR_STONE;
					break;
				case '\'':
					tileClass = TileClasses.FLOOR_WOOD;
					break;
				case '!':
					tileClass = TileClasses.GRASS;
					thing = Things.TREE_TALL;
					heightNoise = random.nextInt(2);
					break;
				case ',':
					tileClass = TileClasses.GRASS;
					heightNoise = random.nextInt(2);
					break;
				case '^':
					tileClass = TileClasses.HILL;
					heightNoise = random.nextInt(2);
					break;
				case 'A':
					tileClass = TileClasses.GRASS;
					thing = Things.ROCK;
					heightNoise = random.nextInt(2);
					break;
				case '_':
					tileClass = TileClasses.ROAD;
					break;
				case 'D':
					tileClass = TileClasses.STAIRS_DOWN;
					break;
				case 'U':
					tileClass = TileClasses.STAIRS_UP;
					break;
				case '*':
					tileClass = TileClasses.GRASS;
					thing = Things.DOOR_CLOSED;
					break;
				case '=':
					// FIXME Need to be able to null this tile instead of rendering two images? Unless the objects are
					// going to be dynamic (e.g. player can build and/or destroy walls).
					tileClass = TileClasses.FLOOR_STONE;
					thing = Things.WALL_TALL;
					break;
				case '~':
					tileClass = TileClasses.WATER;
					break;
				default:
					throw new RuntimeException("Illegal map character: " + ch);	
				}
				
				RealmCell cell = new RealmCell();
				cell.setTile(new Tile(tileClass, tileClass.getYOffset() + heightNoise));
				cell.setThing(thing);
				row[j] = cell;
			}
			
			rowList.add(row);
		}
		
		int numRows = rowList.size();
		int numCols = rowList.get(0).length;
		RealmMap realmMap = new RealmMap(numRows, numCols);
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				RealmCell cell = rowList.get(i)[j];
				realmMap.setCell(i, j, cell);
				
				// Add a little more interest to the height map. This is just temporary.
//				double distance = Math.sqrt(((30 - i) * (30 - i)) + ((30 - j) * (30 - j)));
//				int offset = (int) Math.min(distance - 20.0, 0) * 5;
//				Tile tile = cell.getTile();
//				tile.setHeight(tile.getHeight() + offset);
			}
		}
		
		
		// FIXME Not sure we should re-read all the NPCs when reloading the map. Maybe they should have an existence
		// outside the map. Also the NPCs need to maintain their own positions as well (for movement), and updates need
		// to be synchronized with the object map. Maybe there is a better way to do it but that's what I have in mind
		// for now.
		realmMap.setCharacter(30, 25, new NonPlayerCharacter(NpcClasses.BUG));
		realmMap.setCharacter(28, 22, new NonPlayerCharacter(NpcClasses.PRINCESS));
		realmMap.setCharacter(10, 10, new NonPlayerCharacter(NpcClasses.CAT_GIRL));
		realmMap.setCharacter(20, 20, new NonPlayerCharacter(NpcClasses.HORN_GIRL));
		realmMap.setCharacter(40, 40, new NonPlayerCharacter(NpcClasses.PINK_GIRL));
		
		return realmMap;
	}
}
