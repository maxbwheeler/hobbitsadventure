package com.hobbitsadventure.game;

import java.awt.DisplayMode;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

/**
 * @author David Brackeen
 */
public class ScreenManager {
	private GraphicsDevice device;
	
	public ScreenManager() {
		GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
		device = env.getDefaultScreenDevice();
	}
	
	public DisplayMode[] getCompatibleDisplayModes() {
		return device.getDisplayModes();
	}
	
	public DisplayMode findFirstCompatibleMode(DisplayMode[] desiredModes) {
		DisplayMode[] actualModes = device.getDisplayModes();
		for (DisplayMode desiredMode : desiredModes) {
			for (DisplayMode actualMode : actualModes) {
				System.out.println("actualMode.width=" + actualMode.getWidth() +
						", actualMode.height=" + actualMode.getHeight() +
						", actualMode.bitDepth=" + actualMode.getBitDepth() +
						", actualMode.refreshRate=" + actualMode.getRefreshRate());
				if (displayModesMatch(desiredMode, actualMode)) {
					return desiredMode;
				}
			}
		}
		return null;
	}
	
	public DisplayMode getCurrentDisplayMode() { return device.getDisplayMode(); }
	
	public boolean displayModesMatch(DisplayMode mode1, DisplayMode mode2) {
		if (mode1.getWidth() != mode2.getWidth() || mode1.getHeight() != mode2.getHeight()) {
			return false;
		}
		if (mode1.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI &&
				mode2.getBitDepth() != DisplayMode.BIT_DEPTH_MULTI &&
				mode1.getBitDepth() != mode2.getBitDepth()) {
			return false;
		}
		if (mode1.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN &&
				mode2.getRefreshRate() != DisplayMode.REFRESH_RATE_UNKNOWN &&
				mode1.getRefreshRate() != mode2.getRefreshRate()) {
			return false;
		}
		return true;
	}
	
	public void setFullScreen(DisplayMode displayMode) {
		JFrame frame = new JFrame();
		frame.setUndecorated(true);
		frame.setIgnoreRepaint(true);
		frame.setResizable(false);
		
		device.setFullScreenWindow(frame);
		if (displayMode != null && device.isDisplayChangeSupported()) {
			try {
				device.setDisplayMode(displayMode);
			} catch (IllegalArgumentException e) {
				throw e;
			}
		}
		frame.createBufferStrategy(2);
	}
	
	public Graphics2D getGraphics() {
		Window window = device.getFullScreenWindow();
		if (window != null) {
			BufferStrategy strategy = window.getBufferStrategy();
			return (Graphics2D) strategy.getDrawGraphics();
		} else {
			return null;
		}
	}
	
	public void update() {
		Window window = device.getFullScreenWindow();
		if (window != null) {
			BufferStrategy strategy = window.getBufferStrategy();
			if (!strategy.contentsLost()) {
				strategy.show();
			}
		}
		
		// Sync the display on some systems. On Linux, this fixes event queue problems.
		Toolkit.getDefaultToolkit().sync();
	}
	
	public Window getFullScreenWindow() {
		return device.getFullScreenWindow();
	}
	
	public int getWidth() {
		Window window = device.getFullScreenWindow();
		return (window == null ? 0 : window.getWidth());
	}
	
	public int getHeight() {
		Window window = device.getFullScreenWindow();
		return (window == null ? 0 : window.getHeight());
	}
	
	public void restoreScreen() {
		Window window = device.getFullScreenWindow();
		if (window != null) {
			window.dispose();
		}
		device.setFullScreenWindow(null);
	}
	
	public BufferedImage createCompatibleImage(int w, int h, int transparency) {
		Window window = device.getFullScreenWindow();
		if (window != null) {
			GraphicsConfiguration gc = window.getGraphicsConfiguration();
			return gc.createCompatibleImage(w, h, transparency);
		}
		return null;
	}
}
