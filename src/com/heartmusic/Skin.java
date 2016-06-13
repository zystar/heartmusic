package com.heartmusic;

import com.heartmusic.sharedata.SkinData;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;

public class Skin extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.skin);
		
		int skinData = SkinData.getState();
		
		LinearLayout skinLL = (LinearLayout)findViewById(R.id.skinLL);
		switch(skinData){
		case 1:
			skinLL.setBackgroundResource(R.drawable.bk1);
			break;
		case 2:
			skinLL.setBackgroundResource(R.drawable.bk2);
			break;
		case 3:
			skinLL.setBackgroundResource(R.drawable.bk3);
			break;
		case 4:
			skinLL.setBackgroundResource(R.drawable.bk4);
			break;
		case 5:
			skinLL.setBackgroundResource(R.drawable.bk5);
			break;
		case 6:
			skinLL.setBackgroundResource(R.drawable.bk6);
			break;
		case 7:
			skinLL.setBackgroundResource(R.drawable.bk7);
			break;
		case 8:
			skinLL.setBackgroundResource(R.drawable.bk8);
			break;
		case 9:
			skinLL.setBackgroundResource(R.drawable.bk9);
			break;
		case 10:
			skinLL.setBackgroundResource(R.drawable.bk10);
			break;
		case 11:
			skinLL.setBackgroundResource(R.drawable.bk11);
			break;
		case 12:
			skinLL.setBackgroundResource(R.drawable.bk);
			break;
		}
		
		//返回
		ImageButton ima=(ImageButton) findViewById(R.id.returnBtn);
		ima.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MainActivity.a.finish();//结束MainActivity
				Intent i=new Intent();
				i.setClass(Skin.this, MainActivity.class);
				startActivity(i);
				Skin.this.finish();
			}
		});
		
		ImageView imgBtn1 = (ImageView)findViewById(R.id.imgBtn1);
		ImageView imgBtn2 = (ImageView)findViewById(R.id.imgBtn2);
		ImageView imgBtn3 = (ImageView)findViewById(R.id.imgBtn3);
		ImageView imgBtn4 = (ImageView)findViewById(R.id.imgBtn4);
		ImageView imgBtn5 = (ImageView)findViewById(R.id.imgBtn5);
		ImageView imgBtn6 = (ImageView)findViewById(R.id.imgBtn6);
		ImageView imgBtn7 = (ImageView)findViewById(R.id.imgBtn7);
		ImageView imgBtn8 = (ImageView)findViewById(R.id.imgBtn8);
		ImageView imgBtn9 = (ImageView)findViewById(R.id.imgBtn9);
		ImageView imgBtn10 = (ImageView)findViewById(R.id.imgBtn10);
		ImageView imgBtn11 = (ImageView)findViewById(R.id.imgBtn11);
		ImageView imgBtn12 = (ImageView)findViewById(R.id.imgBtn12);
		
		imgBtn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.a.finish();//结束MainActivity
				SkinData.setState(1);
				Intent i = new Intent(); 
				i.setClass(Skin.this, MainActivity.class);
                startActivity(i);
                finish();  //结束此activity
			}
		});
		imgBtn2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.a.finish();//结束MainActivity
				SkinData.setState(2);
				Intent i = new Intent(); 
				i.setClass(Skin.this, MainActivity.class);
				startActivity(i);
				finish();  //结束此activity
			}
		});
		imgBtn3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.a.finish();//结束MainActivity
				SkinData.setState(3);
				Intent i = new Intent(); 
				i.setClass(Skin.this, MainActivity.class);
				startActivity(i);
				finish();  //结束此activity
			}
		});
		imgBtn4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.a.finish();//结束MainActivity
				SkinData.setState(4);
				Intent i = new Intent(); 
				i.setClass(Skin.this, MainActivity.class);
				startActivity(i);
				finish();  //结束此activity
			}
		});
		imgBtn5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.a.finish();//结束MainActivity
				SkinData.setState(5);
				Intent i = new Intent(); 
				i.setClass(Skin.this, MainActivity.class);
				startActivity(i);
				finish();  //结束此activity
			}
		});
		imgBtn6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.a.finish();//结束MainActivity
				SkinData.setState(6);
				Intent i = new Intent(); 
				i.setClass(Skin.this, MainActivity.class);
				startActivity(i);
				finish();  //结束此activity
			}
		});
		imgBtn7.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.a.finish();//结束MainActivity
				SkinData.setState(7);
				Intent i = new Intent(); 
				i.setClass(Skin.this, MainActivity.class);
				startActivity(i);
				finish();  //结束此activity
			}
		});
		imgBtn8.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.a.finish();//结束MainActivity
				SkinData.setState(8);
				Intent i = new Intent(); 
				i.setClass(Skin.this, MainActivity.class);
				startActivity(i);
				finish();  //结束此activity
			}
		});
		imgBtn9.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.a.finish();//结束MainActivity
				SkinData.setState(9);
				Intent i = new Intent(); 
				i.setClass(Skin.this, MainActivity.class);
				startActivity(i);
				finish();  //结束此activity
			}
		});
		imgBtn10.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.a.finish();//结束MainActivity
				SkinData.setState(10);
				Intent i = new Intent(); 
				i.setClass(Skin.this, MainActivity.class);
				startActivity(i);
				finish();  //结束此activity
			}
		});
		imgBtn11.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.a.finish();//结束MainActivity
				SkinData.setState(11);
				Intent i = new Intent(); 
				i.setClass(Skin.this, MainActivity.class);
				startActivity(i);
				finish();  //结束此activity
			}
		});
		imgBtn12.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				MainActivity.a.finish();//结束MainActivity
				SkinData.setState(12);
				Intent i = new Intent(); 
				i.setClass(Skin.this, MainActivity.class);
				startActivity(i);
				finish();  //结束此activity
			}
		});
	}
}