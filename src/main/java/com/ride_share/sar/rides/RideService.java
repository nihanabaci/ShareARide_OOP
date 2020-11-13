package com.ride_share.sar.rides;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ride_share.sar.accounts.Account;
import com.ride_share.sar.accounts.Rating;
import com.ride_share.sar.database.DatabaseClass;

public class RideService {

	Map<Long, Ride> rides = DatabaseClass.getRideList();
	Map<Long, Account>  accounts = DatabaseClass.getAccounts();
	//rid
	private Map<Long, List<JoinRequest>> join_requests = DatabaseClass.getJoinRequests();
	//rid
	List<JoinRequest> j_request_list = DatabaseClass.getJoinRequestsList();
	
	private Map<Long, List<Message>> messages = DatabaseClass.getMessages();
	//rid
	List<Message> message_list = DatabaseClass.getMessagesList();
	
	public Account getAcc(long id)
	{
		return accounts.get(id);
	}
	
	public List<Ride> getAllRides()
	{
		return new ArrayList<Ride>(rides.values());
	}
	public Ride getRide(long id)
	{
		return rides.get(id);
	}
	public Ride addRide(Ride ride)
	{
		ride.setRid(rides.size() + 1);
		rides.put(ride.getRid(), ride);
		return ride;
	}
	public Ride updateRide(Ride ride)
	{
	
		rides.put(ride.getRid(), ride);
		return ride;
	}
//	public void activateAccount(Account account)
//	{
//		accounts.put(account.getAid(), account);
//	}
//	
	public Ride removeAccount(long id)
	{
		return rides.remove(id);
	}
	public void sendJoinRequest(long rideID, JoinRequest join_request)
	{
		join_request.setJid(j_request_list.size() + 1);
		j_request_list.add(join_request);
		join_requests.put(rideID, j_request_list);
	}
	public List<JoinRequest> getAllJoinRequests(long rid)
	{
		return new ArrayList<JoinRequest>(join_requests.get(rid));
	}

	public void sendMessage(long rideID, Message message)
	{
		message.setMid(message_list.size() + 1);
		message_list.add(message);
		messages.put(rideID, message_list);
	}
	public List<Message> getAllMessages(long rid)
	{
		return new ArrayList<Message>(messages.get(rid));
	}
	public List<Ride> searchRide(String from, String to, String date)
	{
		List<Ride> lof_matched = new ArrayList<Ride>();
		String new_from = from.toLowerCase();
		String new_to = to.toLowerCase();
		String new_date = date.toLowerCase();
			for(Ride r: rides.values()) {
				if(r.getLocation_info().getFrom_city().toLowerCase().contains(new_from) 
						&& r.getLocation_info().getTo_city().toLowerCase().contains(new_to)
						&& r.getDate_time().getDate().contains(new_date)) {
					lof_matched.add(r);
				}
			}
		return lof_matched;
	}
}
