package com.flight.model;

import java.util.List;

public class Booking {
	private long pnrNumber;
	private long bookingDate;
	private long travelDate;
	private List<Passenger> passenger;
	private Flight flight;
	private int totalFare;
	private int status;

	public long getPnrNumber() {
		return pnrNumber;
	}

	public void setPnrNumber(long pnrNumber) {
		this.pnrNumber = pnrNumber;
	}

	public long getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(long bookingDate) {
		this.bookingDate = bookingDate;
	}

	public long getTravelDate() {
		return travelDate;
	}

	public void setTravelDate(long travelDate) {
		this.travelDate = travelDate;
	}

	public List<Passenger> getPassenger() {
		return passenger;
	}

	public void setPassenger(List<Passenger> passenger) {
		this.passenger = passenger;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public int getTotalFare() {
		return totalFare;
	}

	public void setTotalFare(int totalFare) {
		this.totalFare = totalFare;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Booking(long pnrNumber, long bookingDate, long travelDate, List<Passenger> passenger, Flight flight,
			int totalFare, int status) {
		this.pnrNumber = pnrNumber;
		this.bookingDate = bookingDate;
		this.travelDate = travelDate;
		this.passenger = passenger;
		this.flight = flight;
		this.totalFare = totalFare;
		this.status = status;
	}

	public Booking(long pnrNumber, List<Passenger> passenger, Flight flight, int totalFare, int status) {
		this.pnrNumber = pnrNumber;
		this.passenger = passenger;
		this.flight = flight;
		this.totalFare = totalFare;
		this.status = status;
	}

}
