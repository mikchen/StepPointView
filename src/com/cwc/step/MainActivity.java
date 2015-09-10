package com.cwc.step;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity implements OnPageChangeListener
{

	private ViewPager	vp;
	private StepPointView	spv;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		vp = (ViewPager) findViewById(R.id.pager);
		spv = (StepPointView) findViewById(R.id.spv);
		vp.setAdapter(mAdapter);
		spv.setStepCounts(mAdapter.getCount());
		vp.setOnPageChangeListener(this);
	}
	
	private PagerAdapter mAdapter = new PagerAdapter() {
		
		@Override
		public boolean isViewFromObject(View arg0, Object arg1)
		{
			// TODO Auto-generated method stub
			return arg0==arg1;
		}
		
		@Override
		public int getCount()
		{
			// TODO Auto-generated method stub
			return 10;
		}
		
		public Object instantiateItem(android.view.ViewGroup container, int position) {
			TextView tv = new TextView(MainActivity.this);
			tv.setText(position+"");
			return tv;
		}
		
		public void destroyItem(android.view.ViewGroup container, int position, Object object) {
			
			container.removeView((View) object);
		};
	};

	@Override
	public void onPageScrollStateChanged(int arg0)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2)
	{
		spv.setStepOffset(arg1, arg0);
		
	}

	@Override
	public void onPageSelected(int arg0)
	{
		// TODO Auto-generated method stub
		
	}

}
