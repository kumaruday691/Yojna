package com.isha.yojna;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class LetMeScan3 extends Fragment {
	public static final String ARG_PLANET_NUMBER = "planet_number";

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

		View rv = inflater.inflate(R.layout.letmescan3, container, false);

		ImageView imgview = (ImageView) rv.findViewById(R.id.image);

		Bundle bundle = this.getArguments();
		String imagepath = bundle.getString("image", "");
		imgview.setImageURI(Uri.parse(imagepath));

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
