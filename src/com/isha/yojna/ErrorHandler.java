package com.isha.yojna;

public class ErrorHandler {

	public final static String WH_210 = "Response Content is EMPTY: WH-210";
	public final static String WH_300 = "Missing input fields: WH-300";
	public final static String WH_310 = "Invalid input format: WH-310";
	public final static String WH_320 = "Input out of range: WH-320";
	public final static String WH_400 = "Unexpected exception in the server: WH-400";
	public final static String WH_410 = "Server is busy right now. Please retry later";
	public final static String WH_412 = "Request validation error (Response Code: 412)";
	public final static String WH_500 = "Critical exception in the server: WH-500";
	public final static String UNKNOWERROR = "Unknown error occurred: %s";
	public final static String JSONTASK_PARSE_EXCP = "Cannot retrieve response-code from the reponse";
	public final static String JSONTASK_PARSE_EXCP1 = "Cannot create the JSON Object from the response";
	public final static String UNSUPPORTEDENCODINGEXCEPTION = "Cannot convert the response into UTF-8 string";
	public final static String IOEXCEPTION_DECOMP = "IOException while decompressing the response";
	public final static String IOEXCEPTION_DOWN = "IOException while downloading data";
	public final static String ILLEGALSTATEEXCEPTION = "Illegal response status";
	public final static String SERVER_NOT_AVALIABLE = "Server not available (Response Code: %d )";
	public final static String ERR_500 = "Unexpected exception in the server (Response Code: 500)";
	public final static String ERR_412 = "Request validation error (Response Code: 412)";
	public final static String IOEXCP = "Cannot connect to the server";
	public final static String SOCKETTIMEOUT_EXCP = "Socket Connection timed out";
	public final static String NOHTTPRESPONSE_EXCP = "No response from the server";
	public final static String CONNECTTIMEOUT_EXCP = "Connection timed out ";
	public final static String IOEXCEPTION = "Cannot connect to the server";
	public final static String IOEXCEPTION_CANTINITIATE = "Cannot initiate downloading the response";
	public final static String CLIENTPROTOCOL_EXCP = "ClientProtocolException";
	public final static String SERVER_NOT_RESPONDING = "Unable to Connect/Respond to Server";

}
