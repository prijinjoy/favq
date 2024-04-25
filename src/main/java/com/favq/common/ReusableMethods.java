package com.favq.common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ReusableMethods {

	public static JsonPath rawToJson(Response r) {
		String respon = r.asString();
		JsonPath x = new JsonPath(respon);
		System.out.println("Response is : " + respon);
		return x;
	}

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response res;

	public RequestSpecification requestSpecificationPostFavQuote(String quoteId) throws IOException {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

		reqSpec = new RequestSpecBuilder().setBaseUri(getURI())
				.addHeader("Authorization", "Token token=\"" + getAPIKey() + "\"")
				.addHeader("User-Token", getUserToken()).addPathParam("quote_id", quoteId)
				.addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return reqSpec;
	}

	public RequestSpecification requestSpecificationGetQuotes() throws IOException {

		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		reqSpec = new RequestSpecBuilder().setBaseUri(getURI())
				.addHeader("Authorization", "Token token=\"" + getAPIKey() + "\"")
				.addHeader("User-Token", getUserToken()).addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log)).build();
		return reqSpec;
	}

	public RequestSpecification requestSpecificationGetQuoteWithFilter(String filter) throws IOException {

		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		reqSpec = new RequestSpecBuilder().setBaseUri(getURI())
				.addHeader("Authorization", "Token token=\"" + getAPIKey() + "\"")
				.addHeader("User-Token", getUserToken()).addQueryParam("filter", filter)
				.addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return reqSpec;
	}

	public RequestSpecification requestSpecificationGetQuoteWithFilterAndType(String filter, String type)
			throws IOException {

		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		reqSpec = new RequestSpecBuilder().setBaseUri(getURI())
				.addHeader("Authorization", "Token token=\"" + getAPIKey() + "\"")
				.addHeader("User-Token", getUserToken()).addQueryParam("filter", filter).addQueryParam("type", type)
				.addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		return reqSpec;
	}

	static Properties prop = new Properties();

	public static String getURI() throws IOException {
		String dir = System.getProperty("user.dir");
		String propFileName = dir + "\\src\\main\\resources\\env.properties";
		FileInputStream fls = new FileInputStream(propFileName);
		prop.load(fls);
		String uri = prop.getProperty("host");
		return uri;
	}

	public static final String getAPIKey() throws IOException {
		String dir = System.getProperty("user.dir");
		String propFileName = dir + "\\src\\main\\resources\\env.properties";
		FileInputStream fls = new FileInputStream(propFileName);
		prop.load(fls);
		String api_key = prop.getProperty("api_key");
		return api_key;
	}

	public static final String getUserToken() throws IOException {
		String dir = System.getProperty("user.dir");
		String propFileName = dir + "\\src\\main\\resources\\env.properties";
		FileInputStream fls = new FileInputStream(propFileName);
		prop.load(fls);
		String user_token = prop.getProperty("user_token");
		return user_token;
	}

	public static void restAssuredConfig() throws IOException {
		RestAssured.config = RestAssuredConfig.newConfig().sslConfig(new SSLConfig().relaxedHTTPSValidation());
	}

}
