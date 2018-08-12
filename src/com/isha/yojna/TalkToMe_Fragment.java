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

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class TalkToMe_Fragment extends Fragment {
	public static final String ARG_PLANET_NUMBER = "planet_number";
	ListView listView;
	EditText editText;
	Button button;
	DataBase db;
	ArrayList<MessageDetails> al;

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

		View rv = inflater.inflate(R.layout.talktome, container, false);

		listView = (ListView) rv.findViewById(R.id.listView);
		editText = (EditText) rv.findViewById(R.id.editText);
		button = (Button) rv.findViewById(R.id.button);

		db = new DataBase(getActivity());
		db.open();

		getData();

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (editText.getText().toString().length() == 0) {

				} else {

					db.insertMessgaesData(editText.getText().toString(), 0, 0,
							"0");
					db.insertMessgaesData("Result Display Here", 1, 1, "1");
					editText.setText("");
					getData();

				}

			}
		});

		return rv;
	}

	private void getData() {

		al = new ArrayList<MessageDetails>();

		Cursor c = db.getMessages();

		try {

			if (c != null) {
				if (c.moveToFirst()) {
					do {
						al.add(new MessageDetails(c.getString(c
								.getColumnIndex("message")), c.getString(c
								.getColumnIndex("time")), c.getInt(c
								.getColumnIndex("messagetype")), c.getInt(c
								.getColumnIndex("sendertype"))));
						Log.e("messgae : ",
								c.getString(c.getColumnIndex("message")));
						Log.e("sendertype : ",
								c.getString(c.getColumnIndex("sendertype")));
					} while (c.moveToNext());

				}
			}

			listView.setAdapter(new Adapter(al));

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	class Adapter extends BaseAdapter {
		ArrayList<MessageDetails> al;

		public Adapter(ArrayList<MessageDetails> al) {
			super();
			this.al = al;
		}

		public int getCount() {

			return al.size();
		}

		public Object getItem(int position) {

			return null;
		}

		public long getItemId(int position) {

			return 0;
		}

		@SuppressLint("InflateParams")
		public View getView(final int position, View view, ViewGroup parent) {
			View v = view;
			if (view == null) {

				LayoutInflater inflater = (LayoutInflater) getActivity()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

				Log.e("111", "" + al.get(position).getSendertype());

				if (al.get(position).getSendertype() == 0) {
					v = inflater.inflate(R.layout.messageadapter1, parent,
							false);
				} else if (al.get(position).getSendertype() == 1) {
					v = inflater.inflate(R.layout.messageadapter2, parent,
							false);
				}

			}

			TextView textView = (TextView) v.findViewById(R.id.textView);
			textView.setText("" + al.get(position).getMessage());

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