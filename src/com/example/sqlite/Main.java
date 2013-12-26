package com.example.sqlite;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Main extends Activity implements OnClickListener{
	Button sqlUpdate, sqlView;
	EditText sqlName, sqlHotness;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		sqlUpdate =(Button)findViewById(R.id.bSQLUpdate);
		sqlView =(Button)findViewById(R.id.bSQLOpenView);
		sqlName =(EditText)findViewById(R.id.etSQLName);
		sqlHotness=(EditText)findViewById(R.id.etSQLHotness);
		
		sqlView.setOnClickListener(this);
		sqlUpdate.setOnClickListener(this);
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case R.id.bSQLUpdate:
			
			boolean didItWork =true;
			try{
			String name = sqlName.getText().toString();
			String hotness = sqlHotness.getText().toString();
			
			HotOrNot entry = new HotOrNot(Main.this);
			entry.open();
			entry.createEntry(name,hotness);
			entry.close();
			}catch(Exception e){
				
				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Dame it");
				TextView tv = new TextView(this);
				tv.setText("Error");
				d.setContentView(tv);
				d.show();
				
				
			}finally{
				if (didItWork){
					
					Dialog d = new Dialog(this);
					d.setTitle("Keck Yea!");
					TextView tv = new TextView(this);
					tv.setText("Success");
					d.setContentView(tv);
					d.show();
				}
				
			}
			break;
		case R.id.bSQLOpenView:
			Intent i = new Intent("android.intent.action.SQLVIEW");
			startActivity(i);
			break;
		}
	}

}
