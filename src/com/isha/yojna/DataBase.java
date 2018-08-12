package com.isha.yojna;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase extends SQLiteOpenHelper {

	SQLiteDatabase db;
	private static final String DATABASE_NAME = "YOJANA_DB";
	private static final String TABLE_MESSAGES = "messages";
	private static final String KEY_MESSAGE = "message";
	private static final String KEY_USERID = "userid";
	private static final String KEY_MESSAGETYPE = "messagetype";
	private static final String KEY_SENDERTYPE = "sendertype";
	private static final String KEY_TIME = "time";

	private static final String CREATE_ITEMS_TABLE = "create table if not exists messages (userid text,messagetype int,sendertype int,message text,time text)";
	private static final int VERSION = 1;

	public DataBase(Context context) {
		super(context, DATABASE_NAME, null, VERSION);

	}

	public void open() {
		db = getWritableDatabase();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(CREATE_ITEMS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

	public void insertMessgaesData(String message, int messagetype,
			int sendertype, String userid) {

		ContentValues con = new ContentValues();
		con.put(KEY_MESSAGE, message);
		con.put(KEY_MESSAGETYPE, messagetype);
		con.put(KEY_SENDERTYPE, sendertype);
		con.put(KEY_USERID, userid);
		con.put(KEY_TIME,
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));

		db.insert(TABLE_MESSAGES, null, con);

	}

	public Cursor getMessages() {

		return db.rawQuery("select * from messages", null);

	}

	// private static final String USER_REGIST_TABLE = "RegistarionTable";
	//
	// private static final String USER_NAME = "name";
	// private static final String USER_MAILID = "mailid";
	// private static final String USER_PHONENO = "phoneno";
	// private static final String USER_PASSWORD = "password";
	//
	// private static final String CREATE_USER_REGIST_TABLE =
	// "create table if not exists RegistarionTable(name text,"
	// + "mailid text primary key,phoneno text,password text)";
	//
	// public void insertRegistarionData(String name, String mailid,
	// String phoneno, String password) {
	//
	// ContentValues con = new ContentValues();
	// con.put(USER_NAME, name);
	// con.put(USER_MAILID, mailid);
	// con.put(USER_PHONENO, phoneno);
	// con.put(USER_PASSWORD, password);
	//
	// db.insert(USER_REGIST_TABLE, null, con);
	//
	// }
	//
	// public Cursor getlogintype(String userid, String password) {
	// // TODO Auto-generated method stub
	// return db.rawQuery("select * from RegistarionTable where "
	// + USER_MAILID + " = '" + userid + "' and " + USER_PASSWORD
	// + " = '" + password + "'", null);
	// }
	//
	// public Cursor getNumber(String mailid) {
	// return db.rawQuery("select * from RegistarionTable where mailid" + "='"
	// + mailid + "'", null);
	// }
	//
	// // /////////////////////////////////////////////////////////////
	//
	// private static final String USER_MESSAGE_TABLE = "UserMessageTable";
	//
	// private static final String FROM = "smsfrom";
	// private static final String TO = "smsto";
	// private static final String DESCRIPTION = "smsdesc";
	// private static final String SUBJECT = "smssubject";
	// private static final String DATE = "smsdate";
	//
	// private static final String CREATE_USER_MESSAGE_TABLE =
	// "create table if not exists UserMessageTable(smsfrom text,"
	// + "smsto text,smsdesc text,smssubject text,smsdate text)";
	//
	// public void insertMessageData(String smsfrom, String smsto, String
	// smsdesc,
	// String smssubject, String smsdate) {
	//
	// ContentValues con = new ContentValues();
	// con.put(FROM, smsfrom);
	// con.put(TO, smsto);
	// con.put(DESCRIPTION, smsdesc);
	// con.put(SUBJECT, smssubject);
	// con.put(DATE, smsdate);
	//
	// db.insert(USER_MESSAGE_TABLE, null, con);
	//
	// }
	//
	// public Cursor getMessages(String to) {
	// return db.rawQuery("select * from UserMessageTable where smsto" + "='"
	// + to + "'", null);
	// }
	//
	// public void deleteSMS(String time) {
	// db.delete(USER_MESSAGE_TABLE, DATE + "='" + time + "'", null);
	// }
	//
	// // /////////////////////////////////////////////////////////////////
	//
	// private static final String USER_CONTACT_TABLE = "UserContactTable";
	//
	// private static final String FROM_NAME = "name";
	// private static final String FROM_NUMBER = "number";
	// private static final String FROM_DESCRIPTION = "description";
	// private static final String FROM_SUBJECT = "subject";
	// private static final String FROM_DATE = "date";
	// private static final String SMS_ID = "smsid";
	//
	// private static final String CREATE_USER_CONTACT_TABLE =
	// "create table if not exists UserContactTable(name text,"
	// +
	// "number text,description text,subject text,date text,smsid integer primary key autoincrement)";
	//
	// public void insertContactData(String name, String number,
	// String description, String subject, String date) {
	//
	// ContentValues con = new ContentValues();
	// con.put(FROM_NAME, name);
	// con.put(FROM_NUMBER, number);
	// con.put(FROM_DESCRIPTION, description);
	// con.put(FROM_SUBJECT, subject);
	// con.put(FROM_DATE, date);
	//
	// db.insert(USER_CONTACT_TABLE, null, con);
	//
	// }
	//
	// public Cursor getContactMessages() {
	// return db.rawQuery("select * from UserMessageTable", null);
	// }

	// ///////////////////////////////////////////////////////////////////

}
