package com.favq.test;

import java.io.IOException;

import org.testng.annotations.BeforeTest;

import com.favq.common.ReusableMethods;

import io.restassured.RestAssured;
import io.restassured.config.RestAssuredConfig;
import io.restassured.config.SSLConfig;

public class BaseTest {
	@BeforeTest
	public void getRestConfig() throws IOException {
		ReusableMethods.restAssuredConfig();
	}
	@BeforeTest
	public static String getURI() throws IOException {
		return ReusableMethods.getURI();

	}
	
	@BeforeTest
	 public void setUp() throws IOException {
	        RestAssured.baseURI = ReusableMethods.getURI();;
	        RestAssured.config = RestAssuredConfig.newConfig().sslConfig(
		            new SSLConfig().relaxedHTTPSValidation());
	    }

}
