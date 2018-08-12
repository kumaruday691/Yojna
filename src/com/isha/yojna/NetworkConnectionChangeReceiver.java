package com.isha.yojna;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class NetworkConnectionChangeReceiver extends BroadcastReceiver {
	static int value = 1;

	@SuppressLint("NewApi")
	@Override
	public void onReceive(final Context context, final Intent intent1) {
		String status = NetworkUtil.getConnectivityStatusString(context);

		Log.e("Status : ", status);

		String action = intent1.getAction();

		if (action.equals(Intent.ACTION_POWER_CONNECTED)) {
			// Do something when power connected
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (action.equals(Intent.ACTION_SCREEN_OFF)) {
			// Do something when power disconnected
			// Log.e("invoked receiver", "broad cast receiver invoked");
			try {

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (status.contains("Please")) {
			Log.e("App :  ", status + ":" + APPActivity.appInFront);
			if (APPActivity.appInFront) {
				if (value == 1) {
					Toast.makeText(context, status, Toast.LENGTH_LONG).show();
					value++;

					Intent intent = new Intent(context, NotifySMSReceived.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
							| Intent.FLAG_ACTIVITY_NEW_TASK);
					context.startActivity(intent);

				}
			}
		} else {

		}

	}
}
