package com.heartmusic;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.MediaStore;

public class MusicUtil {
	public static List<Music> getMusicData(Context context) {

		List<Music> musicList = new ArrayList<Music>();

		ContentResolver musicResolver = context.getContentResolver();
		Cursor cursor = musicResolver.query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
				MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
		while (cursor.moveToNext()) {
			String musicName = cursor.getString(cursor
					.getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
			if (musicName.endsWith(".mp3")) {

				Music music = new Music();
				String title = cursor.getString(cursor
						.getColumnIndex(MediaStore.Audio.Media.TITLE));
				String artist = cursor.getString(cursor
						.getColumnIndex(MediaStore.Audio.Media.ARTIST));
				if (artist.equals("<unknown>")) {
					artist = "未知";
				}
				String path = cursor.getString(cursor
						.getColumnIndex(MediaStore.Audio.Media.DATA));
				music.setTitle(title);
				music.setArtist(artist);
				music.setPath(path);
				musicList.add(music);
			}
		}

		return musicList;
	}
}
