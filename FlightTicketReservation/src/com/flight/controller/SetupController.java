package com.flight.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flight.model.Flight;
import com.flight.repository.FlightDetailsDatabase;
import com.flight.view.MenuView;

public class SetupController {
	private MenuView menuView;
	private ObjectMapper objectMapper = new ObjectMapper();
	private Path fileName = Path.of("C:\\Users\\home\\eclipse-workspace_new\\FlightTicketReservation\\src\\com\\flight\\repository\\flightRoutes.txt");
	
	
	public SetupController(MenuView menuView) {
		this.menuView = menuView;
	}

	void getFromFile() {
		try {
			String jsonString = Files.readString(fileName);
			List<Flight> flightList = objectMapper.readValue(jsonString, new TypeReference<List<Flight>>() {});
			FlightDetailsDatabase.getInstance().insertFlight(flightList);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void writeToFile() {
		try {
			String jsonString = objectMapper.writeValueAsString(FlightDetailsDatabase.getInstance().getFlightList());
			Files.writeString(fileName, jsonString);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
