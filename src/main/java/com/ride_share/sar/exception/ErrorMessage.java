package com.ride_share.sar.exception;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {
	private String type;
	private String title;
	private String detail;
	int status;
	String instance;
	
	public ErrorMessage()
	{
		
	}
	public ErrorMessage(String type, String title, String detail, int status, String instance) {
		super();
		this.type = type;
		this.title = title;
		this.detail = detail;
		this.status = status;
		this.instance = instance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}

	
	
	

}
