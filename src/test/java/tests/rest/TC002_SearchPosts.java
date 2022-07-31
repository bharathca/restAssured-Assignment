package tests.rest;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import io.restassured.response.Response;
import lib.restAssured.RESTAssuredServiceImpl;

public class TC002_SearchPosts extends RESTAssuredServiceImpl{

	@BeforeTest
	public void setValues() {
		testCaseName = "Search Posts";
		testDescription = "Search for the User's Posts";
		authors = "Bharath";
		nodes ="posts";
	}

	@Test
	public void searchUserPosts() {		

		Response response = get("posts", "userId", Integer.toString(userId));
		response.prettyPrint();
		verifyContentType(response, "application/json");
		verifyResponseCode(response, 200);


	}

}
