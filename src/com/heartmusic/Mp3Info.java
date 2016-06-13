package com.heartmusic;

import java.io.Serializable;


public class Mp3Info implements Comparable<Mp3Info>,Serializable{
	long id;
	String title;
	String artist;
	long duration;
	long size;
	String url;

	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public long getDuration() {
		return duration;
	}
	public void setDuration(long duration) {
		this.duration = duration;
	}
	public double getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public void setTitle(String title) {
		this.title = title;

	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	@Override
	public String toString() {
		return "Music [title=" + title + ", artist=" + artist + ", url="
				+ url + "]";
	}
	@Override
	public int compareTo(Mp3Info arg0) {  
        return this.getArtist().compareTo(arg0.getArtist());  
    }
}