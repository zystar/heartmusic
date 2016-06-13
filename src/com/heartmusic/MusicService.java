package com.heartmusic;

import java.io.IOException;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.IBinder;

public class MusicService extends Service {
	private ActivityReceiver receiver;
	private Music currentMusic;
	private MediaPlayer mPlayer = new MediaPlayer();
	private int state;
	private int currentPosition;
	private int duration;
	private boolean flag = false;

	public IBinder onBind(Intent intent) {
		return null;
	}

	public void onCreate() {
		super.onCreate();
		//注册一个广播
		receiver = new ActivityReceiver();
		IntentFilter filter = new IntentFilter(
				"com.heartmusic.activityRecivity");
		registerReceiver(receiver, filter);

		//当前歌曲播放完毕后
		mPlayer.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				Intent intent = new Intent(
						"com.heartmusic.serviceReceiver");
				intent.putExtra("over", true);
				sendBroadcast(intent);
			}
		});

	}

	public class ActivityReceiver extends BroadcastReceiver {
		public void onReceive(Context context, Intent intent) {
			int newSong = intent.getIntExtra("newSong", -1);
			if (newSong != -1) {
				currentMusic = (Music) intent
						.getSerializableExtra("currentMusic");
				prepareAndPlay(currentMusic);
				state = 0x01;// 播放状态
			} else {
				int control = intent.getIntExtra("control", -1);
				switch (control) {
				case 1:
					mPlayer.stop();
					state = 0x03;//̬停止状态
					break;
				case 2:
					if (state == 0x01) {
						mPlayer.pause();
						state = 0x02;// 暂停状态
					} else if (state == 0x02) {
						mPlayer.start();
						state = 0x01;
					} else {
						prepareAndPlay(currentMusic);
						state = 0x01;
					}
					break;

				default:
					break;
				}
			}

			currentPosition = intent.getIntExtra("currentPosition", -1);
			if (currentPosition != -1) {
				mPlayer.seekTo(currentPosition);
			}

			Intent intentService = new Intent(
					"com.heartmusic.serviceReceiver");
			intentService.putExtra("state", state);
			intentService.putExtra("currentMusic", currentMusic);
			intentService.putExtra("currentPosition", currentPosition);
			sendBroadcast(intentService);
		}

	}

	public void prepareAndPlay(Music music) {

		mPlayer.reset();
		try {
			mPlayer.setDataSource(music.getPath());  //得到歌曲路径
			mPlayer.prepare();  //准备播放
			mPlayer.start();    //播放音乐

			new Thread() {
				public void run() {
					super.run();
					while ((currentPosition < duration) && !flag) {

						try {
							Thread.sleep(1000);
								currentPosition = mPlayer.getCurrentPosition();
								duration = mPlayer.getDuration();
								Intent intent = new Intent(
										"com.heartmusic.serviceReceiver");
								intent.putExtra("currentPosition",
										currentPosition);
								intent.putExtra("duration", duration);
								intent.putExtra("state", state);
								intent.putExtra("currentMusic", currentMusic);
								sendBroadcast(intent);

						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void onDestroy() {
		super.onDestroy();
		if (receiver != null) {
			unregisterReceiver(receiver);
		}
		flag = true;
		mPlayer.stop();
		
	}

}
