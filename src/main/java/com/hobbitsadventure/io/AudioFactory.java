package com.hobbitsadventure.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import org.apache.commons.io.IOUtils;

/**
 * @author Willie Wheeler (willie.wheeler@gmail.com)
 */
public class AudioFactory {
	private static final AudioFactory INSTANCE = new AudioFactory();
	
	private Map<String, Sequence> sequences = new HashMap<String, Sequence>();
	private ExecutorService executorService;
	private byte[] clickBytes;
	
	public static AudioFactory instance() { return INSTANCE; }
	
	private AudioFactory() {
		this.executorService = Executors.newFixedThreadPool(5);
		try {
			this.clickBytes = IOUtils.toByteArray(ClassLoader.getSystemResource("audio/click.mp3"));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void playBackgroundMusic(String id) {
		try {
			Sequencer sequencer = MidiSystem.getSequencer();
			sequencer.open();
			sequencer.setLoopCount(Sequencer.LOOP_CONTINUOUSLY);
			sequencer.setSequence(getSequence("ff4fores"));
			sequencer.start();
		} catch (MidiUnavailableException e) {
			throw new RuntimeException(e);
		} catch (InvalidMidiDataException e) {
			throw new RuntimeException(e);
		}
	}
	
	private Sequence getSequence(String id) {
		Sequence sequence = sequences.get(id);
		
		if (sequence == null) {
			try {
				InputStream is = ClassLoader.getSystemResourceAsStream("audio/" + id + ".mid");
				sequence = MidiSystem.getSequence(is);
				sequences.put(id, sequence);
			} catch (IOException e) {
				throw new RuntimeException(e);
			} catch (InvalidMidiDataException e) {
				throw new RuntimeException(e);
			}
		}
		
		return sequence;
	}
	
	public void playClickSound() {
		executorService.submit(new Runnable() {

			@Override
			public void run() {
				ByteArrayInputStream bais = new ByteArrayInputStream(clickBytes);
				try {
					new Player(new BufferedInputStream(bais)).play();
				} catch (JavaLayerException e) {
					throw new RuntimeException(e);
				}
			}
		});
	}
}
