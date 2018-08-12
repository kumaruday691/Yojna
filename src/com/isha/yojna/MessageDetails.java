package com.isha.yojna;

public class MessageDetails {
	String message, time;
	int messagetype, sendertype;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getMessagetype() {
		return messagetype;
	}

	public void setMessagetype(int messagetype) {
		this.messagetype = messagetype;
	}

	public int getSendertype() {
		return sendertype;
	}

	public void setSendertype(int sendertype) {
		this.sendertype = sendertype;
	}

	public MessageDetails(String message, String time, int messagetype,
			int sendertype) {
		super();
		this.message = message;
		this.time = time;
		this.messagetype = messagetype;
		this.sendertype = sendertype;
	}

}
