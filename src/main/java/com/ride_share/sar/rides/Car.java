package com.ride_share.sar.rides;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Car {
	String make;
	String model;
	String color; //maybe make enum later
	String plate_state;
	String plate_serial;
	
	public Car()
	{
		
	}
	
	public Car(String ma, String m,String c,String pl,String sio)
	{
		make = ma;
		this.color = c;
		this.model = m;
		this.plate_serial = pl;
		this.plate_state = sio;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getPlate_serial() {
		return plate_serial;
	}

	public void setPlate_serial(String plate_serial) {
		this.plate_serial = plate_serial;
	}

	public String getPlate_state() {
		return plate_state;
	}

	public void setPlate_state(String plate_state) {
		this.plate_state = plate_state;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}



}
