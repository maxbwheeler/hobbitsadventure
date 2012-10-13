package com.hobbitsadventure.io;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class ImageFactory {
	private Map<String, BufferedImage> images = new HashMap<String, BufferedImage>();
	
	public BufferedImage getImage(String id) {
		BufferedImage image = images.get(id);
		
		if (image == null) {
			try {
				InputStream is = ClassLoader.getSystemResourceAsStream(id + ".png");
				image = ImageIO.read(new BufferedInputStream(is));
				images.put(id, image);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
		
		return image;
	}
}
