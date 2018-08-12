package com.isha.yojna;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.isha.yojna.ScalingUtilities.ScalingLogic;

public class LetMeScan_fragment extends Fragment {
	public static final String ARG_PLANET_NUMBER = "planet_number";
	private ProgressDialog dialoguplaod = null;
	private String upLoadServerUri = "http://ishasoftwares.com/demo/quickorder/QUICK_ORDER/UploadToServer.php";
	private String imagepath = null;
	private int serverResponseCode = 0;
	byte[] buf;
	InputStream in;
	OutputStream out;
	boolean isSDPresent;
	File folder, f1, f2;
	AlertDialog.Builder builder;
	private Uri mImageCaptureUri;
	private static final int PICK_FROM_CAMERA = 1;
	Bitmap bitmap = null;
	private static final int PICK_FROM_FILE = 2;
	String path, idimage;
	// ////////////////
	ImageView option1;
	ImageView option2;
	ImageView option3;
	ImageView option4;
	ImageView option5;
	ImageView option6;
	int photostate = 0;

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

		View rv = inflater.inflate(R.layout.letmescan, container, false);

		option1 = (ImageView) rv.findViewById(R.id.option1);
		option2 = (ImageView) rv.findViewById(R.id.option2);
		option3 = (ImageView) rv.findViewById(R.id.option3);
		option4 = (ImageView) rv.findViewById(R.id.option4);
		option5 = (ImageView) rv.findViewById(R.id.option5);
		option6 = (ImageView) rv.findViewById(R.id.option6);

		isSDPresent = android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED);

		if (isSDPresent) {

			folder = new File(Environment.getExternalStorageDirectory()
					+ "/QuickOrder");

			if (!folder.exists()) {
				folder.mkdir();
			}

		}
		captureDialog();

		option1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog dialogid = builder.create();
				dialogid.show();
				photostate = 1;
			}
		});
		option2.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog dialogid = builder.create();
				dialogid.show();
				photostate = 2;
			}
		});
		option3.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				File file = new File(Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/example.pdf");
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(file), "application/pdf");
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent);
			}
		});
		option4.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				Intent intent = new Intent(
						Intent.ACTION_PICK,
						android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
				startActivity(intent);
			}
		});
		option5.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				AlertDialog dialogid = builder.create();
				dialogid.show();
				photostate = 3;
			}
		});
		option6.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				File file = new File(Environment.getExternalStorageDirectory()
						.getAbsolutePath() + "/example.pdf");
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setDataAndType(Uri.fromFile(file), "application/pdf");
				intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
				startActivity(intent);
			}
		});

		return rv;
	}

	private void captureDialog() {

		final String[] items = new String[] { "From Camera", "From SD Card" };
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),
				android.R.layout.simple_list_item_1, items);
		builder = new AlertDialog.Builder(getActivity());
		builder.setTitle("Select Image");
		builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialogb, int item) {
				if (item == 0) {
					Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
					File file = new File(Environment
							.getExternalStorageDirectory(), "image_"
							+ String.valueOf(System.currentTimeMillis())
							+ ".png");
					mImageCaptureUri = Uri.fromFile(file);

					try {
						intent.putExtra(
								android.provider.MediaStore.EXTRA_OUTPUT,
								mImageCaptureUri);
						intent.putExtra("return-data", true);

						startActivityForResult(intent, PICK_FROM_CAMERA);
					} catch (Exception e) {
						e.printStackTrace();
					}
					dialogb.cancel();
				} else {
					Intent intent = new Intent(
							Intent.ACTION_PICK,
							android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

					startActivityForResult(Intent.createChooser(intent,
							"Complete action using"), PICK_FROM_FILE);
				}
			}

		});

	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		Log.e("----resultCode : ", resultCode + "");
		
		if (resultCode != Activity.RESULT_OK)
			return;

		if (requestCode == PICK_FROM_FILE) {
			mImageCaptureUri = data.getData();
			path = getPath(mImageCaptureUri); // from Gallery

			if (path == null)
				path = mImageCaptureUri.getPath(); // from File Manager

			if (path != null)
				bitmap = BitmapFactory.decodeFile(path);
		} else {
			path = mImageCaptureUri.getPath();
			bitmap = BitmapFactory.decodeFile(path);
		}

		Log.e("----path : ", path + "");

		String img = decodeFile(path, 200, 300);

		Log.e("photo state", photostate + "");

		if (photostate == 1) {
			Bundle args;
			Fragment fragment = null;

			fragment = new LetMeScan1();
			args = new Bundle();
			args.putInt(LetMeScan1.ARG_PLANET_NUMBER, 0);
			args.putString("image", img);
			fragment.setArguments(args);

			android.app.FragmentTransaction ft = getFragmentManager()
					.beginTransaction();
			ft.replace(R.id.content_frame, fragment).addToBackStack(null)
					.commit();
		} else if (photostate == 2) {
			Bundle args;
			Fragment fragment = null;

			fragment = new LetMeScan2();
			args = new Bundle();
			args.putInt(LetMeScan2.ARG_PLANET_NUMBER, 0);
			args.putString("image", img);
			fragment.setArguments(args);

			android.app.FragmentTransaction ft = getFragmentManager()
					.beginTransaction();
			ft.replace(R.id.content_frame, fragment).addToBackStack(null)
					.commit();
		} else if (photostate == 3) {
			Bundle args;                             
			Fragment fragment = null;

			fragment = new LetMeScan3();
			args = new Bundle();
			args.putInt(LetMeScan3.ARG_PLANET_NUMBER, 0);
			args.putString("image", img);
			fragment.setArguments(args);

			android.app.FragmentTransaction ft = getFragmentManager()
					.beginTransaction();
			ft.replace(R.id.content_frame, fragment).addToBackStack(null)
					.commit();
		}

	}

	public String getPath(Uri uri) {
		String[] projection = { MediaStore.Images.Media.DATA };
		@SuppressWarnings("deprecation")
		Cursor cursor = getActivity().managedQuery(uri, projection, null, null,
				null);
		int column_index = cursor
				.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
		cursor.moveToFirst();
		return cursor.getString(column_index);
	}

	private String decodeFile(String path, int DESIREDWIDTH, int DESIREDHEIGHT) {
		String strMyImagePath = null;
		Bitmap scaledBitmap = null;

		try {
			// Part 1: Decode image
			Bitmap unscaledBitmap = ScalingUtilities.decodeFile(path,
					DESIREDWIDTH, DESIREDHEIGHT, ScalingLogic.FIT);

			if (!(unscaledBitmap.getWidth() <= DESIREDWIDTH && unscaledBitmap
					.getHeight() <= DESIREDHEIGHT)) {
				// Part 2: Scale image
				scaledBitmap = ScalingUtilities.createScaledBitmap(
						unscaledBitmap, DESIREDWIDTH, DESIREDHEIGHT,
						ScalingLogic.FIT);
			} else {
				unscaledBitmap.recycle();
				return path;
			}

			// Store to tmp file

			f1 = new File(folder, "QO" + System.currentTimeMillis() + ".png");

			strMyImagePath = f1.getAbsolutePath();
			FileOutputStream fos = null;
			try {
				fos = new FileOutputStream(f1);
				scaledBitmap.compress(Bitmap.CompressFormat.PNG, 75, fos);

				fos.flush();
				fos.close();
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (Exception e) {

				e.printStackTrace();
			}

			scaledBitmap.recycle();
		} catch (Throwable e) {
		}

		if (strMyImagePath == null) {
			return path;
		}

		return strMyImagePath;

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