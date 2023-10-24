package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlace;
import pojo.Location;

public class TestBuild {

	public AddPlace addLocation(String name, String language) {
		Location l = new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);

		AddPlace addPlace = new AddPlace();
		addPlace.setLocation(l);
		addPlace.setAccuracy(50);
		addPlace.setName(name);
		addPlace.setPhone_number("(+91) 983 893 3937");
		addPlace.setAddress("Ecr, Chennai");

		List<String> types1 = new ArrayList<String>();
		types1.add("Juice shop");
		types1.add("Tea shop");
		addPlace.setTypes(types1);
		addPlace.setWebsite("http://google.com");
		addPlace.setLanguage(language);
		return addPlace;
	}
	
	public String deleteLocation(String place_id) {
		return ("{\r\n"
				+ "    \"place_id\": \""+ place_id +"\"\r\n"
				+ "}");
	}
}
