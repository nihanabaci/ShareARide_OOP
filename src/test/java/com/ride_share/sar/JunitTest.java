package com.ride_share.sar;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

import org.codehaus.jettison.json.JSONArray;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Rule;
import org.junit.Test;

import com.ride_share.sar.accounts.*;
import com.ride_share.sar.accounts.AccountService;
import com.ride_share.sar.accounts.Rating;
import com.ride_share.sar.exception.BadRequestException;
import com.ride_share.sar.exception.BadRequestExceptionMapper;
import com.ride_share.sar.exception.DataNotFoundException;
import com.ride_share.sar.exception.DataNotFoundExceptionMapper;
import com.ride_share.sar.exception.ErrorMessage;
import com.ride_share.sar.report.Detail;
import com.ride_share.sar.report.Report;
import com.ride_share.sar.report.ReportResource;
import com.ride_share.sar.report.ReportService;
import com.ride_share.sar.rides.*;


import junit.framework.Assert;

public class JunitTest{

//	@Test (expected=NullPointerException.class)
//	public void test_account(){
//		
//		AccountService accountService= new AccountService();
//		Account ac = new Account();
//		ac.setFirst_name("John");
//		ac.setLast_name("Smith");
//		ac.setIs_active(false);
//		ac.setPicture("http://example.com/images/john-smith.jpeg");
//		ac.setPhone("312-456-789");
//		accountService.addAccount(ac);
//		
//		assertNotNull(accountService.addAccount(ac));
//		assertNotNull(accountService.getAllAccounts());
//		accountService.activateAccount(ac);
//		assertEquals(accountService.getAccount(1), ac);
//		assertNotNull(accountService.getAccount(1));
//		assertNotNull(accountService.updateAccount(ac));
//		assertNotNull(accountService.removeAccount(ac.getAid()));
//		
//		RideService rs = new RideService();
//		
//		
//		Ride r = new Ride();
//		r.setAid(ac.getAid());
//		RideLocation rl = new RideLocation();
//		rl.setFrom_city("Barrington");
//		rl.setFrom_zip("60010");
//		rl.setTo_city("Milwaukee");
//		rl.setTo_zip("53202");
//		r.setLocation_info(rl);
//		DateTime dt = new DateTime();
//		dt.setDate("14-Apr-2020");
//		dt.setTime("09:00");
//		r.setDate_time(dt);
//		Car c = new Car();
//		c.setMake("Audi");
//		c.setModel("A4");
//		c.setColor("Gray");
//		c.setPlate_state("IL");
//		c.setPlate_serial("COVID19");
//		r.setCar_info(c);
//		r.setMax_passengers(2);
//		r.setAmount_per_passenger(15.00);
//		r.setConditions("No more than one carry on per passenger. No pets.");
//		
//		Rating rt = new Rating();
//		rt.setRid(r.getRid());
//		rt.setSent_by_id(1);
//		rt.setRating(2);
//		rt.setComment("John was punctual and the ride was great.");
//		
//		accountService.rateAccount(ac.getAid(), rt);
//		
//		RideService rideService = new RideService();
//		rideService.addRide(r);
//		assertNull(accountService.ViewAccountRatings(ac.getAid()));		
//		
//	}
	@Test
	public void test_search_key()
	{
		AccountService accountService= new AccountService();
		Account ac = new Account();
		ac.setFirst_name("John");
		ac.setLast_name("Smith");
		ac.setIs_active(false);
		ac.setPicture("http://example.com/images/john-smith.jpeg");
		ac.setPhone("312-456-789");
		accountService.addAccount(ac);
		
		assertNotNull(accountService.searchAccountsForKey("John"));
		assertNotNull(accountService.searchAccountsForKey("Smith"));
		assertNotNull(accountService.searchAccountsForKey("312-456-789"));
	}
	@Test
	public void test_ride(){
		Ride r = new Ride();
		r.setAid(1);
		RideLocation rl = new RideLocation();
		rl.setFrom_city("Barrington");
		rl.setFrom_zip("60010");
		rl.setTo_city("Milwaukee");
		rl.setTo_zip("53202");
		r.setLocation_info(rl);
		DateTime dt = new DateTime();
		dt.setDate("14-Apr-2020");
		dt.setTime("09:00");
		r.setDate_time(dt);
		Car c = new Car();
		c.setMake("Audi");
		c.setModel("A4");
		c.setColor("Gray");
		c.setPlate_state("IL");
		c.setPlate_serial("COVID19");
		r.setCar_info(c);
		r.setMax_passengers(2);
		r.setAmount_per_passenger(15.00);
		r.setConditions("No more than one carry on per passenger. No pets.");
		
		RideService rideService = new RideService();
		assertNotNull(rideService.addRide(r));
		assertNotNull(rideService.getAllRides());
		assertNotNull(rideService.getRide(1));
		assertNotNull(rideService.updateRide(r));
		assertNotNull(rideService.removeAccount(1));
		
	}
	@Test
	public void test_join_r(){
		Ride r = new Ride();
		r.setAid(1);
		RideLocation rl = new RideLocation();
		rl.setFrom_city("Barrington");
		rl.setFrom_zip("60010");
		rl.setTo_city("Milwaukee");
		rl.setTo_zip("53202");
		r.setLocation_info(rl);
		DateTime dt = new DateTime();
		dt.setDate("14-Apr-2020");
		dt.setTime("09:00");
		r.setDate_time(dt);
		Car c = new Car();
		c.setMake("Audi");
		c.setModel("A4");
		c.setColor("Gray");
		c.setPlate_state("IL");
		c.setPlate_serial("COVID19");
		r.setCar_info(c);
		r.setMax_passengers(2);
		r.setAmount_per_passenger(15.00);
		r.setConditions("No more than one carry on per passenger. No pets.");
		
		RideService rideService = new RideService();
		rideService.addRide(r);
		
		JoinRequest jr = new JoinRequest();
		jr.setAid(1);
		jr.setPassengers(2);
		jr.setPickup_confirmed(false);
		jr.setRide_confirmed(false);
		
		rideService.sendJoinRequest(1, jr);
		
		assertNotNull(rideService.getAllJoinRequests(1));
			
	}
	@Test
	public void test_message()
	{
		
		Message m = new Message();
		m.setAid(1);
		m.setMsg("One passenger; could you pick me up at the Starbucks in Barrington at Main and Hough?");
		RideService rideService = new RideService();
		rideService.sendMessage(1, m);
		
		assertNotNull(rideService.getAllMessages(1));
	}
	@Test
	public void test_Report() throws ParseException
	{
		Ride r = new Ride();
		r.setAid(1);
		RideLocation rl = new RideLocation();
		rl.setFrom_city("Barrington");
		rl.setFrom_zip("60010");
		rl.setTo_city("Milwaukee");
		rl.setTo_zip("53202");
		r.setLocation_info(rl);
		DateTime dt = new DateTime();
		dt.setDate("14-Apr-2020");
		dt.setTime("09:00");
		r.setDate_time(dt);
		Car c = new Car();
		c.setMake("Audi");
		c.setModel("A4");
		c.setColor("Gray");
		c.setPlate_state("IL");
		c.setPlate_serial("COVID19");
		r.setCar_info(c);
		r.setMax_passengers(2);
		r.setAmount_per_passenger(15.00);
		r.setConditions("No more than one carry on per passenger. No pets.");
		
		RideService rideService = new RideService();
		rideService.addRide(r);
		rideService.addRide(new Ride(2, rl, c,  dt, 1,  2, 10.00, "comment"));
		
		Report m = new Report();
		m.setPid(1);
		m.setName("Rides posted");
		ReportService reportService = new ReportService();
		reportService.generateReport();
		reportService.getAllReports();
		reportService.getReport(1, "12-Apr-2020", "17-Apr-2020");
		reportService.getReport(1, null, "12-Apr-2020");
		reportService.getReport(1, "12-Apr-2020", null);
		assertNotNull(reportService.getReportMap());
		
		ReportResource rr = new ReportResource();
		assertNotNull(rr.getRides(1, "12-Apr-2020", "17-Apr-2020"));
		Report m2 = new Report();
		m.setPid(2);
		m.setName("Rides taken");
		reportService.getReport(2, "12-Apr-2020", "12-Apr-2020");
	}
	
