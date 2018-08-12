package com.isha.yojna;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;

public class Screen6 extends APPActivity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		setContentView(R.layout.screen_6);

		findViewById(R.id.buttoncontinue).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						finishAffinity();
						startActivity(new Intent(Screen6.this, MainScreen.class));
						overridePendingTransition(R.anim.slide_left_in,
								R.anim.slide_left_out);
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
