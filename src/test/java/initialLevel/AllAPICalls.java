package initialLevel;

import java.util.List;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

public class AllAPICalls {

	public static void main(String[] args) {
		RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
		RequestSpecification request;	
		JsonPath responseInJsonPath;
		int userId = 0;


		System.out.println("*********** Get User details ********");
		
		request= RestAssured.given().queryParam("username", "Delphine");
		responseInJsonPath = request.get("users").jsonPath();
		List<Integer> jsonInList = responseInJsonPath.getList("id");
		for (Integer s : jsonInList) {
			userId = s;
		} 
		System.out.println("The user id " +userId);
		responseInJsonPath.prettyPrint();
		
		System.out.println("*********************************************");
		
		System.out.println("********** Get User's all posts ********");
		request = RestAssured.given().queryParam("userId", userId);
		responseInJsonPath = request.get("posts").jsonPath();
		responseInJsonPath.prettyPrint();
		
		System.out.println("*********************************************");
		
		System.out.println("********** Get User's all post comments ********");
		
		request = RestAssured.given().
				queryParam("posts", "posts")
				.queryParam("postId", userId)
				;
		responseInJsonPath = request.get("comments").jsonPath();
		responseInJsonPath.prettyPrint();
		
		System.out.println("*********************************************");
		
	}
}
