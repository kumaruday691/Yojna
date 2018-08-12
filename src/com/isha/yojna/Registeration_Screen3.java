package com.isha.yojna;

import java.util.ArrayList;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

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
import android.widget.Toast;

public class Registeration_Screen3 extends APPActivity {
	SharedPreferences prfns;
	Editor ed;
	EditText editverifcode;
	Button buttonresend;
	Button buttonfinish;
	TextView otptext;

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
		setContentView(R.layout.register_3);

		findViews();

	}

	private void findViews() {

		editverifcode = (EditText) findViewById(R.id.editverifcode);
		buttonresend = (Button) findViewById(R.id.buttonresend);
		buttonfinish = (Button) findViewById(R.id.buttonfinish);
		otptext = (TextView) findViewById(R.id.otptext);

		prfns = getSharedPreferences("userregisterdata", MODE_WORLD_WRITEABLE);
		ed = prfns.edit();

		otptext.setText("Please enter this otp - " + prfns.getInt("otp", 0)
				+ "(static otp)");

		buttonresend.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				int otp = (int) (Math.random() * 9999) + 1000;
				Log.e("otp : ", otp + "");
				otptext.setText("Please enter this otp - " + otp
						+ "(static otp)");
				ed.putInt("otp", otp);
				ed.commit();

			}
		});

		buttonfinish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				int givenotp = Integer.valueOf(editverifcode.getText()
						.toString().trim());

				if (prfns.getInt("otp", 0) == givenotp) {

					try {
						String insert = insertProfile(
								prfns.getString("firstname", ""),
								prfns.getString("lastname", ""),
								prfns.getString("dob", ""),
								prfns.getString("countrycode", ""),
								prfns.getString("eamilid", ""), "",
								prfns.getString("mobilenumber", ""),
								prfns.getString("nickname", ""));

						Log.e("insert : ", insert);

						if (Integer.valueOf(insert) > 0) {
							startActivity(new Intent(
									Registeration_Screen3.this,
									Registeration_Screen4.class));
							overridePendingTransition(R.anim.slide_left_in,
									R.anim.slide_left_out);

							ed.putBoolean("loginstatus", true);
							ed.commit();

						} else {
							Toast.makeText(getApplicationContext(),
									"Registration Failed", Toast.LENGTH_SHORT)
									.show();
						}

					} catch (Exception e) {
						Toast.makeText(getApplicationContext(),
								"Check Your Internet connection",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					editverifcode.setError("Invalid OTP");
				}

			}
		});
	}

	private String insertProfile(String firstname, String lastname, String dob,
			String country, String emailid, String photo, String mobilenumber,
			String nickname) {
		ArrayList<NameValuePair> np = new ArrayList<NameValuePair>();

		np.add(new BasicNameValuePair("firstname", firstname));
		np.add(new BasicNameValuePair("lastname", lastname));
		np.add(new BasicNameValuePair("dob", dob));
		np.add(new BasicNameValuePair("country", country));
		np.add(new BasicNameValuePair("emailid", emailid));
		np.add(new BasicNameValuePair("photo", photo));
		np.add(new BasicNameValuePair("mobilenumber", mobilenumber));
		np.add(new BasicNameValuePair("nickname", nickname));

		ConnectionClass c = new ConnectionClass(Registeration_Screen3.this);

		return c.sendata(np, "insert_users.php");
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

}
