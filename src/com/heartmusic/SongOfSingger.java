package com.heartmusic;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.heartmusic.MyLike;
import com.heartmusic.sharedata.SkinData;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class SongOfSingger extends Activity {
	static SongOfSingger a;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.local_singlist);

		ImageButton returnBtn = (ImageButton) findViewById(R.id.returnBtn);

		returnBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});

		a = this;
		int skinData = SkinData.getState();
		LinearLayout localLL = (LinearLayout) findViewById(R.id.localLL);
		switch (skinData) {
		case 1:
			localLL.setBackgroundResource(R.drawable.bk1);
			break;
		case 2:
			localLL.setBackgroundResource(R.drawable.bk2);
			break;
		case 3:
			localLL.setBackgroundResource(R.drawable.bk3);
			break;
		case 4:
			localLL.setBackgroundResource(R.drawable.bk4);
			break;
		case 5:
			localLL.setBackgroundResource(R.drawable.bk5);
			break;
		case 6:
			localLL.setBackgroundResource(R.drawable.bk6);
			break;
		case 7:
			localLL.setBackgroundResource(R.drawable.bk7);
			break;
		case 8:
			localLL.setBackgroundResource(R.drawable.bk8);
			break;
		case 9:
			localLL.setBackgroundResource(R.drawable.bk9);
			break;
		case 10:
			localLL.setBackgroundResource(R.drawable.bk10);
			break;
		case 11:
			localLL.setBackgroundResource(R.drawable.bk11);
			break;
		case 12:
			localLL.setBackgroundResource(R.drawable.bk);
			break;
		}
		

		Intent p = getIntent();
		final String sing = p.getStringExtra("singger");
		// 添加头部文本
		TextView head = (TextView) findViewById(R.id.tv_head);
		head.setText(sing);
		List<Mp3Info> ms = getSDcardFile(sing);
		createAdapter(ms);

	}

	// 创建MusicAdapter
	public void createAdapter(final List<Mp3Info> songOFsingger) {
		List<HashMap<String, String>> sslist = new ArrayList<HashMap<String, String>>();
		for (Iterator iterator = songOFsingger.iterator(); iterator.hasNext();) {
			Mp3Info mp3Info = (Mp3Info) iterator.next();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("title", mp3Info.getTitle());
			map.put("Artist", mp3Info.getArtist());
			map.put("duration",
					MyLike.getFormatedDateTime("mm:ss", mp3Info.getDuration()));
			map.put("size", String.valueOf(mp3Info.getSize()));
			map.put("url", mp3Info.getUrl());
			sslist.add(map);
		}
		SimpleAdapter smAdapter = new SimpleAdapter(this, sslist,
				R.layout.local_music_list, new String[] { "title", "Artist",
						"duration" }, new int[] { R.id.tv_locallist_Song,
						R.id.tv_locallist_Artist, R.id.tv_locallist_duration });
		final ListView SsLv = (ListView) findViewById(R.id.Lv_local_singList);
		SsLv.setAdapter(smAdapter);

		// 上下文菜单
		registerForContextMenu(SsLv);

		// 跳转到播放界面
		SsLv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				Mp3Info m = songOFsingger.get(position);
				Intent i = new Intent();
				i.setClass(SongOfSingger.this, MusicPlayActivity.class);
				i.putExtra("path", m.getUrl());
				i.putExtra("Title", m.getTitle());
				i.putExtra("Artist", m.getArtist());
				startActivity(i);
			}
		});
	}

	// 定义一个List来存放所有的mp3文件，可以存放路径也可以存放文件名
	List<String> mp3List = new ArrayList<String>();
	String path;
	// 获得SDCard根目录
	File SdcardFile = Environment.getExternalStorageDirectory();
	String mp3File = "";

	public List<Mp3Info> getSDcardFile(final String sing) {
		mp3List.clear();
		Cursor cursor = getContentResolver().query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
				MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
		List<Mp3Info> mp3Infos = new ArrayList<Mp3Info>();
		for (int i = 0; i < cursor.getCount(); i++) {
			Mp3Info mp3Info = new Mp3Info();
			cursor.moveToNext();
			String artist = cursor.getString(cursor
					.getColumnIndex(MediaStore.Audio.Media.ARTIST));// 艺术家
			int isMusic = cursor.getInt(cursor
					.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));// 是否为音乐
			if (isMusic != 0 && artist.compareTo(sing) == 0) { // 只把本歌手音乐添加到集合当中
				long id = cursor.getLong(cursor
						.getColumnIndex(MediaStore.Audio.Media._ID)); // 音乐id
				String title = cursor.getString((cursor
						.getColumnIndex(MediaStore.Audio.Media.TITLE)));// 音乐标题

				long duration = cursor.getLong(cursor
						.getColumnIndex(MediaStore.Audio.Media.DURATION));// 时长
				long size = cursor.getLong(cursor
						.getColumnIndex(MediaStore.Audio.Media.SIZE)); // 文件大小
				String url = cursor.getString(cursor
						.getColumnIndex(MediaStore.Audio.Media.DATA)); // 文件路径
				mp3Info.setId(id);
				mp3Info.setTitle(title);
				mp3Info.setArtist(artist);
				mp3Info.setDuration(duration);
				mp3Info.setSize(size);
				mp3Info.setUrl(url);
				mp3Infos.add(mp3Info);
			}
		}
		return mp3Infos;
	}
}
