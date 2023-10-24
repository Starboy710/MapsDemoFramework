package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@deletePlace")
	public void beforeDelete() throws IOException {
		
		StepDefinitions stepDef = new StepDefinitions();
		
		if(StepDefinitions.place_id == null) {
			stepDef.add_place_payload_with_and("Anjappar", "Tamil");
			stepDef.user_calls_the_api_using_http_method("addPlace", "POST");
			stepDef.verify_if_the_place_id_created_maps_to_and_api("Anjappar", "getPlace");
		}
	}

}
