package com.cwc.step;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;



public class StepPointView extends RelativeLayout
{
	private LinearLayout	mLL;
	private ImageView		mVisity;
	private int				counts		= 2;
	private int				mInvisityID;
	private int				mVisityID;
	private int				mMarginleft	= 10;

	public StepPointView(Context context) {
		this(context, null);

	}

	private float	spacing;
	
	public StepPointView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initAttrs(context, attrs);

		initView();

		initData();
		
	}

	/**
	 * 初始化自定义属性
	 * 
	 * @param context
	 * @param attrs
	 */
	private void initAttrs(Context context, AttributeSet attrs)
	{
		TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.StepPointView);

		counts = array.getInt(R.styleable.StepPointView_step_counts, 2);
		counts = counts < 2 ? 2 : counts;

		mInvisityID = array.getResourceId(R.styleable.StepPointView_invisity_image, R.drawable.step_point_invisity);

		mVisityID = array.getResourceId(R.styleable.StepPointView_visity_image, R.drawable.step_point_visity);
		
		spacing = array.getFloat(R.styleable.StepPointView_step_spaceing, 4.0f);
		array.recycle();
	}

	/**
	 * 初始化数据
	 */
	private void initData()
	{
		updateSteps();
	}

	/**
	 * 更新界面显示的步奏
	 */
	public void updateSteps()
	{	
		mLL.removeAllViews();
		for (int i = 0; i < counts; i++)
		{
			ImageView inVisity = new ImageView(getContext());
			
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			
			params.leftMargin = i == 0 ? 0 : mMarginleft;
			
			inVisity.setImageResource(mInvisityID);
			
			mLL.addView(inVisity, params);
		}
	}

	/**
	 * 初始化界面
	 */
	private void initView()
	{
		View.inflate(getContext(), R.layout.step_point_view, this);
		mLL = (LinearLayout) findViewById(R.id.step_point_invisity);
		mVisity = (ImageView) findViewById(R.id.step_point_visity);
		mVisity.setImageResource(mVisityID);
	}
	
	private RelativeLayout.LayoutParams mParams;
	
	/**
	 * 将 显示的 小点滑动 滑动偏移的比例
	 * @param offset 滑动比例
	 * @param position 从哪个点开始的
	 */
	public void setStepOffset(float offset,int position){
		if(mParams==null){
			mParams = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		}
		mParams.leftMargin = (int) (offset*spacing+0.5f+position*spacing);
		mVisity.setLayoutParams(mParams);
		
	}
	
	/**
	 * 设置总共的步奏数
	 * 
	 * @param counts
	 */
	public void setStepCounts(int counts)
	{
		if (counts < 2) return;
		this.counts = counts;
		updateSteps();
	}

	public void setMarginLeft(int pixels)
	{
		mMarginleft = pixels;
	}
}
