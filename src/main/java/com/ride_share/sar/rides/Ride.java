package com.ride_share.sar.rides;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Ride {
	long rid = 0;
	RideLocation location_info; 
	long aid; //account ID of the driver who creates the ride.
	DateTime date_time;
	Car car_info;
	int max_passengers;
	double amount_per_passenger;//has to be zero
	String conditions;

	public Ride()
	{
		
	}
	public Ride(long ri, RideLocation rl, Car ci, DateTime dt, long ai, int mp, double a, String con)
	{
		rid = ri;
		location_info = rl;
		aid = ai;
		car_info = ci;
		max_passengers = mp;
		amount_per_passenger = a;
		conditions = con;
		date_time = dt;
		
	}

	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
	}

	public long getAid() {
		return aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}

	public DateTime getDate_time() {
		return date_time;
	}

	public void setDate_time(DateTime date_time) {
		this.date_time = date_time;
	}

	public Car getCar_info() {
		return car_info;
	}

	public void setCar_info(Car car_info) {
		this.car_info = car_info;
	}

	
	public RideLocation getLocation_info() {
		return location_info;
	}
	public void setLocation_info(RideLocation location_info) {
		this.location_info = location_info;
	}
	public int getMax_passengers() {
		return max_passengers;
	}
	public void setMax_passengers(int max_passengers) {
		this.max_passengers = max_passengers;
	}
	public double getAmount_per_passenger() {
		return amount_per_passenger;
	}
	public void setAmount_per_passenger(double amount_per_passenger) {
		this.amount_per_passenger = amount_per_passenger;
	}
	public String getConditions() {
		return conditions;
	}

	public void setConditions(String conditions) {
		this.conditions = conditions;
	}


}
