package com.favq.test;

import static io.restassured.RestAssured.given;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.favq.common.Resources;
import com.favq.common.ReusableMethods;
import com.favq.pojo.FavQuoteApiPojo;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class FavQuotes extends BaseTest {
	
	public static int value1;
	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response res;
	ReusableMethods rm = new ReusableMethods();

    @Test(dataProvider = "addFavorites", groups = {"Add quote to favorites and validate response body"})
    public void testAddQuoteToFavorites( String quoteId, String count) throws IOException {
    
    	FavQuoteApiPojo fav = new FavQuoteApiPojo();
		fav.setQuoteId(quoteId);

		resSpec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		reqSpec= given().spec(rm.requestSpecificationPostFavQuote(quoteId)).body(fav);
		res = reqSpec.when().put(Resources.putFavQuote()).then().spec(resSpec).extract().response();
		JsonPath js = ReusableMethods.rawToJson(res);
		Assert.assertEquals(js.get("id").toString(),quoteId);
		Assert.assertEquals(js.get("favorites_count").toString(),count);

    }
    @DataProvider(name = "addFavorites")
	public Object[][] testAddQuoteToFavoritesData() {
		return new Object[][] {{ "4","38"}};
	}
    
    @Test(dataProvider = "removeFromFavorites", groups = {"Add quote to favorites and validate response body"})
    public void testRemoveQuoteFromFavorites( String quoteId,String count) throws IOException {
    
    	FavQuoteApiPojo fav = new FavQuoteApiPojo();
		fav.setQuoteId(quoteId);

		resSpec= new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		reqSpec= given().spec(rm.requestSpecificationPostFavQuote(quoteId)).body(fav);
		res = reqSpec.when().put(Resources.putUnFavQuote()).then().spec(resSpec).extract().response();
		JsonPath js = ReusableMethods.rawToJson(res);
		Assert.assertEquals(js.get("id").toString(),quoteId);
		Assert.assertEquals(js.get("favorites_count").toString(),count);
    }
    @DataProvider(name = "removeFromFavorites")
	public Object[][] getQuoteIdToUnFav() {
		return new Object[][] {{ "4","37" }};
	}
}
