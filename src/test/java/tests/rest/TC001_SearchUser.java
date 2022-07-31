package tests.rest;

import org.testng.annotations.Test;
import java.util.List;
import org.testng.annotations.BeforeTest;
import io.restassured.response.Response;
import lib.restAssured.RESTAssuredServiceImpl;

public class TC001_SearchUser extends RESTAssuredServiceImpl{

	@BeforeTest
	public void setValues() {
		testCaseName = "Search User";
		testDescription = "Search for the User";
		authors = "Bharath";
		nodes ="users";
	}

	@Test
	public void searchUser() {		

		Response response = get("users", "username", "Delphine");

		response.prettyPrint();
		verifyContentType(response, "application/json");

		verifyResponseCode(response, 200);

		List<Object> id = getContentsWithKey(response, "id");
		for (Object uid : id) {
			userId = Integer.parseInt(uid.toString());

		}
		System.out.println("The user id of Delphine is " +userId);
		verifyContentsWithKey(response, "id", "9");
	}


}