	@Test
	public void test_searchR()
	{
		Ride r = new Ride();
		r.setAid(1);
		RideLocation rl = new RideLocation();
		rl.setFrom_city("Barrington");
		rl.setFrom_zip("60010");
		rl.setTo_city("Milwaukee");
		rl.setTo_zip("53202");
		r.setLocation_info(rl);
		DateTime dt = new DateTime();
		dt.setDate("14-Apr-2020");
		dt.setTime("09:00");
		r.setDate_time(dt);
		Car c = new Car();
		c.setMake("Audi");
		c.setModel("A4");
		c.setColor("Gray");
		c.setPlate_state("IL");
		c.setPlate_serial("COVID19");
		r.setCar_info(c);
		r.setMax_passengers(2);
		r.setAmount_per_passenger(15.00);
		r.setConditions("No more than one carry on per passenger. No pets.");
		
		RideService rideService = new RideService();
		rideService.addRide(r);
		
		rideService.searchRide("Barrington", "Milwaukee", "14-Apr-2020");
	}
	@Test
	public void test_idClasses()
	{
		jidClass jid = new jidClass(1);
		jid.setJid(2);
		jid.getJid();
		midClass mid = new midClass(1);
		mid.setMid(2);
		mid.getMid();
		ridClass rid = new ridClass(1);
		rid.setRid(2);
		rid.getRid();
		idClass id = new idClass(1);
		id.setAid(2);
		id.getAid();
		sidClass sid = new sidClass(1);
		sid.setSid(2);
		sid.getSid();
	}

