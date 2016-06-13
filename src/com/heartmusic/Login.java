package com.heartmusic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.*;
import android.util.Log;

public class Login extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		
		ImageButton login_back = (ImageButton)findViewById(R.id.login_back);
			
		login_back.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
		Intent request=getIntent();
		findViews();
		//跳转到注册界面
		
				Button reg=(Button) findViewById(R.id.register);
				reg.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i=new Intent();
						i.setClass(Login.this, Register.class);
						startActivity(i);
					}
				});
				
	}
	private void findViews() {
		Button login=(Button) findViewById(R.id.login);
		login.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				EditText username=(EditText) findViewById(R.id.name);
				EditText password=(EditText) findViewById(R.id.passw);
				String name=username.getText().toString();
				String pass=password.getText().toString();
				Log.i("TAG",name+"_"+pass);
				UserService uService=new UserService(Login.this);
				boolean flag=uService.login(name, pass);
				if(flag){
					Log.i("TAG","登录成功");
					Toast.makeText(Login.this, "登录成功", Toast.LENGTH_LONG).show();
					MainActivity.a.finish();
					Intent i = new Intent(); 
					i.setClass(Login.this, MainActivity.class);
	      	    	i.putExtra("name" , name);
					startActivity(i);
					finish();
				}else{
					Log.i("TAG","登录失败");
					Toast.makeText(Login.this, "登录失败", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
