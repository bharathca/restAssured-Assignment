package tests.rest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.response.Response;
import lib.restAssured.RESTAssuredServiceImpl;

public class TC003_SearchComments extends RESTAssuredServiceImpl{

	@BeforeTest
	public void setValues() {
		testCaseName = "Fetch Comments";
		testDescription = "Fetch the comments from the user";
		authors = "Bharath";
		nodes ="posts/id/comments";
	}

	@Test
	public void searchUserPostsComments() {		
		int count = 0;
		Map<String, String> parameters = new  HashMap<String, String>();
		parameters.put("posts", "posts");
		parameters.put("postId", Integer.toString(userId));
		Response response = get("comments", parameters);
		response.prettyPrint();
		verifyContentType(response, "application/json");
		verifyResponseCode(response, 200);
		List<Object> emailList = getContentsWithKey(response, "email");
		String emailRegex = "^(.+)@(.+)$";
		List<String> incorrectEmail = new ArrayList<String>();
		Pattern pattern = Pattern.compile(emailRegex);
		for (Object email : emailList) {
			Matcher matcher = pattern.matcher((CharSequence) email);
			if(matcher.matches()) {
				count++;	
			} else {
				incorrectEmail.add(email.toString());
				
			}
		} if (count == emailList.size()) {
			reportRequest("All the email id format are correct", "pass");
		} else {
			reportRequest("Email id format is wrong for the following " +incorrectEmail, "fail");
		}
	}

}
