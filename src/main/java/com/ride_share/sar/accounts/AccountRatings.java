package com.ride_share.sar.accounts;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class AccountRatings {
	long aid;
	String first_name;
	int rides = 0;
	int ratings = 0;
	double average_rating;
	List<Rating> detail;
	
	public AccountRatings()
	{
		
	}
	public AccountRatings(long aid, String first_name, int rides, int ratings, double average_rating,
			List<Rating> detail) {
		this.aid = aid;
		this.first_name = first_name;
		this.rides = rides;
		this.ratings = ratings;
		this.average_rating = average_rating;
		this.detail = detail;
	}

	public long getAid() {
		return aid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public int getRides() {
		return rides;
	}

	public void setRides(int rides) {
		this.rides = rides;
	}

	public int getRatings() {
		return ratings;
	}

	public void setRatings(int ratings) {
		this.ratings = ratings;
	}

	public double getAverage_rating() {
		return average_rating;
	}

	public void setAverage_rating(double average_rating) {
		this.average_rating = average_rating;
	}

	public List<Rating> getDetail() {
		return detail;
	}

	public void setDetail(List<Rating> detail) {
		this.detail = detail;
	}
	
	
	
	

}
