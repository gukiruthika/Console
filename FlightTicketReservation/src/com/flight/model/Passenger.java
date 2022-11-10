package com.flight.model;

public class Passenger {
	private int passengerId;
	private String passengerName;
//	private List<Booking> Bookings;
	private String gender;
	private int age;

	public Passenger(int passengerId, String passengerName, String gender, int age) {
		this.passengerId = passengerId;
		this.passengerName = passengerName;
		this.gender = gender;
		this.age = age;
	}

	public int getPassengerId() {
		return passengerId;
	}

	public void setPassengerId(int passengerId) {
		this.passengerId = passengerId;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengerName) {
		this.passengerName = passengerName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}
