package com.isha.yojna;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;

public class ConnectionClass {

	Context context;
	String retrive_data = "";
	SharedPreferences prfns;
	Editor ed;

	public ConnectionClass(Context context) {
		this.context = context;
		prfns = context.getSharedPreferences("login",
				context.MODE_WORLD_WRITEABLE);
		ed = prfns.edit();
	}

	public String sendata(ArrayList<NameValuePair> data, String urlpath) {

		try {
			retrive_data = new ProgressDetails(data, urlpath).execute().get();
		} catch (InterruptedException e) {

			e.printStackTrace();
		} catch (ExecutionException e) {

			e.printStackTrace();
		}

		Log.e("retrive_data", "" + retrive_data);

		return retrive_data;

	}

	class ProgressDetails extends AsyncTask<String, String, String> {

		ArrayList<NameValuePair> data;
		String urlpath;
		String errormessege = "no error";

		public ProgressDetails(ArrayList<NameValuePair> data, String urlpath) {
			super();

			this.data = data;
			this.urlpath = urlpath;
		}

		protected void onPreExecute() {

			super.onPreExecute();
			retrive_data = "";
		}

		protected String doInBackground(String... params) {

			// //////////////////////////

			String Url = urlpath;
			ArrayList<NameValuePair> jsonparam = data;
			String result = "{no_of_results:0,result:[],status:success}";
			System.out.println(Url);
			System.out.println(jsonparam);

			SchemeRegistry schemeRegistry = new SchemeRegistry();
			schemeRegistry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			schemeRegistry.register(new Scheme("https", SSLSocketFactory
					.getSocketFactory(), 443));
			schemeRegistry.register(new Scheme("rtsp", PlainSocketFactory
					.getSocketFactory(), 543));

			HttpParams httpParams = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParams, 10000);
			HttpConnectionParams.setSoTimeout(httpParams, 30000);

			ClientConnectionManager ccr = new ThreadSafeClientConnManager(
					httpParams, schemeRegistry);
			DefaultHttpClient httpClient = new DefaultHttpClient(ccr,
					httpParams);

			HttpPost httppost = new HttpPost("http://www.ishasoftwares.com/demo/yojana/"
					+ Url);

			try {
				httppost.setEntity(new UrlEncodedFormEntity(jsonparam));
			} catch (UnsupportedEncodingException e) {
				errormessege = "Cannot parse parameters";

				e.printStackTrace();
				return null;
			}
			HttpResponse httpResponse = null;
			Long connectionStrtTime = System.nanoTime();

			try {
				httpResponse = httpClient.execute(httppost);
				System.out.println("raw response" + httpResponse);
			} catch (ClientProtocolException e) {
				errormessege = ErrorHandler.SERVER_NOT_RESPONDING;

				Log.v("HTTP ERROR", ErrorHandler.CLIENTPROTOCOL_EXCP);
				e.printStackTrace();
				return null;
			} catch (NoHttpResponseException e) {
				errormessege = ErrorHandler.SERVER_NOT_RESPONDING;

				Log.v("HTTP ERROR", ErrorHandler.NOHTTPRESPONSE_EXCP);
				e.printStackTrace();
				return null;
			} catch (ConnectTimeoutException e) {
				errormessege = ErrorHandler.SERVER_NOT_RESPONDING;

				Log.v("HTTP ERROR", ErrorHandler.CONNECTTIMEOUT_EXCP);
				e.printStackTrace();
				return null;
			} catch (SocketTimeoutException e) {
				errormessege = ErrorHandler.SERVER_NOT_RESPONDING;

				Log.v("HTTP ERROR", ErrorHandler.SOCKETTIMEOUT_EXCP);
				e.printStackTrace();
				return null;
			} catch (IOException e) {
				errormessege = ErrorHandler.SERVER_NOT_RESPONDING;

				Log.v("HTTP ERROR", ErrorHandler.IOEXCEPTION);
				e.printStackTrace();
				return null;
			}
			Long connectionEndTime = System.nanoTime();

			System.out.println((connectionEndTime - connectionStrtTime)
					* (1000.0 * 1000 * 1000));

			int responsecode = httpResponse.getStatusLine().getStatusCode();
			if (responsecode != 201 && responsecode != 200) {
				if (responsecode == 412) {
					errormessege = ErrorHandler.WH_412;

				} else if (responsecode == 500) {
					errormessege = ErrorHandler.WH_500;

				} else {
					errormessege = String.format(
							ErrorHandler.SERVER_NOT_AVALIABLE, responsecode);

				}
				result = "{no_of_results:0,result:[],status:success}";

			} else {
				HttpEntity entity = httpResponse.getEntity();
				InputStream reader = null;
				try {
					reader = entity.getContent();
				} catch (IllegalStateException e) {
					errormessege = ErrorHandler.SERVER_NOT_RESPONDING;

					Log.v("HTTP ERROR", ErrorHandler.ILLEGALSTATEEXCEPTION);
					e.printStackTrace();
					return null;
				} catch (IOException e) {
					errormessege = ErrorHandler.SERVER_NOT_RESPONDING;

					Log.v("HTTP ERROR", ErrorHandler.IOEXCEPTION_CANTINITIATE);
					e.printStackTrace();
					return null;
				}

				ByteArrayOutputStream buffer = new ByteArrayOutputStream();
				int nRead;
				byte[] data = new byte[16384];
				long startDownloadTime = System.nanoTime();

				try {
					while ((nRead = reader.read(data, 0, data.length)) != -1)
						buffer.write(data, 0, nRead);

					buffer.flush();

				} catch (IOException e) {
					errormessege = ErrorHandler.SERVER_NOT_RESPONDING;

					Log.v("HTTP ERROR", ErrorHandler.IOEXCEPTION_DOWN);
					e.printStackTrace();
					return null;
				}
				byte[] downloadedData = buffer.toByteArray();
				long endDownloadTime = System.nanoTime();
				System.out.println("Download Time: "
						+ (endDownloadTime - startDownloadTime)
						/ (1000.0 * 1000 * 1000));

				try {
					result = new String(downloadedData, "UTF-8");
				} catch (UnsupportedEncodingException e) {
					errormessege = ErrorHandler.SERVER_NOT_RESPONDING;

					Log.v("HTTP ERROR",
							ErrorHandler.UNSUPPORTEDENCODINGEXCEPTION);
					e.printStackTrace();
					return null;
				}

			}

			return result;

		}

		protected void onPostExecute(String result) {

			super.onPostExecute(result);

		}
	}

}
