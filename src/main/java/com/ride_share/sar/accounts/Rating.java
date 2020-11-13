package com.ride_share.sar.accounts;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Rating {
	private long rid;
	private long sent_by_id;
	private int rating;
	private String comment;
	
	public Rating()
	{
		
	}
	public Rating(long r, long sent_by, int rt, String com)
	{
		this.rid = r;
		this.sent_by_id = sent_by;
		this.rating = rt;
		this.comment = com;	
	}
	
	public long getRid() {
		return rid;
	}
	public void setRid(long rid) {
		this.rid = rid;
	}
	public long getSent_by_id() {
		return sent_by_id;
	}
	public void setSent_by_id(long sent_by_id) {
		this.sent_by_id = sent_by_id;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
