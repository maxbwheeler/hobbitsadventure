package com.hobbitsadventure.tools;

import java.awt.Frame;
import java.io.IOException;

import com.hobbitsadventure.io.TerrainMapReader;
import com.hobbitsadventure.model.TerrainMap;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
@SuppressWarnings("serial")
public class MapViewer extends Frame {
	private TerrainMapReader terrainMapReader;
	private TerrainMap worldMap;
	
	public MapViewer() {
		try {
			this.terrainMapReader = new TerrainMapReader();
			this.worldMap = terrainMapReader.read("world");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
