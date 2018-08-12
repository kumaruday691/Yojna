package com.isha.yojna;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.EditText;

public class DatePickerFragment extends DialogFragment implements
		DatePickerDialog.OnDateSetListener {
	static String formattedDate;
	EditText editdate;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		// Use the current date as the default date in the picker
		final Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);

		// Create a new instance of DatePickerDialog and return it
		return new DatePickerDialog(getActivity(), this, year, month, day);
	}

	public DatePickerFragment(EditText editdate) {
		super();
		this.editdate = editdate;
	}

	

	@SuppressLint("SimpleDateFormat")
	public void onDateSet(DatePicker view, int year, int month, int day) {

		Calendar c = Calendar.getInstance();
		c.set(year, month, day);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		formattedDate = sdf.format(c.getTime());
		editdate.setText(formattedDate);
		return;
	}
}