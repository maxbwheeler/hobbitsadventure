package com.hobbitsadventure.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.hobbitsadventure.model.NonPlayerCharacter;
import com.hobbitsadventure.model.RealmCell;
import com.hobbitsadventure.model.RealmMap;
import com.hobbitsadventure.model.Thing;
import com.hobbitsadventure.model.Tile;
import com.hobbitsadventure.model.factory.NpcClasses;
import com.hobbitsadventure.model.factory.Things;
import com.hobbitsadventure.model.factory.Tiles;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class MapReader {
	
	public RealmMap read(String mapId) throws IOException {
		List<RealmCell[]> rowList = new ArrayList<RealmCell[]>();
		InputStream is = ClassLoader.getSystemResourceAsStream("maps/" + mapId + ".txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String rowStr;
		while ((rowStr = br.readLine()) != null) {
			int numCols = rowStr.length();
			RealmCell[] row = new RealmCell[numCols];
			for (int j = 0; j < numCols; j++) {
				Tile tile = null;
				Thing thing = null;
				
				char ch = rowStr.charAt(j);
				switch (ch) {
				case '.':
					tile = Tiles.BEACH;
					break;
				case '$':
					tile = Tiles.FLOOR_STONE;
					thing = Things.CHEST;
					break;
				case ':':
					tile = Tiles.GRASS;
					thing = Things.BRUSH;
					break;
				case '#':
					tile = Tiles.WATER;
					thing = Things.DOCK;
					break;
				case '[':
					tile = Tiles.FLOOR_STONE;
					thing = Things.DOOR_CLOSED;
					break;
				case '`':
					tile = Tiles.FLOOR_STONE;
					break;
				case '\'':
					tile = Tiles.FLOOR_WOOD;
					break;
				case '!':
					tile = Tiles.GRASS;
					thing = Things.TREE_TALL;
					break;
				case ',':
					tile = Tiles.GRASS;
					break;
				case '^':
					tile = Tiles.HILL;
					break;
				case 'A':
					tile = Tiles.GRASS;
					thing = Things.ROCK;
					break;
				case '_':
					tile = Tiles.ROAD;
					break;
				case 'D':
					tile = Tiles.STAIRS_DOWN;
					break;
				case 'U':
					tile = Tiles.STAIRS_UP;
					break;
				case '*':
					tile = Tiles.GRASS;
					thing = Things.DOOR_CLOSED;
					break;
				case '=':
					// FIXME Need to be able to null this tile instead of rendering two images? Unless the objects are
					// going to be dynamic (e.g. player can build and/or destroy walls).
					tile = Tiles.FLOOR_STONE;
					thing = Things.WALL_TALL;
					break;
				case '~':
					tile = Tiles.WATER;
					break;
				default:
					throw new RuntimeException("Illegal map character: " + ch);	
				}
				
				RealmCell cell = new RealmCell();
				cell.setTile(tile);
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
				realmMap.setCell(i, j, rowList.get(i)[j]);
			}
		}
		
		// FIXME Not sure we should re-read all the NPCs when reloading the map. Maybe they should have an existence
		// outside the map. Also the NPCs need to maintain their own positions as well (for movement), and updates need
		// to be synchronized with the object map. Maybe there is a better way to do it but that's what I have in mind
		// for now.
		realmMap.setCharacter(30, 25, new NonPlayerCharacter(NpcClasses.BUG));
		realmMap.setCharacter(29, 28, new NonPlayerCharacter(NpcClasses.PRINCESS));
		realmMap.setCharacter(10, 10, new NonPlayerCharacter(NpcClasses.CAT_GIRL));
		realmMap.setCharacter(20, 20, new NonPlayerCharacter(NpcClasses.HORN_GIRL));
		realmMap.setCharacter(40, 40, new NonPlayerCharacter(NpcClasses.PINK_GIRL));
		
		return realmMap;
	}
}
