package com.flight.model;

import java.util.List;

public class FlightRoutes {
	private int routeId;
	private List<String> locations;

	public FlightRoutes(int routeId, List<String> locations) {
		this.routeId = routeId;
		this.locations = locations;
	}
	
	public int getRouteId() {
		return routeId;
	}

	public void setRouteId(int routeId) {
		this.routeId = routeId;
	}

	public List<String> getLocations() {
		return locations;
	}

	public void setLocations(List<String> locations) {
		this.locations = locations;
	}
}
