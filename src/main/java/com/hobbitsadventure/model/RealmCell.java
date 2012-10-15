package com.hobbitsadventure.model;


/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class RealmCell {
	private Tile tile;
	private Thing thing;
	private GameCharacter character;
	
	public Tile getTile() { return tile; }
	
	public void setTile(Tile tile) { this.tile = tile; }
	
	public Thing getThing() { return thing; }
	
	public void setThing(Thing thing) { this.thing = thing; }
	
	public GameCharacter getCharacter() { return character; }
	
	public void setCharacter(GameCharacter character) { this.character = character; }
}
