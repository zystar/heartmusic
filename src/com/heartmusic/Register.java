package com.heartmusic;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.heartmusic.Login;;

public class Register extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.register);
		Intent request=getIntent();
		Button reg=(Button) findViewById(R.id.reg);
		reg.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				EditText username=(EditText) findViewById(R.id.name1);
				EditText password=(EditText) findViewById(R.id.passw1);
				String name1=username.getText().toString().trim();
				String pass1=password.getText().toString().trim();
				Log.i("TAG",name1+"_"+pass1);
				UserService uService=new UserService(Register.this);
				User user=new User();
				user.setUsername(name1);
				user.setPassword(pass1);
				uService.register(user);
				Toast.makeText(Register.this, "注册成功", Toast.LENGTH_LONG).show();
				MainActivity.a.finish();
				Intent i = new Intent(); 
				i.setClass(Register.this, MainActivity.class);
      	    	i.putExtra("name" , name1);
				startActivity(i);
				finish();
			}
		});
	}
	
}
