package com.heartmusic;

import java.io.Serializable;

public class Music implements Serializable{
	private static final long serialVersionUID = 1L;
	private String title;
	private String artist;
	private String path;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	@Override
	public String toString() {
		return "Music [title=" + title + ", artist=" + artist + ", path="
				+ path + "]";
	}
}
