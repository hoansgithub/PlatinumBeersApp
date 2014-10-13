package com.ikt.platinumbeers;

import java.util.ArrayList;
import java.util.List;

import com.ikt.customelements.MyToast;
import com.ikt.platinumbeers.model.Venue;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class AuthActivity extends ActionBarActivity {
	private Button loginbtn;
	private EditText usernametext;
	private EditText passwordtext;
	private Spinner venuespinner;
	private List<Venue> venueList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_auth);
		this.usernametext=(EditText) findViewById(R.id.auth_username);
		this.passwordtext=(EditText) findViewById(R.id.auth_password);
		this.venuespinner=(Spinner) findViewById(R.id.venue_spinner);
		this.loginbtn=(Button) findViewById(R.id.auth_login);
		this.loginbtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				loginClick(v);
				
			}
		});
		
		this.venueList=((PlatinumBeersApplication)this.getApplication()).getVENUES();
		 this.loadVenueSpinner();
	}
	
	private void loadVenueSpinner() {
		
		List<String> list=new ArrayList<String>();
		List<Venue> vn=((PlatinumBeersApplication)this.getApplication()).getVENUES();
		
		for(int v=0;v<vn.size();v++){
			String vname=((PlatinumBeersApplication)this.getApplication()).getVENUES().get(v).getVenue_name();
			list.add(vname);
		}
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,R.drawable.list_selector_apptheme,list );
 
        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
 
        // attaching data adapter to spinner
        this.venuespinner.setAdapter(dataAdapter);
		
	}

	protected void loginClick(View v)
	{
		String username=usernametext.getText().toString().trim();
		String password=passwordtext.getText().toString().trim();
		boolean check=true;
		String msg="";
		if(username.length()==0 || password.length()==0)
		{
			check=false;
			msg+="* fields are required";
		}
		if(check)
		{
			Intent mainintent=new Intent(this,MainActivity.class);
			startActivity(mainintent);
		}
		else{
			MyToast to=new MyToast(this,msg);
			to.show();
		}
		
	}
}
