package com.heartmusic;

import com.heartmusic.R;
import com.heartmusic.sharedata.SkinData;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class RecentlyBroadcast extends Activity {
	
	static RecentlyBroadcast a;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.recently_broadcast);
		
	    ImageButton returnBtn = (ImageButton)findViewById(R.id.returnBtn);
		
		returnBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		a = this;
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
	}
}