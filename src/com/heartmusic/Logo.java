package com.heartmusic;

import java.util.Timer;
import java.util.TimerTask;

import com.heartmusic.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Logo extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去掉标题栏
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//设置activity全屏  
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,  
		WindowManager.LayoutParams.FLAG_FULLSCREEN);  
        
		setContentView(R.layout.logo);
		
		final Intent i = new Intent();
		i.setClass(Logo.this, MainActivity.class);
		
		
		Timer t = new Timer();
		TimerTask tt = new TimerTask() {
		   @Override
		   public void run(){
			   startActivity(i); //执行
			   Logo.this.finish();//执行完成后销毁此activity
		   }
		};
		t.schedule(tt, 1000 * 2); //2秒后
	}
}
