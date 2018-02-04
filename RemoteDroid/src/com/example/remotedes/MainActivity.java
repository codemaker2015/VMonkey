package com.example.remotedes;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button phone,computer;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		phone=(Button)findViewById(R.id.phtoc);
		computer=(Button)findViewById(R.id.ctoph);
		
		phone.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i;
				PackageManager manager = getPackageManager();
				try {
				    i = manager.getLaunchIntentForPackage("android.androidVNC");
				    if (i == null)
				        throw new PackageManager.NameNotFoundException();
				    i.addCategory(Intent.CATEGORY_LAUNCHER);
				    startActivity(i);
				} catch (PackageManager.NameNotFoundException e) {
					e.printStackTrace();
				}

				
			}
		});
		computer.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i;
				PackageManager manager = getPackageManager();
				try {
				    i = manager.getLaunchIntentForPackage("org.onaips.vnc");
				    if (i == null)
				        throw new PackageManager.NameNotFoundException();
				    i.addCategory(Intent.CATEGORY_LAUNCHER);
				    startActivity(i);
				} catch (PackageManager.NameNotFoundException e) {
					e.printStackTrace();

				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
