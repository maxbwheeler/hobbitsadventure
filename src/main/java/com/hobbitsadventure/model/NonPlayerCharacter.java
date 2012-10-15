package com.hobbitsadventure.model;

import java.awt.image.BufferedImage;


/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class NonPlayerCharacter implements GameCharacter {
	private NpcClass npcClass;
	
	public NonPlayerCharacter(NpcClass npcClass) {
		this.npcClass = npcClass;
	}
	
	public NpcClass getNpcClass() { return npcClass; }
	
	@Override
	public BufferedImage getSprite() { return npcClass.getSprite(); }
}
