package com.isha.yojna;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.telephony.gsm.SmsMessage;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Registeration_Screen1 extends APPActivity {
	ArrayList<String> countrynames, countrycodes;
	EditText editfn;
	EditText editln;
	EditText editdate;
	Spinner spinnercountries;
	EditText editemail;
	Button buttoncontinue, buttondate;
	SharedPreferences prfns;
	Editor ed;
	String countycode = "";

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
		setContentView(R.layout.register_1);

		ArrayList<String> sms = new GetSMSData(this).fetchInboxSMS();

		Log.e("sms : ", "" + sms.size());
		Log.e("sms : ", "" + sms.get(0));

		countrynames = new ArrayList<String>();
		countrycodes = new ArrayList<String>();

		countrynames.add("Afghanistan");
		countrycodes.add("93");
		countrynames.add("Argentina");
		countrycodes.add("54");
		countrynames.add("Australia");
		countrycodes.add("61");
		countrynames.add("Austria");
		countrycodes.add("43");
		countrynames.add("Bangladesh");
		countrycodes.add("880");
		countrynames.add("Belgium");
		countrycodes.add("32");
		countrynames.add("Bermuda");
		countrycodes.add("1-441");
		countrynames.add("Brazil");
		countrycodes.add("55");
		countrynames.add("Canada");
		countrycodes.add("1");
		countrynames.add("Chile");
		countrycodes.add("56");
		countrynames.add("China");
		countrycodes.add("86");
		countrynames.add("Colombia");
		countrycodes.add("57");
		countrynames.add("Cuba");
		countrycodes.add("53");
		countrynames.add("Denmark");
		countrycodes.add("45");
		countrynames.add("Egypt");
		countrycodes.add("20");
		countrynames.add("France");
		countrycodes.add("33");
		countrynames.add("Germany");
		countrycodes.add("49");
		countrynames.add("Great Britain");
		countrycodes.add("44");
		countrynames.add("Greece");
		countrycodes.add("30");
		countrynames.add("Greenland");
		countrycodes.add("299");
		countrynames.add("Hong Kong");
		countrycodes.add("852");
		countrynames.add("Hungary");
		countrycodes.add("36");
		countrynames.add("Iceland");
		countrycodes.add("354");
		countrynames.add("India");
		countrycodes.add("91");
		countrynames.add("Indonesia");
		countrycodes.add("62");
		countrynames.add("Iran");
		countrycodes.add("98");
		countrynames.add("Iraq");
		countrycodes.add("964");
		countrynames.add("Ireland");
		countrycodes.add("353");
		countrynames.add("Israel");
		countrycodes.add("972");
		countrynames.add("Italy");
		countrycodes.add("39");
		countrynames.add("Japan");
		countrycodes.add("81");
		countrynames.add("Kazakhstan");
		countrycodes.add("7");
		countrynames.add("Kenya");
		countrycodes.add("254");
		countrynames.add("Korea-North");
		countrycodes.add("850");
		countrynames.add("Korea-South");
		countrycodes.add("82");
		countrynames.add("Kuwait");
		countrycodes.add("965");
		countrynames.add("Malaysia");
		countrycodes.add("60");
		countrynames.add("Maldives");
		countrycodes.add("960");
		countrynames.add("Mexico");
		countrycodes.add("52");
		countrynames.add("Myanmar");
		countrycodes.add("95");
		countrynames.add("Namibia");
		countrycodes.add("264");
		countrynames.add("Nepal");
		countrycodes.add("977");
		countrynames.add("Netherlands");
		countrycodes.add("31");
		countrynames.add("New Zealand");
		countrycodes.add("64");
		countrynames.add("Pakistan");
		countrycodes.add("92");
		countrynames.add("Peru");
		countrycodes.add("51");
		countrynames.add("Philippines");
		countrycodes.add("63");
		countrynames.add("Poland");
		countrycodes.add("48");
		countrynames.add("Russia");
		countrycodes.add("7");
		countrynames.add("Singapore");
		countrycodes.add("65");
		countrynames.add("South Africa");
		countrycodes.add("27");
		countrynames.add("Spain");
		countrycodes.add("34");
		countrynames.add("Sri Lanka");
		countrycodes.add("94");
		countrynames.add("Sudan");
		countrycodes.add("249");
		countrynames.add("Sweden");
		countrycodes.add("46");
		countrynames.add("Switzerland");
		countrycodes.add("41");
		countrynames.add("Turkey");
		countrycodes.add("90");
		countrynames.add("U.K");
		countrycodes.add("44");
		countrynames.add("Ukraine");
		countrycodes.add("380");
		countrynames.add("USA");
		countrycodes.add("1");
		countrynames.add("Vatican");
		countrycodes.add("39");
		countrynames.add("Venezuela");
		countrycodes.add("58");
		countrynames.add("Zambia");
		countrycodes.add("260");
		countrynames.add("Zimbabwe");
		countrycodes.add("263");

		findViews();

	}

	private void findViews() {

		editfn = (EditText) findViewById(R.id.editfn);
		editln = (EditText) findViewById(R.id.editln);
		editdate = (EditText) findViewById(R.id.editdate);
		spinnercountries = (Spinner) findViewById(R.id.spinnercountries);
		editemail = (EditText) findViewById(R.id.editemail);
		buttoncontinue = (Button) findViewById(R.id.buttoncontinue);
		buttondate = (Button) findViewById(R.id.buttondate);

		spinnercountries
				.setAdapter(new ArrayAdapter<String>(
						Registeration_Screen1.this, R.layout.spinnertext,
						countrynames));

		spinnercountries
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> parent,
							View view, int position, long id) {
						countycode = countrycodes.get(position);
					}

					@Override
					public void onNothingSelected(AdapterView<?> parent) {
						countycode = "";

					}
				});

		prfns = getSharedPreferences("userregisterdata", MODE_WORLD_WRITEABLE);
		ed = prfns.edit();

		buttondate.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				DialogFragment picker = new DatePickerFragment(editdate);
				picker.show(getFragmentManager(), "datePicker");
			}
		});

		buttoncontinue.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (editfn.getText().toString().trim().length() == 0) {
					editfn.setError("Please Enter First Name");
				} else if (editln.getText().toString().trim().length() == 0) {
					editln.setError("Please Enter Last Name");
				} else if (editdate.getText().toString().trim().length() == 0) {
					editdate.setError("Please Enter DOB");
				} else if (countycode.trim().length() == 0) {
					Toast.makeText(getApplicationContext(),
							"Please Select Country", Toast.LENGTH_SHORT).show();
				} else if (editemail.getText().toString().trim().length() == 0) {
					editemail.setError("Please Enter Email ID");
				} else {

					ed.putString("firstname", editfn.getText().toString()
							.trim());
					ed.putString("lastname", editln.getText().toString().trim());
					ed.putString("countrycode", countycode);
					ed.putString("dob", editdate.getText().toString().trim());
					ed.putString("eamilid", editemail.getText().toString()
							.trim());
					ed.commit();

					startActivity(new Intent(Registeration_Screen1.this,
							Registeration_Screen2.class));
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
}
