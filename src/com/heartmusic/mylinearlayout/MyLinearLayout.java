package com.heartmusic.mylinearlayout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;

public class MyLinearLayout extends LinearLayout {

	public MyLinearLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		//Log.e("TAG", "MyLinearLayout");
		setChildrenDrawingOrderEnabled(true);
	}
	@Override
	protected int getChildDrawingOrder(int childCount, int i)
	{

		if (i == 0)
			return 1;
		
		if (i == 1)
			return 0;
		return super.getChildDrawingOrder(childCount, i);

	}

}
