package com.ride_share.sar.rides;

import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class JoinRequest {
	long aid; //account requesting
	int passengers;
	Boolean ride_confirmed = false;
	Boolean pickup_confirmed = false;
	long jid; //join request
	long rid; //ride id
	
	public JoinRequest() {
		
	}
	
	public JoinRequest(int ai, int pass, Boolean ride_c, Boolean pickup_c, long ji, long ri) {
		this.aid = ai;
		this.passengers = pass;
		ride_confirmed = ride_c;
		pickup_confirmed = pickup_c;
		jid = ji;
		rid = ri;


	}

	public int getPassengers() {
		return passengers;
	}

	public void setPassengers(int passengers) {
		this.passengers = passengers;
	}

	public Boolean isRide_confirmed() {
		return ride_confirmed;
	}

	public void setRide_confirmed(Boolean ride_confirmed) {
		this.ride_confirmed = ride_confirmed;
	}

	public Boolean isPickup_confirmed() {
		return pickup_confirmed;
	}

	public void setPickup_confirmed(Boolean pickup_confirmed) {
		this.pickup_confirmed = pickup_confirmed;
	}

	public long getJid() {
		return jid;
	}


	public long getRid() {
		return rid;
	}

	public void setRid(long rid) {
		this.rid = rid;
	}

	public void setAid(long aid) {
		this.aid = aid;
	}
	public long getAid()
	{
		return aid;
	}

	public void setJid(long jid) {
		this.jid = jid;
	}

	
	

}
