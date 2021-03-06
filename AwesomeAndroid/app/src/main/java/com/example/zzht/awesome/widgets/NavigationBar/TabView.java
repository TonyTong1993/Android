package com.example.zzht.awesome.widgets.NavigationBar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioGroup;

import com.example.zzht.awesome.utils.ViewUtil;

public class TabView extends RadioGroup {
	
	private int mItemColorUnchecked;
	private int mItemColorChecked;
	
	private Paint mBorderPaint = new Paint();
	private Paint mDividerPaint = new Paint();
	
	private Rect mBorderRect = new Rect();
	
	private Path mBorderPath;
	
	private OnTabCheckedListener mOnTabCheckedListener;
	private OnTabItemCheckedChangeListener mOnTabItemCheckedChangeListener;
	
	private boolean mIsFixedSize = true;
	
	public interface OnTabCheckedListener {
		public void onChecked(CompoundButton tab, int position);
	}

	public TabView(Context context) {
		this(context, null);
	}
	
	public TabView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setWillNotDraw(false);
		init();
	}
	
	private void init() {
		setOrientation(HORIZONTAL);
		initPaints();
	}
	/***
	 * *
	 * Description:<br/>
	 * @version V1.0
	 */
	private void initPaints() {
		mBorderPaint.setAntiAlias(true);
		mBorderPaint.setStyle(Paint.Style.STROKE);
		mBorderPaint.setStrokeWidth(1);
		mBorderPaint.setStrokeJoin(Paint.Join.ROUND);
		
		mDividerPaint.setAntiAlias(true);
		mDividerPaint.setStyle(Paint.Style.FILL);
		mDividerPaint.setStrokeWidth(1);
	}
	/***
	 * 
	 * @param titles
	 * @param uncheckedColor
	 * @param checkedColor
	 * @param l
	 * @param selectedIndex defualt is 0. set tab selected state,if index >= size of tabs,do nothing  
	 */
	public void setTabs(String[] titles, int uncheckedColor, int checkedColor, OnTabCheckedListener l,int selectedIndex) {
		
		int size = titles.length;
		if (size < 2) {
			throw new RuntimeException("There must be at least tow tabs");
		}
		if(selectedIndex >= size)
			selectedIndex = 0;
		
		mOnTabCheckedListener = l;
		
		mItemColorUnchecked = uncheckedColor;
		mItemColorChecked = checkedColor;
		
		mBorderPaint.setColor(mItemColorChecked - 0x22000000);
		mDividerPaint.setColor(mItemColorChecked - 0x22000000);
		
		removeAllViews();
		
		for (int i = 0; i < size; i++) {
			String title = titles[i];
			TabItem item = new TabItem(getContext());
			item.setId(ViewUtil.generateViewId());
			item.setItemColors(mItemColorUnchecked, mItemColorChecked);
			if (0 == i) {
				item.setPosition(TabItem.POSITION_LEFT);
			} else if (i == size - 1) {
				item.setPosition(TabItem.POSITION_RIGHT);
			} else {
				item.setPosition(TabItem.POSITION_MIDDLE);
			}
			
			if (selectedIndex == i) {
				item.setChecked(true);
			} 
			mOnTabItemCheckedChangeListener = new OnTabItemCheckedChangeListener(i);
			item.setOnCheckedChangeListener(mOnTabItemCheckedChangeListener);
			addView(item);
			item.setText(title);
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (mIsFixedSize) {
			measureChild(widthMeasureSpec, heightMeasureSpec);
		}
	}
	
	void measureChild(int widthMeasureSpec, int heightMeasureSpec) {
		int itemCount = getChildCount();
        if (itemCount <= 0) {
        	return;
        }
        int maxWidth = 0;
        int maxHeight = 0;
        for (int i = 0; i < itemCount; i++) {
        	TabItem tabItem = (TabItem) getChildAt(i);
        	int width = tabItem.getMeasuredWidth();
        	int height = tabItem.getMeasuredHeight();
        	maxWidth = Math.max(maxWidth, width);
        	maxHeight = Math.max(maxHeight, height);
        }
        setMeasuredDimension(maxWidth * itemCount, maxHeight);
	}
	
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		if (!mIsFixedSize) {
			super.onLayout(changed, l, t, r, b);
		} else {
			layoutHorizontal(0, 0, getWidth(), getHeight());
		}
		mBorderRect.set(1, 1, getWidth() - 1, getHeight() - 1);
		initPaths();
	}
	
	void layoutHorizontal(int l, int t, int r, int b) {
        int itemCount = getChildCount();
        if (itemCount <= 0) {
        	return;
        }
        int itemWidth = (r - l) / itemCount;
        for (int i = 0; i < itemCount; i++) {
        	TabItem tabItem = (TabItem) getChildAt(i);
        	tabItem.layout(l + itemWidth * i, t, l + itemWidth * (i + 1), b);
        	tabItem.setText(tabItem.getText());
        }
    }
	
	private void initPaths() {
		if (null == mBorderPath && getHeight() > 0) {
			mBorderPath = new Path();
			mBorderPath.moveTo(mBorderRect.left + mBorderRect.height() / 2, mBorderRect.top);
			mBorderPath.lineTo(mBorderRect.right - mBorderRect.height() / 2, mBorderRect.top);
			RectF ovalRight = new RectF(mBorderRect.right - mBorderRect.height(), mBorderRect.top, mBorderRect.right, mBorderRect.bottom);
			mBorderPath.arcTo(ovalRight, -90, 180);
			mBorderPath.lineTo(mBorderRect.left + mBorderRect.height() / 2, mBorderRect.bottom);
			RectF ovalLeft = new RectF(mBorderRect.left, mBorderRect.top, mBorderRect.left + mBorderRect.height(), mBorderRect.bottom);
			mBorderPath.arcTo(ovalLeft, 90, 180);
			mBorderPath.close();
		}
	}
	
	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		initPaths();
		canvas.drawPath(mBorderPath, mBorderPaint);
		drawDivider(canvas);
	}
	
	private void drawDivider(Canvas canvas) {
		int childCount = getChildCount();
		if (childCount < 3) {
			return;
		}
		for (int i = 0; i < childCount - 1; i++) {
			View child = getChildAt(i);	
			canvas.drawLine(child.getRight(), 0, child.getRight(), getHeight(), mDividerPaint);
		}
		
	}
	
	private class OnTabItemCheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
		
		private int mPosition;
		
		public OnTabItemCheckedChangeListener(int position) {
			mPosition = position;
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
			if (isChecked && mOnTabCheckedListener != null) {
				mOnTabCheckedListener.onChecked(buttonView, mPosition);
			}
		}
		
	}
	
	public void setFixedSize(boolean fixedSize) {
		mIsFixedSize = fixedSize;
		requestLayout();
	}

}
