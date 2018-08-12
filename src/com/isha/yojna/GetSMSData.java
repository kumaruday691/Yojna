package com.isha.yojna;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

public class GetSMSData {

	Context context;

	public GetSMSData(Context context) {
		super();
		this.context = context;
	}

	public ArrayList<String> fetchInboxSMS() {

		ArrayList<String> sms = new ArrayList<String>();

		Uri uriSms = Uri.parse("content://sms/inbox");
		Cursor cursor = context.getContentResolver().query(uriSms,
				new String[] { "address", "body" }, null, null, null);

		if (cursor != null) {

			if (cursor.moveToFirst()) {

				do {
					String address = cursor.getString(0);
					String body = cursor.getString(1);

					sms.add("Address : " + address + "\n SMS : " + body);

				} while (cursor.moveToNext());

			}

		}

		return sms;

	}

}
