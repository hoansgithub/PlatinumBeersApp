package com.ikt.customelements;


import com.ikt.platinumbeers.R;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MyToast extends Toast {

	public MyToast(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public MyToast(Context context,String text) {
		super(context);
		 LayoutInflater inflater = (LayoutInflater) context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
         // Inflate the Layout
         View layout = inflater.inflate(R.layout.custom_toast_layout, null);
         TextView textview = (TextView) layout.findViewById(R.id.textToShow);
         // Set the Text to show in TextView
         textview.setText(text);

         this.setDuration(Toast.LENGTH_SHORT);
         this.setGravity(Gravity.TOP|Gravity.FILL_HORIZONTAL, 0, 0);
         this.setView(layout);
	}
}
