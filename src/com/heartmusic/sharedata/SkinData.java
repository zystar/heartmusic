package com.heartmusic.sharedata;

import android.app.Application;

public class SkinData extends Application {
	private static int skin = 0;
	
	public static int getState(){
		return skin;
	}
	public static void setState(int i){
		skin = i;
	}   
}
