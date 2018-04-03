package model;

import java.util.Date;

public class Trip {

	private Customer customer;
	private Driver driver;
	private float driversRating;
	private float customersRating;
	private String source;
	private String destination;
	private Date startTime;
	private Date endTime;
	

	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Driver getDriver() {
		return driver;
	}
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	public float getDriversRating() {
		return driversRating;
	}
	public void setDriversRating(float driversRating) {
		this.driversRating = driversRating;
	}
	public float getCustomersRating() {
		return customersRating;
	}
	public void setCustomersRating(float customersRating) {
		this.customersRating = customersRating;
	}
	
	
	
}
