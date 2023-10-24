package resources;

public enum apiResource {

	addPlace("/maps/api/place/add/json"),
	getPlace("/maps/api/place/get/json"),
	updatePlace("/maps/api/place/update/json"),
	deletePlace("/maps/api/place/delete/json");;
	
	private String method;

	apiResource(String method) {
		this.method = method;
	}

	public String getMethod() {
		return method;
	}
}
