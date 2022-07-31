package lib.restAssured;

import java.util.List;
import java.util.Map;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class RESTAssuredServiceImpl extends PreAndPost implements RestAssuredService{


	public RequestSpecification setLogs() {
		RequestSpecification log = null;
		log = RestAssured
				.given()
				.log().all();
		return log;
	}

	public ValidatableResponse enableResponseLog(Response response) {
		return response.then().log().all();
	}

	public Response get() {
		return setLogs()
				.get();
	}

	public Response get(String url) {
		return setLogs()
				.get(url);
	}

	public Response get(String url, String parameterName, String parameterValue) {
		return setLogs()
				.param(parameterName, parameterValue)
				.get(url);
	}

	public Response get(String url, Map<String,String> parameters) {
		return setLogs()
				.params(parameters)
				.get(url);
	}

	public void verifyContentType(Response response, String expectedContentType) {
		if(response.getContentType().toLowerCase().contains(expectedContentType.toLowerCase())) {
			reportRequest("The Content type "+expectedContentType+" matches the expected content type", "PASS");
		}else {
			reportRequest("The Content type "+expectedContentType+" does not match the expected content type "+response.getContentType(), "FAIL");	
		}
	}

	public void verifyResponseCode(Response response, int expectedCode) {
		if(response.statusCode() == expectedCode) {
			reportRequest("The status code "+expectedCode+" matches the expected code", "PASS");
		}else {
			reportRequest("The status code "+expectedCode+" does not match the expected code"+response.statusCode(), "FAIL");	
		}
	}

	public void verifyResponseTime(Response response, long time) {
		if(response.time() <= time) {
			reportRequest("The time taken "+response.time() +" with in the expected time", "PASS");
		}else {
			reportRequest("The time taken "+response.time() +" is greater than expected SLA time "+time,"FAIL");		
		}
	}

	public void verifyContentWithKey(Response response, String key, String expectedValue) {
		if(response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			String actValue = jsonPath.get(key);
			if(actValue.equalsIgnoreCase(expectedValue)) {
				reportRequest("The JSON response has value "+expectedValue+" as expected. ", "PASS");
			}else {
				reportRequest("The JSON response :"+actValue+" does not have the value "+expectedValue+" as expected. ", "FAIL");		
			}
		}
	}

	public void verifyContentsWithKey(Response response, String key, String expectedValue) {
		if(response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			List<Object> actValue = jsonPath.getList(key);
			if(actValue.get(0).toString().equalsIgnoreCase(expectedValue)) {
				reportRequest("The JSON response has value "+expectedValue+" as expected. ", "PASS");
			}else {
				reportRequest("The JSON response :"+actValue+" does not have the value "+expectedValue+" as expected. ", "FAIL");		
			}
		}
	}

	public List<Object> getContentsWithKey(Response response, String key) {
		if(response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return jsonPath.getList(key);			
		}else {
			return null;
		}
	}

	public String getContentWithKey(Response response, String key) {
		if(response.getContentType().contains("json")) {
			JsonPath jsonPath = response.jsonPath();
			return (String) jsonPath.get(key);			
		}else {
			return null;
		}
	}
}
