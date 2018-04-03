package service;

import java.util.List;
import java.util.Set;

import model.Customer;
import model.Driver;
import model.Trip;

public interface IBookingService {

	public float getDriversRating(int driverId);
	
	public float getCustomersRating(int customerId);
	
	public boolean updateTripWithDetails(Trip trip, int customerId, int driverId);
	
	public List<Driver> listOfTopDrivers(int size);
	
	public List<Customer> listOfTopCustomers(int size);
	
	public Set<Driver> listOfEligibleDrivers(Customer cutomer);
	
}
