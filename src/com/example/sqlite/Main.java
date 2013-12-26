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

public class Main extends Activity implements OnClickListener {
	Button sqlUpdate, sqlView, sqlModify, sqlGetInfor, sqlDelete;
	EditText sqlName, sqlHotness, sqlRow;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
		sqlView = (Button) findViewById(R.id.bSQLOpenView);
		sqlName = (EditText) findViewById(R.id.etSQLName);
		sqlHotness = (EditText) findViewById(R.id.etSQLHotness);
		sqlRow = (EditText) findViewById(R.id.etSQLRowInfo);
		sqlModify = (Button) findViewById(R.id.bSQLmodify);
		sqlGetInfor = (Button) findViewById(R.id.bgetInfor);
		sqlDelete = (Button) findViewById(R.id.bSQLdelete);

		sqlView.setOnClickListener(this);
		sqlUpdate.setOnClickListener(this);
		sqlModify.setOnClickListener(this);
		sqlGetInfor.setOnClickListener(this);
		sqlDelete.setOnClickListener(this);

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
		switch (v.getId()) {

		case R.id.bSQLUpdate:

			boolean didItWork = true;
			try {
				String name = sqlName.getText().toString();
				String hotness = sqlHotness.getText().toString();

				HotOrNot entry = new HotOrNot(Main.this);
				entry.open();
				entry.createEntry(name, hotness);
				entry.close();
			} catch (Exception e) {

				didItWork = false;
				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Dame it");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();

			} finally {
				if (didItWork) {

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
		case R.id.bgetInfor:
			try {
				String s = sqlRow.getText().toString();
				long l = Long.parseLong(s);
				HotOrNot hon = new HotOrNot(this);
				hon.open();
				String returnedName = hon.getName(l);
				String returnedHotness = hon.getHotness(l);
				hon.close();

				sqlName.setText(returnedName);
				sqlHotness.setText(returnedHotness);
			} catch (Exception e) {

				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Dame it");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();

			}
			break;
		case R.id.bSQLmodify:
			try {
				String sRow = sqlRow.getText().toString();
				String mName = sqlName.getText().toString();
				String mHotness = sqlHotness.getText().toString();
				long lRow = Long.parseLong(sRow);

				HotOrNot ex = new HotOrNot(this);
				ex.open();
				ex.updateEntry(lRow, mName, mHotness);
				ex.close();
			} catch (Exception e) {

				String error = e.toString();
				Dialog d = new Dialog(this);
				d.setTitle("Dame it");
				TextView tv = new TextView(this);
				tv.setText(error);
				d.setContentView(tv);
				d.show();

			}
			break;
		case R.id.bSQLdelete:
			String sRow1 = sqlRow.getText().toString();
			long lRow1 = Long.parseLong(sRow1);
			HotOrNot ex1 = new HotOrNot(this);
			ex1.open();
			ex1.deleteEntry(lRow1);
			ex1.close();
			break;

		}
	}

}
