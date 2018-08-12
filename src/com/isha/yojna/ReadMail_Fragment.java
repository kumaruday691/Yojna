package com.isha.yojna;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class ReadMail_Fragment extends Fragment {
	public static final String ARG_PLANET_NUMBER = "planet_number";
	ImageView scanforpackage;
	ImageView scanforevents;

	@SuppressLint("NewApi")
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		ActionBar ab = getActivity().getActionBar();
		ColorDrawable colorDrawable = new ColorDrawable(
				Color.parseColor("#539DC2"));
		ab.setBackgroundDrawable(colorDrawable);
		ab.setDisplayShowTitleEnabled(false);
		ab.setDisplayShowHomeEnabled(true);
		ab.getDisplayOptions();
		ab.setDisplayHomeAsUpEnabled(true);
		ab.setHomeButtonEnabled(true);

		View rv = inflater.inflate(R.layout.readmail, container, false);

		scanforpackage = (ImageView) rv.findViewById(R.id.scanforpackage);
		scanforevents = (ImageView) rv.findViewById(R.id.scanforevents);

		scanforpackage.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Bundle args;
				Fragment fragment = null;

				fragment = new Readmail1();
				args = new Bundle();
				args.putInt(Readmail1.ARG_PLANET_NUMBER, 0);
				fragment.setArguments(args);

				android.app.FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.content_frame, fragment).addToBackStack(null)
						.commit();

			}
		});
		scanforevents.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// startActivity(new Intent(getActivity(), Readmail2.class));
				Bundle args;
				Fragment fragment = null;

				fragment = new Readmail2();
				args = new Bundle();
				args.putInt(Readmail2.ARG_PLANET_NUMBER, 0);
				fragment.setArguments(args);

				android.app.FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.content_frame, fragment).addToBackStack(null)
						.commit();
			}
		});

		return rv;
	}

	@Override
	public void onResume() {
		super.onResume();

		APPActivity.appInFront = true;

	}

	@Override
	public void onPause() {
		super.onPause();
		APPActivity.appInFront = false;
	}
}