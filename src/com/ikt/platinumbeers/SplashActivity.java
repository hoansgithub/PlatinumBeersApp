package com.ikt.platinumbeers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import com.ikt.customelements.MyToast;
import com.ikt.platinumbeers.model.DatabaseHandler;
import com.ikt.platinumbeers.model.Prize;
import com.ikt.platinumbeers.model.Record;
import com.ikt.platinumbeers.model.User;
import com.ikt.platinumbeers.model.Venue;
import com.ikt.utilities.WebServiceController;
import com.ikt.utilities.WebServiceProperties;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class SplashActivity extends ActionBarActivity {
	 // Splash screen timer
    
    private int duration;
	private List<Venue> venueList;
	private List<Prize> prizeList;
	private String service_host;
	private DatabaseHandler  dbhandler; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        WebServiceController svc=new WebServiceController();
        this.duration= Toast.LENGTH_SHORT;
        this.venueList=new ArrayList<Venue>();
        this.prizeList=new ArrayList<Prize>();
        this.service_host=((PlatinumBeersApplication)this.getApplication()).getSERVICE_HOST();
        this.dbhandler=new DatabaseHandler(this);
        try {
			//records
        	
        	
        	//get curr user
        	
        	User us=dbhandler.getUser();
        	if(us!=null)
        	{
        		((PlatinumBeersApplication)this.getApplication()).setMANAGER(us.isAdmin());
            	((PlatinumBeersApplication)this.getApplication()).setVENUE_ID(us.getVenue_id());
            	((PlatinumBeersApplication)this.getApplication()).setPGID(us.getUser_id());
            	((PlatinumBeersApplication)this.getApplication()).setLOGGED_IN(true);
            	
        	}
        	 WebServiceController svc2=new WebServiceController();
        	//--------PRIZES
        	 Map<String, Object> pizeparams = new HashMap<String, Object>();   
        	 pizeparams.put("param", 0);
        	 String resprizes= svc2.execute(new WebServiceProperties(this.service_host+"/get-prizes", pizeparams)).get();
        	 if (null != resprizes) {
        		 dbhandler.deleteAllPrize();
 	            JSONArray result = new JSONArray(resprizes);
 	            for(int i=0;i<result.length();i++)
 	            {
 	            	
 					JSONObject o=(JSONObject) result.get(i);
 					String desc = o.getString("desc");
 					int prizeid=o.getInt("prize_id");
 					int prizeval=o.getInt("point");
 					this.prizeList.add(new Prize(prizeid,desc,prizeval));
 					dbhandler.addPrize(new Prize(prizeid,desc,prizeval));
 					//list.add(venuename);
 					((PlatinumBeersApplication)this.getApplication()).setPRIZES(prizeList);
 	            }
        	 }
        	 else{
        		 prizeList=dbhandler.getAllPrizes();
 				((PlatinumBeersApplication)this.getApplication()).setPRIZES(prizeList);
        	 }
        	//--------VENUES
        	 WebServiceController svc3=new WebServiceController();
			  Map<String, Object> params = new HashMap<String, Object>();   
			  params.put("param", 0);
			 
			 
			String res= svc3.execute(new WebServiceProperties(this.service_host+"/get-venues", params)).get();
			if (null != res) {
				dbhandler.deleteAllVenue();
	            JSONArray result = new JSONArray(res);
	            for(int i=0;i<result.length();i++)
	            {
	            	
					JSONObject o=(JSONObject) result.get(i);
					String venuename = o.getString("venue_name");
					int venueid=o.getInt("venue_id");
					this.venueList.add(new Venue(venueid,venuename));
					dbhandler.addVenue(new Venue(venueid,venuename));
					//list.add(venuename);
					((PlatinumBeersApplication)this.getApplication()).setVENUES(venueList);
	            }
	            
			}
			else{
				venueList=dbhandler.getAllVenues();
				((PlatinumBeersApplication)this.getApplication()).setVENUES(venueList);
			}
			if(((PlatinumBeersApplication)this.getApplication()).isLOGGED_IN())
			{
				
				Intent i = new Intent(SplashActivity.this, MainActivity.class);
	            startActivity(i);
	           
			}
			else{
				Intent i = new Intent(SplashActivity.this, AuthActivity.class);
	            startActivity(i);
			}
			 finish();
			
		} catch (Exception e) {
			Context context = this;
			CharSequence text = "Connection error";
			venueList=dbhandler.getAllVenues();
			((PlatinumBeersApplication)this.getApplication()).setVENUES(venueList);
			MyToast toast =new MyToast(context, text.toString());
			toast.show();
			 Intent i = new Intent(SplashActivity.this, AuthActivity.class);
             startActivity(i);
             finish();
		}
	}

	
}
