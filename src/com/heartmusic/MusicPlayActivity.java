package com.heartmusic;

import java.util.List;
import java.util.Random;

import com.heartmusic.sharedata.SkinData;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MusicPlayActivity extends Activity {

	private Music currentMusic;
	private List<Music> musics;
	private ServiceReceiver receiver;
	private int currentMusicIndex;
	private int currentPosition;
	private int duration;
	private int currentProgress;
	private int activityState;
	private boolean flagState = true;
	private int playStyle = 2;

	private ImageButton play;
	private TextView showInfo;
	private SeekBar mySeekBar;
	private TextView curTime;
	private TextView durTime;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.play_song);
		
		int skinData = SkinData.getState();
				
		LinearLayout ps_ll = (LinearLayout)findViewById(R.id.ps_ll);
		switch(skinData){
		case 1:
			ps_ll.setBackgroundResource(R.drawable.bk1);
			break;
		case 2:
			ps_ll.setBackgroundResource(R.drawable.bk2);
			break;
		case 3:
			ps_ll.setBackgroundResource(R.drawable.bk3);
			break;
		case 4:
			ps_ll.setBackgroundResource(R.drawable.bk4);
			break;
		case 5:
			ps_ll.setBackgroundResource(R.drawable.bk5);
			break;
		case 6:
			ps_ll.setBackgroundResource(R.drawable.bk6);
			break;
		case 7:
			ps_ll.setBackgroundResource(R.drawable.bk7);
			break;
		case 8:
			ps_ll.setBackgroundResource(R.drawable.bk8);
			break;
		case 9:
			ps_ll.setBackgroundResource(R.drawable.bk9);
			break;
		case 10:
			ps_ll.setBackgroundResource(R.drawable.bk10);
			break;
		case 11:
			ps_ll.setBackgroundResource(R.drawable.bk11);
			break;
		case 12:
			ps_ll.setBackgroundResource(R.drawable.bk);
			break;
		}
		//开启服务
