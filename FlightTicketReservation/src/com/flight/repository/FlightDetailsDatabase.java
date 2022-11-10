package com.flight.repository;

import java.util.ArrayList;
import java.util.List;

import com.flight.model.Booking;
import com.flight.model.Flight;
import com.flight.model.FlightRoutes;
import com.flight.model.Passenger;

public class FlightDetailsDatabase {
	private static FlightDetailsDatabase flightDetailsDatabase;
	private List<Flight> flightList;
	private List<FlightRoutes> flightRoutesList;
	private List<Passenger> passengerList;
	private List<Booking> bookingList;

	private FlightDetailsDatabase() {
		flightList = new ArrayList<>();
		flightRoutesList = new ArrayList<>();
		passengerList = new ArrayList<>();
		bookingList = new ArrayList<>();
	}

	public static FlightDetailsDatabase getInstance() {
		if (flightDetailsDatabase == null) {
			flightDetailsDatabase = new FlightDetailsDatabase();
		}
		return flightDetailsDatabase;
	}

	public void insertFlight(Flight flight) {
		flightList.add(flight);
	}

	public void insertFlight(List<Flight> flightList) {
		this.flightList.addAll(flightList);
	}

	public List<Flight> getFlightList() {
		return flightList;
	}

	public void insertFlightRoutes(FlightRoutes flightRoutes) {
		flightRoutesList.add(flightRoutes);
	}

	public List<FlightRoutes> getFlightRoutesList() {
		return flightRoutesList;
	}

	public void insertPassenger(Passenger passenger) {
		passengerList.add(passenger);
	}

	public List<Passenger> getPassengerList() {
		return passengerList;
	}

	public void insertBooking(Booking booking) {
		bookingList.add(booking);
	}

	public List<Booking> getBookingList() {
		return bookingList;
	}

}
