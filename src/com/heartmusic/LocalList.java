package com.heartmusic;

import java.util.List;

import com.heartmusic.sharedata.SkinData;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class LocalList extends Activity {
	static LocalList a;
	private Intent intentService;
	private ListView myListView;
	private List<Music> musics;
	private Music currentMusic;
	private int currentMusicIndex;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.local_list);

		//开启服务
		intentService = new Intent(LocalList.this, MusicService.class);
		startService(intentService);
		
		//换肤
		a = this;
		int skinData = SkinData.getState();
		RelativeLayout ll_rl = (RelativeLayout)findViewById(R.id.ll_rl);
		switch(skinData){
		case 1:
			ll_rl.setBackgroundResource(R.drawable.bk1);
			break;
		case 2:
			ll_rl.setBackgroundResource(R.drawable.bk2);
			break;
		case 3:
			ll_rl.setBackgroundResource(R.drawable.bk3);
			break;
		case 4:
			ll_rl.setBackgroundResource(R.drawable.bk4);
			break;
		case 5:
			ll_rl.setBackgroundResource(R.drawable.bk5);
			break;
		case 6:
			ll_rl.setBackgroundResource(R.drawable.bk6);
			break;
		case 7:
			ll_rl.setBackgroundResource(R.drawable.bk7);
			break;
		case 8:
			ll_rl.setBackgroundResource(R.drawable.bk8);
			break;
		case 9:
			ll_rl.setBackgroundResource(R.drawable.bk9);
			break;
		case 10:
			ll_rl.setBackgroundResource(R.drawable.bk10);
			break;
		case 11:
			ll_rl.setBackgroundResource(R.drawable.bk11);
			break;
		case 12:
			ll_rl.setBackgroundResource(R.drawable.bk);
			break;
		}

		//开启服务
				intentService = new Intent(LocalList.this, MusicService.class);
				startService(intentService);
		
		myListView = (ListView) findViewById(R.id.myListView);

		musics = MusicUtil.getMusicData(this);

		//为myListView绑定adapter
		myListView.setAdapter(myAdapter);
		
		//为myListView绑定事件监听器
		myListView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				currentMusicIndex = position;
				Intent intent = new Intent(
						"com.heartmusic.activityRecivity");
				intent.putExtra("newSong", 1);
				currentMusic = musics.get(position);
				intent.putExtra("currentMusic", currentMusic);
				sendBroadcast(intent);

				//跳转到播放界面
				Intent intentAcitvity = new Intent(LocalList.this,
						MusicPlayActivity.class);
				intentAcitvity.putExtra("currentMusicIndex", currentMusicIndex);
				intentAcitvity.putExtra("currentMusic", currentMusic);
				startActivity(intentAcitvity);
				LocalList.this.finish();

			}
		});

	}

	//创建adapter，连接数据源和视图布局
	public BaseAdapter myAdapter = new BaseAdapter() {
		public View getView(int position, View convertView, ViewGroup parent) {

			View view = LocalList.this.getLayoutInflater().inflate(
					R.layout.music, null);     
			ImageView img = (ImageView) view.findViewById(R.id.musicImg);
			TextView title = (TextView) view.findViewById(R.id.title);
			TextView artist = (TextView) view.findViewById(R.id.artist);

			img.setImageResource(R.drawable.ic_launcher);
			title.setText(musics.get(position).getTitle());
			artist.setText(musics.get(position).getArtist());

			return view;
		}

		public long getItemId(int position) {
			return position;
		}

		public Object getItem(int position) {
			return musics.get(position);
		}

		public int getCount() {
			return musics.size();
		}
	};

}

