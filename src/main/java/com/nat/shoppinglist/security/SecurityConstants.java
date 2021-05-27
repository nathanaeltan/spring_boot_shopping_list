package com.nat.shoppinglist.security;

import com.nat.shoppinglist.SpringApplicationContext;

public class SecurityConstants {
	public static final long EXPIRATION_TIME = 86400000;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/api/v1/users";
//	public static final String TOKEN_SECRET = "7ja8lq19015";

	public static String getTokenSecret() {
		AppProperties appProperties = (AppProperties) SpringApplicationContext.getBean("appProperties");
		return appProperties.getTokenSecret();
	}
}
