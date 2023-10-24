package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import resources.TestBuild;
import resources.Utils;
import resources.apiResource;

public class StepDefinitions extends Utils{

	TestBuild tb = new TestBuild();
	RequestSpecification baseReqSpec;
	Response response;
	//Marking it as static because when next test scenario runs it will not reset the value to default value
	//Retains the value in simple terms
	static String place_id;

	@Given("Add place payload with {string} and {string}")
	public void add_place_payload_with_and(String name, String language) throws IOException {
		baseReqSpec = given().spec(requestSpecification()).body(tb.addLocation(name, language));
	}

	@When("user calls the {string} API using {string} http method")
	public void user_calls_the_api_using_http_method(String resource, String method) {

		apiResource resourceAPI = apiResource.valueOf(resource);
		System.out.println(resourceAPI);

		if(method.equalsIgnoreCase("POST"))
			response = baseReqSpec.when().post(resourceAPI.getMethod());
		else if(method.equalsIgnoreCase("GET"))
			response = baseReqSpec.when().get(resourceAPI.getMethod());
		else if(method.equalsIgnoreCase("PUT"))
			response = baseReqSpec.when().put(resourceAPI.getMethod());
		else if(method.equalsIgnoreCase("DELETE"))
			response = baseReqSpec.when().post(resourceAPI.getMethod());
	}

	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(int statusCode) {
		assertEquals(response.getStatusCode(), statusCode);
	}

	@Then("{string} in response body is {string}")
	public void in_response_body_is(String keyValue, String expectedValue) {
		assertEquals(jsonPath(response, keyValue), expectedValue);
	}

	@Then("verify if the place_Id created maps to {string} and {string} API")
	public void verify_if_the_place_id_created_maps_to_and_api(String expectedName, String resource) throws IOException {
		place_id = jsonPath(response, "place_id");
		baseReqSpec = given().spec(requestSpecification()).queryParam(place_id).queryParam("place_id", place_id);
		user_calls_the_api_using_http_method(resource, "GET");
		String actualName = jsonPath(response, "name");
		assertEquals(actualName, expectedName);
	}

	@Given("Delete place payload")
	public void delete_place_payload() throws IOException {
		baseReqSpec = given().spec(requestSpecification()).body(tb.deleteLocation(place_id));
	}
}