	@Test 
	public void nullFN() {
	  boolean thrown = false;

	  try {
		  AccountResource aService = new AccountResource();
			 Account ac = new Account();
			 ac.setFirst_name(null);
				ac.setLast_name("Smith");
				ac.setIs_active(false);
				ac.setPicture("http://example.com/images/john-smith.jpeg");
				ac.setPhone("312-456-789");
			 aService.createAccount(ac);  
	    
	  } catch (BadRequestException e) {
	    thrown = true;
	  }

	  assertTrue(thrown);
	}
	@Test 
	public void nullLN() {
	  boolean thrown = false;

	  try {
		  AccountResource aService = new AccountResource();
			 Account ac = new Account();
			 ac.setFirst_name("");
				ac.setLast_name(null);
				ac.setIs_active(false);
				ac.setPicture("http://example.com/images/john-smith.jpeg");
				ac.setPhone("312-456-789");
			 aService.createAccount(ac);  
	    
	  } catch (BadRequestException e) {
	    thrown = true;
	  }

	  assertTrue(thrown);
	}
	@Test 
	public void nullisAc() {
	  boolean thrown = false;

	  try {
		  AccountResource aService = new AccountResource();
			 Account ac = new Account();
			 ac.setFirst_name("");
				ac.setLast_name("Smith");
				ac.setIs_active(null);
				ac.setPicture("http://example.com/images/john-smith.jpeg");
				ac.setPhone("312-456-789");
			 aService.createAccount(ac);  
	    
	  } catch (BadRequestException e) {
	    thrown = true;
	  }

	  assertTrue(thrown);
	}
	@Test 
	public void nullP() {
	  boolean thrown = false;

	  try {
		  AccountResource aService = new AccountResource();
			 Account ac = new Account();
			 ac.setFirst_name("");
			 ac.setLast_name("");
				ac.setIs_active(false);
				ac.setPicture(null);
				ac.setPhone("312-456-789");
			 aService.createAccount(ac);  
	    
	  } catch (BadRequestException e) {
	    thrown = true;
	  }

	  assertTrue(thrown);
	}
	@Test 
	public void nullPhone() {
	  boolean thrown = false;

	  try {
		  AccountResource aService = new AccountResource();
			 Account ac = new Account();
			 ac.setFirst_name("");
				ac.setLast_name("Smith");
				ac.setIs_active(false);
				ac.setPicture("http://example.com/images/john-smith.jpeg");
				ac.setPhone(null);
			 aService.createAccount(ac);  
	    
	  } catch (BadRequestException e) {
	    thrown = true;
	  }

	  assertTrue(thrown);
	}
	@Test 
	public void notNullAccount() {
	
		  AccountResource aService = new AccountResource();
			Account ac = new Account();
			ac.setFirst_name("");
			ac.setLast_name("Smith");
			ac.setIs_active(false);
			ac.setPicture("http://example.com/images/john-smith.jpeg");
			ac.setPhone("312-456-789");
			assertNotNull(aService.createAccount(ac)); 
			
	  
	}
	@Test 
	public void activateAccount() {
	
		  AccountResource aService = new AccountResource();
			Account ac = new Account();
			ac.setFirst_name("");
			ac.setLast_name("Smith");
			ac.setIs_active(false);
			ac.setPicture("http://example.com/images/john-smith.jpeg");
			ac.setPhone("312-456-789");
			aService.createAccount(ac);
			assertNotNull(aService.activateAccount(ac.getAid(), ac));     
	  
	}
	
