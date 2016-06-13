package com.heartmusic;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.heartmusic.R;
import com.heartmusic.sharedata.SkinData;
import com.heartmusic.slidingmenu.Sliding;
import com.heartmusic.tuling.TuLingMainActivity;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;

import android.app.TabActivity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Canvas;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;
import android.widget.Toast;

@SuppressWarnings("deprecation")
public class MainActivity extends TabActivity {
	
    private MediaPlayer mplayer = new MediaPlayer();
	static MainActivity a;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//去掉标题栏
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		setContentView(R.layout.activity_main);

		a = this;
		
		int skinData = SkinData.getState();
		
		TabHost tabhost = (TabHost)findViewById(android.R.id.tabhost);
		switch(skinData){
		case 1:
			tabhost.setBackgroundResource(R.drawable.bk1);
			break;
		case 2:
			tabhost.setBackgroundResource(R.drawable.bk2);
			break;
		case 3:
			tabhost.setBackgroundResource(R.drawable.bk3);
			break;
		case 4:
			tabhost.setBackgroundResource(R.drawable.bk4);
			break;
		case 5:
			tabhost.setBackgroundResource(R.drawable.bk5);
			break;
		case 6:
			tabhost.setBackgroundResource(R.drawable.bk6);
			break;
		case 7:
			tabhost.setBackgroundResource(R.drawable.bk7);
			break;
		case 8:
			tabhost.setBackgroundResource(R.drawable.bk8);
			break;
		case 9:
			tabhost.setBackgroundResource(R.drawable.bk9);
			break;
		case 10:
			tabhost.setBackgroundResource(R.drawable.bk10);
			break;
		case 11:
			tabhost.setBackgroundResource(R.drawable.bk11);
			break;
		case 12:
			tabhost.setBackgroundResource(R.drawable.bk);
			break;
		}
		
		//去掉滚动条
				View sm=(ViewGroup) findViewById(R.id.slidingmenu);
				sm.setHorizontalScrollBarEnabled(false);
			
		
	
		//footview播放界面的功能实现
		
	      //歌曲名称与歌唱者
		TextView title = (TextView)findViewById(R.id.title);
		TextView artist = (TextView)findViewById(R.id.artist);
		Intent j = getIntent();
		String Title = j.getStringExtra("Title");
		title.setText(Title);
		
		Intent k = getIntent();
		String Artist = k.getStringExtra("Artist");
		artist.setText(Artist);
		
		Intent p = getIntent();
		final String Path = p.getStringExtra("path");
		
		//来自登录界面的信息
		Intent l = getIntent();
		String name = l.getStringExtra("name");
		if(name!=null){
			TextView tab1_tv1 = (TextView)findViewById(R.id.tab1_tv1);
			TextView menu_tv1 = (TextView)findViewById(R.id.menu_tv1);
			tab1_tv1.setText(name);
			menu_tv1.setText(name);
		}
		
		
		
		/*
		//SlidingMenu侧滑菜单
		final SlidingMenu menu = new SlidingMenu(this);
		menu.setMode(SlidingMenu.RIGHT);//设置右滑菜单
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);//设置滑动的屏幕范围，该设置为全屏区域都可以滑动
		menu.setBehindWidth(400);//设置SlidingMenu菜单的宽度   
		menu.setFadeDegree(0.35f);  //SlidingMenu滑动时的渐变程度
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);//必须调用  
		menu.setMenu(R.layout.menu_layout_right);//就是普通的layout布局  
		menu.setBehindCanvasTransformer(mTransformer); 
		
		*/
		
