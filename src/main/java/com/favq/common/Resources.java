package com.favq.common;

public class Resources {

	public static String putFavQuote() {
		String resource = "/quotes/{quote_id}/fav";
		return resource;
	}
	
	public static String putUnFavQuote() {
		String resource = "/quotes/{quote_id}/unfav";
		return resource;
	}

	public static String getListOfQuotes() {
		String resource = "/quotes";
		return resource;
	}
	
	public static String addQuotes() {
		String resource = "/api/quotes";
		return resource;
	}
	
	
	

}
