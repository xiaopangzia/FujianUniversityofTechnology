package com.cheng.schoolsell.config;

/**
 * GeetestWeb配置文件
 */
public class GeetestConfig {

	/** 填入自己的captcha_id和private_key */
	private static final String geetest_id = "c306e5fde2b9918a457dbc8ed068bbc4";
	private static final String geetest_key = "b7f280058a82a440138d8b4050b3d3c5";
	private static final boolean newfailback = true;

	public static final String getGeetest_id() {
		return geetest_id;
	}

	public static final String getGeetest_key() {
		return geetest_key;
	}
	
	public static final boolean isnewfailback() {
		return newfailback;
	}

}
