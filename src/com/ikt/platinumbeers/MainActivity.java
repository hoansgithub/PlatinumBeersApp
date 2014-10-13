package com.ikt.platinumbeers;

import java.util.HashMap;
import java.util.Vector;

import com.ikt.utilities.PagerAdapter;
import com.ikt.utilities.TabUtil;
import com.ikt.utilities.TabUtil.TabInfo;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends ActionBarActivity implements TabHost.OnTabChangeListener, ViewPager.OnPageChangeListener {

	private Vector<Fragment> fragments;
	private TabHost mTabHost;
	private HashMap<String, TabInfo> mapTabInfo;
	private PagerAdapter mPagerAdapter;
	private ViewPager mViewPager;
	private Button btnLogout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//init variables
		this.mapTabInfo = new HashMap<String, TabInfo>();
		
		setContentView(R.layout.activity_main);
		 this.fragments = new Vector<Fragment>();
	        fragments.add(Fragment.instantiate(this, NewCustomerFragment.class.getName()));
	        fragments.add(Fragment.instantiate(this, PayBillFragment.class.getName()));
	        fragments.add(Fragment.instantiate(this,	PrizeFragment.class.getName()));
	        fragments.add(Fragment.instantiate(this,	ReportFragment.class.getName()));
	     // Initialise the TabHost
	        this.initialiseTabHost(savedInstanceState);
	        if (savedInstanceState != null) {
	            mTabHost.setCurrentTabByTag(savedInstanceState.getString("tab")); //set the tab as per the saved state
	        }
	        // Intialise ViewPager
	        this.intialiseViewPager();
	        
	        //elements
	        this.btnLogout= (Button) findViewById(R.id.btn_logout);
	        this.btnLogout.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					onLogoutClick(v);
					
				}
			});
	}
	private void onLogoutClick(View v)
	{
		Intent tabi=new Intent(getApplicationContext(),AuthActivity.class);
		startActivity(tabi);
		finish();
	}
	private void intialiseViewPager() {
		  this.mPagerAdapter  = new PagerAdapter(super.getSupportFragmentManager(), fragments);
	        this.mViewPager = (ViewPager)super.findViewById(R.id.viewpager);
	        this.mViewPager.setAdapter(this.mPagerAdapter);
	        this.mViewPager.setOnPageChangeListener(this);
		
	}

	private void initialiseTabHost(Bundle args) {
		// TODO Auto-generated method stub
		 this.mTabHost = (TabHost)findViewById(android.R.id.tabhost);
	        mTabHost.setup();
	     
	        TabInfo tab1=new TabInfo("NEW", NewCustomerFragment.class, args,R.layout.tabs_layout);
	        TabInfo tab2=new TabInfo("PAY BILL", PayBillFragment.class, args,R.layout.tabs_layout);
	        TabInfo tab3=new TabInfo("PRIZE", PrizeFragment.class, args,R.layout.tabs_layout);
	        TabInfo tab4=new TabInfo("REPORT", ReportFragment.class, args,R.layout.tabs_layout);
	        TabUtil.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("NEW").setIndicator("NEW"), ( tab1));
	        this.mapTabInfo.put(tab1.getTag(), tab1);
	        TabUtil.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("PAY BILL").setIndicator("PAY BILL"), ( tab2));
	        this.mapTabInfo.put(tab2.getTag(), tab2);
	        TabUtil.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("PRIZE").setIndicator("PRIZE"), ( tab3));
	        this.mapTabInfo.put(tab3.getTag(), tab3);
	        TabUtil.AddTab(this, this.mTabHost, this.mTabHost.newTabSpec("REPORT").setIndicator("REPORT"), ( tab4));
	        this.mapTabInfo.put(tab4.getTag(), tab4);
	        mTabHost.setOnTabChangedListener(this);
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int position) {
		 this.mTabHost.setCurrentTab(position);
	      
	}

	@Override
	public void onTabChanged(String tabId) {
		int pos = this.mTabHost.getCurrentTab();
	     this.mViewPager.setCurrentItem(pos);
		
	}
}
