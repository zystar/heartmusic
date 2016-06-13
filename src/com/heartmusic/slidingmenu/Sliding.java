package com.heartmusic.slidingmenu;

import com.nineoldandroids.view.ViewHelper;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

public class Sliding extends HorizontalScrollView {
	private ViewGroup mWapper;
	private ViewGroup mMenu;
	private ViewGroup mContent;
	private int mWidth;
	private int rightpadding;
	private boolean once;
	private int mMenuwidth;
	private boolean isOpen;

	public Sliding(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		WindowManager wm=(WindowManager) context.getSystemService(context.WINDOW_SERVICE);
		DisplayMetrics outMetrics=new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		mWidth=outMetrics.widthPixels;
		rightpadding=(int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 80,context.getResources().getDisplayMetrics());
	}
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if(!once){
			mWapper=(LinearLayout)getChildAt(0);
			mContent=(LinearLayout) mWapper.getChildAt(0);
			mMenu=(ViewGroup) mWapper.getChildAt(1);
			 mMenuwidth=mMenu.getLayoutParams().width=mWidth-rightpadding;
			mContent.getLayoutParams().width=mWidth;
			once=true;
		}
		
		
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		super.onLayout(changed, l, t, r, b);
		if(changed){
			this.scrollTo(0, 0);
		}
		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		// TODO Auto-generated method stub
		int action=ev.getAction();
		switch (action) {
		case MotionEvent.ACTION_UP:
			int s=getScrollX();
			if(s>=mMenuwidth/2){
				this.smoothScrollTo(mMenuwidth, 0);
			}else{
				this.smoothScrollTo(0, 0);
			}
			return true;
		
		}
		return super.onTouchEvent(ev);
		
	}
	
	
	/**
	 * 打开菜单
	 */
	public void openMenu()
	{
		if (isOpen)
			return;
		this.smoothScrollTo(mMenuwidth, 0);
		isOpen = true;
	}

	public void closeMenu()
	{
		if (!isOpen)
			return;
		this.smoothScrollTo(0, 0);
		isOpen = false;
	}

	/**
	 * 切换菜单
	 */
	public void toggle()
	{
		if (isOpen)
		{
			closeMenu();
		} else
		{
			openMenu();
		}
	}
	@Override
	protected void onScrollChanged(int l, int t, int oldl, int oldt)
	{
		super.onScrollChanged(l, t, oldl, oldt);
		float scale = l * 1.0f / mMenuwidth; // 1 ~ 0

		/**
		 * 区别1：内容区域1.0~0.7 缩放的效果 scale : 1.0~0.0 0.7 + 0.3 * scale
		 * 
		 * 区别2：菜单的偏移量需要修改
		 * 
		 * 区别3：菜单的显示时有缩放以及透明度变化 缩放：0.7 ~1.0 1.0 - scale * 0.3 透明度 0.6 ~ 1.0 
		 * 0.6+ 0.4 * (1- scale) ;
		 * 
		 */
		float rightScale = 0.8f + 0.2f * (1-scale);
		float leftScale = 1.0f - (1-scale) * 0.3f;
		float leftAlpha = 0.6f + 0.4f * scale;
		

		// 调用属性动画，设置TranslationX
		ViewHelper.setTranslationX(mMenu, -mMenuwidth*(1-scale)*0.8f );
		
		ViewHelper.setScaleX(mMenu, leftScale);
		ViewHelper.setScaleY(mMenu, leftScale);
		ViewHelper.setAlpha(mMenu, leftAlpha);
		// 设置content的缩放的中心点
		ViewHelper.setPivotX(mContent, mWidth);
		ViewHelper.setPivotY(mContent, mContent.getHeight() / 2);
		ViewHelper.setScaleX(mContent, rightScale);
		ViewHelper.setScaleY(mContent, rightScale);
	
	}
	
	public boolean getIsOpen(){
		return isOpen;
	}

}