	@Test 
	public void updateA() {
	
		  AccountResource aService = new AccountResource();
			Account ac = new Account();
			ac.setFirst_name("");
			ac.setLast_name("Smith");
			ac.setIs_active(false);
			ac.setPicture("http://example.com/images/john-smith.jpeg");
			ac.setPhone("312-456-789");
			aService.createAccount(ac);
			assertNotNull(aService.updateAccount(ac.getAid(), ac));     
	  
	}
	@Test 
	public void deleteA() {
	
		  AccountResource aService = new AccountResource();
			Account ac = new Account();
			ac.setFirst_name("");
			ac.setLast_name("Smith");
			ac.setIs_active(false);
			ac.setPicture("http://example.com/images/john-smith.jpeg");
			ac.setPhone("312-456-789");
			aService.createAccount(ac);
			assertNotNull(aService.deleteAccount(ac.getAid()));     
	  
	}
	@Test 
	public void getAllAc() {
	
		  AccountResource aService = new AccountResource();
			Account ac = new Account();
			ac.setFirst_name("");
			ac.setLast_name("Smith");
			ac.setIs_active(false);
			ac.setPicture("http://example.com/images/john-smith.jpeg");
			ac.setPhone("312-456-789");
			aService.createAccount(ac);
			
			Account ac2 = new Account();
			ac.setFirst_name("");
			ac.setLast_name("Smith");
			ac.setIs_active(false);
			ac.setPicture("http://example.com/images/john-smith.jpeg");
			ac.setPhone("312-456-789");
			aService.createAccount(ac);
			
			assertNotNull(aService.getAccounts(""));     
	  
	}
	@Test 
	public void rateAc() {
		
	AccountResource aService = new AccountResource();
	AccountService accountService= new AccountService();
	Account ac = new Account();
	ac.setFirst_name("John");
	ac.setLast_name("Smith");
	ac.setIs_active(false);
	ac.setPicture("http://example.com/images/john-smith.jpeg");
	ac.setPhone("312-456-789");
	accountService.addAccount(ac);
	
	
	Rating rt = new Rating();
	rt.setRid(1);
	rt.setSent_by_id(1);
	rt.setRating(2);
	rt.setComment("John was punctual and the ride was great.");

	
	
	assertNotNull(aService.rateAccount(ac.getAid(), rt));    
	assertNotNull(aService.ViewDriverRat(ac.getAid()));
	
	}
	
