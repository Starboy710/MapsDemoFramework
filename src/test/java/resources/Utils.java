package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {

	public static RequestSpecification req;
	ResponseSpecification resp;

	/*Generic request specification, used later by the FT
	 * Print stream is to print logs into log file
	 * add filter is to log
	 * Used if to not initialize the req object again
	 * Created a static req obj to prevent initialization all the time the object is being used.
	 */
	public RequestSpecification requestSpecification() throws IOException {
		if(req==null)
		{
			PrintStream log =new PrintStream(new FileOutputStream("logging.txt"));
			req=new RequestSpecBuilder().setBaseUri(getPropertyValue("baseUrl")).addQueryParam("key", "qaclick123")
					.addFilter(RequestLoggingFilter.logRequestTo(log))
					.addFilter(ResponseLoggingFilter.logResponseTo(log))
					.setContentType(ContentType.JSON).build();
			return req;
		}
		return req;
	}

	public ResponseSpecification responseSpecification() {
		resp = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return resp;
	}

	//To retrieve value from the global properties file
	public String getPropertyValue(String key) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("src/test/java/resources/global.properties");
		prop.load(fis);
		return prop.getProperty(key);
	}

	//To pick keyValue from JSON response
	public String jsonPath(Response response, String key) {
		String res = response.asString();
		JsonPath js = new JsonPath(res);
		return js.get(key).toString();
	}
}
