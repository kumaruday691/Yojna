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
import java.util.List;

import com.isha.yojna.Readmail1.CardArrayAdapter;
import com.isha.yojna.Readmail1.CardArrayAdapter.CardViewHolder;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class OurGroups_Fragment extends Fragment {
	public static final String ARG_PLANET_NUMBER = "planet_number";
	private static final String TAG = "CardListActivity";
	private CardArrayAdapter cardArrayAdapter;
	private ListView listView;

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

		View rv = inflater.inflate(R.layout.ourgroups, container, false);

		listView = (ListView) rv.findViewById(R.id.card_listView);

		listView.addHeaderView(new View(getActivity()));
		listView.addFooterView(new View(getActivity()));

		cardArrayAdapter = new CardArrayAdapter(getActivity(),
				R.layout.list_item_card1);

		for (int i = 0; i < 10; i++) {
			Card card = new Card("Card " + (i + 1) + " Line 1", "Card "
					+ (i + 1) + " Line 2");
			cardArrayAdapter.add(card);
		}
		listView.setAdapter(cardArrayAdapter);

		return rv;
	}

	public class CardArrayAdapter extends ArrayAdapter<Card> {
		private static final String TAG = "CardArrayAdapter";
		private List<Card> cardList = new ArrayList<Card>();

		public class CardViewHolder {
			TextView line;
		}

		public CardArrayAdapter(Context context, int textViewResourceId) {
			super(context, textViewResourceId);
		}

		@Override
		public void add(Card object) {
			cardList.add(object);
			super.add(object);
		}

		@Override
		public int getCount() {
			return this.cardList.size();
		}

		@Override
		public Card getItem(int index) {
			return this.cardList.get(index);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View row = convertView;
			CardViewHolder viewHolder;
			if (row == null) {
				LayoutInflater inflater = (LayoutInflater) this.getContext()
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				row = inflater.inflate(R.layout.groupadapter, parent, false);
				viewHolder = new CardViewHolder();
				viewHolder.line = (TextView) row.findViewById(R.id.line);
				row.setTag(viewHolder);
			} else {
				viewHolder = (CardViewHolder) row.getTag();
			}
			Card card = getItem(position);
			viewHolder.line.setText("+91 738688867" + position);
			return row;
		}

		public Bitmap decodeToBitmap(byte[] decodedByte) {
			return BitmapFactory.decodeByteArray(decodedByte, 0,
					decodedByte.length);
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