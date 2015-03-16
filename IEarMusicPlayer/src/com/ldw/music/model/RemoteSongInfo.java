package com.ldw.music.model;

public class RemoteSongInfo {
	public String musicName="<unKown Song>";
	public String artist="unKown";
	public int duration= 0;//音乐时长 ms
	public int curtPositon =0;
	public RemotePlayState playState = RemotePlayState.NONE;
	public RemoteSongInfo(String mn,String art,int duration)
	{
		this.musicName = mn;
		this.artist = art;
		this.duration = duration;
	}
	public static enum RemotePlayState{
		PLAYING,
		PAUSE,
		BUFFERING,
		NONE
	}
	public Boolean isPlaying()
	{
		return (playState == RemotePlayState.PLAYING)?true:false;
	}
	public void play()
	{
		playState = RemotePlayState.PLAYING;
	}
	public void pause()
	{
		playState = RemotePlayState.PAUSE;
	}
}
