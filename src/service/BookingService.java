package service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

import model.Customer;
import model.Driver;
import model.Trip;

public class BookingService implements IBookingService{

	private final static int INITIAL_CAPACITY = 10;
	Queue<Customer> customersList;
	Queue<Driver> driversList;
	
	Map<Integer, ArrayList<Trip>> mapOfTrips;
	Map<Integer, Customer> mapOfCustomers;
	Map<Integer, Driver> mapOfDrivers;
	
	
	
	public BookingService() {
		//TODO: Priority Queue won't return the required result while iterating, needs to be fixed to some other DS
		
		this.customersList = new PriorityQueue<Customer>(INITIAL_CAPACITY, new Comparator<Customer>() {
			public int compare(Customer a, Customer b) {
				return Float.compare(a.getRating(), b.getRating());
			}
		});
		this.driversList =  new PriorityQueue<Driver>(INITIAL_CAPACITY, new Comparator<Driver>() {
			public int compare(Driver a, Driver b) {
				return Float.compare(a.getRating(), b.getRating());
			}
		});
		
		this.mapOfCustomers = new HashMap<Integer, Customer>();
		this.mapOfDrivers = new HashMap<Integer, Driver>();
		this.mapOfTrips = new HashMap<Integer, ArrayList<Trip>>();
	}
	
	
	@Override
	public float getDriversRating(int driverId) {
		if(mapOfDrivers.containsKey(driverId))
			return mapOfDrivers.get(driverId).getRating();
		
		return 0f;
	}

	@Override
	public float getCustomersRating(int customerId) {
		if(mapOfCustomers.containsKey(customerId))
			return mapOfCustomers.get(customerId).getRating();
		
		return 0f;
	}

	@Override
	public boolean updateTripWithDetails(Trip trip, int customerId, int driverId) {
		try {
			Driver driver = mapOfDrivers.get(driverId);
			Customer customer = mapOfCustomers.get(customerId);
			
			customersList.remove(customer);
			driversList.remove(driver);
			
			float driverRating = (driver.getRating() * driver.getTrips().size() + trip.getDriversRating()) /  (driver.getTrips().size() + 1);
			driver.setRating(driverRating);

			float customerRating = (customer.getRating() * customer.getTrips().size() + trip.getCustomersRating()) /  (customer.getTrips().size() + 1);
			customer.setRating(customerRating);
		
			customersList.add(customer);
			driversList.add(driver);
			
			mapOfTrips.get(customerId).add(trip);
			
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<Driver> listOfTopDrivers(int size) {
		
		int count = 0;
		
		Iterator<Driver> itr = driversList.iterator();
		List<Driver> result = new ArrayList<Driver>();
		
		while(itr.hasNext() && count < size) {
			result.add(itr.next());
		}
			
		return result;
	}

	@Override
	public List<Customer> listOfTopCustomers(int size) {
		
		int count = 0;
		
		Iterator<Customer> itr = customersList.iterator();
		List<Customer> result = new ArrayList<Customer>();
		
		while(itr.hasNext() && count < size) {
			result.add(itr.next());
		}
			
		return result;
	}

	@Override
	public Set<Driver> listOfEligibleDrivers(Customer customer) {
		
		Iterator<Driver> itr = driversList.iterator();
		Set<Driver> result = new HashSet<Driver>();
		
		float customersRating = customer.getRating();
		//Level 1 filtering
		while(itr.hasNext()) {
			Driver driver = itr.next();
			if(driver.getRating() > customersRating) {
				result.add(driver);
			} else if(!result.isEmpty()) {
				break;
			}
		}
		
		//Level 2 filtering
		List<Trip> tripsList = mapOfTrips.get(customer.getId());
		
		for(Trip trip : tripsList) {
			if(trip.getDriversRating() < 2 && result.contains(trip.getDriver())) {
				result.remove(trip.getDriver());
			}
		}
		
		//Level 3 filtering
		if(result.isEmpty()) {
			for(Trip trip : tripsList) {
				result.add(trip.getDriver());
			}
		}
		
		return result;
	}

}
