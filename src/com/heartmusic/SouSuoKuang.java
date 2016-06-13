package com.heartmusic;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class SouSuoKuang extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
		setContentView(R.layout.webtab2_0);
	}

}
