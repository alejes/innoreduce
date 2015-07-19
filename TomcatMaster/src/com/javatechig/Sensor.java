package com.javatechig;

import java.io.Serializable;
import java.sql.Date;

public class Sensor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String type;
	private Date date;
	private double value;

	public Sensor() {
	}

	public Sensor(String id, String type, Date date, double value) {
		this.setId(id);
		this.setType(type);
		this.setDate(date);
		this.setValue(value);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return id + " - " + type + " - " + date + " - " + value;
	}
}