/*
 * Copyright 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.isha.yojna;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Fragment;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;

public class Menus_Fragment extends Fragment {
	public static final String ARG_PLANET_NUMBER = "planet_number";
	int[] menuimages = { R.drawable.menu1, R.drawable.menu2, R.drawable.menu3,
			R.drawable.menu4, R.drawable.menu5, R.drawable.menu6,
			R.drawable.menu7 };

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

		View rv = inflater.inflate(R.layout.menuscreen, container, false);

		ListView listView = (ListView) rv.findViewById(R.id.listView);

		listView.setAdapter(new Adapter());

		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {

				Bundle args;
				Fragment fragment = null;

				if (position == 0) {

					fragment = new TalkToMe_Fragment();
					args = new Bundle();
					args.putInt(TalkToMe_Fragment.ARG_PLANET_NUMBER, position);
					fragment.setArguments(args);

				} else if (position == 1) {

					fragment = new ReadMail_Fragment();
					args = new Bundle();
					args.putInt(ReadMail_Fragment.ARG_PLANET_NUMBER, position);
					fragment.setArguments(args);

				} else if (position == 2) {

					fragment = new LetMeScan_fragment();
					args = new Bundle();
					args.putInt(LetMeScan_fragment.ARG_PLANET_NUMBER, position);
					fragment.setArguments(args);

				} else if (position == 3) {

					fragment = new OurGroups_Fragment();
					args = new Bundle();
					args.putInt(OurGroups_Fragment.ARG_PLANET_NUMBER, position);
					fragment.setArguments(args);

				} else if (position == 4) {

					fragment = new Readmail2();
					args = new Bundle();
					args.putInt(Readmail2.ARG_PLANET_NUMBER, position);
					fragment.setArguments(args);

				} else if (position == 5) {

					fragment = new Surpriseme_Fragment();
					args = new Bundle();
					args.putInt(Surpriseme_Fragment.ARG_PLANET_NUMBER, position);
					fragment.setArguments(args);

				} else if (position == 6) {

					fragment = new Settings_Fragment();
					args = new Bundle();
					args.putInt(Settings_Fragment.ARG_PLANET_NUMBER, position);
					fragment.setArguments(args);

				}

				android.app.FragmentTransaction ft = getFragmentManager()
						.beginTransaction();
				ft.replace(R.id.content_frame, fragment).addToBackStack(null)
						.commit();

			}

		});

		return rv;
	}

	class Adapter extends BaseAdapter {

		public int getCount() {

			return menuimages.length;
		}

		public Object getItem(int position) {

			return null;
		}

		public long getItemId(int position) {

			return 0;
		}

		@SuppressLint("InflateParams")
		public View getView(final int position, View v, ViewGroup parent) {

			if (v == null) {

				v = getActivity().getLayoutInflater().inflate(
						R.layout.menu_adapter, null);

			}
			ImageView image = (ImageView) v.findViewById(R.id.image);
			image.setImageResource(menuimages[position]);

			return v;
		}
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