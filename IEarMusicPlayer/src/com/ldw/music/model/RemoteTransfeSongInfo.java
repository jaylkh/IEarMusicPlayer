package com.ldw.music.model;

public class RemoteTransfeSongInfo {
	public String musicName;
	public String artist;	
	public String path;
	public Boolean isSelected = false;
	public RemoteTransfeSongInfo(String musicName, String artist, String path,Boolean isSelected) {
		this.musicName = musicName;
		this.artist = artist;
		this.path = path;
		this.isSelected = isSelected;
	}
}
