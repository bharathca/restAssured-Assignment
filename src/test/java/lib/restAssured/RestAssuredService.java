package lib.restAssured;

import java.util.List;
import java.util.Map;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public interface RestAssuredService {

	/**
	 * This method will set the log for the API request using log().all() method.
	 * @author Bharath
	 * @return RequestSpecification with the log set. 
	 */
	public RequestSpecification setLogs();

	/**
	 * This method will enable the log for the API response using log().all() method.
	 * @param response - The response of a API call request made in which the log is to be enabled.
	 * @author Bharath
	 * @return ValidatableResponse with the log enabled. 
	 */
	public ValidatableResponse enableResponseLog(Response response);

	/**
	 * This method will get the API response by performing API GET request after the log set. 
	 * @author Bharath
	 * @return Response of the GET request.  
	 */
	public Response get();

	/**
	 * This method will get the API response by performing API GET request to a path after the log set 
	 * @param url - The url to which the request is sent to.
	 * @author Bharath
	 * @return Response of the GET request.
	 */
	public Response get(String url);

	/**
	 * This method will get the API response by performing API GET request to a path with a parameter after the log set  
	 * @param url - The url to which the request is sent to.
	 * @param parameterName - Specify the name of parameter that'll be sent with the request
	 * @param parameterValue - Specify the value of the parameter that'll be sent with the request
	 * @author Bharath
	 * @return Response of the GET request.
	 */
	public Response get(String url, String parameterName, String parameterValue);

	/**
	 * This method will get the API response by performing API GET request to a path with a collection of parameters using Map after the log set  
	 * @param url - The url to which the request is sent to.
	 * @param parameters - Specify a collection of parameters in Map that'll be sent with the request
	 * @author Bharath
	 * @return Response of the GET request.
	 */
	public Response get(String url, Map<String,String> parameters);

	/**
	 * This method will verify the API response's content type and report the same in the Extent report  
	 * @param response - The response of a API call request made.
	 * @param expectedContentType - Specify the expected Content type to validate with the response content type
	 * @author Bharath
	 */
	public void verifyContentType(Response response, String expectedContentType);

	/**
	 * This method will verify the API response's response code and report the same in the Extent report  
	 * @param response - The response of a API call request made.
	 * @param expectedCode - Specify the expected code to validate with the response code
	 * @author Bharath
	 */
	public void verifyResponseCode(Response response, int expectedCode);

	/**
	 * This method will verify the API JSON response's content whether specified key with value is present and report the same in the Extent report  
	 * @param response - The response of a API call request made.
	 * @param key - Specify the key to be found from the response content
	 * @param expectedValue - Specify the value to be found for the key from the response content
	 * @author Bharath
	 */
	public void verifyContentWithKey(Response response, String key, String expectedValue);

	/**
	 * This method will verify the API JSON response's content that comes as JSON list whether specified key with value is present and report the same in the Extent report  
	 * @param response - The response of a API call request made.
	 * @param key - Specify the key to be found from the response content
	 * @param expectedValue - Specify the value to be found for the key from the response content
	 * @author Bharath
	 */
	public void verifyContentsWithKey(Response response, String key, String expectedValue);

	/**
	 * This method will get the API JSON response's content that comes as JSON list  
	 * @param response - The response of a API call request made.
	 * @param key - Specify the key to be found from the response content
	 * @author Bharath
	 * @return list of key values from the json as object
	 */
	public List<Object> getContentsWithKey(Response response, String key);

	/**
	 * This method will get the API JSON response's content that comes as JSON  
	 * @param response - The response of a API call request made.
	 * @param key - Specify the key to be found from the response content
	 * @author Bharath
	 * @return key's value from the json
	 */
	public String getContentWithKey(Response response, String key);
}
