package com.isha.yojna;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

public class NotifySMSReceived extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_screen);
		displayAlert();
	}

	@SuppressLint("NewApi")
	private void displayAlert() {
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				NotifySMSReceived.this);
		alertDialogBuilder
				.setMessage("No Network Connection!")
				.setCancelable(false)
				.setPositiveButton("Retry",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								Intent intn = new Intent(
										NotifySMSReceived.this,
										Splash_Screen.class);
								intn.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
								NotifySMSReceived.this.startActivity(intn);

							}
						})
				.setNegativeButton("Exit",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();

								finish();

							}
						});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}
}
