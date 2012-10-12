package com.hobbitsadventure.io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.hobbitsadventure.model.GameMap;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class MapReader {
	
	/**
	 * Eventually this will read a game map from a map file or some other such source, but for right now it just returns
	 * a dummy map.
	 * 
	 * @return
	 */
	public GameMap readGameMap() throws IOException {
		List<int[]> rowList = new ArrayList<int[]>();
		InputStream is = ClassLoader.getSystemResourceAsStream("maps/map1.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String rowStr;
		while ((rowStr = br.readLine()) != null) {
			int rowLen = rowStr.length();
			int[] row = new int[rowLen];
			for (int i = 0; i < rowLen; i++) {
				char ch = rowStr.charAt(i);
				switch (ch) {
				case '_':
					row[i] = GameMap.WATER;
					break;
				case '^':
					row[i] = GameMap.MOUNTAIN;
					break;
				case ',':
					row[i] = GameMap.GRASS;
					break;
				case '!':
					row[i] = GameMap.FOREST;
					break;
				case '.':
					row[i] = GameMap.DESERT;
					break;
				case 'H': 
	                row[i] = GameMap.HOUSE;
	                break;
				default:
					throw new RuntimeException("Illegal map character: " + ch);	
				}
			}
			rowList.add(row);
		}
		
		int[][] rows = new int[rowList.size()][];
		for (int i = 0; i < rowList.size(); i++) {
			rows[i] = rowList.get(i);
		}
		
		return new GameMap(rows);
	}
}