	@Test 
	public void createAC() {
		
	RideResource aService = new RideResource();
	
	Ride r = new Ride();
	r.setAid(1);
	RideLocation rl = new RideLocation();
	rl.setFrom_city("Barrington");
	rl.setFrom_zip("60010");
	rl.setTo_city("Milwaukee");
	rl.setTo_zip("53202");
	r.setLocation_info(rl);
	DateTime dt = new DateTime();
	dt.setDate("14-Apr-2020");
	dt.setTime("09:00");
	r.setDate_time(dt);
	Car c = new Car();
	c.setMake("Audi");
	c.setModel("A4");
	c.setColor("Gray");
	c.setPlate_state("IL");
	c.setPlate_serial("COVID19");
	r.setCar_info(c);
	r.setMax_passengers(2);
	r.setAmount_per_passenger(15.00);
	r.setConditions("No more than one carry on per passenger. No pets.");
	
	assertNotNull(aService.createRide(r));
	assertNotNull(aService.updateRide(r.getRid(), r));
	assertNotNull(aService.deleteRide(r.getRid()));
	assertNotNull(aService.getRides(null, null, null));
	aService.createRide(r);
	assertNotNull(aService.getRides("Barrington", "Milwaukee", "14-Apr-2020"));
	assertNotNull(aService.getRide(r.getRid()));
	}
	@Test 
	public void jR() {
	
		RideResource aService = new RideResource();
		
		Ride r = new Ride();
		r.setAid(1);
		RideLocation rl = new RideLocation();
		rl.setFrom_city("Barrington");
		rl.setFrom_zip("60010");
		rl.setTo_city("Milwaukee");
		rl.setTo_zip("53202");
		r.setLocation_info(rl);
		DateTime dt = new DateTime();
		dt.setDate("14-Apr-2020");
		dt.setTime("09:00");
		r.setDate_time(dt);
		Car c = new Car();
		c.setMake("Audi");
		c.setModel("A4");
		c.setColor("Gray");
		c.setPlate_state("IL");
		c.setPlate_serial("COVID19");
		r.setCar_info(c);
		r.setMax_passengers(2);
		r.setAmount_per_passenger(15.00);
		r.setConditions("No more than one carry on per passenger. No pets.");
		
		RideService rideService = new RideService();
		rideService.addRide(r);
		
		JoinRequest jr = new JoinRequest();
		jr.setAid(1);
		jr.setPassengers(2);
		jr.setPickup_confirmed(false);
		jr.setRide_confirmed(false);
		
		assertNotNull(aService.sendJoinRequest(r.getRid(), jr));
		assertNotNull(aService.getJoinRequest(r.getRid()));
		assertNotNull(aService.patchRow(r.getRid(), jr.getJid(), jr));
	}
	@Test 
	public void mess() {
		Ride r = new Ride();
		r.setAid(1);
		RideLocation rl = new RideLocation();
		rl.setFrom_city("Barrington");
		rl.setFrom_zip("60010");
		rl.setTo_city("Milwaukee");
		rl.setTo_zip("53202");
		r.setLocation_info(rl);
		DateTime dt = new DateTime();
		dt.setDate("14-Apr-2020");
		dt.setTime("09:00");
		r.setDate_time(dt);
		Car c = new Car();
		c.setMake("Audi");
		c.setModel("A4");
		c.setColor("Gray");
		c.setPlate_state("IL");
		c.setPlate_serial("COVID19");
		r.setCar_info(c);
		r.setMax_passengers(2);
		r.setAmount_per_passenger(15.00);
		r.setConditions("No more than one carry on per passenger. No pets.");
		
		RideResource aService = new RideResource();
		
		RideService rideService = new RideService();
		rideService.addRide(r);
		Message m = new Message();
		m.setAid(1);
		m.setMsg("One passenger; could you pick me up at the Starbucks in Barrington at Main and Hough?");
		assertNotNull(aService.sendMessage(r.getRid(), m));
		assertNotNull(aService.getMessages(r.getRid()));
		
	}
	@Test
	public void testErrors()
	{
		BadRequestException bre =  new BadRequestException("a", "a");
		BadRequestExceptionMapper m = new BadRequestExceptionMapper();
		m.toResponse(bre);
		assertEquals(bre.getMessage(), "a");
		DataNotFoundException dne = new DataNotFoundException("a");
		DataNotFoundExceptionMapper m2 = new DataNotFoundExceptionMapper();
		m2.toResponse(dne);
		assertEquals(dne.getMessage(), "a");
	}
	@Test
	public void MyR()
	{
		MyResource mr = new MyResource();
		assertNotNull(mr.getIt());
	}
	@Test
	public void Rep() throws ParseException
	{
		ReportResource rr = new ReportResource();
		assertNotNull(rr.getReport());
		
	}
	@Test
	public void findjr()
	{
		Ride r = new Ride();
		r.setAid(1);
		RideLocation rl = new RideLocation();
		rl.setFrom_city("Barrington");
		rl.setFrom_zip("60010");
		rl.setTo_city("Milwaukee");
		rl.setTo_zip("53202");
		r.setLocation_info(rl);
		DateTime dt = new DateTime();
		dt.setDate("14-Apr-2020");
		dt.setTime("09:00");
		r.setDate_time(dt);
		Car c = new Car();
		c.setMake("Audi");
		c.setModel("A4");
		c.setColor("Gray");
		c.setPlate_state("IL");
		c.setPlate_serial("COVID19");
		r.setCar_info(c);
		r.setMax_passengers(2);
		r.setAmount_per_passenger(15.00);
		r.setConditions("No more than one carry on per passenger. No pets.");
		
		RideService rideService = new RideService();
		rideService.addRide(r);
		
		Ride r2 = new Ride();
		r.setAid(1);
		RideLocation rl2 = new RideLocation();
		rl.setFrom_city("Barrington");
		rl.setFrom_zip("84981");
		rl.setTo_city("LA");
		rl.setTo_zip("53202");
		r.setLocation_info(rl2);
		DateTime dt2 = new DateTime();
		dt.setDate("14-Apr-2020");
		dt.setTime("09:00");
		r.setDate_time(dt2);
		r.setCar_info(c);
		r.setMax_passengers(2);
		r.setAmount_per_passenger(15.00);
		r.setConditions("No more than one carry on per passenger. No pets.");
		
		rideService.addRide(r2);
		
		JoinRequest jr = new JoinRequest();
		jr.setRid(r.getRid());
		jr.setAid(1);
		jr.setPassengers(2);
		jr.setPickup_confirmed(false);
		jr.setRide_confirmed(false);
		
		List<Ride> lor = new ArrayList<Ride>();
		assertNotNull(lor.add(r));
		assertNotNull(lor.add(r2));
		
		assertNotNull(new JoinRequest(1, 1,true, false, 1,1));
		assertNotNull(new Detail("", "", 1));
		assertNotNull(new Car("", "","","",""));
		assertNotNull(new Report(1, "", "","", 1,null));
	}
//	@Test (expected =  BadRequestException.class)
//	public void testingN()
//	{
//		RideResource rr = new RideResource();
//		Ride r = new Ride();
//		r.setAid(1);
//		r.setLocation_info(null);
//				
//		rr.createRide(r);
//	}
	@Test
	public void errorM()
	{
		ErrorMessage em = new ErrorMessage();
		em.setDetail("");
		em.setStatus(400);
		em.setTitle("");
		em.setType("");
		assertNotNull(em.getDetail());
		assertNotNull(em.getStatus());
		assertNotNull(em.getTitle());
		assertNotNull(em.getType());
	}



}
