package com.heartmusic.websongs;

import java.io.Serializable;

public class Info implements Comparable<Info>,Serializable{
	int id;
	String nameString;
	String artist;
	long duration;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
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
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the nameString
	 */
	public String getNameString() {
		return nameString;
	}
	/**
	 * @param nameString the nameString to set
	 */
	public void setNameString(String nameString) {
		this.nameString = nameString;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "info [id=" + id + ", nameString=" + nameString + "]";
	}
	@Override
	public int compareTo(Info another) {
		// TODO 自动生成的方法存根
		return 0;
	}
}