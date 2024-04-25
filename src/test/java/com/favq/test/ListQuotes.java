package com.favq.test;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.favq.common.Resources;
import com.favq.common.ReusableMethods;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class ListQuotes extends BaseTest {
	
	public static int value1;
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response res;
	ReusableMethods rm = new ReusableMethods();

    @Test(groups = {"Getting a random list of quotes and validate response body"})
    public void testRandomListQuotes() throws IOException {

		resSpec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		reqSpec= given().spec(rm.requestSpecificationGetQuotes());
		res = reqSpec.when().get(Resources.getListOfQuotes()).then().spec(resSpec).extract().response();
		String body = res.body().asString();
		JsonPath js = ReusableMethods.rawToJson(res);
		Assert.assertEquals(200, res.getStatusCode(), "Expected status code: 200 OK");
		Assert.assertNotEquals(js.get("page"),0);
		System.out.println("Response Body: " + body);
    }
	
	@Test(dataProvider = "testFilter",groups = {"Getting a list of quotes with filter and validate response body"})
    public void testFilter(String filter) throws IOException {
		resSpec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		reqSpec= given().spec(rm.requestSpecificationGetQuoteWithFilter(filter));
		res = reqSpec.when().get(Resources.getListOfQuotes()).then().spec(resSpec).extract().response();
		JsonPath js = ReusableMethods.rawToJson(res);
		Assert.assertEquals(200, res.getStatusCode(), "Expected status code: 200 OK");
		List<Object> allTags = js.getList("quotes.tags.flatten()");
        Assert.assertTrue(allTags.contains("funny"), "The 'funny' tag is not present in the tags arrays.");
		
    }
    
    @DataProvider(name = "testFilter")
	public Object[][] testFilterData() {
		return new Object[][] {{ "funny"}};
	}
	
	@Test(dataProvider = "testFilterAndType", groups = { "Getting the list of quotes with type, filter and validate response body" })
	public void testFilterAndType(String filter, String type) throws IOException {
		resSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		reqSpec = given().spec(rm.requestSpecificationGetQuoteWithFilterAndType(filter, type));
		res = reqSpec.when().get(Resources.getListOfQuotes()).then().spec(resSpec).extract().response();
		JsonPath js = ReusableMethods.rawToJson(res);
		Assert.assertEquals(200, res.getStatusCode(), "Expected status code: 200 OK");
		Assert.assertEquals(js.get("quotes.author.get(1)"), filter);
	}
	 @DataProvider(name = "testFilterAndType")
		public Object[][] testFilterAndTypeData() {
			return new Object[][] {{ "Mark Twain","author" }};
		}
}
