package com.ride_share.sar.exception;

public class BadRequestException extends RuntimeException {


	private static final long serialVersionUID = 3130565404398040358L;

	/**
	 * 
	 */
	private String instance;
	public BadRequestException(String message, String instance)
	{
		super(message);
		this.instance = instance;
	}
	public String getInstance() {
		return instance;
	}
	public void setInstance(String instance) {
		this.instance = instance;
	}
	

}
