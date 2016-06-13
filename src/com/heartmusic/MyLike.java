package com.heartmusic;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.heartmusic.R;
import com.heartmusic.sharedata.SkinData;

import android.app.Activity;
import android.app.TabActivity;
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
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

public class MyLike extends Activity {

	static MyLike a;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.singger);

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

		final List<Mp3Info> music = getSDcardFile();
		Collections.sort(music); // 按照歌手排序

		List<singger> slist = getSinggerlist(music);
		createSinggerAdapter(slist);
		// 添加本地音乐文本
		TextView head = (TextView) findViewById(R.id.tv_head);
		head.setText("本地歌手");
	}

	// ========================变量的声明===========================
	// 定义一个List来存放所有的mp3文件，可以存放路径也可以存放文件名
	List<String> mp3List = new ArrayList<String>();
	String path;
	// 获得SDCard根目录
	File SdcardFile = Environment.getExternalStorageDirectory();
	String mp3File = "";

	// 获取歌手列表
	public List<singger> getSinggerlist(final List<Mp3Info> mp3Infos){
		List<singger> singgerlist = new ArrayList<singger>(); 
	    singger singg= new singger(mp3Infos.get(0).getArtist(),0);
	    for (Iterator<Mp3Info> iterator = mp3Infos.iterator(); iterator.hasNext();) {  
	    	Mp3Info m = (Mp3Info) iterator.next();  
			if( (m.getArtist().compareTo(singg.getSingger())) == 0){
					singg.addNum();
			}
			else{
					singgerlist.add(singg);
					singg= new singger(m.getArtist(),1);
			}
		}
		singgerlist.add(singg);
		return singgerlist;
		}
	// 实现SinggerAdapter
	public void createSinggerAdapter(final List<singger> singgerlist) {
		List<HashMap<String, String>> sinlist = new ArrayList<HashMap<String, String>>();
		for (Iterator iterator = singgerlist.iterator(); iterator.hasNext();) {
			singger s = (singger) iterator.next();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("singger", s.getSingger());
			String num = s.getNum() + "首";
			map.put("num", num);
			sinlist.add(map);
		}
		SimpleAdapter sAdapter = new SimpleAdapter(this, sinlist,
				R.layout.local_singger_list, new String[] { "singger", "num" },
				new int[] { R.id.tv_locallist_Singger, R.id.tv_locallist_num });
		ListView sLv = (ListView) findViewById(R.id.Lv_local_singgerList);
		sLv.setAdapter(sAdapter);

		// 上下文菜单
		registerForContextMenu(sLv);

		// 跳转到播放界面
		sLv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				singger si = singgerlist.get(position);
				Intent i = new Intent();
				i.setClass(MyLike.this, SongOfSingger.class);
				i.putExtra("singger", si.getSingger());
				startActivity(i);
			}
		});
	}

	// 扫描SDCard
	public List<Mp3Info> getSDcardFile() {
		mp3List.clear();
		Cursor cursor = getContentResolver().query(
				MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
				MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
		List<Mp3Info> mp3Infos = new ArrayList<Mp3Info>();
		for (int i = 0; i < cursor.getCount(); i++) {
			Mp3Info mp3Info = new Mp3Info();
			cursor.moveToNext();
			int isMusic = cursor.getInt(cursor
					.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC));// 是否为音乐
			if (isMusic != 0) { // 只把音乐添加到集合当中
				long id = cursor.getLong(cursor
						.getColumnIndex(MediaStore.Audio.Media._ID)); // 音乐id
				String title = cursor.getString((cursor
						.getColumnIndex(MediaStore.Audio.Media.TITLE)));// 音乐标题
				String artist = cursor.getString(cursor
						.getColumnIndex(MediaStore.Audio.Media.ARTIST));// 艺术家
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

	// 转换音乐时长格式
	public static String getFormatedDateTime(String pattern, long dateTime) {
		SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
		return sDateFormat.format(new Date(dateTime + 0));
	}
}
