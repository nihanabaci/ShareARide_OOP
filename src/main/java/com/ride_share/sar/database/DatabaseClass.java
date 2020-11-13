package com.ride_share.sar.database;
import com.ride_share.sar.report.Report;
import com.ride_share.sar.rides.JoinRequest;
import com.ride_share.sar.rides.Message;
import com.ride_share.sar.rides.Ride;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ride_share.sar.accounts.Account;
import com.ride_share.sar.accounts.Rating;


public class DatabaseClass {
	
	private static Map<Long, Account> accounts = new HashMap<Long, Account>();
	private static List<Account> accountst_list = new ArrayList<Account>();
	private static Map<Long, List<Rating>> ratings = new HashMap<Long, List<Rating>>();
	private static List<Rating> rating_list = new ArrayList<Rating>();
	private static Map<Long, Ride> rides = new HashMap<Long,Ride>();
	
	private static Map<Long, List<JoinRequest>> join_requests = new HashMap<Long, List<JoinRequest>>();
	private static List<JoinRequest> join_request_list = new ArrayList<JoinRequest>();
	
	private static Map<Long, List<Message>> message_requests = new HashMap<Long, List<Message>>();
	private static List<Message> message_list = new ArrayList<Message>();
	private static Map<Long, Report> reports = new HashMap<Long, Report>();
	
	public static Map<Long, Account> getAccounts() {
		return accounts;
	}
	
	public static Map<Long, List<Rating>> getRatings() {
		return ratings;
	}
	
	public static List<Rating> getRatingsList() {
		return rating_list;
	}
	public static  Map<Long, Ride> getRideList() {
		return rides;
	}
	public static Map<Long, List<JoinRequest>> getJoinRequests() {
		return join_requests;
	}
	
	public static List<JoinRequest> getJoinRequestsList() {
		return join_request_list;
	}
	
	public static Map<Long, List<Message>> getMessages() {
		return message_requests;
	}
	
	public static List<Message> getMessagesList() {
		return message_list;
	}
	
	public static List<Account> getAccountList() {
		return accountst_list;
	}
	public static Map<Long, Report> getReports() {
		return reports;
	}



	
}
