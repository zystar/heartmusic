package com.heartmusic; 

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import com.heartmusic.R;
import com.heartmusic.sharedata.SkinData;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class WebPlayUi extends Activity{

	private Handler handler = new Handler();
	private MediaPlayer mplayer = new MediaPlayer();
	private SeekBar seekBar1;
	private TextView currentTime;
	private List<String> files;
	private String currentfile;
	private TextView filenameTv;
	private TextView allTime;
	private File path;
	private Runnable thread = new Runnable()
	{
		@Override
		public void run()
		{
			updateTextView();
//			playNext(true);
//			showLrc();
			handler.postDelayed(thread, 1000);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.web_play_ui);
		
		int skinData = SkinData.getState();
		RelativeLayout wpu_rl = (RelativeLayout)findViewById(R.id.wpu_rl);
		switch(skinData){
		case 1:
			wpu_rl.setBackgroundResource(R.drawable.bk1);
			break;
		case 2:
			wpu_rl.setBackgroundResource(R.drawable.bk2);
			break;
		case 3:
			wpu_rl.setBackgroundResource(R.drawable.bk3);
			break;
		case 4:
			wpu_rl.setBackgroundResource(R.drawable.bk4);
			break;
		case 5:
			wpu_rl.setBackgroundResource(R.drawable.bk5);
			break;
		case 6:
			wpu_rl.setBackgroundResource(R.drawable.bk6);
			break;
		case 7:
			wpu_rl.setBackgroundResource(R.drawable.bk7);
			break;
		case 8:
			wpu_rl.setBackgroundResource(R.drawable.bk8);
			break;
		case 9:
			wpu_rl.setBackgroundResource(R.drawable.bk9);
			break;
		case 10:
			wpu_rl.setBackgroundResource(R.drawable.bk10);
			break;
		case 11:
			wpu_rl.setBackgroundResource(R.drawable.bk11);
			break;
		case 12:
			wpu_rl.setBackgroundResource(R.drawable.bk);
			break;
		}
		
		allTime = (TextView) findViewById(R.id.music_end_time);
		
		//歌曲名称
		final TextView title = (TextView)findViewById(R.id.title);
		Intent j = getIntent();
		String Title = j.getStringExtra("Title");
		title.setText(Title);
		// 获取所有音乐文件
		files = getSDcardFile(j.getStringArrayListExtra("mp3List"));

		Intent p = getIntent();
		final String Path = p.getStringExtra("path");
		
		try {
			mplayer.setDataSource(Path);
			mplayer.prepare();
		} catch (IllegalArgumentException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		

		final ImageButton play=(ImageButton)findViewById(R.id.start);
		play.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if (!mplayer.isPlaying())
				{
					mplayer.start();
					play.setImageResource(R.drawable.pause);

				} else if (mplayer.isPlaying())
				{
					mplayer.pause();
					play.setImageResource(R.drawable.play);
				}
			}
		});
		seekBar1 = (SeekBar)findViewById(R.id.seekbar1);
		//获得歌曲的长度并设置成播放进度条的最大值  
        seekBar1.setMax(mplayer.getDuration());
		currentTime = (TextView) findViewById(R.id.music_start_time);
		currentTime.setText("0:00");
        new Thread(thread).start();
		seekBar1.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				mplayer.start();
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				mplayer.pause();
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				 if(fromUser){
			            mplayer.seekTo(progress);    //当进度条的值改变时，音乐播放器从新的位置开始播放
			        }
			}
		});
		// 上一首
		final ImageButton pre=(ImageButton)findViewById(R.id.previous);
		pre.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				int pos = files.indexOf(currentfile);
				if (pos - 1 >= 0)
				{
					play(files.get(pos - 1));
					mplayer.start();
					play.setImageResource(R.drawable.pause);
				} else
					
					Toast.makeText(WebPlayUi.this, "没有歌曲", Toast.LENGTH_SHORT).show();
			}
		});
		// 下一首
		final ImageButton next=(ImageButton)findViewById(R.id.next);
		next.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				int pos = files.indexOf(currentfile);
				if (pos + 1 < files.size())
				{
					play(files.get(pos + 1));
					Log.e("files",files.toString());
					mplayer.start();
				} else
				{
					Toast.makeText(WebPlayUi.this, "没有歌曲", Toast.LENGTH_SHORT).show();
				}
			}
		});
		
	}
	private ArrayList<String> getSDcardFile(ArrayList<String> mp3List) {
		return mp3List;
	}
	// 播放
	private void play(String filename)
	{
		mplayer.reset();
		try
		{
			// Log.i("MediaPlayerActivity","文件内存："+files.size()+"");
			// Log.i("MediaPlayerActivity", "第一首歌："+filename);
			mplayer.setDataSource(path + "/" + filename);
			currentfile = filename;
			filenameTv.setText(currentfile);
			mplayer.prepare();
			int m = mplayer.getDuration() / 1000;
			int s = m / 60;
			int add = m % 60;
			allTime.setText(s + ":" + add);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void onDestroy()
	{
		mplayer.release();
		mplayer = null;
		super.onDestroy();
	}
	//更新进度
	private void updateTextView()
	{
		if (mplayer != null)
		{
			int m = mplayer.getCurrentPosition() / 1000;
			int s = m / 60;
			int add = m % 60;
			if (add < 10)
				currentTime.setText(s + ":0" + add);
			else
				currentTime.setText(s + ":" + add);
			seekBar1.setProgress(mplayer.getCurrentPosition());
		}
	}
	
}
	
	

