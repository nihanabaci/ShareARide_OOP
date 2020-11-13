package com.ride_share.sar.report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.ride_share.sar.database.DatabaseClass;
import com.ride_share.sar.rides.JoinRequest;
import com.ride_share.sar.rides.Ride;

public class ReportService {
	//Number of rides posted between a start date and an end date, broken down between departure zip code and arrival zip code.
	//Number of rides taken between a start date and an end date, broken down between departure zip code and arrival zip code.

	static Map<Long, Report> reports = DatabaseClass.getReports();
	List<Ride> confirmed_requests = new ArrayList<Ride>();
	private Map<Long, List<JoinRequest>> join_requests = DatabaseClass.getJoinRequests();
	
	public void generateReport()
	{
		Report rp_posted = new Report();
		long pid = (reports.size()+1);
		rp_posted.setPid(pid);
		rp_posted.setName("Rides posted between two dates");
		reports.put(pid, rp_posted);
		Report rp_took = new Report();
		long pid2 = (reports.size()+1);
		rp_took.setPid(pid2);
		rp_took.setName("Rides taken between two dates");
		reports.put(pid2, rp_took);
		
		//call get report for rides confirmed
	}
	public List<Report> getAllReports()
	{
		return new ArrayList<Report>(reports.values());
	}
	public  Map<Long, Report> getReportMap()
	{
		return reports;
	}
	
	public Report getReport(long pid, String start_date, String end_date) throws ParseException
	{
		Report r = reports.get(pid);
	
		
		//GETTING ALL THE RIDES BETWEEN THE GIVEN DATES
		List<Ride> matched_rides = new ArrayList<Ride>(DatabaseClass.getRideList().values()); 
		
		if(start_date != null && end_date != null) 
		{
			matched_rides = this.getRidesBetweenDate(start_date, end_date);
			r.setStart_date(start_date);
			r.setEnd_date(end_date);
		}
		
		//GOT THE RIDES
		
	    //With their id's find all join requests
		
		if(r.getName().contains("posted")) //rides posted
		{
			r.setDetail(getDetail(matched_rides));
			r.setRides(matched_rides.size());
		}
		else //rides taken
		{
			r.setDetail(getDetail(findJoinRequests(matched_rides)));
			r.setRides(findJoinRequests(matched_rides).size());
		}
		return r;
		
		
	}
	
	
	public List<Ride> getRidesBetweenDate(String start_date, String end_date) throws ParseException
	{
		List<Ride> ridesBetweenDates = new ArrayList<Ride>();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		Date startDate = sdf.parse(start_date);
		Date endDate = sdf.parse(end_date);
//		if(end_date == null)
//		{
//			for(Ride r: DatabaseClass.getRideList().values())
//			{
//				 Date date = sdf.parse(r.getDate_time().getDate());
//				 if((date.compareTo(startDate) > 0))
//				 {
//					 ridesBetweenDates.add(r);
//				 }
//			}
//		}
//		else if(start_date == null)
//		{
//			for(Ride r: DatabaseClass.getRideList().values())
//			{
//				 Date date = sdf.parse(r.getDate_time().getDate());
//				 if((date.compareTo(endDate) < 0))
//				 {
//					 ridesBetweenDates.add(r);
//				 }
//			}	
//		}
	//	else
	//	{
			for(Ride r: DatabaseClass.getRideList().values())
			{
				 Date date = sdf.parse(r.getDate_time().getDate());
				 if((date.compareTo(startDate) > 0)  && (date.compareTo(endDate) < 0)) 
				 {
					 ridesBetweenDates.add(r);
				 }
			}
	//	}
		return ridesBetweenDates;
		
	}
	public List<Ride> findJoinRequests(List<Ride> matched_rides)
	{
//		for(Ride r : matched_rides)
//		{
//			long rid = r.getRid();
//			List<JoinRequest> lof_jr = join_requests.get(rid);
//			for(JoinRequest jr : lof_jr)
//			{
//				if(jr.isPickup_confirmed())
//				{
//					confirmed_requests.add(DatabaseClass.getRideList().get(rid));
//				}
//			}
//		}
		return confirmed_requests;
	}
	public List<Detail> getDetail(List<Ride> r)
	{
		List<Detail> dt = new ArrayList<Detail>();
		
		for(Ride ri : r)
		{
			Detail d = new Detail();
			d.setFrom_zip(ri.getLocation_info().getFrom_zip());
			d.setTo_zip(ri.getLocation_info().getTo_zip());
			d.setCount(1);
			dt.add(d);
			
		}
		
		return dt;
		
	}

}
