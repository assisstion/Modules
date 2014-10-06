package com.github.assisstion.ModulePack.media;

public interface AudioPlayable extends Comparable<AudioPlayable>{
	void setPaused(boolean paused);

	boolean isPaused();
}
