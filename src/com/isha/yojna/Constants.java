package com.isha.yojna;

public class Constants {

	private Constants() {
	}

	public static class Config {
		public static final boolean DEVELOPER_MODE = false;

		String addressnamePattern = "[a-z A-Z 0-9]+";
		String namePattern = "[a-z A-Z]+";
		String phonenoPattern = "^([7-9]{1})([0-9]{9})$";
		String pincodepattern = "^5[0-9]{5}$";
		String passwordPattern = "^([a-zA-Z0-9$%^?!><@*#]{8,15})$";
		String emailPattern = "^[\\w-]+(?:\\.[\\w-]+)*@(?:[\\w-]+\\.)+[a-zA-Z]{2,7}$";
		String datePattern = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$";
		String timePattern = "^(([0-1]?[0-9])|([2][0-3])):([0-5]?[0-9])(:([0-5]?[0-9]))?$";
	}

}
