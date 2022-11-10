package com.flight.controller;

import java.util.List;

import com.flight.model.Booking;
import com.flight.model.Flight;
import com.flight.model.FlightRoutes;
import com.flight.repository.FlightDetailsDatabase;
import com.flight.view.MenuView;

public class MenuController {

	private MenuView menuView;

	public MenuController(MenuView menuView) {
		this.menuView = menuView;
	}

	public List<FlightRoutes> getListOfFlightRoutes() {
		return FlightDetailsDatabase.getInstance().getFlightRoutesList();
	}

	public List<Flight> getListOfFlights(String fromLocation, String toLocation) {
		return null;
	}

	public void toAddFlight(int flightNumber, String name, String departureTime, String arrivalTime,
			List<String> routes, int totalSeats, int fare) {
		FlightDetailsDatabase.getInstance()
				.insertFlight(new Flight(flightNumber, name, departureTime, arrivalTime, routes, totalSeats, fare));
	}

	public List<Flight> getListOfAllFlights() {
		return FlightDetailsDatabase.getInstance().getFlightList();
	}

	public int getTotalFare(Flight flight, int numberOfPassenger) {
		return numberOfPassenger * flight.getFare();
	}

	public long getPnrNumber() {
		return (long) (Math.random() * (9999999 - 1000000) + 1000000);
	}

	public Flight getFlight(int flightNumber) {
		List<Flight> flightList = FlightDetailsDatabase.getInstance().getFlightList();
		for (Flight flight : flightList) {
			if (flight.getFlightNumber() == flightNumber)
				return flight;
		}
		return null;
	}

	public String getMessage(int status, Booking booking) {
		switch (status) {
		case 1:
			return "Ticket(s) booked successfully.";
		case 2:
			return "Booking cancelled";
		case 3:
			return "Booking -rescheduled";
		}
		return null;
	}

}
