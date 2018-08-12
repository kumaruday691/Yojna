package com.isha.yojna;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

public class Splash_Screen extends Activity implements AnimationListener {

	protected int _splashTime = 1500;
	final Splash_Screen Splash_Screen = this;
	private Thread splashTread;
	LinearLayout img;
	Animation animFadein, animFadeout;

	@SuppressLint("NewApi")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		getActionBar().hide();
		setContentView(R.layout.splash_screen);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		splashTread = new Thread() {

			public void run() {
				try {
					synchronized (this) {
						wait(_splashTime);
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					finish();

					SharedPreferences prfns = getSharedPreferences(
							"userregisterdata", MODE_WORLD_WRITEABLE);

					if (prfns.getBoolean("loginstatus", false) == true) {
						startActivity(new Intent(Splash_Screen.this,
								MainScreen.class));
					} else {
						startActivity(new Intent(Splash_Screen.this,
								Registeration_Screen1.class));
					}

				}
			}
		};
		splashTread.start();
	}

	public void onAnimationEnd(Animation arg0) {

	}

	public void onAnimationRepeat(Animation arg0) {

	}

	public void onAnimationStart(Animation arg0) {

	}

}