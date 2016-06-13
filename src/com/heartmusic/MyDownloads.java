package com.heartmusic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.heartmusic.R;
import com.heartmusic.sharedata.SkinData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class MyDownloads extends Activity {
	
//	static MyDownloads a;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.my_downloads);
		
		//返回
	    ImageButton returnBtn = (ImageButton)findViewById(R.id.returnBtn);
		returnBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		//
		TextView tv_head = (TextView)findViewById(R.id.tv_head);
		tv_head.setText("我的下载");
		
//		a = this;
		int skinData = SkinData.getState();
		LinearLayout localLL = (LinearLayout)findViewById(R.id.localLL);
		switch(skinData){
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
		File f = new File("/sdcard/DownFile");
		List<Music> list = getSDcardFile(f);
		createMusicAdapter(list);
	}
	ArrayList<String> mp3List = new ArrayList<String>();
	String path;
	//获得SDCard根目录
	String mp3File = "";
	public List<Music> getSDcardFile(File groupPath){
		mp3List.clear();
		List<Music> mp3Infos = new ArrayList<Music>();  
		//循环获取sdcard目录下面的目录和文件
		for(int i=0; i< groupPath.listFiles().length; i++){
			Music mp3Info = new Music();  
			File childFile = groupPath.listFiles()[i];
			//如果是文件的话，判断是不是以.mp3结尾，是就加入到List里面
			if(childFile.toString().endsWith(".mp3")){
				
				//打印文件的文件名
				Log.e("name", childFile.getName().toString());
				//打印文件的路径
				Log.e("path", childFile.getAbsolutePath().toString());
				mp3List.add(childFile.getName());
				mp3List.add(childFile.getAbsolutePath());
				path = childFile.getAbsolutePath();
				mp3Info.setTitle(childFile.getName());
				mp3Info.setPath(childFile.getAbsolutePath());
				mp3Infos.add(mp3Info);  
			}
		}
		return mp3Infos;
	}
	public void createMusicAdapter(final List<Music> mp3Infos){
		List<HashMap<String, String>> mp3list = new ArrayList<HashMap<String, String>>();  
        for (Iterator<Music> iterator = mp3Infos.iterator(); iterator.hasNext();) {  
        	Music mp3Info = (Music) iterator.next();  
            HashMap<String, String> map = new HashMap<String, String>();  
            map.put("title", mp3Info.getTitle());  
            map.put("Artist", mp3Info.getArtist());   
            map.put("url", mp3Info.getPath());  
            mp3list.add(map);  
        }  
        SimpleAdapter mAdapter = new SimpleAdapter(this, mp3list,
        		R.layout.local_music_list, 
        		new String[] { "title", "Artist", "duration" },  
                new int[] { R.id.tv_locallist_Song, R.id.tv_locallist_Artist,R.id.tv_locallist_duration});
        ListView my_downloads_ListView = (ListView)findViewById(R.id.my_downloads_ListView);
        my_downloads_ListView.setAdapter(mAdapter);
        
        //上下文菜单
        registerForContextMenu(my_downloads_ListView);
      //跳转到播放界面
        my_downloads_ListView.setOnItemClickListener(new OnItemClickListener() {
     	      @Override
			public void onItemClick(AdapterView<?> parent,View v, int position,long id){
     	    	 Music mp3Info = mp3Infos.get(position);
     	    	 Intent i=new Intent();
     	    	 i.setClass(MyDownloads.this,WebPlayUi.class);
     	    	 i.putExtra("path" , mp3Info.getPath());
     	    	 i.putExtra("Title" , mp3Info.getTitle());
     	    	 i.putExtra("Artist" , mp3Info.getArtist());
     	    	 i.putExtra("position" , position);
     	    	 i.putStringArrayListExtra("mp3List", mp3List);
     	    	 startActivity(i);
     	 }
		});
	}
}