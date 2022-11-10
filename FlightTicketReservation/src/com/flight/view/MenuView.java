package com.flight.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.flight.controller.MenuController;
import com.flight.controller.SetupController;
import com.flight.model.Booking;
import com.flight.model.Flight;
import com.flight.model.Passenger;

public class MenuView {

	private MenuController menuController;
	private SetupController setupController;
	private InputValidation inputValidation = new InputValidation();

	MenuView() {
		menuController = new MenuController(this);
		setupController = new SetupController(this);
	}

	void init() {
		viewMenu();
	}

	private void viewMenu() {
		System.out.println("""
				Enter your option :
					1-Booking
					2-Get PNR status
					3-Booked tickets
					4-Cancel Tickets
					5-Search passenger
					6-Change ticket status of a passenger
					7-List flight routes
					8-Add flight routes
					9-Exit
				""");
		String option = inputValidation.getInput("Option -> ", "[1-9]");
		switch (option) {
		case "1":
			booking();
			break;
		case "2":
			getPNRStatus();
			break;
		case "3":
			viewBookedtickets();
			break;
		case "4":
			cancelTicket();
			break;
		case "5":
			searchPassenger();
			break;
		case "6":
			changeTicketStatus();
			break;
		case "7":
			listFlight();
			break;
		case "8":
			addFlight();
			break;
		case "9":
			break;
		}
		if (!option.equals("9")) {
			viewMenu();
		}
		setupController.writeToFile();
		System.out.println("Thank you!!");
	}

	private void listFlight() {
		List<Flight> flightList = menuController.getListOfAllFlights();
		for (Flight flight : flightList) {
			System.out.println("\nFlight no : " + flight.getFlightNumber() + " ||name : " + flight.getFlightName()
					+ " || Departure Time : " + flight.getDepartureTime() + " || Arrival Time : "
					+ flight.getArrivalTime() + " || Travel Time : " + " || Fare : " + " || seats : ");
		}
	}

	private void booking() {
		String fromLocation = inputValidation.getInput("From location: ", "^[A-Z][a-z]+");
		String toLocation = inputValidation.getInput("To location: ", "^[A-Z][a-z]+");
		System.out.println("Available Flights");
		List<Flight> flightList = menuController.getListOfFlights(fromLocation, toLocation);
		for (Flight flight : flightList) {
			System.out.println("\nFlight no : " + flight.getFlightNumber() + " ||name : " + flight.getFlightName()
					+ " || Departure Time : " + flight.getDepartureTime() + " || Arrival Time : "
					+ flight.getArrivalTime() + " || Travel Time : " + " || Fare : " + flight.getFare() + " || seats : "
					+ flight.getTotalSeats());
		}
		int flightNumber = Integer.valueOf(inputValidation.getInput("Enter flight number : ", ""));
		Flight flight = menuController.getFlight(flightNumber);
		int numberOfPassenger = Integer
				.valueOf(inputValidation.getInput("Enter number Of Passenger : ", "[1-9][0-9]*"));
		List<Passenger> passengerList = new ArrayList<>();
		String passengerName, gender;
		int age, passengerId;
		for (int i = 1; i <= numberOfPassenger; i++) {
			System.out.println("Enter Passenger " + i + " details : ");
			passengerName = inputValidation.getInput("Name : ", "^[A-Z][a-z]+");
			age = Integer.valueOf(inputValidation.getInput("Age : ", "[1-9][0-9]{0,1}"));
			gender = inputValidation.getInput("Gender : ", "male|female|Male|Female");
			passengerId = Integer.valueOf(inputValidation.getInput("ID : ", "[0-9]{4}"));
			passengerList.add(new Passenger(passengerId, passengerName, gender, age));
		}
		int totalFare = menuController.getTotalFare(flight, numberOfPassenger);
		System.out.println("Total Fare : " + totalFare);
		System.out.println("Pay : ");

		int status = Integer.valueOf(inputValidation.getInput("(1-Pay,2-Cancel,3-reschedule)", "[1-3]"));
		long pnrNumber = menuController.getPnrNumber();
		System.out.println(
				menuController.getMessage(status, new Booking(pnrNumber, passengerList, flight, totalFare, status)));
		if (status == 1) {
			System.out.println("Ticket Details");
			System.out.println("Flight Details \n -----------");
			System.out.println("\nFlight no : " + flight.getFlightNumber() + " ||name : " + flight.getFlightName()
					+ " || Departure Time : " + flight.getDepartureTime() + " || Arrival Time : "
					+ flight.getArrivalTime() + " || From : " + fromLocation + " || To : " + toLocation
					+ " || PNR No : " + pnrNumber + " || Total Fare : " + totalFare);
		}
	}

	private void changeTicketStatus() {
		// TODO Auto-generated method stub

	}

	private void searchPassenger() {
		// TODO Auto-generated method stub

	}

	private void cancelTicket() {
		// TODO Auto-generated method stub

	}

	private void viewBookedtickets() {
		// TODO Auto-generated method stub

	}

	private void getPNRStatus() {
		// TODO Auto-generated method stub

	}

	private void addFlight() {
		int flightNumber = Integer.valueOf(inputValidation.getInput("Flight Number : ", "^[1-9]{4}"));
		String name = inputValidation.getInput("Flight Name : ", "^[A-Z][A-Za-z ]+");
		String departureTime = inputValidation.getInput("Flight departure time : ",
				"([0-9]|1[0-9]|2[0-3])[.]([0-5][0-9])");
		String arrivalTime = inputValidation.getInput("Flight Arrival time : ", "([0-9]|1[0-9]|2[0-3])[.]([0-5][0-9])");
		String route = inputValidation.getInput("Flight routes :  ", "[A-Za-z, ]+");
		List<String> routes = Arrays.asList(route.split(","));
		int totalSeats = Integer.valueOf(inputValidation.getInput("Total seats : ", "[0-9]*"));
		int fare = Integer.valueOf(inputValidation.getInput("Fare : ", "[0-9]*"));
		menuController.toAddFlight(flightNumber, name, departureTime, arrivalTime, routes, totalSeats, fare);
	}

}
