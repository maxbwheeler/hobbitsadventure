package com.hobbitsadventure.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.hobbitsadventure.model.TerrainMap;
import com.hobbitsadventure.model.Tile;
import com.hobbitsadventure.model.Tiles;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class TerrainMapReader {
	
	public TerrainMap read(String mapId) throws IOException {
		List<Tile[]> rowList = new ArrayList<Tile[]>();
		InputStream is = ClassLoader.getSystemResourceAsStream("maps/" + mapId + ".txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String rowStr;
		while ((rowStr = br.readLine()) != null) {
			int rowLen = rowStr.length();
			Tile[] row = new Tile[rowLen];
			for (int i = 0; i < rowLen; i++) {
				char ch = rowStr.charAt(i);
				switch (ch) {
				case '.': row[i] = Tiles.BEACH; break;
				case '$': row[i] = Tiles.CHEST; break;
				case ':': row[i] = Tiles.BRUSH; break;
				case '#': row[i] = Tiles.DOCK; break;
				case '[': row[i] = Tiles.DOOR_CLOSED; break;
				case '`': row[i] = Tiles.FLOOR_STONE; break;
				case '\'': row[i] = Tiles.FLOOR_WOOD; break;
				case '!': row[i] = Tiles.FOREST; break;
				case ',': row[i] = Tiles.GRASS; break;
				case '^': row[i] = Tiles.HILL; break;
				case 'A': row[i] = Tiles.MOUNTAIN; break;
				case '_': row[i] = Tiles.ROAD; break;
				case 'D': row[i] = Tiles.STAIRS_DOWN; break;
				case 'U': row[i] = Tiles.STAIRS_UP; break;
				case '*': row[i] = Tiles.TOWN; break;
				case '=': row[i] = Tiles.WALL_TALL; break;
				case '~': row[i] = Tiles.WATER; break;
				default:
					throw new RuntimeException("Illegal map character: " + ch);	
				}
			}
			rowList.add(row);
		}
		
		Tile[][] rows = new Tile[rowList.size()][];
		for (int i = 0; i < rowList.size(); i++) {
			rows[i] = rowList.get(i);
		}
		
		return new TerrainMap(rows);
	}
}
