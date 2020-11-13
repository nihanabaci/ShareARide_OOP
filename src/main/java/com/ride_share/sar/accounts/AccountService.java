package com.ride_share.sar.accounts;

import java.util.*;

import com.ride_share.sar.database.DatabaseClass;
import com.ride_share.sar.rides.Ride;

public class AccountService {
	
	private Map<Long, Account> accounts = DatabaseClass.getAccounts();
	private Map<Long, List<Rating>> ratings = DatabaseClass.getRatings();
	List<Rating> rating_list = DatabaseClass.getRatingsList();
	Map<Long, Ride> rides = DatabaseClass.getRideList();
	
	
	
	public List<Account> getAllAccounts()
	{
		return new ArrayList<Account>(accounts.values());
	}
	public Account getAccount(long id)
	{
		return accounts.get(id);
	}
	public Account addAccount(Account account)
	{
		account.setAid(accounts.size() + 1);
		accounts.put(account.getAid(), account);
		return account;
	}
	public Account updateAccount(Account account)
	{
//		if(account.getAid() <= 0)
//		{
//			return null;
//		}
		accounts.put(account.getAid(), account);
		return account;
	}
	public void activateAccount(Account account)
	{
		accounts.put(account.getAid(), account);
	}
	
	public Account removeAccount(long id)
	{
		return accounts.remove(id);
	}
	public void rateAccount(long to_be_rated, Rating rating)
	{
		rating_list.add(rating);
		ratings.put(to_be_rated, rating_list);
	}
	public AccountRatings ViewAccountRatings(long id)
	{
		AccountRatings dr = new AccountRatings();
		dr.setAid(id);
		dr.setFirst_name(accounts.get(id).getFirst_name());
		List<Ride> lo_rides = new ArrayList<Ride>();
		for(Ride r: rides.values())
		{
			if(r.getAid() == id)
			{
				lo_rides.add(r);
			}
		}
		dr.setRides(lo_rides.size());
		List<Rating> lof_rating =ratings.get(id);
		dr.setRatings(lof_rating.size());
		int rating_total = 0;
		for(Rating r: lof_rating)
		{
			rating_total+= r.getRating();
		}
		dr.setAverage_rating(rating_total/lof_rating.size());
		dr.setDetail(lof_rating);
		return dr;
	}
	
	public List<Account> searchAccountsForKey(String key) 
	{
		
		List<Account> lof_matched = new ArrayList<Account>();
		String new_key = key.toLowerCase();
			for(Account a: accounts.values()) {
				if(a.getFirst_name().toLowerCase().contains(new_key) 
						|| a.getLast_name().toLowerCase().contains(new_key)
						|| a.getPhone().contains(new_key)) {
					lof_matched.add(a);
				}
			}
		
		return lof_matched;
	}
	
}
	

