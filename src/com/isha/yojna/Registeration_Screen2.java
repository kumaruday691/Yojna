package com.isha.yojna;

import java.util.ArrayList;
import java.util.Random;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Registeration_Screen2 extends APPActivity {
	SharedPreferences prfns;
	Editor ed;
	EditText editcountrycode;
	EditText editmobileno;
	EditText editnickname;
	Button buttonsubmit;

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);

		ActionBar ab = getActionBar();
		ColorDrawable colorDrawable = new ColorDrawable(
				Color.parseColor("#539DC2"));
		ab.setBackgroundDrawable(colorDrawable);
		ab.setIcon(android.R.color.transparent);
		setContentView(R.layout.register_2);

		prfns = getSharedPreferences("userregisterdata", MODE_WORLD_WRITEABLE);
		ed = prfns.edit();

		findViews();

	}

	private void findViews() {

		editcountrycode = (EditText) findViewById(R.id.editcountrycode);
		editmobileno = (EditText) findViewById(R.id.editmobileno);
		editnickname = (EditText) findViewById(R.id.editnickname);
		buttonsubmit = (Button) findViewById(R.id.buttonsubmit);

		editcountrycode.setText("+" + prfns.getString("countrycode", ""));

		buttonsubmit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (editcountrycode.getText().toString().trim().length() == 0) {
					editcountrycode.setError("Please Enter Country Code");
				} else if (editmobileno.getText().toString().trim().length() == 0) {
					editmobileno.setError("Please Enter Mobile Number");
				} else if (editnickname.getText().toString().trim().length() == 0) {
					editnickname.setError("Please Enter Nick Name");
				} else {

					int otp = (int) (Math.random() * 9999) + 1000;
					Log.e("otp : ", otp + "");

					ed.putString("mobilenumber", editmobileno.getText()
							.toString().trim());
					ed.putString("nickname", editnickname.getText().toString()
							.trim());
					ed.putInt("otp", otp);
					ed.commit();

					startActivity(new Intent(Registeration_Screen2.this,
							Registeration_Screen3.class));
					overridePendingTransition(R.anim.slide_left_in,
							R.anim.slide_left_out);

				}
			}
		});
	}

	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK) {
			finish();
			overridePendingTransition(R.anim.slide_right_in,
					R.anim.slide_right_out);

			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

	private int generateRandomNumber() {
		int rnd = 0;
		Random rand = new Random();
		int[] randNo = new int[8];
		ArrayList numbers = new ArrayList();
		for (int k = 0; k < 8; k++) {
			rnd = rand.nextInt(8) + 1;
			if (k == 0) {
				randNo[0] = rnd;
				numbers.add(randNo[0]);
			} else {
				while (numbers.contains(new Integer(rnd))) {
					rnd = rand.nextInt(8) + 1;
				}
				randNo[k] = rnd;
				numbers.add(randNo[k]);
			}
		}
		return rnd;
	}
}
