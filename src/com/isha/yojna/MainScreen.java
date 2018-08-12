package com.isha.yojna;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainScreen extends APPActivity {
	DrawerLayout drawer;
	LinearLayout left_slider;
	static ListView right_slider;
	ActionBarDrawerToggle drawer_toggle;
	String nameis, titleis;

	@SuppressWarnings("deprecation")
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
		setContentView(R.layout.menu);

		if (android.os.Build.VERSION.SDK_INT > 9) {
			StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
					.permitAll().build();
			StrictMode.setThreadPolicy(policy);
		}

		titleis = nameis = (String) getTitle();
		drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
		left_slider = (LinearLayout) findViewById(R.id.left_drawer);
		drawer.setDrawerShadow(R.drawable.abc_tab_selected_holo,
				GravityCompat.START);

		right_slider = (ListView) findViewById(R.id.right_drawer);

		TextView name = (TextView) findViewById(R.id.name);
		TextView phoneno = (TextView) findViewById(R.id.phoneno);
		TextView signout = (TextView) findViewById(R.id.signout);

		SharedPreferences prfns = getSharedPreferences("userregisterdata",
				MODE_WORLD_WRITEABLE);
		final Editor ed = prfns.edit();

		name.setText("+" + prfns.getString("firstname", "") + "  "
				+ prfns.getString("lastname", ""));
		phoneno.setText(prfns.getString("countrycode", "") + "  "
				+ prfns.getString("mobilenumber", ""));

		signout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				ed.putBoolean("loginstatus", false);
				ed.commit();
				Toast.makeText(getApplicationContext(), "SIGNOUT Successfully",
						Toast.LENGTH_SHORT).show();
			}
		});

		drawer_toggle = new ActionBarDrawerToggle(this, drawer,
				R.drawable.icon_menu, R.string.app_name, R.string.app_name) {
			@SuppressLint("NewApi")
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(titleis);
				invalidateOptionsMenu();
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(nameis);
				switch (drawerView.getId()) {
				case R.id.left_drawer:
					boolean drawerOpen = drawer.isDrawerOpen(right_slider);
					if (drawerOpen) {
						drawer.closeDrawer(right_slider);
					}
					break;
				case R.id.right_drawer:
					boolean drawerOpenl = drawer.isDrawerOpen(left_slider);
					if (drawerOpenl) {
						drawer.closeDrawer(left_slider);
					}
					break;
				}

				invalidateOptionsMenu();
			}
		};

		drawer.setDrawerListener(drawer_toggle);

		if (savedInstanceState == null) {
			selectItem(0);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.first_screen, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {

		return super.onPrepareOptionsMenu(menu);
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if (drawer_toggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action buttons
		switch (item.getItemId()) {
		case R.id.action_right:
			// create intent to perform web search for this planet
			boolean drawerOpen = drawer.isDrawerOpen(right_slider);
			if (drawerOpen) {
				drawer.closeDrawer(right_slider);
			} else {
				drawer.openDrawer(right_slider);

			}
			drawer.setDrawerListener(drawer_toggle);
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@SuppressLint("NewApi")
	private void selectItem(int position) {
		// update the main content by replacing fragments

		Bundle args;
		Fragment fragment = null;

		fragment = new Menus_Fragment();
		args = new Bundle();
		args.putInt(Menus_Fragment.ARG_PLANET_NUMBER, position);
		fragment.setArguments(args);

		try {

			android.app.FragmentTransaction ft = getFragmentManager()
					.beginTransaction();
			ft.replace(R.id.content_frame, fragment).commit();

			boolean drawerOpen = drawer.isDrawerOpen(right_slider);
			if (drawerOpen) {
				drawer.closeDrawer(right_slider);
			}
			boolean drawerOpenl = drawer.isDrawerOpen(left_slider);
			if (drawerOpenl) {
				drawer.closeDrawer(left_slider);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressLint("NewApi")
	public void setTitle(String title) {
		getActionBar().setTitle(title);
	}

	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		drawer_toggle.syncState();
	}

	@SuppressLint("NewApi")
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		drawer_toggle.onConfigurationChanged(newConfig);
	}
	// @SuppressLint("NewApi")
	// public boolean onKeyDown(int keyCode, KeyEvent event) {
	//
	// if (keyCode == KeyEvent.KEYCODE_BACK) {
	// finishAffinity();
	// overridePendingTransition(R.anim.slide_right_in,
	// R.anim.slide_right_out);
	//
	// return true;
	// }
	//
	// return super.onKeyDown(keyCode, event);
	// }
}
