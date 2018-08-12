package com.isha.yojna;

import android.app.Activity;

public class APPActivity extends Activity {

	public static boolean appInFront;

	@Override
	public void onResume() {
		super.onStart();
		appInFront = true;

	}

	@Override
	public void onPause() {
		super.onPause();
		appInFront = false;
	}
}