//		intentService = new Intent(MusicPlayActivity.this, MusicService.class);
//		startService(intentService);

		//注册一个广播
		receiver = new ServiceReceiver();
		IntentFilter filter = new IntentFilter(
				"com.heartmusic.serviceReceiver");
		registerReceiver(receiver, filter);

		play = (ImageButton) findViewById(R.id.play);
		showInfo = (TextView) findViewById(R.id.showInfo);
		mySeekBar = (SeekBar) findViewById(R.id.mySeekBar);
		curTime = (TextView) findViewById(R.id.currentTime);
		durTime = (TextView) findViewById(R.id.durTime);

		musics = MusicUtil.getMusicData(this);

		mySeekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			public void onStopTrackingTouch(SeekBar seekBar) {
				currentProgress = mySeekBar.getProgress();
				currentPosition = (int) (currentProgress * 1.0 / 100 * duration);
				Intent intent = new Intent(
						"com.heartmusic.activityRecivity");
				intent.putExtra("currentPosition", currentPosition);
				sendBroadcast(intent);
			}

			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
			}
		});

	}

	protected void onResume() {
		super.onResume();
	    //接收LocalList携带过来的数据，显示歌曲名和歌唱者
		Intent intentMain = MusicPlayActivity.this.getIntent();
		currentMusic = (Music) intentMain.getSerializableExtra("currentMusic");
		currentMusicIndex = intentMain.getIntExtra("currentMusicIndex", -1);

		if (currentMusic != null) {
			showInfo.setText(currentMusic.getTitle() + "——"
					+ currentMusic.getArtist());
		}
	}

	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		setIntent(intent);
	}

	public class ServiceReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			//接收广播
			int state = intent.getIntExtra("state", -1);
			currentMusic = (Music) intent.getSerializableExtra("currentMusic");
			currentPosition = intent.getIntExtra("currentPosition", -1);
			duration = intent.getIntExtra("duration", -1);
			if (currentPosition != -1) {
				currentProgress = (int) (currentPosition * 1.0 / duration * 100);
				mySeekBar.setProgress(currentProgress);
				curTime.setText(intToString(currentPosition));
				durTime.setText(intToString(duration));
			}

			if (state == activityState) {
				flagState = false;
			} else {
				flagState = true;
			}
			if (state != -1) {
				if (state == 0x01) {
					play.setImageResource(R.drawable.pause); //播放按钮转变为暂停按钮
					if (flagState) {
						showInfo.setText(currentMusic.getTitle() + "——"
								+ currentMusic.getArtist());
					}

				} else if (state == 0x02 || state == 0x03) {
					play.setImageResource(R.drawable.play); //暂停按钮转变为播放按钮
					if (state == 0x03) {
						showInfo.setText("");
						curTime.setText("");
						durTime.setText("");
						mySeekBar.setProgress(0);
					} else if (state == 0x02) {
						if (flagState) {
							showInfo.setText(currentMusic.getTitle() + "——"
									+ currentMusic.getArtist());
						}
					}
				}
			}

			boolean isOver = intent.getBooleanExtra("over", false);

			if (isOver) {

				switch (playStyle) {
				case 2:
					currentMusicIndex = (currentMusicIndex + 1) % musics.size(); //歌曲循环列表播放
					break;
				case 3:
					currentMusicIndex = new Random().nextInt(musics.size()); //歌曲随机播放
					break;
				default:
					break;
				}

				currentMusic = musics.get(currentMusicIndex);
				Intent intentActivity = new Intent(
						"com.heartmusic.activityRecivity");
				intentActivity.putExtra("newSong", 1);
				intentActivity.putExtra("currentMusic", currentMusic);
				sendBroadcast(intentActivity);
				showInfo.setText(currentMusic.getTitle() + "——"
						+ currentMusic.getArtist());

			}

		}

	}

	public String intToString(int position) {
		int curMin = position / 1000 / 60;
		int curSecond = position / 1000 % 60;

		return addZero(curMin) + ":" + addZero(curSecond);

	}

	public String addZero(int num) {
		if (num < 10) {
			return "0" + num;
		} else {
			return num + "";
		}
	}
	
    //下一曲
	public void previous(View view) {
		currentMusicIndex--;
		if (currentMusicIndex == -1) {
			currentMusicIndex = 0;
		}
		currentMusic = musics.get(currentMusicIndex);
		Intent intent = new Intent("com.heartmusic.activityRecivity");
		intent.putExtra("newSong", 1);
		intent.putExtra("currentMusic", currentMusic);
		sendBroadcast(intent);

		showInfo.setText(currentMusic.getTitle() + "——"
				+ currentMusic.getArtist());
	}
   //停止
	public void stop(View view) {
		Intent intent = new Intent("com.heartmusic.activityRecivity");
		intent.putExtra("control", 1);
		sendBroadcast(intent);
		activityState = 0x03;
	}

	//播放
	public void play(View view) {

		Intent intent = new Intent("com.heartmusic.activityRecivity");
		intent.putExtra("control", 2);
		if (currentMusic == null) {
			currentMusic = musics.get(0);
			intent.putExtra("newSong", 1);
		}
		intent.putExtra("currentMusic", currentMusic);
		sendBroadcast(intent);
		if (activityState == 0x01) {
			activityState = 0x02;
		} else {
			activityState = 0x01;
		}
		showInfo.setText(currentMusic.getTitle() + "——"
				+ currentMusic.getArtist());

	}
   //下一曲 
	public void next(View view) {
		currentMusicIndex++;
		if (currentMusicIndex == musics.size()) {
			currentMusicIndex = musics.size() - 1;
		}
		currentMusic = musics.get(currentMusicIndex);
		Intent intent = new Intent("com.heartmusic.activityRecivity");
		intent.putExtra("newSong", 1);
		intent.putExtra("currentMusic", currentMusic);
		sendBroadcast(intent);

		showInfo.setText(currentMusic.getTitle() + "——"
				+ currentMusic.getArtist());
	}

	public boolean onCreateOptionsMenu(Menu menu) {

		getMenuInflater().inflate(R.menu.main, menu);

		return super.onCreateOptionsMenu(menu);
	}
	
   //播放样式菜单
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.single:
			item.setChecked(true);
			playStyle = 1;
			break;
		case R.id.listCircle:
			item.setChecked(true);
			playStyle = 2;
			break;
		case R.id.random:
			item.setChecked(true);
			playStyle = 3;
			break;
		case R.id.songList:
			Intent intent = new Intent(MusicPlayActivity.this,
					LocalList.class);
			startActivity(intent);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	protected void onDestroy() {
		super.onDestroy();
		if (receiver != null) {
			unregisterReceiver(receiver);
		}
	}
}
