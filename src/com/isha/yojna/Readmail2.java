package com.isha.yojna;

import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Readmail2 extends Fragment {
	public static final String ARG_PLANET_NUMBER = "planet_number";
	private static final String TAG = "CardListActivity";
	private CardArrayAdapter cardArrayAdapter;
	private ListView listView;

	@SuppressLint("NewApi")
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

		View rv = inflater.inflate(R.layout.listview, container, false);
		
		listView = (ListView) rv.findViewById(R.id.card_listView);

		listView.addHeaderView(new View(getActivity()));
		listView.addFooterView(new View(getActivity()));

		cardArrayAdapter = new CardArrayAdapter(getActivity(),
				R.layout.list_item_card1);

		for (int i = 0; i < 6; i++) {
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
			TextView line1;
			TextView line2;
			TextView line3;
			TextView line0;
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
				row = inflater.inflate(R.layout.list_item_card2, parent, false);
				viewHolder = new CardViewHolder();
				viewHolder.line0 = (TextView) row.findViewById(R.id.line0);
				viewHolder.line1 = (TextView) row.findViewById(R.id.line1);
				viewHolder.line2 = (TextView) row.findViewById(R.id.line2);
				viewHolder.line3 = (TextView) row.findViewById(R.id.line3);
				row.setTag(viewHolder);
			} else {
				viewHolder = (CardViewHolder) row.getTag();
			}
			Card card = getItem(position);
			viewHolder.line0.setText("< EVENT NAME " + position + " > ");
			viewHolder.line1.setText("Thursday 12th March,07:30 PM");
			viewHolder.line2.setText("Location");
			viewHolder.line3.setText("No Description");
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
