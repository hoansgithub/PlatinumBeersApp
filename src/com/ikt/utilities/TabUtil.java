package com.ikt.utilities;

import com.ikt.platinumbeers.R;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.TabContentFactory;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;



public class TabUtil{
	 public static class TabInfo {
         private String tag;
         private Class<?> clss;
         private Bundle args;
         private Fragment fragment;
         private int tabLayoutResouceID;
         public TabInfo(String tag, Class<?> clazz, Bundle args,int tabLayoutResourceID) {
             this.setTag(tag);
             this.setClss(clazz);
             this.setArgs(args);
             this.setTabLayoutResouceID(tabLayoutResourceID);
         }
		public String getTag() {
			return this.tag;
		}
		public void setTag(String tag) {
			this.tag = tag;
		}
		public Class<?> getClss() {
			return clss;
		}
		public void setClss(Class<?> clss) {
			this.clss = clss;
		}
		public Bundle getArgs() {
			return args;
		}
		public void setArgs(Bundle args) {
			this.args = args;
		}
		public Fragment getFragment() {
			return fragment;
		}
		public void setFragment(Fragment fragment) {
			this.fragment = fragment;
		}
	
		public int getTabLayoutResouceID() {
			return tabLayoutResouceID;
		}
		public void setTabLayoutResouceID(int tabLayoutResouceID) {
			this.tabLayoutResouceID = tabLayoutResouceID;
		}
 
    }
	 public static class TabFactory implements TabContentFactory {
		 
	        private final Context mContext;
	        public TabFactory(Context context) {
	            mContext = context;
	        }
	        public View createTabContent(String tag) {
	            View v = new View(mContext);
	            v.setMinimumWidth(0);
	            v.setMinimumHeight(0);
	            return v;
	        }
	 
	    }
	 private static View createTabView(final Context context,final int tabLayoutResouceId, final String text) {
		 	View view=null;
		 	view = LayoutInflater.from(context).inflate(tabLayoutResouceId, null);
		 	TextView t=(TextView) view.findViewById(R.id.tab_text);
		 	t.setText(text);
		    return view;
		}
	  public  static void AddTab(Activity mainActivity, TabHost tabHost, TabHost.TabSpec tabSpec, TabInfo tabInfo) {
		  	TabFactory t=new TabFactory(mainActivity);
		  	View tabview = createTabView(tabHost.getContext(),tabInfo.getTabLayoutResouceID(), tabInfo.getTag());
		    TabSpec setContent = tabHost.newTabSpec(tabInfo.getTag()).setIndicator(tabview).setContent(t);
		    tabHost.addTab(setContent);
	    }
}