		//跳转到皮肤
		RelativeLayout skin=(RelativeLayout) findViewById(R.id.skin);
		skin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent();
				i.setClass(MainActivity.this, Skin.class);
				startActivity(i);
				
			}
		});

		//跳转到登录界面
		LinearLayout login=(LinearLayout) findViewById(R.id.menu_login);
		login.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent();
				i.setClass(MainActivity.this, Login.class);
				startActivity(i);
				
			}
		});
		
		//我的下载
		RelativeLayout my_downloads = (RelativeLayout)findViewById(R.id.my_downloads);
		my_downloads.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setClass(getApplicationContext(), MyDownloads.class);
				startActivity(i);
			}
		});
		
		//图灵随心聊
		RelativeLayout tuling = (RelativeLayout)findViewById(R.id.tuling);
		tuling.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setClass(getApplicationContext(), TuLingMainActivity.class);
				startActivity(i);
			}
		});
		
		//退出
		RelativeLayout exit = (RelativeLayout)findViewById(R.id.exit);
		exit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				System.exit(0);
			}
		});
		
		ImageButton ima=(ImageButton) findViewById(R.id.returnBtn);
		ima.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent();
				i.setClass(MainActivity.this, Login.class);
				startActivity(i);
				
			}
		});
		
		addTabHost();
		
		LinearLayout tab1_1 = (LinearLayout)findViewById(R.id.tab1_1);
		LinearLayout tab1_2 = (LinearLayout)findViewById(R.id.tab1_2);
		LinearLayout tab1_3 = (LinearLayout)findViewById(R.id.tab1_3);
		LinearLayout tab1_4 = (LinearLayout)findViewById(R.id.tab1_4);
		LinearLayout tab1_5 = (LinearLayout)findViewById(R.id.tab1_5);
		LinearLayout tab1_6 = (LinearLayout)findViewById(R.id.tab1_6);
		RelativeLayout tab1_7 = (RelativeLayout)findViewById(R.id.tab1_7);
		LinearLayout tab2_0 = (LinearLayout)findViewById(R.id.tab2_0);
		LinearLayout tab2_1 = (LinearLayout)findViewById(R.id.tab2_1);

		LinearLayout tab2_2 = (LinearLayout)findViewById(R.id.tab2_2);
		LinearLayout tab2_3 = (LinearLayout)findViewById(R.id.tab2_3);
		LinearLayout tab2_4 = (LinearLayout)findViewById(R.id.tab2_4);
		LinearLayout tab2_5 = (LinearLayout)findViewById(R.id.tab2_5);
		LinearLayout tab2_6 = (LinearLayout)findViewById(R.id.tab2_6);

		ImageButton optionBtn = (ImageButton)findViewById(R.id.optionBtn);
		

		tab1_1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//跳转到本地播放列表
				Intent i = new Intent();
				i.setClass(MainActivity.this, LocalList.class);
				startActivity(i);
			}
		});
		tab1_2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//跳转到我的收藏列表
				Intent i = new Intent();
				i.setClass(MainActivity.this, MyCollection.class);
				startActivity(i);
			}
		});
		tab1_3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//跳转到我的下载列表
				Intent i = new Intent();
				i.setClass(MainActivity.this, MyDownloads.class);
				startActivity(i);
			}
		});
		tab1_4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//跳转到最近播放列表
				Intent i = new Intent();
				i.setClass(MainActivity.this, RecentlyBroadcast.class);
				startActivity(i);
			}
		});
		tab1_5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//跳转到我喜欢听列表
				Intent i = new Intent();
				i.setClass(MainActivity.this, MyLike.class);
				startActivity(i);
			}
		});
		tab1_6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//跳转到默认播放列表
				Intent i = new Intent();
				i.setClass(MainActivity.this, LocalList.class);
				startActivity(i);
			}
		});
		tab1_7.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//跳转到登陆/注册列表
				Intent i = new Intent();
				i.setClass(MainActivity.this, Login.class);
				startActivity(i);
			}
		});
		tab2_0.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//跳转到网络播放列表
				Intent i = new Intent();
				i.setClass(MainActivity.this, SouSuoKuang.class);
				startActivity(i);
			}
		});
		tab2_1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//跳转到网络播放列表
				new Thread(){  
		            @Override  
		            public void run() {  
		                // TODO Auto-generated method stub  
		                super.run(); 
						try {
							URL url = new URL("http://heartmusic.oschina.mopaas.com/mp3/paihangbang.xml");
							HttpURLConnection conn=(HttpURLConnection)url.openConnection();
							int code=conn.getResponseCode();
							if(code==200){
								Intent i = new Intent();
				      	    	i.putExtra("xml_name" , "paihangbang.xml");
								i.setClass(MainActivity.this, WebList.class);
								startActivity(i);
							}else{
								Toast.makeText(MainActivity.this, "请求超时",Toast.LENGTH_LONG).show();
								Log.e("hehe", "hehe");
							}
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }  
		        }.start();
			}
		});

		tab2_2.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//跳转到网络播放列表
				new Thread(){  
		            @Override  
		            public void run() {  
		                // TODO Auto-generated method stub  
		                super.run(); 
						try {
							URL url = new URL("http://heartmusic.oschina.mopaas.com/mp3/paihangbang.xml");
							HttpURLConnection conn=(HttpURLConnection)url.openConnection();
							int code=conn.getResponseCode();
							if(code==200){
								Intent i = new Intent();
				      	    	i.putExtra("xml_name" , "yingshijinqu.xml");
								i.setClass(MainActivity.this, WebList.class);
								startActivity(i);
							}else{
								Toast.makeText(MainActivity.this, "请求超时",Toast.LENGTH_LONG).show();
								Log.e("hehe", "hehe");
							}
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }  
		        }.start();
			}
		});
		tab2_3.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//跳转到网络播放列表
				new Thread(){  
		            @Override  
		            public void run() {  
		                // TODO Auto-generated method stub  
		                super.run(); 
						try {
							URL url = new URL("http://heartmusic.oschina.mopaas.com/mp3/paihangbang.xml");
							HttpURLConnection conn=(HttpURLConnection)url.openConnection();
							int code=conn.getResponseCode();
							if(code==200){
								Intent i = new Intent();
				      	    	i.putExtra("xml_name" , "liuxingbang.xml");
								i.setClass(MainActivity.this, WebList.class);
								startActivity(i);
							}else{
								Toast.makeText(MainActivity.this, "请求超时",Toast.LENGTH_LONG).show();
								Log.e("hehe", "hehe");
							}
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }  
		        }.start();
			}
		});
		tab2_4.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//跳转到网络播放列表
				new Thread(){  
		            @Override  
		            public void run() {  
		                // TODO Auto-generated method stub  
		                super.run(); 
						try {
							URL url = new URL("http://heartmusic.oschina.mopaas.com/mp3/paihangbang.xml");
							HttpURLConnection conn=(HttpURLConnection)url.openConnection();
							int code=conn.getResponseCode();
							if(code==200){
								Intent i = new Intent();
				      	    	i.putExtra("xml_name" , "kgebang.xml");
								i.setClass(MainActivity.this, WebList.class);
								startActivity(i);
							}else{
								Toast.makeText(MainActivity.this, "请求超时",Toast.LENGTH_LONG).show();
								Log.e("hehe", "hehe");
							}
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }  
		        }.start();
			}
		});
		tab2_5.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//跳转到网络播放列表
				new Thread(){  
		            @Override  
		            public void run() {  
		                // TODO Auto-generated method stub  
		                super.run(); 
						try {
							URL url = new URL("http://heartmusic.oschina.mopaas.com/mp3/paihangbang.xml");
							HttpURLConnection conn=(HttpURLConnection)url.openConnection();
							int code=conn.getResponseCode();
							if(code==200){
								Intent i = new Intent();
				      	    	i.putExtra("xml_name" , "qupailiufeng.xml");
								i.setClass(MainActivity.this, WebList.class);
								startActivity(i);
							}else{
								Toast.makeText(MainActivity.this, "请求超时",Toast.LENGTH_LONG).show();
								Log.e("hehe", "hehe");
							}
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }  
		        }.start();
			}
		});
		tab2_6.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//跳转到网络播放列表
				new Thread(){  
		            @Override  
		            public void run() {  
		                // TODO Auto-generated method stub  
		                super.run(); 
						try {
							URL url = new URL("http://heartmusic.oschina.mopaas.com/mp3/paihangbang.xml");
							HttpURLConnection conn=(HttpURLConnection)url.openConnection();
							int code=conn.getResponseCode();
							if(code==200){
								Intent i = new Intent();
				      	    	i.putExtra("xml_name" , "lingshengtuijian.xml");
								i.setClass(MainActivity.this, WebList.class);
								startActivity(i);
							}else{
								Toast.makeText(MainActivity.this, "请求超时",Toast.LENGTH_LONG).show();
								Log.e("hehe", "hehe");
							}
						} catch (MalformedURLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		            }  
		        }.start();
			}
		});
		
		
		optionBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				View sm1=(ViewGroup) findViewById(R.id.slidingmenu);
				Sliding sm2=(Sliding)sm1;
				sm2.toggle();
			}
		});
		
		


	}
	

	CanvasTransformer mTransformer = new CanvasTransformer() {  
		@Override  
		public void transformCanvas(Canvas canvas, float percentOpen) {  
			float scale = (float) (percentOpen*0.25 + 0.75);  
			canvas.scale(scale, scale, canvas.getWidth()/2, canvas.getHeight()/2);  
		}  
	};
	
	//创建一个方法addTabHost()
	public void addTabHost(){
		//获取TabHost
        TabHost myTabHost = getTabHost();
        //添加第1个选项页面
        TabSpec spec = myTabHost.newTabSpec("我的音乐").setIndicator("我的音乐",null).setContent(R.id.tab1);
        myTabHost.addTab(spec);
        
        //添加第2个选项页面
        spec = myTabHost.newTabSpec("在线音乐").setIndicator("在线音乐", null).setContent(R.id.tab2);
        myTabHost.addTab(spec);
	}
	
	//重写返回键和菜单键
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Sliding sm=(Sliding) findViewById(R.id.slidingmenu);
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			if(sm.getIsOpen()){
				sm.closeMenu();
			}else{
				//改写返回键事件监听，使得back键功能类似home键，让Acitivty退至后台时不被系统销毁
				PackageManager pm = getPackageManager();    
		        ResolveInfo homeInfo =   
		            pm.resolveActivity(new Intent(Intent.ACTION_MAIN).addCategory(Intent.CATEGORY_HOME), 0);
		        ActivityInfo ai = homeInfo.activityInfo;    
	            Intent startIntent = new Intent(Intent.ACTION_MAIN);    
	            startIntent.addCategory(Intent.CATEGORY_LAUNCHER);    
	            startIntent.setComponent(new ComponentName(ai.packageName, ai.name));    
	            startActivitySafely(startIntent);    
	            return true;
			}
			return true;
		case KeyEvent.KEYCODE_MENU:
			if(sm.getIsOpen()){
				sm.closeMenu();
			}else{
				sm.openMenu();
			}
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}
	private void startActivitySafely(Intent intent) {    
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);    
        try {    
            startActivity(intent);    
        } catch (ActivityNotFoundException e) {    
            Toast.makeText(this, "null",    
                    Toast.LENGTH_SHORT).show();    
        } catch (SecurityException e) {    
            Toast.makeText(this, "null",    
                    Toast.LENGTH_SHORT).show();     
        }    
    } 
}

