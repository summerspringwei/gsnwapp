package com.example.network;

import com.example.gsnwapp.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;

public class BascActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
	    .detectDiskReads()
	    .detectDiskWrites()
	    .detectNetwork()   // or 
	    //.detectAll() for all detectable problems
	    .penaltyLog()
	    .build());
	    StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
	            .detectLeakedSqlLiteObjects()
	            .detectLeakedClosableObjects()
	            .penaltyLog()
	            .penaltyDeath()
	            .build()); 
		}
}
